package com.example.myapplication.ui.span.creator

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import com.example.myapplication.ui.span.renderer.character.AbsoluteSizeSpanRenderer
import com.example.myapplication.ui.span.renderer.character.BackgroundColorSpanRenderer
import com.example.myapplication.ui.span.renderer.character.ClickableSpanRenderer
import com.example.myapplication.ui.span.renderer.character.ForegroundColorSpanRenderer
import com.example.myapplication.ui.span.renderer.character.ImageSpanRenderer
import com.example.myapplication.ui.span.renderer.character.MaskFilterSpanRenderer
import com.example.myapplication.ui.span.renderer.character.RelativeSizeSpanRenderer
import com.example.myapplication.ui.span.renderer.character.ScaleXSpanRenderer
import com.example.myapplication.ui.span.renderer.character.StrikethroughSpanRenderer
import com.example.myapplication.ui.span.renderer.character.StyleSpanRenderer
import com.example.myapplication.ui.span.renderer.character.SubscriptSpanRenderer
import com.example.myapplication.ui.span.renderer.character.SuperscriptSpanRenderer
import com.example.myapplication.ui.span.renderer.character.TextAppearanceSpanRenderer
import com.example.myapplication.ui.span.renderer.character.TypefaceSpanRenderer
import com.example.myapplication.ui.span.renderer.character.UnderlineSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.AlignmentSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.BulletSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.LeadingImageSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.LeadingMarginSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.LineBackgroundSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.LineHeightSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.QuoteSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.TabStopSpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.BackgroundColor
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Clickable
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.ForegroundColor
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Image
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.MaskFilter
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.RelativeSize
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.ScaleX
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Strikethrough
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Style
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Subscript
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Superscript
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.TextAppearance
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Typeface
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Underline
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Alignment
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Bullet
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LeadingImage
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LeadingMargin
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LineBackground
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LineHeight
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Quote
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.TabStop
import com.example.myapplication.ui.string.combine.TextCombineRenderer.PhraseSpan

class DefaultSpannableCreator : SpannableCreator {

    override fun createSpan(
        context: Context,
        text: CharSequence,
        textSpans: List<PhraseSpan>
    ): CharSequence {
        val spannable = SpannableString(text)

        textSpans.forEach { textSpan ->
            textSpan.spans.forEach { span ->
                spannable.setSpan(
                    span.resolve(context = context),
                    textSpan.start,
                    textSpan.end,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
            }
        }


        return spannable
    }

    private fun StyleSpan.resolve(context: Context) = when (this) {
        is StyleSpan.CharacterStyle -> resolve(context = context)
        is StyleSpan.ParagraphStyle -> resolve(context = context)
    }

    private fun StyleSpan.CharacterStyle.resolve(context: Context) = when (this) {
        is AbsoluteSize -> AbsoluteSizeSpanRenderer(context = context).renderSpan(styleSpan = this)
        is BackgroundColor -> BackgroundColorSpanRenderer(context = context).renderSpan(styleSpan = this)
        is Clickable -> ClickableSpanRenderer().renderSpan(styleSpan = this)
        is ForegroundColor -> ForegroundColorSpanRenderer(context = context).renderSpan(styleSpan = this)
        is Image -> ImageSpanRenderer(context = context).renderSpan(styleSpan = this)
        is MaskFilter -> MaskFilterSpanRenderer().renderSpan(styleSpan = this)
        is RelativeSize -> RelativeSizeSpanRenderer().renderSpan(styleSpan = this)
        is ScaleX -> ScaleXSpanRenderer().renderSpan(styleSpan = this)
        is Strikethrough -> StrikethroughSpanRenderer().renderSpan(styleSpan = this)
        is Style -> StyleSpanRenderer().renderSpan(styleSpan = this)
        is Subscript -> SubscriptSpanRenderer().renderSpan(styleSpan = this)
        is Superscript -> SuperscriptSpanRenderer().renderSpan(styleSpan = this)
        is TextAppearance -> TextAppearanceSpanRenderer(context = context).renderSpan(styleSpan = this)
        is Typeface -> TypefaceSpanRenderer(context = context).renderSpan(styleSpan = this)
        is Underline -> UnderlineSpanRenderer().renderSpan(styleSpan = this)
    }

    private fun StyleSpan.ParagraphStyle.resolve(context: Context) = when (this) {
        is Alignment -> AlignmentSpanRenderer().renderSpan(styleSpan = this)
        is Bullet -> BulletSpanRenderer(context = context).renderSpan(styleSpan = this)
        is LeadingImage -> LeadingImageSpanRenderer(context = context).renderSpan(styleSpan = this)
        is LeadingMargin -> LeadingMarginSpanRenderer().renderSpan(styleSpan = this)
        is LineBackground -> LineBackgroundSpanRenderer(context = context).renderSpan(styleSpan = this)
        is LineHeight -> LineHeightSpanRenderer(context = context).renderSpan(styleSpan = this)
        is Quote -> QuoteSpanRenderer(context = context).renderSpan(styleSpan = this)
        is TabStop -> TabStopSpanRenderer(context = context).renderSpan(styleSpan = this)
    }
}
