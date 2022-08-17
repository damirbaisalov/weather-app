package com.example.weatherapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ThirdScreen() {

    val context = LocalContext.current

    LazyColumn {
        for (i in 0..10) {
            if (i==0) {
                item {
                    Card(
                        elevation = 8.dp,
                        backgroundColor = Color.White,
                        modifier = Modifier.
                        padding(start = 50.dp, end = 50.dp, top = 50.dp, bottom = 5.dp)
                            .fillMaxWidth()
                            .clickable {
                            showToast(context, i.toString())
                            }
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                text = "Test",
                                textAlign = TextAlign.Center
                            )
                        }
                }
            } else {
                item {
                    Card(
                        elevation = 8.dp,
                        backgroundColor = Color.White,
                        modifier = Modifier.
                        padding(horizontal = 50.dp, vertical = 5.dp)
                            .fillMaxWidth()
                            .clickable {
                                showToast(context, i.toString())
                            }
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                text = "Test",
                                textAlign = TextAlign.Center
                            )
                        }
                }
            }
        }
    }
}

fun showToast(context: Context,  message: String ) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}