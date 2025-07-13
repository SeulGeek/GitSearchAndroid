package com.android.gitsearch.presentation.userdetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.gitsearch.domain.model.Repository

@Composable
fun RepositoryListItem(
    repository: Repository,
    onClick: (Repository) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(repository) }) {
        Text(text = repository.name, style = MaterialTheme.typography.titleMedium)
        repository.description.takeIf { it.isNotBlank() }?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repository.language?.let {
                Text(text = it, style = MaterialTheme.typography.labelSmall)
            }
            Text(
                text = "⭐ ${repository.stargazersCount}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RepositoryListItemPreview() {
    val dummyRepo = Repository(
        id = 1296269,
        name = "monalisa octocat",
        description = "This your first repo!",
        language = "Flutter",
        stargazersCount = 420,
        htmlUrl = "https://github.com/octocat"
    )

    RepositoryListItem(repository = dummyRepo, onClick = {})
}