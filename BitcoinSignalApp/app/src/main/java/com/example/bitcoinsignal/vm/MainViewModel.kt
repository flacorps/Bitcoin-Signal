package com.example.bitcoinsignal.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoinsignal.data.SignalRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = SignalRepository(application)
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            while(true) {
                try {
                    val data = repo.fetchAll()
                    val raw = repo.deriveRawScores(data)
                    val weights = repo.loadWeights()
                    val ages = repo.computeAges(data)
                    val probs = com.example.bitcoinsignal.util.ScoringEngine.computeProbabilities(raw, weights, ages)
                    _uiState.value = _uiState.value.copy(scenarios = probs)
                    repo.logSignal(raw, probs)
                    repo.checkThresholdsAndNotify(probs)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(60_000)
            }
        }
    }

    fun exportHistoryCsv() {
        viewModelScope.launch {
            repo.exportHistoryCsv()
        }
    }
}
