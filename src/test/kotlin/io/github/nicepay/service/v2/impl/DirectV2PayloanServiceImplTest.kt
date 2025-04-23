package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2CheckStatus
import io.github.nicepay.data.model.DirectV2Payloan
import io.github.nicepay.data.model.DirectV2RequestPaymentEwallet
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2PaymentService
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.code.NICEPayMethod
import io.github.nicepay.utils.code.PayloanMitra
import org.apache.logging.log4j.util.Strings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class DirectV2PayloanServiceImplTest {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val v2PayloanService : DirectV2Service<DirectV2Payloan> = DirectV2PayloanServiceImpl()

        private val paymentService : DirectV2PaymentService<DirectV2RequestPaymentEwallet> = DirectV2PaymentEwalletServiceImpl()

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
    fun requestRegistrationPayloanV2() {
        val request : DirectV2Payloan = DirectV2Payloan.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayMethod.PAY_METHOD_PAYLOAN)
            .currency("IDR")
            .mitraCd(PayloanMitra.AKULAKU)
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
            .cartData("{\"count\":\"1\",\"item\":[{\"goods_id\":\"BB12345678\",\"img_url\":\"https://d3nevzfk7ii3be.cloudfront.net/igi/vOrGHXlovukA566A.medium\",\"goods_name\":\"Nokia 3360\",\"goods_detail\":\"Old Nokia 3360\",\"goods_amt\":\"" + DEFAULT_AMOUNT + "\",\"goods_type\":\"Smartphone\",\"goods_url\":\"http://merchant.com/cellphones/iphone5s_64g\",\"goods_quantity\":\"1\",\"goods_sellers_id\":\"SEL123\",\"goods_sellers_name\":\"Sellers1\"}]}")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .sellers("[{\"sellersId\":\"SEL123\",\"sellersNm\":\"Sellers1\",\"sellersEmail\":\"sellers@test.com\",\"sellersUrl\":\"http://nicestore.store\",\"sellersAddress\":{\"sellerNm\":\"Sellers\",\"sellerLastNm\":\"1\",\"sellerAddr\":\"jalanberbangsa1\",\"sellerCity\":\"JakartaBarat\",\"sellerPostCd\":\"12344\",\"sellerPhone\":\"08123456789\",\"sellerCountry\":\"ID\"}}]")
            .deliveryNm("Nicepay Test Delivery Name")
            .deliveryPhone("081234567890")
            .deliveryAddr("EightyEight@Kota Kasablanka, 29th Floor")
            .deliveryCity("Jakarta")
            .deliveryState("DKI Jakarta")
            .deliveryPostCd("12140")
            .deliveryCountry("Indonesia")
            .instmntType("1")
            .instmntMon("1")
            .recurrOpt("")
            .build()

        val response : NICEPayResponseV2 = v2PayloanService.registration(request, config)!!

        print.logInfoV2("TXID : " + response.tXid)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)

        registeredData = response
    }

    @Test
    fun checkStatus() {
        requestRegistrationPayloanV2()

        val request: DirectV2CheckStatus = DirectV2CheckStatus.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : NICEPayResponseV2 = v2PayloanService.checkStatus(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

    @Test
    fun payment() {
        requestRegistrationPayloanV2()

        val request : DirectV2RequestPaymentEwallet = DirectV2RequestPaymentEwallet.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .callBakUrl((config?.getNICEPayBaseUrl()) + "/IONPAY_CLIENT/paymentResult.jsp")
            .amt(DEFAULT_AMOUNT)
            .build()

        val response : String = paymentService.registration(request, config)!!

        Assertions.assertNotNull(response)
        Assertions.assertNotEquals(Strings.EMPTY, response)
    }

    @Test
    fun cancel() {
        payment()

        val request : DirectV2Cancel = DirectV2Cancel.Builder()
            .timeStamp(TestingConstants.V2_TIMESTAMP)
            .tXid(registeredData.tXid!!)
            .iMid(DEFAULT_IMID)
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .amt(DEFAULT_AMOUNT)
            .payMethod(NICEPayMethod.PAY_METHOD_PAYLOAN)
            .cancelType("1")
            .build()

        val response : NICEPayResponseV2 = v2PayloanService.cancel(request, config)!!

        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

}