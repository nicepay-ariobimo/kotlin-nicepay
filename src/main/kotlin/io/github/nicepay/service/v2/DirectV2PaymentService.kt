package io.github.nicepay.service.v2

import io.github.nicepay.data.model.DirectV2RequestPaymentToMitra

interface DirectV2PaymentService<T : DirectV2RequestPaymentToMitra> : V2Service<T, String> {
}