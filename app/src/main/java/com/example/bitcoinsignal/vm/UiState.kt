package com.example.bitcoinsignal.vm

data class UiState(
    val scenarios: Map<String, Double> = mapOf(
        "Bull spike" to 0.5,
        "Bear squeeze" to 0.5
    )
)
