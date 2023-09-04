package com.example.giphy.presentation.screens.gif

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.giphy.R
import com.example.giphy.presentation.components.ConnectionErrorScreen
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GifScreen(
    state: GifScreenState,
    reload: () -> Unit,
    navigateBack: () -> Unit
) = Scaffold(
    topBar = { 
        TopAppBar(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 10.dp),
            title = {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = stringResource(R.string.gif),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(
                        modifier = Modifier.size(27.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        )        
    },
    content = {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize(), contentAlignment = Alignment.Center) {
            when(state) {
                is GifScreenState.Error -> ConnectionErrorScreen(reload = reload)

                is GifScreenState.Loading -> CircularProgressIndicator()

                is GifScreenState.Success -> GlideImage(
                    imageModel = { state.value.url },
                    modifier = Modifier
                        .aspectRatio(state.value.widthPx.toFloat() / state.value.heightPx.toFloat()),
                    requestBuilder = {
                        Glide
                            .with(LocalContext.current)
                            .asGif()
                            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    },
                    loading = {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    },
                    failure = { ConnectionErrorScreen(reload = reload) }
                )
            }
        }
    }
)