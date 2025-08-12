package com.example.bitcoinsignal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bitcoinsignal.vm.UiState

@Composable
fun Dashboard(state: UiState) {
    for ((name, prob) in state.scenarios) {
        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = 4.dp, shape = RoundedCornerShape(8.dp)) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = name)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(progress = prob.toFloat(), modifier = Modifier.fillMaxWidth().height(8.dp))
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Probability: ${'${'}String.format("%.1f", prob*100)}% ")
            }
        }
    }
}
