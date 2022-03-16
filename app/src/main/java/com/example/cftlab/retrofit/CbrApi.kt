package com.example.cftlab.retrofit

import com.example.cftlab.model.Valutes
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CbrApi {
    @GET("daily_json.js")
    fun getDailyCurse() : Observable<Valutes>
}