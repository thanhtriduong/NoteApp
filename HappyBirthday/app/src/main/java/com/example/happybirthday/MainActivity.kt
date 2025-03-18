package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        GreetingText(
                            message = "Happy Birthday Thanh Tri ",
                            from = "From Thanh Tri",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    val bgColor = Color(184, 213, 118);
    val txtColor = Color(215, 6, 84);
    Column(
        modifier = modifier
            .background(bgColor),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 100.sp,
            textAlign = TextAlign.Center,
            color = txtColor
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.End),
            color = txtColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HappyBirthdayPreview() {
    HappyBirthdayTheme {
        GreetingText( message = "Happy Birthday Thanh Tri ", from = "From Thanh Tri")
    }
}