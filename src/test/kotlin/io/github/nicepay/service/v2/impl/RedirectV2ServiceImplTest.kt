package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.TestingConstants
import io.github.nicepay.data.model.RedirectV2
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.RedirectV2Service
import io.github.nicepay.utils.LoggerPrint
import io.github.nicepay.utils.NICEPay
import io.github.nicepay.utils.NICEPayConstants
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.IOException

class RedirectV2ServiceImplTest {

    companion object {
        private val print: LoggerPrint = LoggerPrint()

        private val v2RedirectService : RedirectV2Service = RedirectV2ServiceImpl()
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
    fun registerRedirect() {
        val request : RedirectV2 = RedirectV2.Builder()
            .timeStamp(timeStamp)
            .iMid(DEFAULT_IMID)
            .payMethod(NICEPayConstants.PAY_METHOD_ALL)
            .currency("IDR")
            .amt(DEFAULT_AMOUNT)
            .referenceNo(DEFAULT_REFERENCE_NO)
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
            .callBakUrl("https://dev.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp")
            .merchantKey(DEFAULT_MERCHANT_KEY)
            .cartData("{\"count\":\"1\",\"item\":[{\"goods_id\":\"BB12345678\",\"img_url\":\"https://d3nevzfk7ii3be.cloudfront.net/igi/vOrGHXlovukA566A.medium\",\"goods_name\":\"Nokia 3360\",\"goods_detail\":\"Old Nokia 3360\",\"goods_amt\":\"" + DEFAULT_AMOUNT + "\",\"goods_type\":\"Smartphone\",\"goods_url\":\"http://merchant.com/cellphones/iphone5s_64g\",\"goods_quantity\":\"1\",\"goods_sellers_id\":\"SEL123\",\"goods_sellers_name\":\"Sellers1\"}]}")
            .userIP("127.0.0.1")
            .build()

        val response : NICEPayResponseV2 = v2RedirectService.registration(request, config)!!
        print.logInfoV2("TXID : " + response.tXid)

        Assertions.assertNotNull(response.tXid)
        Assertions.assertEquals(TestingConstants.DEFAULT_NICEPAY_SUCCESS_RESULT_CODE, response.resultCd)
    }

}