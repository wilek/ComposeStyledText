package eu.wilek.textcombine.renderer.spannable

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.BackgroundColor
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Clickable
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ForegroundColor
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Image
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.MaskFilter
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.RelativeSize
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ScaleX
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Strikethrough
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Style
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Subscript
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Superscript
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.TextAppearance
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Typeface
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Underline
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Alignment
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Bullet
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingImage
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingMargin
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LineBackground
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LineHeight
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Quote
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.TabStop
import eu.wilek.textcombine.renderer.TextCombineRenderer
import eu.wilek.textcombine.renderer.span.character.AbsoluteSizeSpanRenderer
import eu.wilek.textcombine.renderer.span.character.BackgroundColorSpanRenderer
import eu.wilek.textcombine.renderer.span.character.ClickableSpanRenderer
import eu.wilek.textcombine.renderer.span.character.ForegroundColorSpanRenderer
import eu.wilek.textcombine.renderer.span.character.ImageSpanRenderer
import eu.wilek.textcombine.renderer.span.character.MaskFilterSpanRenderer
import eu.wilek.textcombine.renderer.span.character.RelativeSizeSpanRenderer
import eu.wilek.textcombine.renderer.span.character.ScaleXSpanRenderer
import eu.wilek.textcombine.renderer.span.character.StrikethroughSpanRenderer
import eu.wilek.textcombine.renderer.span.character.StyleSpanRenderer
import eu.wilek.textcombine.renderer.span.character.SubscriptSpanRenderer
import eu.wilek.textcombine.renderer.span.character.SuperscriptSpanRenderer
import eu.wilek.textcombine.renderer.span.character.TextAppearanceSpanRenderer
import eu.wilek.textcombine.renderer.span.character.TypefaceSpanRenderer
import eu.wilek.textcombine.renderer.span.character.UnderlineSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.AlignmentSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.BulletSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.LeadingImageSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.LeadingMarginSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.LineBackgroundSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.LineHeightSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.QuoteSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.TabStopSpanRenderer

class DefaultSpannableCreator : SpannableCreator {

    override fun createSpan(
        context: Context,
        text: CharSequence,
        textSpans: List<TextCombineRenderer.PhraseSpan>
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
