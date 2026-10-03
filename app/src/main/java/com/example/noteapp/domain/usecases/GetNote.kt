package com.example.noteapp.domain.usecases

import com.example.noteapp.domain.model.Note
import com.example.noteapp.feature_note.data.repository.NoteRepository

class GetNote(
    private var repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}