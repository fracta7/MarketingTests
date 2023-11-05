package com.fracta7.marketing_tests.ui.categorized_questions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.marketing_tests.R
import com.fracta7.marketing_tests.ui.navigation.Screens
import com.fracta7.marketing_tests.util.isOptionCorrect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FifthCategory(navController: NavController) {
    val viewModel = hiltViewModel<FifthCategoryViewModel>()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getQuestions(context)
    }
    var currentQuestion by remember { mutableStateOf(0) }
    var correctAnswers by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf("-") }
    var finishedCurrent by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }

    if (viewModel.questions.isNotEmpty()) {
        val color = if (isOptionCorrect(
                selectedOption, viewModel.questions[currentQuestion]
            )
        ) Color.Green else Color.Red
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Marketing, Tourism, Hospitality and Event") }, navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_arrow_back_24),
                        contentDescription = "back"
                    )
                }
            })
        }, content = { it ->

            LazyColumn(modifier = Modifier.padding(it)) {
                item {
                    LinearProgressIndicator(
                        progress = currentQuestion / 200f, modifier = Modifier.padding(12.dp).fillMaxWidth()
                    )
                }
                item {
                    Text(
                        text = "${currentQuestion + 1}. " + viewModel.questions[currentQuestion].question,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(12.dp),
                        fontSize = 24.sp
                    )
                }
                item {
                    Column(Modifier.selectableGroup()) {
                        val options = listOf(
                            viewModel.questions[currentQuestion].option1,
                            viewModel.questions[currentQuestion].option2,
                            viewModel.questions[currentQuestion].option3,
                            viewModel.questions[currentQuestion].option4
                        )
                        options.forEach { text ->
                            Divider(Modifier.fillMaxWidth())
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .defaultMinSize(minHeight = 64.dp)
                                    .selectable(
                                        selected = (text == selectedOption), onClick = {
                                            selectedOption = text
                                            if (isOptionCorrect(
                                                    selectedOption, viewModel.questions[currentQuestion]
                                                )
                                            ) correctAnswers++
                                            finishedCurrent = true
                                        }, role = Role.RadioButton, enabled = !finishedCurrent
                                    )
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(selected = (text == selectedOption), onClick = null)
                                val tColor = if (selectedOption == text) {
                                    color
                                } else MaterialTheme.colorScheme.onBackground
                                val wColor = if (isOptionCorrect(
                                        text, viewModel.questions[currentQuestion]
                                    ) && finishedCurrent
                                ) Color.Green else MaterialTheme.colorScheme.onBackground
                                Text(
                                    text = text,
                                    modifier = Modifier.padding(start = 16.dp),
                                    color = if (selectedOption == text) tColor else wColor
                                )
                            }
                            Divider(Modifier.fillMaxWidth())
                        }

                    }
                }
                item {
                    Button(
                        onClick = {
                            selectedOption = "-"
                            finishedCurrent = false
                            when (currentQuestion) {
                                199 -> {
                                    openDialog = true
                                }

                                else -> {
                                    currentQuestion++
                                }
                            }
                        },
                        enabled = finishedCurrent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .size(height = 48.dp, width = 64.dp)
                    ) {
                        val text = when (currentQuestion) {
                            199 -> {
                                "Complete"
                            }

                            else -> {
                                "Next"
                            }
                        }
                        Text(text = text)
                    }
                }

            }
            if (openDialog) {
                AlertDialog(onDismissRequest = {
                    openDialog = false
                    navController.navigate(Screens.CategorizedMain.route)
                }, confirmButton = {
                    TextButton(onClick = {
                        openDialog = false
                        navController.navigate(Screens.CategorizedMain.route)
                    }) {
                        Text(text = "Confirm")
                    }

                }, title = {
                    Text(text = "Test is finished")
                }, text = {
                    Text(text = "You got $correctAnswers out of 200 or ${(correctAnswers / 200f * 100 * 10).toInt() / 10f}%")
                })
            }
        })
    }
}