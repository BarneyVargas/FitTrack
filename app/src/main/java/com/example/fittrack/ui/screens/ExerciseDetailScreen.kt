package com.example.fittrack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fittrack.data.Exercise
import com.example.fittrack.ui.components.DifficultyBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(
    exercise: Exercise?,
    durationInput: String,
    isInputError: Boolean,
    isDurationValid: Boolean,
    onDurationChange: (String) -> Unit,
    onLogWorkout: () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(exercise?.name ?: "Exercise") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
    ) { padding ->
        if (exercise == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Exercise not found.",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .imePadding()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                DifficultyBadge(difficulty = exercise.difficulty)
                Text(
                    text = exercise.description,
                    style = MaterialTheme.typography.bodyLarge,
                )
                OutlinedTextField(
                    value = durationInput,
                    onValueChange = onDurationChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Duration (minutes)") },
                    isError = isInputError,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    supportingText = {
                        if (isInputError) {
                            Text(
                                text = "Enter a whole number of minutes from 1 to 999.",
                                color = MaterialTheme.colorScheme.error,
                            )
                        } else {
                            Text("How many minutes did you train?")
                        }
                    },
                )
                Button(
                    onClick = onLogWorkout,
                    enabled = isDurationValid,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Log workout")
                }
            }
        }
    }
}
