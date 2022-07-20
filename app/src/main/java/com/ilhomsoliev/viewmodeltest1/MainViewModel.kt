package com.ilhomsoliev.viewmodeltest1

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ilhomsoliev.viewmodeltest1.ui.theme.RedOrange

class MainViewModel : ViewModel() {
    val notes = mutableStateListOf<Note>()

    val titleState = mutableStateOf("")
    val noteColor = mutableStateOf(RedOrange)
    val contentState = mutableStateOf("")

    fun sortByDate(){
        notes.sortBy {
            it.date
        }
        notes.reverse()
    }
    fun sortByAlphabet(){
        notes.sortBy {
            it.title
        }
    }
    fun addNote() {

        if (titleState.value.isNotEmpty() && contentState.value.isNotEmpty()) {
            val id = (Math.random() * 100000).toInt()
            val note = Note(
                id,
                titleState.value,
                contentState.value,
                noteColor.value,
                System.currentTimeMillis()
            )
            notes.add(note)
            titleState.value = ""
            contentState.value = ""
            noteColor.value = RedOrange
        }

    }

    fun deleteNote(id: Int) {
        val note = notes.find {
            it.id == id
        }
        notes.remove(note)
    }
}