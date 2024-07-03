package me.ashutoshkk.recipeapp.data.remote.dto

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)

fun AnalyzedInstruction.toEquipment(): List<Equipment> {
    return steps.flatMap { step -> step.equipment }
}

fun List<AnalyzedInstruction>.getEquipments(): List<Equipment> {
    val result = mutableListOf<Equipment>()
    forEach {
        result.addAll(it.toEquipment())
    }
    return result.distinct()
}