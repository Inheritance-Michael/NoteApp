package com.example.noteapp.domain.usecases

import com.example.noteapp.domain.model.Note
import com.example.noteapp.feature_note.data.repository.NoteRepository
import com.example.noteapp.domain.util.NoteOrder
import com.example.noteapp.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Data(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map {
            notes ->
            when(noteOrder.orderType){
                is OrderType.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Data -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Title -> notes.sortedBy { it.tittle.lowercase() }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Data -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Title -> notes.sortedByDescending { it.tittle.lowercase() }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}