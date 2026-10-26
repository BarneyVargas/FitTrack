package com.example.fittrack.data

enum class Difficulty {
    Beginner,
    Intermediate,
    Advanced,
}

data class Category(
    val id: String,
    val name: String,
    val description: String,
)

data class Exercise(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String,
    val difficulty: Difficulty,
)

data class WorkoutLog(
    val id: String,
    val exerciseId: String,
    val exerciseName: String,
    val durationMinutes: Int,
    val loggedAtMillis: Long,
)
