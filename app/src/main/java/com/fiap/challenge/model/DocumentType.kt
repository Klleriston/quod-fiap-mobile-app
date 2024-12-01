package com.fiap.challenge.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.DriveFileRenameOutline

enum class DocumentType(val displayName: String, val icon: ImageVector) {
    CPF("CPF", Icons.Filled.Person),
    COMPROVANTE_RESIDENCIA("Comprovante de Residência", Icons.Filled.Home),
    RG("RG", Icons.Filled.DocumentScanner),
    CNH("CNH", Icons.Filled.DriveFileRenameOutline)
}
