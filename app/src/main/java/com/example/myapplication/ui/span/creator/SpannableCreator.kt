package com.example.myapplication.ui.span.creator

import android.content.Context
import com.example.myapplication.ui.string.combine.TextCombineRenderer.PhraseSpan

interface SpannableCreator {

    fun createSpan(context: Context, text: CharSequence, textSpans: List<PhraseSpan>): CharSequence
}
