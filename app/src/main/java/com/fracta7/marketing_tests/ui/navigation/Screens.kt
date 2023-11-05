package com.fracta7.marketing_tests.ui.navigation

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object CategorizedMain : Screens("categorized_main")
    object Records : Screens("records_screen")
    object RandomQuestionsScreen : Screens("random_questions_screen")
    object FirstCategoryScreen : Screens("first_category_screen")
    object SecondCategoryScreen : Screens("second_category_screen")
    object ThirdCategoryScreen : Screens("third_category_screen")
    object FourthCategoryScreen : Screens("fourth_category_screen")
    object FifthCategoryScreen : Screens("fifth_category_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}