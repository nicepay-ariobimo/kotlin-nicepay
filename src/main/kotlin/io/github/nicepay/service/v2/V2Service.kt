package io.github.nicepay.service.v2

import io.github.nicepay.data.response.v2.NICEPayResponseV2
import io.github.nicepay.utils.NICEPay

interface V2Service<T> {

    fun registration(data: T, config: NICEPay?): NICEPayResponseV2?

}