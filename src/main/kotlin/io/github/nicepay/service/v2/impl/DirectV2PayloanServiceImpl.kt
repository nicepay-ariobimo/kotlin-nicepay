package io.github.nicepay.service.v2.impl

import io.github.nicepay.api.v2.RegistrationDirectV2Api
import io.github.nicepay.data.model.DirectV2Payloan
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2PayMethodService
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay

class DirectV2PayloanServiceImpl : CommonDirectV2ServiceImpl(), DirectV2PayMethodService<DirectV2Payloan> {

    override fun registration(data: DirectV2Payloan, config: NICEPay?): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null
        val directV2Service : DirectV2Service<DirectV2Payloan, RegistrationDirectV2Api> = DirectV2ServiceImpl()
        try {
            nicePayResponse = directV2Service.generate(data, ApiUtils.createServiceV2(RegistrationDirectV2Api::class.java, config!!), config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}