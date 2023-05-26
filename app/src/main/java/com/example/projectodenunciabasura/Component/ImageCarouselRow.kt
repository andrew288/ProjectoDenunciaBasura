package com.example.projectodenunciabasura.Component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projectodenunciabasura.R

@OptIn(ExperimentalFoundationApi::class)
@Composable

fun ImageCarouselRow(images: List<ImageBitmap>) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){
        items(images) { imageData ->
            if(imageData != null){

                Image(
                    bitmap = imageData,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .aspectRatio(1f)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.logo_add_image),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .aspectRatio(1f)
                )
            }
        }
    }
}