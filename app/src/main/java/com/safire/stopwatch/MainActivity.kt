package com.safire.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.safire.stopwatch.ui.theme.StopwatchTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StopwatchTheme {
                Scaffold(
                    topBar = {
                        TopBar()
                    }
                ) { innerPadding ->
                    App(innerPadding)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.app_name))
        }, colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            Icon(
                painterResource(R.drawable.ic_launcher_foreground),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "App icon"
            )
        }
    )
}

@Composable
fun App(
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {

    var seconds by rememberSaveable { mutableIntStateOf(0) }
    var minutes by rememberSaveable { mutableIntStateOf(0) }
    var hours by rememberSaveable { mutableIntStateOf(0) }

    var isRunning by rememberSaveable { mutableStateOf(false) }
    var buttonText by rememberSaveable { mutableStateOf("Start") }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(fontSize = 50.sp, text = hours.formatTime(), fontWeight = FontWeight.SemiBold)
            Text(fontSize = 50.sp, text = ":", fontWeight = FontWeight.SemiBold)
            Text(fontSize = 50.sp, text = minutes.formatTime(), fontWeight = FontWeight.SemiBold)
            Text(fontSize = 50.sp, text = ":", fontWeight = FontWeight.SemiBold)
            Text(fontSize = 50.sp, text = seconds.formatTime(), fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(50.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(48.dp)
        ) {

            Button(
                onClick = {
                    isRunning = !isRunning
                    buttonText = if (isRunning) "Pause" else "Resume"
                },
                modifier = Modifier
                    .weight(0.5f)

            ) {
                Text(buttonText, style = MaterialTheme.typography.bodyLarge)
            }

            Button(
                onClick = {
                    isRunning = false
                    buttonText = "Start"

                    hours = 0
                    minutes = 0
                    seconds = 0

                }, enabled = seconds > 0 || minutes > 0 || hours != 0,
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text("Reset", style = MaterialTheme.typography.bodyLarge)
            }
        }


        LaunchedEffect(isRunning) {
            while (isRunning) {
                delay(1000L)
                if (seconds == 59) {
                    if (minutes == 59) {
                        hours = hours + 1
                        minutes = 0
                    }
                    seconds = 0
                } else {
                    seconds = seconds + 1
                }


            }
        }
    }
}

fun Int.formatTime(): String = toString().padStart(2, '0')

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    StopwatchTheme {
        App()
    }
}