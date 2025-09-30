package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalc.ui.theme.TipCalcTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var orderAmount by remember { mutableStateOf("") }
    var dishCount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf(0f) }
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Сумма заказа:",
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = orderAmount,
                onValueChange = { newValue -> orderAmount = newValue },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                placeholder = {
                    Text("0")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Количество блюд:",
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = dishCount,
                onValueChange = { newValue -> dishCount = newValue },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                placeholder = {
                    Text("0")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Text(
            text = "Чаевые:",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Slider(
            value = tipPercentage,
            onValueChange = { newValue -> tipPercentage = newValue },
            valueRange = 0f..25f,
            steps = 24,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "0%",
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Текущий процент: ${tipPercentage.toInt()}%",
                fontSize = 14.sp
            )

            Text(
                text = "25%",
                fontSize = 14.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Скидка:",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            val discountPercentage = remember(dishCount) {
                when {
                    dishCount.isEmpty() -> 0
                    dishCount.toIntOrNull() == null -> 0
                    dishCount.toInt() in 1..2 -> 3
                    dishCount.toInt() in 3..5 -> 5
                    dishCount.toInt() in 6..10 -> 7
                    dishCount.toInt() > 10 -> 10
                    else -> 0
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(
                        selected = discountPercentage == 3,
                        onClick = {}
                    )
                    Text(text = "3%")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(
                        selected = discountPercentage == 5,
                        onClick = {}
                    )
                    Text(text = "5%")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(
                        selected = discountPercentage == 7,
                        onClick = {}
                    )
                    Text(text = "7%")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(
                        selected = discountPercentage == 10,
                        onClick = {}
                    )
                    Text(text = "10%")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalcTheme {
        Greeting("")
    }
}