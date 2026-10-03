package com.example.noteapp.presentation.notes.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteapp.domain.util.NoteOrder
import com.example.noteapp.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOder: NoteOrder = NoteOrder.Data(OrderType.Descending),
    onOrderChange: (NoteOrder)-> Unit
){
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = noteOder is NoteOrder.Title,
                onSelect = {onOrderChange(NoteOrder.Title(noteOder.orderType))}
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = noteOder is NoteOrder.Data,
                onSelect = {onOrderChange(NoteOrder.Data(noteOder.orderType))}
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = noteOder is NoteOrder.Color,
                onSelect = {onOrderChange(NoteOrder.Color(noteOder.orderType))}
            )
        }
        Spacer(modifier= Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = noteOder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(noteOder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = noteOder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(noteOder.copy(OrderType.Descending))
                }
            )
        }
    }

}