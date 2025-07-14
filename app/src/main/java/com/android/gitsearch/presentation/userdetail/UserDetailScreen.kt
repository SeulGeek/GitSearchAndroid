package com.android.gitsearch.presentation.userdetail

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.android.gitsearch.domain.model.Repository
import com.android.gitsearch.domain.model.UserDetail
import com.android.gitsearch.presentation.userdetail.components.UserInfoHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    navController: NavController,
    userDetail: UserDetail,
    repositories: List<Repository>?, // TODO: rever nullable (for testing)
    onRepositoryClick: (Repository) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = userDetail.userName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                UserInfoHeader(userDetail)
            }
//            items(repositories) { repo ->
//                RepositoryListItem(repository = repo, onClick = onRepositoryClick)
//                HorizontalDivider()
//            }

        }
    }
}

@Composable
fun UserDetailScreenWithViewModel(
    navController: NavController,
) {
    val viewModel: UserDetailViewModel = hiltViewModel()
    val userDataState by viewModel.state.collectAsState()
    val context = LocalContext.current

    if (userDataState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    userDataState.error?.let { errorMsg ->
        Text("Error: $errorMsg")
        return
    }

    val userDetail = userDataState.userDetail ?: return

    UserDetailScreen(
        navController = navController,
        userDetail = userDetail,
        repositories = null, // TODO: userDataState.repositories,
        onRepositoryClick = { repo ->
            val intent = Intent(Intent.ACTION_VIEW, repo.htmlUrl.toUri())
            context.startActivity(intent)
        }
    )
}

@Preview
@Composable
fun UserDetailScreenPreview() {
    val dummyUser = UserDetail(
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
        userName = "judy",
        fullName = "hey judy",
        followers = 1000,
        following = 42
    )

    val dummyRepos = listOf(
        Repository(1, "grit", "Git library", "Ruby", 250, "https://github.com/mojombo/grit"),
        Repository(
            2,
            "semver",
            "Semantic versioning",
            "Go",
            100,
            "https://github.com/mojombo/semver"
        )
    )

    UserDetailScreen(
        navController = rememberNavController(),
        userDetail = dummyUser,
        repositories = dummyRepos,
        onRepositoryClick = {}
    )
}
