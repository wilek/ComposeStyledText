package com.example.myapplication.ui.string.combine

import android.content.Context
import com.example.myapplication.ui.span.creator.SpannableCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Underline
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Quote
import com.example.myapplication.ui.string.combine.TextCombineRenderer.PhraseSpan
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class TextCombineCreateSpansTest {

    private val context: Context = mock()
    private val spanCreator: SpannableCreator = mock()
    private val textCombineRenderer = TextCombineRenderer(context = context, spanCreator = spanCreator)

    @Test
    fun `should apply span for text combine`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("Paragraph one"),
                    spans = listOf(Quote())
                )
            )
        )

        // when
        textCombineRenderer.render(textCombine = textCombine)

        // then
        verify(spanCreator).createSpan(
            context = context,
            text = "Paragraph one",
            textSpans = listOf(PhraseSpan(start = 0, end = 13, spans = listOf(Quote())))
        )
    }

    @Test
    fun `should apply span for multiple text combine`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("Paragraph one"),
                    spans = listOf(Quote())
                ),
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("\n")
                ),
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("Paragraph two"),
                    spans = listOf(Quote())
                )
            )
        )

        // when
        textCombineRenderer.render(textCombine = textCombine)

        // then
        verify(spanCreator).createSpan(
            context = context,
            text = "Paragraph one\nParagraph two",
            textSpans = listOf(
                PhraseSpan(start = 0, end = 13, spans = listOf(Quote())),
                PhraseSpan(start = 14, end = 27, spans = listOf(Quote()))
            )
        )
    }

    @Test
    fun `should apply span for multiple text combine with format arguments`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("Paragraph %s"),
                    spans = listOf(Quote()),
                    formatArgs = listOf(
                        TextCombine.TextValue(
                            source = TextCombine.TextSource.FromString("one"),
                            spans = listOf(Underline)
                        )
                    )
                ),
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("\n")
                ),
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("Paragraph two"),
                    spans = listOf(Quote())
                )
            )
        )

        // when
        textCombineRenderer.render(textCombine = textCombine)

        // then
        verify(spanCreator).createSpan(
            context = context,
            text = "Paragraph one\nParagraph two",
            textSpans = listOf(
                PhraseSpan(start = 0, end = 13, spans = listOf(Quote())),
                PhraseSpan(start = 10, end = 13, spans = listOf(Underline)),
                PhraseSpan(start = 14, end = 27, spans = listOf(Quote()))
            )
        )
    }
}
