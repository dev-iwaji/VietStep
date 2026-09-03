package com.iwaji.vietstep

import kotlinx.coroutines.launch

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.alpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.CubicBezierEasing

import com.iwaji.vietstep.ui.auth.AuthViewModel
import com.iwaji.vietstep.ui.main.MainScreen
import com.iwaji.vietstep.ui.main.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        com.github.mikephil.charting.utils.Utils.init(this)

        val prefs =
            getSharedPreferences(
                "app",
                MODE_PRIVATE
            )

        setContent {

            var showSplash by remember {
                mutableStateOf(true)
            }

            val authViewModel: AuthViewModel =
                viewModel()

            LaunchedEffect(Unit) {
                authViewModel.init()
            }

            val mainViewModel: MainViewModel =
                viewModel()

            LaunchedEffect(Unit) {
                mainViewModel.initialize(prefs)
                mainViewModel.load()
            }

            val mainUiState by
            mainViewModel.uiState.collectAsState()

            MaterialTheme(
                colorScheme =
                if (mainUiState.darkMode) {
                    darkColorScheme()
                } else {
                    lightColorScheme()
                }
            ) {
                if (showSplash) {
                    SplashScreen (
                        darkMode = mainUiState.darkMode
                    ){
                        showSplash = false
                    }
                } else {
                    MainScreen(
                        authViewModel = authViewModel,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun SplashScreen(
    darkMode: Boolean,
    onFinish: () -> Unit
) {
    val alpha = remember { Animatable(1f) }
    val scale = remember { Animatable(0.6f) }

    LaunchedEffect(Unit) {
        val scaleJob = launch {
            scale.animateTo(
                targetValue = 2.0f,   // ← 最終的にもっと大きくしたい
                animationSpec = tween(
                    durationMillis = 1500,
                    easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)
                )
            )
        }

        val alphaJob = launch {
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)
                )
            )
        }

        scaleJob.join()
        alphaJob.join()
        onFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (darkMode) {
                    Color.Black
                } else {
                    Color.White
                }
            ),
        contentAlignment = Alignment.Center
    ) {

        // ★ ここが重要：Image を Box で包む
        Box(
            modifier = Modifier
                .scale(scale.value)   // ← 初期フレームから確実に反映される
                .alpha(alpha.value)
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = null,
                modifier = Modifier.size(180.dp)  // ← Image 自体には scale をかけない
            )
        }
    }
}
