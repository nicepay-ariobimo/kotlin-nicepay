package io.github.nicepay.service.v2.impl

import io.github.nicepay.data.model.DirectV2RequestPaymentToMitra
import io.github.nicepay.service.v2.ApiHttpPaymentService
import io.github.nicepay.service.v2.DirectV2PaymentService
import io.github.nicepay.utils.NICEPay

class DirectV2PaymentToMitraServiceImpl : DirectV2PaymentService<DirectV2RequestPaymentToMitra> {

    override fun registration(data: DirectV2RequestPaymentToMitra, config: NICEPay?): String? {
        var nicePayResponse: String? = null
        val apiHttpService : ApiHttpPaymentService<DirectV2RequestPaymentToMitra> = ApiHttpPaymentServiceImpl()
        try {
            nicePayResponse = apiHttpService.generate(data, config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}