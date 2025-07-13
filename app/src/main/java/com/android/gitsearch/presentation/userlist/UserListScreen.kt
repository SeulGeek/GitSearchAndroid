package com.android.gitsearch.presentation.userlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.gitsearch.domain.model.User
import com.android.gitsearch.presentation.userlist.components.UserListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    state: UserListState,
    onQueryChanged: (String) -> Unit,
    onUserClick: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("GitHub User Search") }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            OutlinedTextField(
                value = state.query,
                onValueChange = onQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                label = { Text("Search Users") }
            )
            LazyColumn {
                items(state.users) { user ->
                    UserListItem(user = user, onClick = { onUserClick(it.userName) })
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun UserListScreenWithViewModel(onUserClick: (String) -> Unit) {
    val viewModel = remember { UserListViewModel() }
    val state by viewModel.state.collectAsState()
    UserListScreen(
        state = state, onQueryChanged = viewModel::onQueryChanged, onUserClick = onUserClick
    )

}

@Preview(showBackground = true)
@Composable
fun UserListScreenPreview() {
    val dummyState = UserListState(
        query = "ju",
        users = listOf(
            User(1, "judy", "https://avatars.githubusercontent.com/u/1?v=4"),
            User(2, "edy", "https://avatars.githubusercontent.com/u/2?v=4"),
            User(3, "jolie", "https://avatars.githubusercontent.com/u/3?v=4"),
        )
    )
    UserListScreen(
        state = dummyState,
        onQueryChanged = {},
        onUserClick = {}
    )
}