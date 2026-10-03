package com.example.noteapp.presentation.add_edit_note


import android.os.Message
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.InvalidNoteException
import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.usecases.NoteUseCases
import com.example.noteapp.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter Title"
    ))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter Content"
    ))
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private var currentNoteID: Int? = null

    init {
        savedStateHandle.get<Int>("noteID")?.let{
            noteID ->
            if(noteID != -1){
                viewModelScope.launch {
                    noteUseCases.getNote(noteID)?.also {
                        note ->
                        currentNoteID = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.tittle,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.value = noteColor.value
                    }
                }
            }
        }
    }
    private val _eventFlow = MutableStateFlow<UIEvent>(
        value = TODO()
    )
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                tittle = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteID
                            )
                        )
                        _eventFlow.emit(UIEvent.SavaNote)
                    }catch(e: InvalidNoteException) {
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                message = e.message ?: "Unknow Error"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UIEvent{
        data class ShowSnackBar(val message: String): UIEvent()
        object SavaNote: UIEvent()
    }
}