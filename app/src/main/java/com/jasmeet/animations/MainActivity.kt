package com.jasmeet.animations

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jasmeet.animations.options.BounceAnimationScreen
import com.jasmeet.animations.options.CombinationAnimationScreen
import com.jasmeet.animations.options.FadeInAnimationScreen
import com.jasmeet.animations.options.FlipAnimationScreen
import com.jasmeet.animations.options.PulseAnimationScreen
import com.jasmeet.animations.options.RotateAnimationScreen
import com.jasmeet.animations.options.ScaleAnimationScreen
import com.jasmeet.animations.options.ShakeAnimationScreen
import com.jasmeet.animations.options.SlideAnimationScreen
import com.jasmeet.animations.options.StaggerAnimationScreen
import com.jasmeet.animations.ui.theme.AnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
        )
        setContent {
            AnimationsTheme {
                val navController = rememberNavController()
                MainNavigation(navController = navController)
            }
        }
    }
}

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            OptionsScreen(navController = navController)
        }

        composable("scale") {
            ScaleAnimationScreen()
        }

        composable("fade") {
            FadeInAnimationScreen()
        }

        composable("slide") {
            SlideAnimationScreen()
        }

        composable("rotate") {
            RotateAnimationScreen()
        }

        composable("bounce") {
            BounceAnimationScreen()
        }

        composable("flip") {
             FlipAnimationScreen()
        }

        composable("stagger") {
            StaggerAnimationScreen()
        }

        composable("pulse") {
            PulseAnimationScreen()
        }

        composable("shake") {
            ShakeAnimationScreen()
        }
        composable("combination") {
            CombinationAnimationScreen()

        }

    }

}


data class Item(
    val title: String, val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsScreen(navController: NavHostController) {

    val options = listOf(
        Item(
            title = "Scale Animation",
            onClick = {
                navController.navigate("scale")
            }
        ),
        Item(
            title = "Fade-In Animation",
            onClick = {
                navController.navigate("fade")
            }
        ),
        Item(
            title = "Slide-In Animation",
            onClick = {
                navController.navigate("slide")
            }
        ),
        Item(
            title = "Rotation  Animation",
            onClick = {
                navController.navigate("rotate")
            }
        ),
        Item(
            title = "Bounce  Animation",
            onClick = {
                navController.navigate("bounce")
            }
        ),
        Item(
            title = "Flip  Animation",
            onClick = {
                navController.navigate("flip")
            }
        ),
        Item(
            title = "Stagger  Animation",
            onClick = {
                navController.navigate("stagger")
            }
        ),
        Item(
            title = "Pulse  Animation",
            onClick = {
                navController.navigate("pulse")
            }
        ),
        Item(
            title = "Shake  Animation",
            onClick = {
                navController.navigate("shake")
            }
        ),
        Item(
            title = "Combination of Animations",
            onClick = {
                navController.navigate("combination")
            }
        ),
    )

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Animations")
        })
    }) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            items(options.size) { index ->
                Text(text = options[index].title, modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        options[index].onClick()
                    }
                    .padding(12.dp))

                if (index != options.size - 1)
                    HorizontalDivider()
            }

        }
    }

}