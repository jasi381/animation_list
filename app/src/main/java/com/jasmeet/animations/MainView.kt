package com.jasmeet.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
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

@Composable
fun MainView(imgList : List<String>,modifier: Modifier) {

    val context = LocalContext.current

    LazyColumn(modifier,  verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(imgList.size / 2) { rowIndex ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                (0..1).forEach { columnIndex ->
                    val index = rowIndex * 2 + columnIndex
                    if (index < imgList.size) {
                        val item = imgList[index]
                        val animatable = remember {
                            Animatable(0.85f)
                        }

                        LaunchedEffect(key1 = true) {
                            animatable.animateTo(
                                1f,
                                tween(350, delayMillis = 100, easing = LinearEasing)
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
                                .graphicsLayer {
                                    this.scaleX = animatable.value
                                    this.scaleY = animatable.value
                                }
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



