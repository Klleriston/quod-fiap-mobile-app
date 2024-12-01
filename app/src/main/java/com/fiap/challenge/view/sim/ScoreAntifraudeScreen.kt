package com.fiap.challenge.view.sim

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiap.challenge.ui.theme.Purple
import com.fiap.challenge.util.generateRandomScore
import com.fiap.challenge.util.isValidCPF
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ScoreAntifraudeScreen(
    onBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var cpf by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf<Int?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Gerar Score Antifraude",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = cpf,
                onValueChange = { input ->
                    if (input.length <= 11 && input.all { it.isDigit() }) {
                        cpf = input
                        errorMessage = null
                    }
                },
                label = { Text("CPF") },
                placeholder = { Text("Digite seu CPF") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = errorMessage != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = Purple
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Gerando score...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            } else {
                Button(
                    onClick = {
                        if (isValidCPF(cpf)) {
                            isLoading = true
                            coroutineScope.launch {
                                delay(2000L)
                                score = generateRandomScore()
                                isLoading = false
                            }
                        } else {
                            errorMessage = "CPF inválido. Certifique-se de inserir 11 dígitos."
                        }
                    },
                    enabled = cpf.length == 11 && !isLoading,
                    colors = ButtonDefaults.buttonColors(containerColor = Purple),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Gerar Score",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            if (score != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE0FFE0)),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Seu Score Antifraude",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = score.toString(),
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Purple,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedButton(
                onClick = onBack,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Purple
                ),
                border = BorderStroke(width = 1.dp, color = Purple),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Voltar",
                    color = Purple,
                    fontSize = 16.sp
                )
            }
        }
    }
}
