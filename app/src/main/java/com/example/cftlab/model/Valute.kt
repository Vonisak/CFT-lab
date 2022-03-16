package com.example.cftlab.model

import com.google.gson.annotations.SerializedName

data class Valute(
    @SerializedName("AUD")
    val aud: ValuteInfo,
    @SerializedName("AZN")
    val azn: ValuteInfo,
    @SerializedName("GBP")
    val gbp: ValuteInfo,
    @SerializedName("AMD")
    val amd: ValuteInfo,
    @SerializedName("BYN")
    val byn: ValuteInfo,
    @SerializedName("BGN")
    val bgn: ValuteInfo,
    @SerializedName("BRL")
    val brl: ValuteInfo,
    @SerializedName("HUF")
    val huf: ValuteInfo,
    @SerializedName("HKD")
    val hkd: ValuteInfo,
    @SerializedName("DKK")
    val dkk: ValuteInfo,
    @SerializedName("USD")
    val usd: ValuteInfo,
    @SerializedName("EUR")
    val eur: ValuteInfo,
    @SerializedName("INR")
    val inr: ValuteInfo,
    @SerializedName("KZT")
    val kzt: ValuteInfo,
    @SerializedName("CAD")
    val cad: ValuteInfo,
    @SerializedName("KGS")
    val kgs: ValuteInfo,
    @SerializedName("CNY")
    val cny: ValuteInfo,
    @SerializedName("MDL")
    val mdl: ValuteInfo,
    @SerializedName("NOK")
    val nok: ValuteInfo,
    @SerializedName("PLN")
    val pln: ValuteInfo,
    @SerializedName("RON")
    val ron: ValuteInfo,
    @SerializedName("XDR")
    val xdr: ValuteInfo,
    @SerializedName("SGD")
    val sgd: ValuteInfo,
    @SerializedName("TJS")
    val tjs: ValuteInfo,
    @SerializedName("TRY")
    val try_: ValuteInfo,
    @SerializedName("TMT")
    val tmt: ValuteInfo,
    @SerializedName("UZS")
    val uzs: ValuteInfo,
    @SerializedName("UAH")
    val uah: ValuteInfo,
    @SerializedName("CZK")
    val czk: ValuteInfo,
    @SerializedName("SEK")
    val sek: ValuteInfo,
    @SerializedName("CHF")
    val chf: ValuteInfo,
    @SerializedName("ZAR")
    val zar: ValuteInfo,
    @SerializedName("KRW")
    val krw: ValuteInfo,
    @SerializedName("JPY")
    val jpy: ValuteInfo
)
