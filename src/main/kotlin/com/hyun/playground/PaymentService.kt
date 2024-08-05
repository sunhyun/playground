package com.hyun.playground

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime
import java.util.stream.Collectors

class PaymentService {
    fun prepare(
        orderId: Long,
        currency: String,
        foreignCurrencyAmount: BigDecimal,
    ): Payment {
        // 환율 가져오기
        val url = URL("https://open.er-api.com/v6/latest/$currency")
        val urlConnection = url.openConnection() as HttpURLConnection

        val br = BufferedReader(InputStreamReader(urlConnection.inputStream))
        val response = br.lines().collect(Collectors.joining())
        br.close()

        val mapper = ObjectMapper().registerKotlinModule()
        val exchangeRateData = mapper.readValue(response, ExchangeRateData::class.java)

        val exchangeRate = exchangeRateData.rates["KRW"] ?: throw Exception("Not found KRW")
        val convertedAmount = foreignCurrencyAmount.multiply(exchangeRate)
        val validUntil = LocalDateTime.now().plusMinutes(30)

        return Payment(
            orderId = orderId,
            currency = currency,
            foreignCurrencyAmount = foreignCurrencyAmount,
            exchangeRate = exchangeRate,
            convertedAmount = convertedAmount,
            validUntil = validUntil,
        )
    }
}

fun main(args: Array<String>) {
    val paymentService = PaymentService()
    val payment =
        paymentService.prepare(
            orderId = 1,
            currency = "USD",
            foreignCurrencyAmount = BigDecimal.valueOf(50.7),
        )

    println(payment)
}
