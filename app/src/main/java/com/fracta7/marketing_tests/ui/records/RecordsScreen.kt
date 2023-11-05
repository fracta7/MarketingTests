package com.fracta7.marketing_tests.ui.records

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fracta7.marketing_tests.R
import com.fracta7.marketing_tests.ui.elements.Chart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordsScreen(navController: NavController) {
    val viewModel = hiltViewModel<RecordsViewModel>()
    var total by remember { mutableStateOf(0.0f) }
    var average by remember { mutableStateOf(0.0f) }
    val modifier = Modifier.padding(horizontal = 12.dp)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Records History") }, navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_arrow_back_24),
                            contentDescription = "back"
                        )
                    }
                }
            )
        },
        content = { it ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                item {
                    Text(
                        text = "Your progress",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(12.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                average = viewModel.avg(viewModel.state.data)
                item {
                    Chart(
                        info = viewModel.state.data.map { it.toFloat() },
                        modifier = Modifier
                            .padding(12.dp, 48.dp,12.dp,12.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.45f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "${(average / 75f * 100 * 10).toInt() / 10f}%",
                                fontWeight = FontWeight.Black,
                                fontSize = 32.sp, textAlign = TextAlign.Center
                            )
                            Text(text = "Your average score", textAlign = TextAlign.Center)
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(0.45f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${(average * 10).toInt() / 10f}",
                                fontWeight = FontWeight.Black,
                                fontSize = 32.sp, textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Your average correct answers",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(12.dp))
                }

                items(viewModel.state.data.size) {
                    Divider(Modifier.fillMaxWidth())
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(
                            text = "${it + 1}. ${viewModel.state.data[it]} out of 75",
                            modifier = modifier
                        )
                        val percent = viewModel.state.data[it] / 75f
                        LinearProgressIndicator(
                            progress = percent,
                            modifier = Modifier
                                .width(128.dp)
                                .padding(horizontal = 12.dp)
                        )
                        Text(
                            text = "${(percent * 100 * 10).toInt() / 10f}%",
                            modifier = modifier
                        )
                    }
                    Divider(Modifier.fillMaxWidth())
                }

            }
        }
    )
}