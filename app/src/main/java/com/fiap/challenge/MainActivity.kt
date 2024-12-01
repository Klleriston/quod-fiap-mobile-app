package com.fiap.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fiap.challenge.view.*
import com.fiap.challenge.view.biometrics.*
import com.fiap.challenge.view.document.RegisterScreenAnaliseDocumento
import com.fiap.challenge.view.sim.ScoreAntifraudeScreen
import com.fiap.challenge.view.sim.SimResultScreen
import com.fiap.challenge.ui.theme.AppTheme
import com.fiap.challenge.view.sim.SimVerificationScreen
import com.fiap.challenge.view.document.InfoScreenAnaliseDocumento

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToServices = {
                    navController.navigate("hub")
                }
            )
        }
        composable("hub") {
            HubScreen { serviceName ->
                when (serviceName) {
                    "Biometria Facial" -> navController.navigate("info_biometria_facial")
                    "Biometria Digital" -> navController.navigate("info_biometria_digital")
                    "Análise de Documento" -> navController.navigate("info_analise_documento")
                    "SIM SWAP" -> navController.navigate("sim_verification")
                    "Autenticação Cadastra e Score Antifraude" -> navController.navigate("score_antifraude")
                }
            }
        }
        composable("info_biometria_facial") {
            InfoScreenBiometriaFacial(
                onProceedToRegister = {
                    navController.navigate("register_biometria_facial")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("register_biometria_facial") {
            RegisterScreenBiometriaFacial(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("info_biometria_digital") {
            InfoScreenBiometriaDigital(
                onProceedToRegister = {
                    navController.navigate("register_biometria_digital")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("register_biometria_digital") {
            RegisterScreenBiometriaDigital(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("info_analise_documento") {
            InfoScreenAnaliseDocumento(
                onProceedToRegister = {
                    navController.navigate("register_analise_documento")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("register_analise_documento") {
            RegisterScreenAnaliseDocumento(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("sim_verification") {
            SimVerificationScreen(
                onVerificationComplete = { result ->
                    navController.navigate("sim_result/$result")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "sim_result/{result}",
            arguments = listOf(navArgument("result") { type = NavType.StringType })
        ) { backStackEntry ->
            val result = backStackEntry.arguments?.getString("result") ?: "Erro"
            SimResultScreen(
                verificationResult = result,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("score_antifraude") {
            ScoreAntifraudeScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavigatorPreview() {
    AppTheme {
        AppNavigator()
    }
}
