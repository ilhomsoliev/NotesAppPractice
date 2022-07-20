package com.ilhomsoliev.viewmodeltest1.screens.notesScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ilhomsoliev.viewmodeltest1.MainViewModel
import com.ilhomsoliev.viewmodeltest1.Note
import com.ilhomsoliev.viewmodeltest1.Screens
import com.ilhomsoliev.viewmodeltest1.screens.addNoteScreen.AddNoteScreen

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val notes = viewModel.notes
    var isSortVisible by remember{ mutableStateOf(false)}
    Scaffold(floatingActionButton = {
            FloatingActionButton(backgroundColor = Color.White, onClick = {
                navController.navigate(Screens.ADD_NOTE_SCREEN)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.Black)
            }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF282C2A))
        ) {
            TopAppBar {
                isSortVisible = !isSortVisible
            }
            AnimatedVisibility(visible = isSortVisible) {
                Column(modifier = Modifier.fillMaxWidth()){
                    Text(modifier = Modifier.clickable {
                      viewModel.sortByDate()
                    },text = "Sort by date", color = Color.White, fontSize = 28.sp)
                    Text(modifier = Modifier.clickable {
                        viewModel.sortByAlphabet()
                    },text = "Sort by alphabet", color = Color.White, fontSize = 28.sp)
                }
            }

            LazyColumn() {
                items(notes) { note: Note ->
                    NoteItem(
                        modifier = Modifier
                            .padding(all = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(note.color),
                        title = note.title,
                        noteContent = note.content
                    ) {
                        viewModel.deleteNote(note.id)
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBar(
    onIconClick:()->Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Your Notes", fontSize = 38.sp, color = Color.White)

        IconButton(onClick = { onIconClick() }) {
            Icon(imageVector = Icons.Default.Sort, contentDescription = null, tint = Color.White)
        }
    }
}