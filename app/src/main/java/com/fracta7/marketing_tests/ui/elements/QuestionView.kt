package com.fracta7.marketing_tests.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fracta7.marketing_tests.domain.model.Question

@Composable
fun QuestionView(question: Question, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = question.question,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(4.dp)
        )
        LazyColumn{
            question.choices.forEach {
                item {
                    OutlinedButton(onClick = onClick) {
                        Text(text = it)
                    }
                }
            }
        }
    }
}