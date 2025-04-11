package io.github.nicepay.service.v2.impl

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.github.nicepay.api.v2.CancelDirectV2Api
import io.github.nicepay.api.v2.CheckStatusDirectV2Api
import io.github.nicepay.api.v2.VirtualAccountRequestDirectV2Api
import io.github.nicepay.data.model.DirectV2Cancel
import io.github.nicepay.data.model.DirectV2InquiryStatus
import io.github.nicepay.data.model.DirectV2VirtualAccount
import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.service.v2.DirectV2PayMethodService
import io.github.nicepay.service.v2.DirectV2Service
import io.github.nicepay.utils.ApiUtils
import io.github.nicepay.utils.NICEPay
import java.io.IOException

class DirectV2VirtualAccountServiceImpl : CommonDirectV2ServiceImpl(), DirectV2PayMethodService<DirectV2VirtualAccount> {

    @Throws(IOException::class)
    override fun registration(data: DirectV2VirtualAccount, config: NICEPay?): NICEPayResponseV2? {
        var nicePayResponse: NICEPayResponseV2? = null
        val directV2Service : DirectV2Service<DirectV2VirtualAccount, VirtualAccountRequestDirectV2Api> = DirectV2ServiceImpl()
        try {
            nicePayResponse = directV2Service.generate(data, ApiUtils.createServiceV2(VirtualAccountRequestDirectV2Api::class.java, config!!), config)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return nicePayResponse
    }

}