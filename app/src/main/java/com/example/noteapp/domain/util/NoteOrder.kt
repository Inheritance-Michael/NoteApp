package com.example.noteapp.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): NoteOrder(orderType)
    class Data(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when(this){
            is Color -> Color(orderType)
            is Data -> Data(orderType)
            is Title -> Title(orderType)
        }
    }
}