package com.example.giphy.presentation.screens.trending_gifs

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.giphy.R
import com.example.giphy.domain.model.Gif
import com.example.giphy.presentation.components.ConnectionErrorScreen
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TrendingGifsScreen(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<Gif>,
    onItemClick: (String) -> Unit
) = Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    when(pagingItems.loadState.refresh) {
        is LoadState.Error -> ConnectionErrorScreen(reload = pagingItems::refresh)

        LoadState.Loading -> CircularProgressIndicator()

        is LoadState.NotLoading -> LazyVerticalStaggeredGrid(
            modifier = modifier.fillMaxSize(),
            columns = StaggeredGridCells.Adaptive(minSize = 180.dp)
        ) {
            items(pagingItems.itemCount) { index ->
                val item = pagingItems[index]!!
                GlideImage(
                    imageModel = { item.url },
                    modifier = Modifier
                        .border(
                            width = (0.5).dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RectangleShape
                        )
                        .clickable { onItemClick(item.id) }
                        .aspectRatio(item.widthPx.toFloat() / item.heightPx.toFloat()),
                    requestBuilder = {
                        Glide
                            .with(LocalContext.current)
                            .asGif()
                            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    },
                    loading = {
                        Box(modifier = Modifier.matchParentSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    failure = {
                        Box(modifier = Modifier.matchParentSize(), contentAlignment = Alignment.Center) {
                            Icon(
                                modifier = Modifier.fillMaxSize(0.5f),
                                imageVector = Icons.Default.Close,
                                tint = MaterialTheme.colorScheme.error,
                                contentDescription = null
                            )
                        }
                    }
                )
            }

            when(pagingItems.loadState.append) {
                is LoadState.Error -> item(span = StaggeredGridItemSpan.FullLine) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp), contentAlignment = Alignment.Center) {
                        Icon(
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(R.drawable.outline_wifi_off_24),
                            tint = MaterialTheme.colorScheme.error,
                            contentDescription = null
                        )
                    }
                }

                LoadState.Loading -> item(span = StaggeredGridItemSpan.FullLine) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.size(48.dp))
                    }
                }

                is LoadState.NotLoading -> Unit
            }
        }
    }
}