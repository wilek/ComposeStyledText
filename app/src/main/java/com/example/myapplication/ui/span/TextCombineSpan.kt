package com.example.myapplication.ui.span

import android.content.Context
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan

interface TextCombineSpan<T : StyleSpan> {

    fun createSpan(context: Context, styleSpan: T): Any
}