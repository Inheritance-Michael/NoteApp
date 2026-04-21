package com.example.noteapp.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.noteapp.domain.usecases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel(){

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {}
            is NotesEvent.DeleteNote -> {}
            is NotesEvent.RestoreNote -> {}
            is NotesEvent.ToggleOrderSection -> {
//                _state.value = state.value.copy(
//                    isOrderSectionVisible = !state.value.isOrderSectionVisible
//                )
            }
        }
    }
}