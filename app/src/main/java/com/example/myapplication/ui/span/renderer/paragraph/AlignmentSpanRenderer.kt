package com.example.myapplication.ui.span.renderer.paragraph

import android.content.Context
import android.text.Layout
import android.text.style.AlignmentSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Alignment
import com.example.myapplication.ui.string.combine.TextCombine.TextAlignmentType

open class AlignmentSpanRenderer : SpanRenderer<Alignment> {

    override fun renderSpan(context: Context, styleSpan: Alignment): Any {
        return AlignmentSpan.Standard(styleSpan.alignment.toAlignment())
    }

    private fun TextAlignmentType.toAlignment() = when (this) {
        TextAlignmentType.ALIGN_NORMAL -> Layout.Alignment.ALIGN_NORMAL
        TextAlignmentType.ALIGN_OPPOSITE -> Layout.Alignment.ALIGN_OPPOSITE
        TextAlignmentType.ALIGN_CENTER -> Layout.Alignment.ALIGN_CENTER
    }
}
