package com.codez.sportsapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SportsImage(
    modifier:Modifier,
    imageSrc: ImageSrc,
    contentDescription: String?,
) {
    val sizedModifier = modifier
        .height(40.dp)
        .width(40.dp)
    when(imageSrc){
        is ImageSrc.Resource -> Image(
            painter = painterResource(imageSrc.resourceId),
            contentDescription = contentDescription,
            sizedModifier
        )
        is ImageSrc.Remote -> GlideImage(
            model = imageSrc.url,
            contentDescription = contentDescription,
            sizedModifier
        )
    }
}

sealed class ImageSrc {
    data class Resource(
        @DrawableRes val resourceId: Int
    ) : ImageSrc()

    data class Remote(
        val url: String
    ) : ImageSrc()
}