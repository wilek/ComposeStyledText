package com.example.myapplication.ui.span.creator

import android.content.Context
import com.example.myapplication.ui.string.combine.TextCombine
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan

interface SpanCreator {

    fun createSpan(context: Context, span: StyleSpan): Any
}
