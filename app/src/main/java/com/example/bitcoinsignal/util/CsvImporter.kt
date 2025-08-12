package com.example.bitcoinsignal.util

import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import java.io.InputStream

object CsvImporter {
    fun parseETFFlowsCsv(input: InputStream): List<com.example.bitcoinsignal.data.ETFFlow> {
        val rows = CsvReader().readAll(input)
        return rows.drop(1).mapNotNull { r ->
            try {
                val ticker = r[0]
                val date = r[1]
                val netUsd = r[2].toDouble()
                com.example.bitcoinsignal.data.ETFFlow(ticker, date, netUsd)
            } catch (e: Exception) { null }
        }
    }
}
