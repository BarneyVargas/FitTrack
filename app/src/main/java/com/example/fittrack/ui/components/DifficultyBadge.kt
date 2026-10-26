package com.example.fittrack.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fittrack.data.Difficulty

@Composable
fun DifficultyBadge(
    difficulty: Difficulty,
    modifier: Modifier = Modifier,
) {
    val container = when (difficulty) {
        Difficulty.Beginner -> MaterialTheme.colorScheme.secondaryContainer
        Difficulty.Intermediate -> MaterialTheme.colorScheme.tertiaryContainer
        Difficulty.Advanced -> MaterialTheme.colorScheme.errorContainer
    }
    val label = when (difficulty) {
        Difficulty.Beginner -> MaterialTheme.colorScheme.onSecondaryContainer
        Difficulty.Intermediate -> MaterialTheme.colorScheme.onTertiaryContainer
        Difficulty.Advanced -> MaterialTheme.colorScheme.onErrorContainer
    }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = container,
    ) {
        Text(
            text = difficulty.name,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            color = label,
        )
    }
}
