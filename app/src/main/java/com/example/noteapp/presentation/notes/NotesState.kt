package com.example.noteapp.presentation.notes

import com.example.noteapp.domain.model.Note
import com.example.noteapp.domain.util.NoteOrder
import com.example.noteapp.domain.util.OrderType

data class NotesState(
    val note: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Data(OrderType.Descending),
    val isOrderSectionVisibility: Boolean = false
    )
