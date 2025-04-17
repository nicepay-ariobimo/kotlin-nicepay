package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2CheckStatus
import io.github.nicepay.data.model.DirectV2Ewallet
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.NICEPayConstants
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class DirectV2EwalletServiceImplTest {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val v2EwalletService : DirectV2Service<DirectV2Ewallet> = DirectV2EwalletServiceImpl()
        private val timeStamp: String = TestingConstants.V2_TIMESTAMP

        private var config: NICEPay? = NICEPay.Builder()
            .isProduction(false)
            .clientSecret(TestingConstants.CLIENT_SECRET)
            .partnerId(TestingConstants.PARTNER_ID)
            .externalID(TestingConstants.EXTERNAL_ID)
            .timestamp(TestingConstants.TIMESTAMP)
            .privateKey(TestingConstants.PRIVATE_KEY)
            .build()

        private lateinit var registeredData : NICEPayResponseV2

        private val DEFAULT_AMOUNT = "100"
        private val DEFAULT_REFERENCE_NO = "NICEPAYVA111213"
        private val DEFAULT_IMID = "IONPAYTEST"
        private val DEFAULT_MERCHANT_KEY = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A=="
    }

    @Test
    @Throws(IOException::class)
    fun requestRegistrationEwalletV2() {
        val request : DirectV2Ewallet = DirectV2Ewallet.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayConstants.PAY_METHOD_EWALLET)
            .currency("IDR")
            .mitraCd(NICEPayConstants.Code.Ewallet.DANA)
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .payValidDt("")
            .payValidTm("")
            .goodsNm("Goods")
            .billingNm("NICEPAY Testing")
            .billingPhone("081363681274")
            .billingEmail("nicepay@example.com")
            .billingAddr("Jln. Raya Kasablanka Kav.88")
            .billingCity("South Jakarta")
            .billingState("DKI Jakarta")
            .billingPostCd("15119")
            .billingCountry("Indonesia")
            .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
            .cartData("{\"count\":\"1\",\"item\":[{\"img_url\":\"https://d3nevzfk7ii3be.cloudfront.net/igi/vOrGHXlovukA566A.medium\",\"goods_name\":\"Nokia 3360\",\"goods_detail\":\"Old Nokia 3360\",\"goods_amt\":\"" + DEFAULT_AMOUNT + "\",\"goods_quantity\":\"1\"}]}")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .build()

        val response : NICEPayResponseV2 = v2EwalletService.registration(request, config)!!

        print.logInfoV2("TXID : " + response.tXid)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        registeredData = response
    }

    @Test
    fun checkStatus() {
        requestRegistrationEwalletV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : NICEPayResponseV2 = v2EwalletService.checkStatus(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun cancel() {
        requestRegistrationEwalletV2()

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod(NICEPayConstants.PAY_METHOD_CONVINIENCE_STORE)
            .cancelType("1")
            .build()

        val response : NICEPayResponseV2 = v2EwalletService.cancel(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

}