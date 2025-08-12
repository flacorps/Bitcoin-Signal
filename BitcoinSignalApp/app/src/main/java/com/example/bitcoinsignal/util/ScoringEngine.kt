package com.example.bitcoinsignal.util

import kotlin.math.exp

object ScoringEngine {
    fun computeProbabilities(rawScores: Map<String, Double>, weights: Map<String, Double>, agesHours: Map<String, Double>): Map<String, Double> {
        val adjusted = rawScores.mapValues { (k, v) ->
            val w = weights[k] ?: 1.0
            val age = agesHours[k] ?: 1.0
            val decay = kotlin.math.exp(-age / 24.0)
            v * w * decay
        }
        val exps = adjusted.mapValues { (_, v) -> kotlin.math.exp(v) }
        val sum = exps.values.sum().coerceAtLeast(1e-9)
        return exps.mapValues { (_, ev) -> ev / sum }
    }
}
