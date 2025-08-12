package com.example.bitcoinsignal.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.util.*
import kotlin.random.Random

class SignalRepository(private val ctx: Context) {

    suspend fun fetchAll(): Map<String, Any> = withContext(Dispatchers.IO) {
        // Minimal fetch: price + dummy funding to keep app local-only
        val providers = RemoteProviders()
        val price = providers.fetchCoinGeckoPriceUsd("bitcoin") ?: 0.0
        mapOf("price" to price, "funding" to listOf<FundingInfo>())
    }

    fun deriveRawScores(data: Map<String, Any>): Map<String, Double> {
        // Simple placeholder scoring deriving two scenario raw scores
        val price = (data["price"] as? Double) ?: 0.0
        val r1 = (Math.log10(price + 1.0)) / 10.0
        return mapOf("Bull spike" to r1, "Bear squeeze" to (1.0 - r1))
    }

    fun loadWeights(): Map<String, Double> {
        // default weights
        return mapOf("Bull spike" to 1.0, "Bear squeeze" to 1.0)
    }

    fun computeAges(data: Map<String, Any>): Map<String, Double> {
        return mapOf("Bull spike" to 1.0, "Bear squeeze" to 1.0)
    }

    fun logSignal(raw: Map<String, Double>, probs: Map<String, Double>) {
        // Append to local file (history)
        try {
            val f = File(ctx.filesDir, "signal_history.csv")
            if (!f.exists()) {
                f.writeText("timestamp,type,raw,prob\n")
            }
            val ts = Date().time
            for ((k,v) in probs) {
                FileWriter(f, true).use { fw ->
                    fw.append("${'$'}ts,${'$'}k,${'$'}${raw[k] ?: 0.0},${'$'}v\n")
                }
            }
        } catch (e: Exception) { e.printStackTrace() }
    }

    fun checkThresholdsAndNotify(probs: Map<String, Double>) {
        // Simple threshold: if any prob > 0.8 notify
        for ((k,v) in probs) {
            if (v > 0.8) {
                NotificationHelper.notifyIf(ctx = ctx, id = k.hashCode(), title = "Signal", body = "${k} > 80% (${String.format("%.1f", v*100)}%)") 
            }
        }
    }

    suspend fun exportHistoryCsv() = withContext(Dispatchers.IO) {
        // Copy internal file to external cache for sharing - basic implementation
        try {
            val f = File(ctx.filesDir, "signal_history.csv")
            val out = File(ctx.cacheDir, "signal_history_export.csv")
            if (f.exists()) {
                f.copyTo(out, overwrite = true)
            } else {
                out.writeText("timestamp,type,raw,prob\n")
            }
        } catch (e: Exception) { e.printStackTrace() }
    }
}
