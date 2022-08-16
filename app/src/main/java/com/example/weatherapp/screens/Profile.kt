package com.example.weatherapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Profile() {

//    Column {
//        Box(
//            modifier = Modifier
//                .fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
////        Text("Profile Screen", style = MaterialTheme.typography.h5)
//            Card(
//                elevation = 8.dp,
//                backgroundColor = Color.White,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(24.dp)
//                    .height(400.dp)
//            ) {
//                Column {
//                    Text(
//                        "Hello", fontSize = 24.sp, color = Color.Black,
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//                    Text(
//                        "Damir", fontSize = 24.sp, color = Color.Black,
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//                }
//
//                Box {
//                    Box(modifier = Modifier
//                        .align(Alignment.Center)
//                        .background(Color.Black)
//                        .size(100.dp))
//                    Text(modifier = Modifier.align(Alignment.Center), text = "Hello", color = Color.White)
//                }
//
//            }
//        }

        val context = LocalContext.current

        LazyColumn {
            for (i in 0..100) {
                if (i==0) {
                    item {
                        Card(
                            elevation = 8.dp,
                            backgroundColor = Color.White,
                            modifier = Modifier.
                            padding(start = 50.dp, end = 50.dp, top = 50.dp, bottom = 5.dp).
                            fillMaxWidth().clickable {
                                showToast(context, i.toString())
                            }

                        ) {
                            Text(modifier = Modifier.padding(10.dp), text = "Test")
                        }
                    }
                } else {
                    item {
                        Card(
                            elevation = 8.dp,
                            backgroundColor = Color.White,
                            modifier = Modifier.
                            padding(horizontal = 50.dp, vertical = 5.dp).
                            fillMaxWidth().clickable {
                                showToast(context, i.toString())
                            }

                        ) {
                            Text(modifier = Modifier.padding(10.dp), text = "Test")
                        }
                    }
                }

            }
        }
//    }
}

fun showToast(context: Context,  message: String ) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}