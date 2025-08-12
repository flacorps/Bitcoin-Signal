package com.example.bitcoinsignal.data

data class FundingInfo(val exchange: String, val fundingRate: Double, val openInterest: Double)

data class ETFFlow(val ticker: String, val date: String, val netFlowsUsd: Double)
