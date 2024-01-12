package com.example.myapplication.ui.span.renderer

import android.content.Context
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan

interface SpanRenderer<T : StyleSpan> {

    fun renderSpan(context: Context, styleSpan: T): Any
}
