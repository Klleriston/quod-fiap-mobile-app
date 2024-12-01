package com.fiap.challenge.util

fun isValidCPF(cpf: String): Boolean {
    val cleanedCPF = cpf.replace(".", "").replace("-", "")
    if (cleanedCPF.length != 11 || cleanedCPF.all { it == cleanedCPF[0] }) {
        return false
    }
    fun calculateDigit(cpf: String, factor: Int): Int {
        var total = 0
        for (i in 0 until factor - 1) {
            total += (cpf[i].toString().toInt()) * (factor - i)
        }
        val remainder = total % 11
        return if (remainder < 2) 0 else 11 - remainder
    }

    val digit1 = calculateDigit(cleanedCPF, 10)
    val digit2 = calculateDigit(cleanedCPF, 11)

    return cleanedCPF[9].toString().toInt() == digit1 && cleanedCPF[10].toString().toInt() == digit2
}

fun isSystemInFocus(cpf: String): Boolean {
    return cpf.isNotEmpty()
}

fun generateRandomScore(): Int {
    return (300..850).random()
}