package io.github.nicepay.service.v2

import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.utils.NICEPay

interface DirectV2PayMethodService<T> : CommonDirectV2Service {

    fun registration(data: T, config: NICEPay?): NICEPayResponseV2?

}