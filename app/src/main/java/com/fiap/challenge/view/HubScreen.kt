package com.fiap.challenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiap.challenge.model.Tip
import com.fiap.challenge.ui.theme.AppTheme

data class Service(val name: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun HubScreen(onServiceClick: (String) -> Unit) {
    val services = listOf(
        Service("Biometria Facial", Icons.Filled.Face),
        Service("Biometria Digital", Icons.Filled.Fingerprint),
        Service("Análise de Documento", Icons.Filled.Description),
        Service("SIM SWAP", Icons.Filled.SimCard),
        Service("Autenticação Cadastra e Score Antifraude", Icons.Filled.Security)
    )

    val tips = listOf(
        Tip(
            title = "Monitore Suas Transações",
            description = "Acompanhe todas as suas transações em tempo real para maior segurança.",
            icon = Icons.Filled.Info
        ),
        Tip(
            title = "Proteja Seus Dados",
            description = "Use autenticações seguras para proteger suas informações pessoais.",
            icon = Icons.Filled.Security
        ),
        Tip(
            title = "Avalie Seu Score Antifraude",
            description = "Confira regularmente seu score antifraude para evitar surpresas.",
            icon = Icons.Filled.Info
        )
    )

    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Quod",
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                ) {
                    items(services) { service ->
                        ServiceCard(serviceName = service.name, icon = service.icon) {
                            onServiceClick(service.name)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    tips.forEach { tip ->
                        TipCard(tip = tip)
                    }
                }
            }
        }
    }
}
