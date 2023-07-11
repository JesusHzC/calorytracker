package com.github.jesushzc.tracker_domain.model

data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val caloriesPer100g: Int,
    var carbsPer100g: Int,
    var proteinPer100g: Int,
    var fatPer100g: Int,
)
