package com.example.giphy.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.giphy.data.api.GiphyApi
import com.example.giphy.data.api.model.GifData
import com.example.giphy.domain.GifRepository
import com.example.giphy.domain.model.Gif
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

class GifRepositoryImpl @Inject constructor(
    private val api: GiphyApi
) : GifRepository {

    override fun gifsFlow(): Flow<PagingData<Gif>> = Pager(
        config = PagingConfig(pageSize = GiphyApi.DEFAULT_LIMIT, prefetchDistance = 6),
        initialKey = null,
        pagingSourceFactory = {
            object : PagingSource<Int, Gif>() {
                override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
                    val anchorPosition = state.anchorPosition ?: return null
                    val page = state.closestPageToPosition(anchorPosition) ?: return null
                    return page.prevKey?.plus(1) ?: page.nextKey?.minus(-1)
                }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
                    val page = params.key ?: 0
                    val response = api.getGifs(
                        offset = GiphyApi.DEFAULT_LIMIT * page,
                        limit = GiphyApi.DEFAULT_LIMIT
                    )

                    if(!response.isSuccessful) return LoadResult.Error(HttpException(response))
                    if(response.body() == null) return LoadResult.Error(IllegalArgumentException())
                    return LoadResult.Page(
                        data = response.body()!!.data.map { it.toGif(downsized = true) },
                        nextKey = if(response.body()!!.data.size < GiphyApi.DEFAULT_LIMIT) null else page + 1,
                        prevKey = if(page == 0) null else page - 1
                    )
                }
            }
        },
    ).flow

    override suspend fun getGif(id: String): Result<Gif> {
        TODO("Not yet implemented")
    }
}

private fun GifData.toGif(downsized: Boolean) : Gif = if(downsized) Gif(
    id = this.id,
    url = this.images.downsized.url,
    widthPx = this.images.downsized.width.toInt(),
    heightPx = this.images.downsized.height.toInt()
) else Gif(
    id = this.id,
    url = this.images.original.url,
    widthPx = this.images.original.width.toInt(),
    heightPx = this.images.original.height.toInt()
)