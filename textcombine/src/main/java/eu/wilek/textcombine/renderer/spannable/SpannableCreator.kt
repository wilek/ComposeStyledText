package eu.wilek.textcombine.renderer.spannable

import android.content.Context
import eu.wilek.textcombine.renderer.TextCombineRenderer

interface SpannableCreator {

    fun createSpan(context: Context, text: CharSequence, textSpans: List<TextCombineRenderer.PhraseSpan>): CharSequence
}
