package com.example.myapplication.ui.span.renderer

import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan

interface SpanRenderer<T : StyleSpan> {

    fun renderSpan(styleSpan: T): Any
}
