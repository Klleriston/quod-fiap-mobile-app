package com.fiap.challenge.view.biometrics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiap.challenge.ui.theme.Purple

@Composable
fun RegisterScreenBiometriaFacial(onBack: () -> Unit) {
    var isRegistered by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Cadastro de Biometria Facial",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(200.dp)
                    .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(16.dp))
                    .border(
                        BorderStroke(width = 2.dp, color = Purple),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        isRegistered = true
                    }
            ) {
                if (isRegistered) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Cadastro Concluído",
                        tint = Color.Green,
                        modifier = Modifier.size(64.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.CameraAlt,
                        contentDescription = "Capturar Biometria Facial",
                        tint = Purple,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            if (isRegistered) {
                Text(
                    text = "Biometria Facial cadastrada com sucesso!",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
            } else {
                Text(
                    text = "Clique no ícone da câmera para capturar sua biometria facial.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
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
                    text = if (isRegistered) "Voltar ao Hub" else "Cancelar",
                    color = Purple,
                    fontSize = 16.sp
                )
            }
        }
    }
}
