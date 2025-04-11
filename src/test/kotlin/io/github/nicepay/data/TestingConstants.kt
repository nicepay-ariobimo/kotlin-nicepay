package io.github.nicepay.data

import java.text.SimpleDateFormat
import java.util.*

class TestingConstants {

    companion object {
        private fun TestingConstants() {}


        var f: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")

        val TIMESTAMP: String = f.format(Date())

        var rand: Random = Random()

        var random: Int = rand.nextInt(10000)

        val EXTERNAL_ID: String =
            "OrdNo" + TIMESTAMP.substring(0, 10).replace("-", "") + TIMESTAMP.substring(11, 19).replace(":", "") + random

        val PARTNER_ID: String = ""

        val CLIENT_SECRET: String = ""

        val PRIVATE_KEY: String = ""

        val PUBLIC_KEY: String = ""



        var v2_format: SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")

        val V2_TIMESTAMP: String = v2_format.format(Date())

        val MERCHANT_KEY: String = ""

        val I_MID_NORMALCLOSED: String = ""

        val I_MID_INSTLMNT: String = ""

        val INSTLMNT_CLIENT_SECRET: String = ""

        val I_MID_RECURRING: String = ""

        val I_MID_PAC: String = ""


        val I_MID_QRIS: String = ""

        val QRIS_CLIENT_SECRET: String = ""

        val QRIS_STORE_ID: String = ""

        val I_MID_EWALLET: String = ""

        val I_MID: String = ""
        val CLOUD_CLIENT_SECRET: String = ""
        val CLOUD_PRIVATE_KEY: String = ""
        val NORMALTEST_CLOUD_PRIVATE_KEY: String = ""

        val DEFAULT_NICEPAY_SUCCESS_RESULT_CODE = "0000"
        val DEFAULT_NICEPAY_SUCCESS_RESULT_MESSAGE = "SUCCESS"
    }
}