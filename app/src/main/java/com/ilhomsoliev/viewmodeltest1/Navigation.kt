package com.ilhomsoliev.viewmodeltest1

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilhomsoliev.viewmodeltest1.screens.addNoteScreen.AddNoteScreen
import com.ilhomsoliev.viewmodeltest1.screens.notesScreen.NotesScreen

@Composable
fun Navigation(viewModel: MainViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.LIST_OF_NOTES_SCREEN) {
        composable(route = Screens.LIST_OF_NOTES_SCREEN) {
            NotesScreen(navController = navController, viewModel)
        }

        composable(route = Screens.ADD_NOTE_SCREEN) {
            AddNoteScreen(navController = navController, viewModel)
        }
    }
}

