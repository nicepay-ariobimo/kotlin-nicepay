package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2InquiryStatus
import io.github.nicepay.data.model.DirectV2VirtualAccount
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2PayMethodService
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class DirectV2VirtualAccountServiceImplTest {

    companion object {
        val print: LoggerPrint = LoggerPrint()

        val v2VirtualAccountService : DirectV2PayMethodService<DirectV2VirtualAccount> = DirectV2VirtualAccountServiceImpl()
        val timeStamp: String = TestingConstants.V2_TIMESTAMP

        var config: NICEPay? = NICEPay.Builder()
            .isProduction(false)
            .clientSecret(TestingConstants.CLIENT_SECRET)
            .partnerId(TestingConstants.PARTNER_ID)
            .externalID(TestingConstants.EXTERNAL_ID)
            .timestamp(TestingConstants.TIMESTAMP)
            .privateKey(TestingConstants.PRIVATE_KEY)
            .build()

        lateinit var registeredData : NICEPayResponseV2

        val DEFAULT_AMOUNT = "100"
        val DEFAULT_REFERENCE_NO = "NICEPAYVA111213"
        val DEFAULT_IMID = "IONPAYTEST"
        val DEFAULT_MERCHANT_KEY = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A=="
    }

    @Test
    @Throws(IOException::class)
    fun createVirtualAccountV2() {
        val request: DirectV2VirtualAccount = DirectV2VirtualAccount.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod("02")
            .currency("IDR")
            .bankCd("BMRI")
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .vacctValidDt("")
            .vacctValidTm("")
            .goodsNm("Goods")
            .billingNm("NICEPAY Testing")
            .billingPhone("081363681274")
            .billingEmail("nicepay@example.com")
            .billingAddr("Jln. Raya Kasablanka Kav.88")
            .billingCity("South Jakarta")
            .billingState("DKI Jakarta")
            .billingPostCd("15119")
            .billingCountry("Indonesia")
            .merFixAcctId("")
            .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

        val response: NICEPayResponseV2 = v2VirtualAccountService.registration(request, config)!!
        print.logInfoV2("TXID : " + response.tXid)
        print.logInfoV2("VA : " + response.vacctNo)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertNotNull(response.vacctNo)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_MESSAGE, response.resultMsg)

        registeredData = response
    }

    @Test
    fun checkStatus() {
        createVirtualAccountV2()

        val request: DirectV2InquiryStatus = DirectV2InquiryStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response: NICEPayResponseV2 = v2VirtualAccountService.checkStatus(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
        Assertions.assertNotNull(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_MESSAGE)
    }

    @Test
    fun cancelVirtualAccount() {
        createVirtualAccountV2()

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod("02")
            .cancelType("1")
            .build()

        val response: NICEPayResponseV2 = v2VirtualAccountService.cancel(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
        Assertions.assertNotNull(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_MESSAGE)
    }

}