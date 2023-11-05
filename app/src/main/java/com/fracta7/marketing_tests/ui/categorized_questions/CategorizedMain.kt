package com.fracta7.marketing_tests.ui.categorized_questions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.marketing_tests.R
import com.fracta7.marketing_tests.ui.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorizedMenu(navController: NavController) {
    val viewModel = hiltViewModel<CategorizedViewModel>()
    val modifier = Modifier.padding(4.dp)
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Categorized Questions")
            },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_back_24),
                            contentDescription = "back"
                        )
                    }
                })
        },
        content = {
            LazyColumn(modifier = Modifier.padding(it)){
                item{
                    Button(onClick = {
                                     navController.navigate(Screens.FirstCategoryScreen.route)
                    }, modifier) {
                        Text(text = "Introduction to Economics")
                    }
                }
                item{
                    Button(onClick = {
                        navController.navigate(Screens.SecondCategoryScreen.route)
                    }, modifier) {
                        Text(text = "Introduction to Management & Marketing")
                    }
                }
                item{
                    Button(onClick = {
                        navController.navigate(Screens.ThirdCategoryScreen.route)
                    }, modifier) {
                        Text(text = "Introduction to Tourism and Hospitality Business")
                    }
                }
                item{
                    Button(onClick = {
                        navController.navigate(Screens.FourthCategoryScreen.route)
                    }, modifier) {
                        Text(text = "Information Communication Technology in Tourism")
                    }
                }
                item{
                    Button(onClick = {
                        navController.navigate(Screens.FifthCategoryScreen.route)
                    }, modifier) {
                        Text(text = "Marketing, Tourism, Hospitality and Event")
                    }
                }
            }
        }
    )
}