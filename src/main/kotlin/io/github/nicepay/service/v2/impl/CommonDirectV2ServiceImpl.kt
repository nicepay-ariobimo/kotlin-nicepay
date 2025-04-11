package io.github.nicepay.service.v2.impl

import io.github.nicepay.api.v2.CancelDirectV2Api
import io.github.nicepay.api.v2.CheckStatusDirectV2Api
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2InquiryStatus
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.CommonDirectV2Service
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay

open class CommonDirectV2ServiceImpl : CommonDirectV2Service {

    override fun checkStatus(data: DirectV2InquiryStatus, config: NICEPay?): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null
        val directV2Service : DirectV2Service<DirectV2InquiryStatus, CheckStatusDirectV2Api> = DirectV2ServiceImpl()
        try {
            nicePayResponse = directV2Service.generate(data, ApiUtils.createServiceV2(CheckStatusDirectV2Api::class.java, config!!), config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

    override fun cancel(data: DirectV2Cancel, config: NICEPay?): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null
        val directV2Service : DirectV2Service<DirectV2Cancel, CancelDirectV2Api> = DirectV2ServiceImpl()
        try {
            nicePayResponse = directV2Service.generate(data, ApiUtils.createServiceV2(CancelDirectV2Api::class.java, config!!), config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}