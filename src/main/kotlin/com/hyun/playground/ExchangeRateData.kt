package com.hyun.playground

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class ExchangeRateData(
    val result: String,
    val rates: Map<String, BigDecimal>
)
