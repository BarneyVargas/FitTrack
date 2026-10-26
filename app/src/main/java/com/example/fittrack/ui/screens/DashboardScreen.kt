package com.example.fittrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.fittrack.data.Category
import com.example.fittrack.data.Exercise
import com.example.fittrack.data.WorkoutLog
import com.example.fittrack.ui.WorkoutUiState
import com.example.fittrack.ui.components.HistoryItem
import com.example.fittrack.ui.components.StatsCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    uiState: WorkoutUiState,
    categories: List<Category>,
    exercises: List<Exercise>,
    onCategoryClick: (Category) -> Unit,
    onRemoveWorkout: (String) -> Unit,
    onClearHistory: () -> Unit,
    onDismissDialog: () -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("FitTrack") }) },
        bottomBar = {
            Button(
                onClick = onClearHistory,
                enabled = uiState.history.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                ),
            ) {
                Text("Clear")
            }
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item(key = "stats") {
                StatsCard(
                    workoutCount = uiState.workoutCount,
                    totalMinutes = uiState.totalMinutes,
                )
            }
            item(key = "categories-header") { SectionTitle("Categories") }
            items(categories, key = { it.id }) { category ->
                CategoryRow(
                    category = category,
                    exerciseCount = exercises.count { it.categoryId == category.id },
                    onClick = { onCategoryClick(category) },
                )
            }
            item(key = "history-header") { SectionTitle("History") }
            if (uiState.history.isEmpty()) {
                item(key = "history-empty") { EmptyHistory() }
            } else {
                item(key = "history-hint") {
                    Text(
                        text = "Swipe left to remove a workout.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                items(uiState.history, key = { it.id }) { log ->
                    SwipeToRemoveHistoryItem(
                        log = log,
                        onRemove = { onRemoveWorkout(log.id) },
                    )
                }
            }
        }
    }

    if (uiState.showSuccessDialog) {
        val latest = uiState.history.firstOrNull()
        AlertDialog(
            onDismissRequest = onDismissDialog,
            confirmButton = {
                TextButton(onClick = onDismissDialog) {
                    Text("OK")
                }
            },
            title = { Text("Workout logged") },
            text = {
                Text(
                    text = if (latest != null) {
                        "${latest.exerciseName} · ${latest.durationMinutes} min was added to your history."
                    } else {
                        "Your workout was added to your history."
                    },
                )
            },
        )
    }
}

@Composable
private fun SwipeToRemoveHistoryItem(
    log: WorkoutLog,
    onRemove: () -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState()
    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier.fillMaxWidth(),
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        onDismiss = { direction ->
            if (direction == SwipeToDismissBoxValue.EndToStart) {
                onRemove()
            }
        },
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CardDefaults.shape)
                    .background(MaterialTheme.colorScheme.error),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Remove",
                    tint = MaterialTheme.colorScheme.onError,
                    modifier = Modifier.padding(end = 20.dp),
                )
            }
        },
    ) {
        HistoryItem(log = log)
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun CategoryRow(
    category: Category,
    exerciseCount: Int,
    onClick: () -> Unit,
) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(text = category.name, style = MaterialTheme.typography.titleMedium)
            Text(
                text = category.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = "$exerciseCount exercises",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun EmptyHistory() {
    Text(
        text = "No workouts yet. Choose a category to log one.",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(vertical = 8.dp),
    )
}
