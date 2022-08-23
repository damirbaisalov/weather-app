package com.example.weatherapp.presentation.screens.first_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R

@Composable
fun FirstScreen(firstScreenViewModel: FirstScreenViewModel) {

    val context = LocalContext.current
    var cityName by remember { mutableStateOf("") }
    val onCityNameChange = { text : String ->
        cityName = text
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            
            Image(
                painter = painterResource(id = R.drawable.cloudy),
                contentDescription = "cloudy",
                modifier = Modifier
                    .size(100.dp)
            )
            
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                title = "Enter city name",
                textState = cityName,
                onTextChange = onCityNameChange
            )

            Spacer(modifier = Modifier.size(30.dp))

            Button(
                onClick = {
                    if (cityName.isNotBlank())
                        firstScreenViewModel.getIntent(
                            FirstScreenIntent.NavigateToSecondScreen(cityName = cityName)
                        )
                    else
                       showToast(context = context, "Enter CityName")
                },
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Find weather",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    )
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview(firstScreenViewModel: FirstScreenViewModel = viewModel()) {
    FirstScreen(firstScreenViewModel = firstScreenViewModel)
}

fun showToast(context: Context, message: String ) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}