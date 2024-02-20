package eu.wilek.textcombine.renderer.spannable

import android.content.Context
import eu.wilek.textcombine.TextCombine
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Underline
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Quote
import eu.wilek.textcombine.TextCombine.TextSource.FromString
import eu.wilek.textcombine.renderer.TextCombineRenderer
import eu.wilek.textcombine.renderer.TextCombineRenderer.PhraseSpan
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class DefaultSpannableCreatorTest {

    private val context: Context = mock()
    private val spanCreator: SpannableCreator = mock()
    private val textCombineRenderer = TextCombineRenderer(context = context, spanCreator = spanCreator)

    @Test
    fun `should apply span for text combine`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextCombine.TextValue(
                    source = FromString("Paragraph one"),
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
                    source = FromString("Paragraph one"),
                    spans = listOf(Quote())
                ),
                TextCombine.TextValue(
                    source = FromString("\n")
                ),
                TextCombine.TextValue(
                    source = FromString("Paragraph two"),
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
                    source = FromString("Paragraph %s"),
                    spans = listOf(Quote()),
                    formatArgs = listOf(
                        TextCombine.TextValue(
                            source = FromString("one"),
                            spans = listOf(Underline)
                        )
                    )
                ),
                TextCombine.TextValue(
                    source = FromString("\n")
                ),
                TextCombine.TextValue(
                    source = FromString("Paragraph two"),
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
