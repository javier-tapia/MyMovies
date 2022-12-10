package com.example.mymovies.ui.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mymovies.R
import com.example.mymovies.model.MediaItem
import com.example.mymovies.model.getMedia

@Composable
fun MediaList(navController: NavHostController, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_xsmall)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_padding)),
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.cell_min_width)),
        modifier = modifier
    ) {
        items(getMedia()) { item ->
            MediaListItem(
                mediaItem = item,
                navController = navController,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_xsmall))
            )
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun MediaListItem(
    mediaItem: MediaItem,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { navController.navigate("detail/${mediaItem.id}") },
        elevation = 0.dp,
//        shape = RoundedCornerShape(8.dp)
        border = BorderStroke(1.dp, Color.LightGray),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.secondary
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.cell_thumb_height))
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(mediaItem.thumb)
                        .crossfade(2000)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                if (mediaItem.type == MediaItem.Type.VIDEO) {
                    Icon(
                        imageVector = Icons.Default.PlayCircleOutline,
                        contentDescription = null,
                        modifier = Modifier.size(dimensionResource(id = R.dimen.cell_play_icon_size)),
                        tint = Color.White
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(MaterialTheme.colors.secondary)
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(
                    text = mediaItem.title,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Preview
@Composable
fun MediaListPreview() {
//    MediaList(navController)
}
