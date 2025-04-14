package io.github.nicepay.utils

class NICEPayConstants {

    companion object {
        private val SANDBOX_BASE_URL: String = "https://dev.nicepay.co.id/"
        private val PRODUCTION_BASE_URL: String = "https://www.nicepay.co.id/"

        private val AWS_SANDBOX_BASE_URL: String = "https://dev-services.nicepay.co.id"
        private val AWS_PRODUCTION_BASE_URL: String = "https://services.nicepay.co.id"

        val PAY_METHOD_CARD = "01"
        val PAY_METHOD_VIRTUAL_ACCOUNT = "02"
        val PAY_METHOD_CONVINIENCE_STORE = "03"
        val PAY_METHOD_EWALLET = "05"
        val PAY_METHOD_PAY_LOAN = "06"
        val PAY_METHOD_PAYOUT = "07"
        val PAY_METHOD_QRIS = "08"
        val PAY_METHOD_GPN = "09"

        fun getSandboxBaseUrl(isCloudServer: Boolean): String {
            return if (isCloudServer) {
                AWS_SANDBOX_BASE_URL
            } else {
                SANDBOX_BASE_URL
            }
        }

        fun getProductionBaseUrl(isCloudServer: Boolean): String {
            return if (isCloudServer) {
                AWS_PRODUCTION_BASE_URL
            } else {
                PRODUCTION_BASE_URL
            }
        }
    }

}