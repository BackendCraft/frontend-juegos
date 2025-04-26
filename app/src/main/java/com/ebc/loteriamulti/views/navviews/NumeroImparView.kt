package com.ebc.loteriamulti.views.navviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ebc.loteriamulti.viewmodel.NumeroImparViewModel

@Preview(showBackground = true)
@Composable
fun NumeroImparView(
    navController: NavHostController = rememberNavController(),
    viewModel: NumeroImparViewModel = NumeroImparViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.numeroInput.value,
            onValueChange = { viewModel.numeroInput.value = it },
            label = { Text("Ingresa un número") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        viewModel.errorMessage.value?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = { viewModel.validarNumero() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Validar Impar")
        }

        if (viewModel.isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }

        viewModel.resultado.value?.let { res ->
            Text(
                text = res,
                color = if (res.contains("✅")) Color.Green else Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}