package com.iwaji.vietstep

import kotlinx.coroutines.delay

import android.os.Bundle
import android.view.animation.OvershootInterpolator

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
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
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import com.iwaji.vietstep.ui.auth.AuthViewModel
import com.iwaji.vietstep.ui.main.MainScreen
import com.iwaji.vietstep.ui.main.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        com.github.mikephil.charting.utils.Utils.init(this)

        val prefs =
            getSharedPreferences(
                "app",
                MODE_PRIVATE
            )

        setContent {

            var showIntro by remember {
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
                MainScreen(
                    authViewModel = authViewModel,
                    mainViewModel = mainViewModel
                )

                if (showIntro) {

                    IntroOverlay() {
                        showIntro = false
                    }
                }
            }
        }
    }
}

@Composable
fun IntroOverlay(
    onFinished: () -> Unit
) {
    var visible by remember { mutableStateOf(true) }

    val scale = remember { Animatable(0.6f) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(visible) {

        val overshoot = OvershootInterpolator(2.0f)

        // ✅ 小さいアイコンから拡大（少しオーバーシュート）
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = { overshoot.getInterpolation(it) }
            )
        )

        // ✅ 少し停止
        delay(200)

        // ✅ フェードアウト
        alpha.animateTo(
            targetValue = 0f,
            animationSpec = tween(300)
        )

        visible = false
        onFinished()
    }

    AnimatedVisibility(
        visible = visible,
        exit = ExitTransition.None
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                         Color(0xFF1F1F1F)
                 ),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(R.drawable.splash_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .graphicsLayer {
                        scaleX = scale.value
                        scaleY = scale.value
                        this.alpha = alpha.value
                    }
            )
        }
    }
}
