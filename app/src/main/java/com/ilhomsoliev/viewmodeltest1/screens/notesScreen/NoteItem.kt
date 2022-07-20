package com.ilhomsoliev.viewmodeltest1.screens.notesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteItem(
    title:String,
    noteContent:String,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 32.dp, end = 32.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                maxLines = 1,
                fontSize = 28.sp,
                overflow = TextOverflow.Ellipsis // ...
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = noteContent,
                maxLines = 10,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "")
        }
    }
}