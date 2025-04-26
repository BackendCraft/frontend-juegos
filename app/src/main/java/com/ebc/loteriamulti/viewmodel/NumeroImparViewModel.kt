package com.ebc.loteriamulti.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NumeroImparViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/") // Asegurar que coincide con tu backend
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val service = retrofit.create(ParImparService::class.java)

    val isLoading = mutableStateOf(false)
    val numeroInput = mutableStateOf("")
    val resultado = mutableStateOf<String?>(null)
    val errorMessage = mutableStateOf<String?>(null)

    fun validarNumero() {
        val numero = numeroInput.value.toIntOrNull()

        when {
            numeroInput.value.isEmpty() -> {
                errorMessage.value = "Ingresa un número primero"
                resultado.value = null
            }
            numero == null -> {
                errorMessage.value = "Debe ser un número válido"
                resultado.value = null
            }
            else -> {
                isLoading.value = true
                errorMessage.value = null

                viewModelScope.launch {
                    try {
                        val response = service.verificarParImpar(numero)
                        resultado.value = if (response.contains("Impar"))
                            "Es Impar"
                        else
                            "Es Par"
                    } catch (e: Exception) {
                        errorMessage.value = "Error: ${e.message}"
                    } finally {
                        isLoading.value = false
                    }
                }
            }
        }
    }
}

interface ParImparService {
    @GET("par-impar") // Asegurar que esta ruta existe en tu backend
    suspend fun verificarParImpar(
        @Query("numero") numero: Int
    ): String
}