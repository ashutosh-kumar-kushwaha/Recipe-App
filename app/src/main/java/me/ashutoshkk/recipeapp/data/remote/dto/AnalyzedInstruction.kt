package me.ashutoshkk.recipeapp.data.remote.dto

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)