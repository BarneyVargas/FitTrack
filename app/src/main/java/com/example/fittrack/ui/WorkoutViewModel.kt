package com.example.fittrack.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fittrack.data.Exercise
import com.example.fittrack.data.WorkoutLog
import java.util.UUID

data class WorkoutUiState(
    val history: List<WorkoutLog> = emptyList(),
    val durationInput: String = "",
    val isInputError: Boolean = false,
    val showSuccessDialog: Boolean = false,
) {
    val workoutCount: Int
        get() = history.size

    val totalMinutes: Int
        get() = history.sumOf { it.durationMinutes }

    val isDurationValid: Boolean
        get() = durationInput.isValidDuration()
}

class WorkoutViewModel : ViewModel() {
    var uiState by mutableStateOf(WorkoutUiState())
        private set

    fun onDurationChange(value: String) {
        uiState = uiState.copy(
            durationInput = value,
            isInputError = value.isNotEmpty() && !value.isValidDuration(),
        )
    }

    fun logWorkout(exercise: Exercise): Boolean {
        val minutes = uiState.durationInput.toIntOrNull()
        if (minutes == null || minutes !in MIN_DURATION_MINUTES..MAX_DURATION_MINUTES) {
            uiState = uiState.copy(isInputError = true)
            return false
        }
        val entry = WorkoutLog(
            id = UUID.randomUUID().toString(),
            exerciseId = exercise.id,
            exerciseName = exercise.name,
            durationMinutes = minutes,
            loggedAtMillis = System.currentTimeMillis(),
        )
        uiState = uiState.copy(
            history = listOf(entry) + uiState.history,
            durationInput = "",
            isInputError = false,
            showSuccessDialog = true,
        )
        return true
    }

    fun dismissDialog() {
        uiState = uiState.copy(showSuccessDialog = false)
    }

    fun removeWorkout(id: String) {
        uiState = uiState.copy(history = uiState.history.filterNot { it.id == id })
    }

    fun clearHistory() {
        uiState = uiState.copy(history = emptyList())
    }
}

private const val MIN_DURATION_MINUTES = 1
private const val MAX_DURATION_MINUTES = 999

private fun String.isValidDuration(): Boolean =
    toIntOrNull()?.let { it in MIN_DURATION_MINUTES..MAX_DURATION_MINUTES } == true
