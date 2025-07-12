package com.android.gitsearch.presentation.userlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.android.gitsearch.domain.model.User

@Composable
fun UserListItem(
    user: User,
    onClick: (User) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(user) }
            .padding(12.dp)
    ) {
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_user_placeholder),
            error = painterResource(id = R.drawable.ic_user_error)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = user.userName,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserListItemPreview() {
    val dummyUser = User(
        1,
        "judy",
        "https://avatars.githubusercontent.com/u/1?v=4"
    )
    UserListItem(user = dummyUser, onClick = {})
}