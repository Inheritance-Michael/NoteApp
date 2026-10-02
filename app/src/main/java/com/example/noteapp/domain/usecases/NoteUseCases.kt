package com.example.noteapp.domain.usecases

data class NoteUseCases (
    val getNotes: GetNotes,
    val deleteNotes: DeleteNotes,
    val addNote: AddNote
)