package com.android.gitsearch.presentation.userlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.gitsearch.domain.model.User
import com.android.gitsearch.presentation.userlist.components.UserListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    query: String,
    onQueryChanged: (String) -> Unit,
    users: LazyPagingItems<User>,
    onUserClick: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("GitHub User Search") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                label = { Text("Search Users") }
            )

            if (query.isBlank()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Please enter a search term.")
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(count = users.itemCount) { index ->
                        val user = users[index]
                        user?.let {
                            UserListItem(user = it, onClick = { onUserClick(it.userName) })
                            HorizontalDivider()
                        }
                    }

                    item {
                        when {
                            users.loadState.append is LoadState.Loading ||
                                    users.loadState.refresh is LoadState.Loading -> {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                                }
                            }

                            users.itemCount == 0 &&
                                    users.loadState.refresh is LoadState.NotLoading -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("No users found.")
                                }
                            }

                            users.loadState.refresh is LoadState.Error && query.isNotBlank() -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Error occurred. Please try again.")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserListScreenWithViewModel(onUserClick: (String) -> Unit) {
    val viewModel: UserListViewModel = hiltViewModel()
    val users = viewModel.usersFlow.collectAsLazyPagingItems()
    val query by viewModel.query.collectAsState()

    UserListScreen(
        query = query,
        onQueryChanged = viewModel::onQueryChanged,
        users = users,
        onUserClick = onUserClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun UserListScreenFakePreview() {
    val dummyUsers = listOf(
        User(1, "judy", "https://avatars.githubusercontent.com/u/1?v=4"),
        User(2, "andy", "https://avatars.githubusercontent.com/u/2?v=4"),
        User(3, "cindy", "https://avatars.githubusercontent.com/u/3?v=4"),
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("GitHub User Search") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OutlinedTextField(
                value = "ju",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                label = { Text("Search Users") }
            )

            LazyColumn {
                items(dummyUsers) { user ->
                    UserListItem(user = user, onClick = {})
                    HorizontalDivider()
                }
            }
        }
    }
}
