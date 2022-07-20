package com.ilhomsoliev.viewmodeltest1.screens.addNoteScreen

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ilhomsoliev.viewmodeltest1.MainViewModel
import com.ilhomsoliev.viewmodeltest1.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun AddNoteScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val titleState = viewModel.titleState
    val contentState = viewModel.contentState
    val scaffoldState = rememberScaffoldState()
    val noteColor: MutableState<Color> = viewModel.noteColor
    val notesColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)

    val noteBackgroundAnimatable = remember {
        Animatable(noteColor.value)
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        /*viewModel.eventFlow.collectLatest { event ->
            when(event){
                is AddEditViewModel.UiEvent.ShowSnackbar->{
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditViewModel.UiEvent.SaveNote->{
                    navController.navigateUp()
                }
            }
        }*/
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            viewModel.addNote()
            navController.popBackStack()
            // viewModel.onEvent(AddEditNoteEvent.SaveNote)
        }, backgroundColor = MaterialTheme.colors.primary) {
            Icon(imageVector = Icons.Default.Save, contentDescription = null)
        }
    }, scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                notesColors.forEach { color: Color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                3.dp,
                                color = if (
                                    noteColor.value.toArgb() == colorInt
                                ) Color.Black else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt), animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                noteColor.value = Color(colorInt)
                            }
                    )

                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            TransparentTextField(
                text = titleState.value,
                hint = "Title",
                onValueChange = {
                    titleState.value = it
                    //viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    //viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.value == "",//titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentTextField(
                text = contentState.value,//contentState.text,
                hint = "Content",//contentState.hint,
                onValueChange = {
                    contentState.value = it
                    //viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    //viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.value == "",//contentState.isHintVisible,
                singleLine = false,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}

