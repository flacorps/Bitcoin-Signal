package com.example.bitcoinsignal.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bitcoinsignal.vm.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppScaffold(vm: MainViewModel = viewModel()) {
    val state by vm.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Bitcoin Signal") })
    }) { inner ->
        Column(modifier = Modifier.padding(inner).fillMaxSize().padding(12.dp)) {
            Dashboard(state = state)
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { vm.exportHistoryCsv() }) {
                Text("Export CSV")
            }
        }
    }
}
