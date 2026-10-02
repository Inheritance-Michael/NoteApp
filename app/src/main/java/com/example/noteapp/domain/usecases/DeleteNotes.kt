package com.example.noteapp.domain.usecases

import com.example.noteapp.domain.model.Note
import com.example.noteapp.feature_note.data.repository.NoteRepository

class DeleteNotes(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}