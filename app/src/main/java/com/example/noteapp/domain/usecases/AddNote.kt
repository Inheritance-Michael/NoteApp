package com.example.noteapp.domain.usecases


import com.example.noteapp.domain.model.InvalidNoteException
import com.example.noteapp.domain.model.Note
import com.example.noteapp.feature_note.data.repository.NoteRepository
import kotlin.jvm.Throws


class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if (note.tittle.isBlank()){
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if (note.content.isBlank()){
            throw InvalidNoteException("The content of the note can't be empty")
        }
        repository.insertNote(note)
    }
}