package com.jasmeet.animations.options

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jasmeet.animations.R
import com.jasmeet.animations.imgFinalList
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaggerAnimationScreen() {

    val context = LocalContext.current
    val imgList = imgFinalList

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Stagger Animation")
            }
        )
    }
    ) {paddingValues->



        LazyColumn(modifier = Modifier.padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 8.dp),  verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(imgList.size / 2) { rowIndex ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    (0..1).forEach { columnIndex ->
                        val index = rowIndex * 2 + columnIndex
                        if (index < imgList.size) {
                            val item = imgList[index]

                            val offset = remember { Animatable(200f) }

                            LaunchedEffect(key1 = true) {
                                delay(index * 10L)
                                offset.animateTo(
                                    0f,
                                    animationSpec = spring(dampingRatio = 0.8f, stiffness = 300f)
                                )
                            }


                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(item)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(id = R.drawable.img_placeholder),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = null,
                                modifier = Modifier
                                    .offset(y = offset.value.dp)
                                    .height(LocalConfiguration.current.screenHeightDp.dp * 2 / 6f)
                                    .clip(MaterialTheme.shapes.large)
                                    .weight(1f)
                            )
                        }
                    }
                }
            }


        }

    }

}
