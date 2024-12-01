package com.fiap.challenge.view.document

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiap.challenge.model.DocumentType
import com.fiap.challenge.ui.theme.Purple
import com.fiap.challenge.util.getFileName

@Composable
fun RegisterScreenAnaliseDocumento(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var uploadStatus by remember { mutableStateOf<Map<DocumentType, String?>>(emptyMap()) }
    var selectedUris by remember { mutableStateOf<Map<DocumentType, Uri?>>(emptyMap()) }

    val launchers = DocumentType.values().associateWith { documentType ->
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                selectedUris = selectedUris.toMutableMap().apply { put(documentType, it) }
                val fileName = getFileName(context.contentResolver, it)
                uploadStatus = uploadStatus.toMutableMap().apply { put(documentType, "Documento enviado com sucesso!") }
            }
        }
    }

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
                text = "Upload de Documentos",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                DocumentUploadCard(
                    documentType = DocumentType.CPF,
                    selectedUri = selectedUris[DocumentType.CPF],
                    uploadStatus = uploadStatus[DocumentType.CPF],
                    onClick = { launchers[DocumentType.CPF]?.launch("*/*") }
                )
                DocumentUploadCard(
                    documentType = DocumentType.COMPROVANTE_RESIDENCIA,
                    selectedUri = selectedUris[DocumentType.COMPROVANTE_RESIDENCIA],
                    uploadStatus = uploadStatus[DocumentType.COMPROVANTE_RESIDENCIA],
                    onClick = { launchers[DocumentType.COMPROVANTE_RESIDENCIA]?.launch("*/*") }
                )
                DocumentUploadCard(
                    documentType = DocumentType.RG,
                    selectedUri = selectedUris[DocumentType.RG],
                    uploadStatus = uploadStatus[DocumentType.RG],
                    onClick = { launchers[DocumentType.RG]?.launch("*/*") }
                )
                DocumentUploadCard(
                    documentType = DocumentType.CNH,
                    selectedUri = selectedUris[DocumentType.CNH],
                    uploadStatus = uploadStatus[DocumentType.CNH],
                    onClick = { launchers[DocumentType.CNH]?.launch("*/*") }
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
                    text = "Voltar ao Hub",
                    color = Purple,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun DocumentUploadCard(
    documentType: DocumentType,
    selectedUri: Uri?,
    uploadStatus: String?,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(16.dp))
            .border(
                BorderStroke(width = 2.dp, color = Purple),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
    ) {
        if (selectedUri != null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "${documentType.displayName} Upload Concluído",
                    tint = Color.Green,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = getFileName(context.contentResolver, selectedUri),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.UploadFile,
                    contentDescription = "Upload de ${documentType.displayName}",
                    tint = Purple,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Clique para fazer upload de ${documentType.displayName}",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}
