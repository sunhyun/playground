package com.hyun.playground

import java.math.BigDecimal
import java.time.LocalDateTime

class Payment(
    private val orderId: Long,
    private val currency: String,
    private val foreignCurrencyAmount: BigDecimal,
    private val exchangeRate: BigDecimal,
    private val convertedAmount: BigDecimal,
    private val validUntil: LocalDateTime,
) {
    override fun toString(): String {
        return "Payment(orderId=$orderId, currency='$currency', foreignCurrencyAmount=$foreignCurrencyAmount, exchangeRate=$exchangeRate, convertedAmount=$convertedAmount, validUntil=$validUntil)"
    }
}
