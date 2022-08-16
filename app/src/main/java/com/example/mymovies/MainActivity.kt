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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
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
//            text = "Hello world",
            text = stringResource(id = R.string.lorem),
            color = Color.Red,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 5.sp,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Justify,
            lineHeight = 2.em, // Cada 'em' representa la altura de una letra
            // Las siguientes 3 propiedades, suelen trabajar en conjunto
            maxLines = 2,
            overflow = TextOverflow.Ellipsis, // Por defecto, usa el TextOverflow.Clip
            softWrap = false, // Por defecto, es true
//            onTextLayout =   // Es un listener que devuelve un TextLayoutResult

            // Permite abstraer el estilo completo en lugar de setear cada argumento
            // y también permite configurar los estilos en el propio tema de Jetpack Compose
            style = MaterialTheme.typography.h6.copy(
                shadow = Shadow(
                    offset = Offset(5f, 5f),
                    blurRadius = 10f,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            )
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
            Text(
                text = "Title 1",
                style = MaterialTheme.typography.h6
            )
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