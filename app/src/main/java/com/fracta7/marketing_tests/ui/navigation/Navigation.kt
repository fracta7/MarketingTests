package com.fracta7.marketing_tests.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fracta7.marketing_tests.ui.categorized_questions.CategorizedMenu
import com.fracta7.marketing_tests.ui.categorized_questions.FifthCategory
import com.fracta7.marketing_tests.ui.categorized_questions.FirstCategory
import com.fracta7.marketing_tests.ui.categorized_questions.FourthCategory
import com.fracta7.marketing_tests.ui.categorized_questions.SecondCategory
import com.fracta7.marketing_tests.ui.categorized_questions.ThirdCategory
import com.fracta7.marketing_tests.ui.main_menu.MainMenu
import com.fracta7.marketing_tests.ui.random_questions_screen.RandomQuestionsScreen
import com.fracta7.marketing_tests.ui.records.RecordsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {
        composable(route = Screens.MainScreen.route)
        {
            MainMenu(navController = navController)
        }
        composable(route = Screens.CategorizedMain.route) {
            CategorizedMenu(navController = navController)
        }
        composable(route = Screens.Records.route)
        {
            RecordsScreen(navController = navController)
        }
        composable(route = Screens.RandomQuestionsScreen.route) {
            RandomQuestionsScreen(navController = navController)
        }
        composable(route = Screens.FirstCategoryScreen.route) {
            FirstCategory(navController = navController)
        }
        composable(route = Screens.SecondCategoryScreen.route) {
            SecondCategory(navController = navController)
        }
        composable(route = Screens.ThirdCategoryScreen.route) {
            ThirdCategory(navController = navController)
        }
        composable(route = Screens.FourthCategoryScreen.route) {
            FourthCategory(navController = navController)
        }
        composable(route = Screens.FifthCategoryScreen.route) {
            FifthCategory(navController = navController)
        }
    }
}