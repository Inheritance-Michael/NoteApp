package com.example.noteapp.presentation.notes.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.copy
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.noteapp.domain.model.Note
import java.nio.file.Files.size

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier= Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerRadius: Dp = 30.dp,
    onDeleteClick: () -> Unit
){
    Box(
        modifier = modifier
    ){
        Canvas(modifier = Modifier.matchParentSize()){
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerRadius.toPx(),0f)
                lineTo(size.width, cutCornerRadius.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath){
                drawRoundRect(
                    color = Color(note.color),
                    size= size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
            clipPath(clipPath){
                drawRoundRect(
                    color = Color(ColorUtils.blendARGB(note.color, 0x000000, 0.2f)),
                    topLeft = Offset(size.width -cutCornerRadius.toPx(), -100f),
                    size= Size(cutCornerRadius.toPx() + 100f, cornerRadius.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }

        Column() { }
    }
}
