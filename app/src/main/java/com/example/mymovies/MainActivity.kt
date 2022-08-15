package com.example.mymovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymovies.ui.theme.MyMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ButtonText()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true, widthDp = 200, heightDp = 100)
@Composable
fun ButtonText() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello world",
            modifier = Modifier
                .clickable { /* TODO */ }
                .background(Color.Cyan)
                .border(width = 2.dp, color = Color.Blue)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MediaItem() {
    Column {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(color = Color.Red)
        ) {

        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
                .padding(16.dp)
        ) {
            Text(text = "Title 1")
        }
    }
}


//@Preview(
//    showBackground = true,
//    name = "Default Greeting",
//    widthDp = 400,
//    heightDp = 200
//)
@Composable
fun DefaultPreview() {
    MyMoviesTheme {
        // Row: El LinearLayout horizontal de Compose
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Greeting(
                name = "Javi",
                modifier = Modifier.background(Color.LightGray)
            )
            Greeting(
                name = "Android",
                modifier = Modifier.background(Color.Yellow)
            )
        }

        // Column: El LinearLayout vertical de Compose
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly, // Is this or adding weight
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting(
                name = "Javi",
                modifier = Modifier
                    .background(Color.LightGray)
                    .weight(2f)
            )
            Greeting(
                name = "Android",
                modifier = Modifier
                    .background(Color.Yellow)
                    .weight(2f)
            )
            Greeting(
                name = "Otro",
                modifier = Modifier
                    .background(Color.Cyan)
                    .weight(1f)
            )
        }

        // Box: El FrameLayout de Compose
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Greeting(name = "Javi")
            Greeting(
                name = "Android",
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}