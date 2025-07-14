package com.android.gitsearch.data.remote.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.gitsearch.data.remote.api.GitHubApi
import com.android.gitsearch.domain.model.User

class UserPagingResource(
    private val api: GitHubApi,
    private val query: String
) : PagingSource<Int, User>() {

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val ITEMS_PER_PAGE = 30
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = api.searchUsers(query, page = page, perPage = ITEMS_PER_PAGE)
            val users = response.items.map {
                User(
                    id = it.id,
                    userName = it.userName,
                    avatarUrl = it.avatarUrl
                )
            }
            LoadResult.Page(
                data = users,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Log.e("UserPagingResource", "load error", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchor ->
            val anchorPage = state.closestPageToPosition(anchor)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}