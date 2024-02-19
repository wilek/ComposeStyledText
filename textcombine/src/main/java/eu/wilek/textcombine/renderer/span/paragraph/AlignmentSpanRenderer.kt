package eu.wilek.textcombine.renderer.span.paragraph

import android.text.Layout
import android.text.style.AlignmentSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Alignment
import eu.wilek.textcombine.TextCombine.TextAlignmentType
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class AlignmentSpanRenderer : SpanRenderer<Alignment> {

    override fun renderSpan(styleSpan: Alignment): Any {
        return AlignmentSpan.Standard(styleSpan.alignment.toAlignment())
    }

    private fun TextAlignmentType.toAlignment() = when (this) {
        TextAlignmentType.ALIGN_NORMAL -> Layout.Alignment.ALIGN_NORMAL
        TextAlignmentType.ALIGN_OPPOSITE -> Layout.Alignment.ALIGN_OPPOSITE
        TextAlignmentType.ALIGN_CENTER -> Layout.Alignment.ALIGN_CENTER
    }
}
