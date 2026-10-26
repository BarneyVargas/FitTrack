package com.example.fittrack

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fittrack.data.ExerciseRepository
import com.example.fittrack.navigation.Routes
import com.example.fittrack.ui.WorkoutViewModel
import com.example.fittrack.ui.screens.DashboardScreen
import com.example.fittrack.ui.screens.ExerciseDetailScreen
import com.example.fittrack.ui.screens.ExerciseListScreen

@Composable
fun FitTrackApp() {
    val viewModel: WorkoutViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.DASHBOARD,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                uiState = viewModel.uiState,
                categories = ExerciseRepository.categories,
                exercises = ExerciseRepository.exercises,
                onCategoryClick = { category ->
                    navController.navigate(Routes.exercises(category.id))
                },
                onRemoveWorkout = viewModel::removeWorkout,
                onClearHistory = viewModel::clearHistory,
                onDismissDialog = viewModel::dismissDialog,
            )
        }
        composable(
            route = Routes.EXERCISES,
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
            ),
        ) { entry ->
            val categoryId = entry.arguments?.getString("category").orEmpty()
            val category = ExerciseRepository.categories.find { it.id == categoryId }
            val exercises = ExerciseRepository.exercises.filter { it.categoryId == categoryId }
            val uiState = viewModel.uiState
            ExerciseListScreen(
                title = category?.name ?: "Exercises",
                description = category?.description
                    ?: "This category does not have any exercises.",
                exercises = exercises,
                workoutCount = uiState.workoutCount,
                totalMinutes = uiState.totalMinutes,
                onExerciseClick = { exercise ->
                    navController.navigate(Routes.detail(exercise.id))
                },
                onBack = { navController.popBackStack() },
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("exerciseId") { type = NavType.StringType },
            ),
        ) { entry ->
            val exerciseId = entry.arguments?.getString("exerciseId").orEmpty()
            val exercise = ExerciseRepository.exercises.find { it.id == exerciseId }
            val uiState = viewModel.uiState
            ExerciseDetailScreen(
                exercise = exercise,
                durationInput = uiState.durationInput,
                isInputError = uiState.isInputError,
                isDurationValid = uiState.isDurationValid,
                onDurationChange = viewModel::onDurationChange,
                onLogWorkout = {
                    if (exercise != null && viewModel.logWorkout(exercise)) {
                        navController.popBackStack(Routes.DASHBOARD, inclusive = false)
                    }
                },
                onBack = { navController.popBackStack() },
            )
        }
    }
}
