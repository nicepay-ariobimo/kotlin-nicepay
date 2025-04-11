package io.github.nicepay.utils

class NICEPay(
    partnerId: String?,
    clientSecret: String?,
    isProduction: Boolean,
    isCloudServer: Boolean,
    externalID: String?,
    timestamp: String?,
    privateKey: String?
) {

    var partnerId: String? = null

    var clientSecret: String? = null

    var isProduction = false

    var isCloudServer = false

    var externalID: String? = null

    var timestamp: String? = null

    var privateKey: String? = null

    fun NICEPay(
        partnerId: String?,
        clientSecret: String?,
        isProduction: Boolean,
        isCloudServer: Boolean,
        externalID: String?,
        timestamp: String?,
        privateKey: String?
    ) {
        this.partnerId = partnerId
        this.clientSecret = clientSecret
        this.isProduction = isProduction
        this.isCloudServer = isCloudServer
        this.externalID = externalID
        this.timestamp = timestamp
        this.privateKey = privateKey
    }

    fun getNICEPayBaseUrl(): String {
        return if (isProduction) {
            NICEPayConstants.getProductionBaseUrl(isCloudServer)
        } else {
            NICEPayConstants.getSandboxBaseUrl(isCloudServer)
        }
    }

    override fun toString(): String {
        return "NICEPay{" +
                "partnerId='" + partnerId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", isProduction=" + isProduction +
                '}'
    }

    class Builder {

        private var partnerId: String? = null
        private var clientSecret: String? = null
        private var isProduction: Boolean = false
        private var isCloudServer: Boolean = false
        private var externalID: String? = null
        private var timestamp: String? = null
        private var privateKey: String? = null

        fun partnerId(partnerId: String) = apply { this.partnerId = partnerId }
        fun clientSecret(clientSecret: String) = apply { this.clientSecret = clientSecret }
        fun isProduction(isProduction: Boolean) = apply { this.isProduction = isProduction }
        fun isCloudServer(isCloudServer: Boolean) = apply { this.isCloudServer = isCloudServer }
        fun externalID(externalID: String?) = apply { this.externalID = externalID }
        fun timestamp(timestamp: String?) = apply { this.timestamp = timestamp }
        fun privateKey(privateKey: String?) = apply { this.privateKey = privateKey }

        fun build(): NICEPay {
            return NICEPay(
                partnerId,
                clientSecret,
                isProduction,
                isCloudServer,
                externalID,
                timestamp,
                privateKey
            )
        }
    }

}