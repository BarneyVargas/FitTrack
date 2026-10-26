package com.example.fittrack.data

object ExerciseRepository {
    val categories: List<Category> = listOf(
        Category(
            id = "strength",
            name = "Strength",
            description = "Build force with barbell and bodyweight lifts.",
        ),
        Category(
            id = "cardio",
            name = "Cardio",
            description = "Raise your heart rate and stay moving.",
        ),
        Category(
            id = "flexibility",
            name = "Flexibility",
            description = "Loosen tight muscles and improve range of motion.",
        ),
        Category(
            id = "core",
            name = "Core",
            description = "Train the muscles that stabilize every other lift.",
        ),
    )

    val exercises: List<Exercise> = listOf(
        Exercise(
            id = "push-up",
            categoryId = "strength",
            name = "Push-up",
            description = "Lower your chest to the floor and press back up with a straight body.",
            difficulty = Difficulty.Beginner,
        ),
        Exercise(
            id = "back-squat",
            categoryId = "strength",
            name = "Back Squat",
            description = "Sit your hips back and drive through your heels to stand tall.",
            difficulty = Difficulty.Intermediate,
        ),
        Exercise(
            id = "deadlift",
            categoryId = "strength",
            name = "Deadlift",
            description = "Hinge at the hips and lift the bar close to your legs until you lock out.",
            difficulty = Difficulty.Advanced,
        ),
        Exercise(
            id = "brisk-walk",
            categoryId = "cardio",
            name = "Brisk Walk",
            description = "Walk at a pace that lifts your heart rate but still lets you talk.",
            difficulty = Difficulty.Beginner,
        ),
        Exercise(
            id = "jump-rope",
            categoryId = "cardio",
            name = "Jump Rope",
            description = "Turn the rope with your wrists and stay light on the balls of your feet.",
            difficulty = Difficulty.Intermediate,
        ),
        Exercise(
            id = "interval-run",
            categoryId = "cardio",
            name = "Interval Run",
            description = "Alternate hard efforts with easy jogs and keep the hard parts honest.",
            difficulty = Difficulty.Advanced,
        ),
        Exercise(
            id = "hamstring-stretch",
            categoryId = "flexibility",
            name = "Hamstring Stretch",
            description = "Hinge forward with a flat back until you feel a stretch down the back of the leg.",
            difficulty = Difficulty.Beginner,
        ),
        Exercise(
            id = "worlds-greatest-stretch",
            categoryId = "flexibility",
            name = "World's Greatest Stretch",
            description = "Step into a lunge, drop the same-side elbow, then reach the other arm up.",
            difficulty = Difficulty.Intermediate,
        ),
        Exercise(
            id = "couch-stretch",
            categoryId = "flexibility",
            name = "Couch Stretch",
            description = "Kneel with the back foot up on a couch and squeeze the glute to open the hip.",
            difficulty = Difficulty.Advanced,
        ),
        Exercise(
            id = "dead-bug",
            categoryId = "core",
            name = "Dead Bug",
            description = "Press your low back down and reach the opposite arm and leg away slowly.",
            difficulty = Difficulty.Beginner,
        ),
        Exercise(
            id = "plank",
            categoryId = "core",
            name = "Plank",
            description = "Hold a straight line from head to heels without letting the hips sag.",
            difficulty = Difficulty.Intermediate,
        ),
        Exercise(
            id = "hanging-leg-raise",
            categoryId = "core",
            name = "Hanging Leg Raise",
            description = "Hang from a bar and lift your legs without swinging.",
            difficulty = Difficulty.Advanced,
        ),
    )
}
