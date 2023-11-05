package com.fracta7.marketing_tests.ui.main_menu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fracta7.marketing_tests.R
import com.fracta7.marketing_tests.ui.navigation.Screens

@Composable
fun MainMenu(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val modifier = Modifier.padding(4.dp)

        item {
            Text(
                text = "Marketing-220 Tests",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(28.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.padding(12.dp))
        }
        item {
            Button(
                onClick = {
                    navController.navigate(Screens.CategorizedMain.route)
                },
                modifier
            ) {
                Text(text = "Categorized Questions List")
            }
        }
        item {
            Button(
                onClick = {
                    navController.navigate(Screens.RandomQuestionsScreen.route)
                },
                modifier
            ) {
                Text(text = "Take a test with random 75 questions")
            }
        }
        item {
            Button(
                onClick = {
                    navController.navigate(Screens.Records.route)
                },
                modifier
            ) {
                Text(text = "Records history")
            }
        }
    }
}