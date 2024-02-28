package eu.wilek.textcombine.renderer

import eu.wilek.textcombine.TextCombine

interface TextCombineRenderer {

    fun render(textCombine: TextCombine): CharSequence
}
