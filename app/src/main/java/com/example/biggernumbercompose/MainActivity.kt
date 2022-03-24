package com.example.biggernumbercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biggernumbercompose.ui.theme.BiggerNumberComposeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiggerNumberComposeTheme {
                TextAndButton()
            }
        }
    }
}

var random: Int = Random.nextInt(1,1000)

@Composable
fun TextAndButton() {
    var textnumber by remember { mutableStateOf("") }
    var hint by remember { mutableStateOf("Please enter your guess number") }
    var points by remember { mutableStateOf(0)}

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color.Cyan),
            ){
        Text(
            text = "Number Guessing",
            color = Color.DarkGray,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color(0xFFBCBCBC))
                .padding(8.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(10.dp)
            )
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "$hint",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 70.dp)
                .fillMaxWidth()

        )
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = textnumber,
                onValueChange = {
                    textnumber = it
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.hint))
                },
                modifier = Modifier
                    .alignByBaseline()
                    .padding(start = 10.dp)
            )


            Button(
                modifier = Modifier
                    .alignByBaseline()
                    .padding(10.dp),
                onClick = {
                    val number: Int = textnumber.toString().toInt()

                    if (number < random){
                        points ++
                        hint = "Your guess number is Lower!!"

                    } else if (number > random) {
                        points ++
                        hint ="Your guess number is Higher!!"

                    } else{

                        hint ="Your number is Correct!!"

                    }
                    textnumber = ""

                }

            ) {
                Text(text = stringResource(id = R.string.check))
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(
                text = "Wrong : $points",
                textAlign = TextAlign.Center,

                )
            Spacer(modifier = Modifier.padding(20.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White),

                onClick = {
                    random = Random.nextInt(1, 1000)
                    hint = "Please enter your guess:"
                    points = 0

                }
            ) {
                Text(text = stringResource(id = R.string.reset))
            }
        }
    }
}
