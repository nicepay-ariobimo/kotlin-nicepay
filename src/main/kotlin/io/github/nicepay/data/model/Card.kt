package io.github.nicepay.data.model

import io.github.nicepay.utils.SHA256Util

class Card(
    timeStamp: String?,
    iMid: String?,
    payMethod: String?,
    currency: String?,
    merchantToken: String,
    referenceNo: String?,
    dbProcessUrl: String?,
    instmntType: String?,
    instmntMon: String?,
    recurrOpt: String?,
    userIP: String?,
    userLanguage: String?,
    userAgent: String?,
    amt: String?,
    cartData: String?,
    goodsNm: String?,
    billingNm: String?,
    billingPhone: String?,
    billingEmail: String?,
    billingAddr: String?,
    billingCity: String?,
    billingState: String?,
    billingCountry: String?,
    billingPostCd: String?,
    merchantKey: String?,
    tXid: String?,
    cardNo: String?,
    cardExpYymm: String?,
    cardCvv: String?,
    cardHolderNm: String?,
    callBackUrl: String?,
    recurringToken: String?,
    preauthToken: String?,
    description: String?,
    deliveryNm: String?,
    deliveryPhone: String?,
    deliveryEmail: String?,
    deliveryAddr: String?,
    deliveryCity: String?,
    deliveryState: String?,
    deliveryPostCd: String?,
    deliveryCountry: String?
) {

    private val timeStamp: String? = null
    private val iMid: String? = null
    private val payMethod: String? = null
    private val currency: String? = null
    private val merchantToken: String? = null
    private val referenceNo: String? = null

    private val dbProcessUrl: String? = null

    private val instmntType: String? = null
    private val instmntMon: String? = null
    private val recurrOpt: String? = null

    private val userIP: String? = null
    private val userLanguage: String? = null
    private val userAgent: String? = null

    private val amt: String? = null
    private val cartData: String? = null
    private val goodsNm: String? = null
    private val billingNm: String? = null
    private val billingPhone: String? = null
    private val billingEmail: String? = null
    private val billingAddr: String? = null
    private val billingCity: String? = null
    private val billingState: String? = null
    private val billingCountry: String? = null
    private val billingPostCd: String? = null
    private val merchantKey: String? = null

    // PAYMENT
    private val tXid: String? = null

    private val cardNo: String? = null
    private val cardExpYymm: String? = null
    private val cardCvv: String? = null
    private val cardHolderNm: String? = null

    private val callBackUrl: String? = null

    // For Recurring and Pre-auth feature
    private val recurringToken: String? = null
    private val preauthToken: String? = null

    // V1
    private val description: String? = null
    private val deliveryNm: String? = null
    private val deliveryPhone: String? = null
    private val deliveryEmail: String? = null
    private val deliveryAddr: String? = null
    private val deliveryCity: String? = null
    private val deliveryState: String? = null
    private val deliveryPostCd: String? = null
    private val deliveryCountry: String? = null

    class Builder {
        private var timeStamp: String? = null
        private var iMid: String? = null
        private var payMethod: String? = null
        private var currency: String? = null
        private var merchantToken: String? = null
        private var referenceNo: String? = null
        private var dbProcessUrl: String? = null
        private var instmntType: String? = null
        private var instmntMon: String? = null
        private var recurrOpt: String? = null
        private var userIP: String? = null
        private var userLanguage: String? = null
        private var userAgent: String? = null
        private var amt: String? = null
        private var cartData: String? = null
        private var goodsNm: String? = null
        private var billingNm: String? = null
        private var billingPhone: String? = null
        private var billingEmail: String? = null
        private var billingAddr: String? = null
        private var billingCity: String? = null
        private var billingState: String? = null
        private var billingCountry: String? = null
        private var billingPostCd: String? = null
        private var merchantKey: String? = null
        private var tXid: String? = null
        private var cardNo: String? = null
        private var cardExpYymm: String? = null
        private var cardCvv: String? = null
        private var cardHolderNm: String? = null
        private var callBackUrl: String? = null
        private var recurringToken: String? = null
        private var preauthToken: String? = null
        private var description: String? = null
        private var deliveryNm: String? = null
        private var deliveryPhone: String? = null
        private var deliveryEmail: String? = null
        private var deliveryAddr: String? = null
        private var deliveryCity: String? = null
        private var deliveryState: String? = null
        private var deliveryPostCd: String? = null
        private var deliveryCountry: String? = null

        // Builder methods
        fun timeStamp(timeStamp: String?) = apply { this.timeStamp = timeStamp }
        fun iMid(iMid: String?) = apply { this.iMid = iMid }
        fun payMethod(payMethod: String?) = apply { this.payMethod = payMethod }
        fun currency(currency: String?) = apply { this.currency = currency }
        fun referenceNo(referenceNo: String?) = apply { this.referenceNo = referenceNo }
        fun dbProcessUrl(dbProcessUrl: String?) = apply { this.dbProcessUrl = dbProcessUrl }
        fun instmntType(instmntType: String?) = apply { this.instmntType = instmntType }
        fun instmntMon(instmntMon: String?) = apply { this.instmntMon = instmntMon }
        fun recurrOpt(recurrOpt: String?) = apply { this.recurrOpt = recurrOpt }
        fun userIP(userIP: String?) = apply { this.userIP = userIP }
        fun userLanguage(userLanguage: String?) = apply { this.userLanguage = userLanguage }
        fun userAgent(userAgent: String?) = apply { this.userAgent = userAgent }
        fun amt(amt: String?) = apply { this.amt = amt }
        fun cartData(cartData: String?) = apply { this.cartData = cartData }
        fun goodsNm(goodsNm: String?) = apply { this.goodsNm = goodsNm }
        fun billingNm(billingNm: String?) = apply { this.billingNm = billingNm }
        fun billingPhone(billingPhone: String?) = apply { this.billingPhone = billingPhone }
        fun billingEmail(billingEmail: String?) = apply { this.billingEmail = billingEmail }
        fun billingAddr(billingAddr: String?) = apply { this.billingAddr = billingAddr }
        fun billingCity(billingCity: String?) = apply { this.billingCity = billingCity }
        fun billingState(billingState: String?) = apply { this.billingState = billingState }
        fun billingCountry(billingCountry: String?) = apply { this.billingCountry = billingCountry }
        fun billingPostCd(billingPostCd: String?) = apply { this.billingPostCd = billingPostCd }
        fun merchantKey(merchantKey: String?) = apply { this.merchantKey = merchantKey }
        fun tXid(tXid: String?) = apply { this.tXid = tXid }
        fun cardNo(cardNo: String?) = apply { this.cardNo = cardNo }
        fun cardExpYymm(cardExpYymm: String?) = apply { this.cardExpYymm = cardExpYymm }
        fun cardCvv(cardCvv: String?) = apply { this.cardCvv = cardCvv }
        fun cardHolderNm(cardHolderNm: String?) = apply { this.cardHolderNm = cardHolderNm }
        fun callBackUrl(callBackUrl: String?) = apply { this.callBackUrl = callBackUrl }
        fun recurringToken(recurringToken: String?) = apply { this.recurringToken = recurringToken }
        fun preauthToken(preauthToken: String?) = apply { this.preauthToken = preauthToken }
        fun description(description: String?) = apply { this.description = description }
        fun deliveryNm(deliveryNm: String?) = apply { this.deliveryNm = deliveryNm }
        fun deliveryPhone(deliveryPhone: String?) = apply { this.deliveryPhone = deliveryPhone }
        fun deliveryEmail(deliveryEmail: String?) = apply { this.deliveryEmail = deliveryEmail }
        fun deliveryAddr(deliveryAddr: String?) = apply { this.deliveryAddr = deliveryAddr }
        fun deliveryCity(deliveryCity: String?) = apply { this.deliveryCity = deliveryCity }
        fun deliveryState(deliveryState: String?) = apply { this.deliveryState = deliveryState }
        fun deliveryPostCd(deliveryPostCd: String?) = apply { this.deliveryPostCd = deliveryPostCd }
        fun deliveryCountry(deliveryCountry: String?) = apply { this.deliveryCountry = deliveryCountry }

        fun build() : Card {

            return Card(
                timeStamp, iMid, payMethod, currency,
                SHA256Util.encrypt(this.timeStamp + this.iMid + this.referenceNo + this.amt + this.merchantKey).toString(),
                referenceNo, dbProcessUrl, instmntType, instmntMon, recurrOpt,
                userIP, userLanguage, userAgent, amt, cartData, goodsNm, billingNm, billingPhone, billingEmail, billingAddr,
                billingCity, billingState, billingCountry, billingPostCd, merchantKey, tXid, cardNo, cardExpYymm, cardCvv,
                cardHolderNm, callBackUrl, recurringToken, preauthToken, description, deliveryNm, deliveryPhone, deliveryEmail,
                deliveryAddr, deliveryCity, deliveryState, deliveryPostCd, deliveryCountry
            )
        }
    }
}
