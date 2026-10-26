package com.example.fittrack.navigation

object Routes {
    const val DASHBOARD = "dashboard"
    const val EXERCISES = "exercises/{category}"
    const val DETAIL = "detail/{exerciseId}"

    fun exercises(category: String): String = "exercises/$category"

    fun detail(exerciseId: String): String = "detail/$exerciseId"
}
