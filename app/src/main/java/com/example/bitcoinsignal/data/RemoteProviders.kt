package com.example.bitcoinsignal.data

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteProviders {
    private val client = HttpClient(OkHttp)

    suspend fun fetchCoinGeckoPriceUsd(id: String = "bitcoin"): Double? = withContext(Dispatchers.IO) {
        try {
            val url = "https://api.coingecko.com/api/v3/simple/price?ids=${id}&vs_currencies=usd"
            val resp: HttpResponse = client.get(url)
            val body = resp.readText()
            val json = Json.parseToJsonElement(body).jsonObject
            val usd = json[id]?.jsonObject?.get("usd")?.toString()
            return@withContext usd?.replace("\"", "")?.toDoubleOrNull()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
