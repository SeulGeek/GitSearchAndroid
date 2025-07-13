package com.android.gitsearch.presentation.userdetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.android.gitsearch.R
import com.android.gitsearch.domain.model.UserDetail

@Composable
fun UserInfoHeader(user: UserDetail) {
    Row(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_user_placeholder),
            error = painterResource(id = R.drawable.ic_user_error)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = user.fullName, style = MaterialTheme.typography.titleMedium)
            Text(text = "@${user.userName}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Followers: ${user.followers} • Following: ${user.following}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoHeaderPreview() {
    val dummyUser = UserDetail(
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
        userName = "judy",
        fullName = "hey judy",
        followers = 1000,
        following = 42
    )

    UserInfoHeader(user = dummyUser)
}