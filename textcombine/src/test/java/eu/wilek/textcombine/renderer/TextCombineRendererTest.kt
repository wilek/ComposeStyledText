package eu.wilek.textcombine.renderer

import android.content.Context
import android.content.res.Resources
import eu.wilek.textcombine.TextCombine
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Underline
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Quote
import eu.wilek.textcombine.TextCombine.TextSource.FromString
import eu.wilek.textcombine.TextCombine.TextSource.FromStringPluralResource
import eu.wilek.textcombine.TextCombine.TextSource.FromStringResource
import eu.wilek.textcombine.TextCombine.TextValue
import eu.wilek.textcombine.renderer.spannable.SpannableCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.AdditionalAnswers.returnsSecondArg
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.eq
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.willReturn

internal class TextCombineRendererTest {

    private val resourcesMock: Resources = mock()
    private val context: Context = mock { on { resources } doAnswer { resourcesMock } }
    private val spanCreator: SpannableCreator = mock()
    private val textCombineRenderer = TextCombineRenderer(context = context, spanCreator = spanCreator)

    @BeforeEach
    fun setUp() {
        `when`(
            spanCreator.createSpan(
                context = eq(context),
                text = any(),
                textSpans = any()
            )
        ).then(returnsSecondArg<CharSequence>())
    }

    @Test
    fun `should format simple text from string source`() {
        // given

        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromString("Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry."),
                    formatArgs = listOf(
                        TextValue(text = FromString("Ipsum")),
                        TextValue(text = FromString("dummy")),
                        TextValue(text = FromString("printing")),
                        TextValue(text = FromString("typesetting"))
                    )
                )
            )
        )

        // when
        val text = textCombineRenderer.render(textCombine).toString()

        // then
        assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting industry.", text)
    }

    @Test
    fun `should format text from string source with nested format arguments`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromString("What is Lorem Ipsum? %1\$s"),
                    formatArgs = listOf(
                        TextValue(
                            text = FromString("Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry."),
                            formatArgs = listOf(
                                TextValue(text = FromString("Ipsum")),
                                TextValue(text = FromString("dummy")),
                                TextValue(text = FromString("printing")),
                                TextValue(text = FromString("typesetting"))
                            )
                        )
                    )
                )
            )
        )

        // when
        val text = textCombineRenderer.render(textCombine).toString()

        // then
        assertEquals(
            "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            text
        )
    }

    @Test
    fun `should format simple text from resource source`() {
        // given
        given { context.getString(1) } willReturn { "Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry." }
        given { context.getString(2) } willReturn { "Ipsum" }
        given { context.getString(3) } willReturn { "dummy" }
        given { context.getString(4) } willReturn { "printing" }
        given { context.getString(5) } willReturn { "typesetting" }
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromStringResource(stringResId = 1),
                    formatArgs = listOf(
                        TextValue(text = FromStringResource(stringResId = 2)),
                        TextValue(text = FromStringResource(stringResId = 3)),
                        TextValue(text = FromStringResource(stringResId = 4)),
                        TextValue(text = FromStringResource(stringResId = 5))
                    )
                )
            )
        )

        // when
        val text = textCombineRenderer.render(textCombine).toString()

        // then
        assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting industry.", text)
    }

    @Test
    fun `should format text from resource source with nested format arguments`() {
        // given
        given { context.getString(1) } willReturn { "What is Lorem Ipsum? %1\$s" }
        given { context.getString(2) } willReturn { "Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry." }
        given { context.getString(3) } willReturn { "Ipsum" }
        given { context.getString(4) } willReturn { "dummy" }
        given { context.getString(5) } willReturn { "printing" }
        given { context.getString(6) } willReturn { "typesetting" }
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromStringResource(stringResId = 1),
                    formatArgs = listOf(
                        TextValue(
                            text = FromStringResource(stringResId = 2),
                            formatArgs = listOf(
                                TextValue(text = FromStringResource(stringResId = 3)),
                                TextValue(text = FromStringResource(stringResId = 4)),
                                TextValue(text = FromStringResource(stringResId = 5)),
                                TextValue(text = FromStringResource(stringResId = 6))
                            )
                        )
                    )
                )
            )
        )

        // when
        val text = textCombineRenderer.render(textCombine).toString()

        // then
        assertEquals(
            "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            text
        )
    }

    @Test
    fun `should format simple text from quantity source`() {
        // given
        given {
            resourcesMock.getQuantityString(
                1,
                1
            )
        } willReturn { "Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry." }
        given { resourcesMock.getQuantityString(2, 1) } willReturn { "Ipsum" }
        given { resourcesMock.getQuantityString(3, 1) } willReturn { "dummy" }
        given { resourcesMock.getQuantityString(4, 1) } willReturn { "printing" }
        given { resourcesMock.getQuantityString(5, 1) } willReturn { "typesetting" }
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromStringPluralResource(pluralResId = 1, count = 1),
                    formatArgs = listOf(
                        TextValue(text = FromStringPluralResource(pluralResId = 2, count = 1)),
                        TextValue(text = FromStringPluralResource(pluralResId = 3, count = 1)),
                        TextValue(text = FromStringPluralResource(pluralResId = 4, count = 1)),
                        TextValue(text = FromStringPluralResource(pluralResId = 5, count = 1))
                    )
                )
            )
        )

        // when
        val text = textCombineRenderer.render(textCombine).toString()

        // then
        assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting industry.", text)
    }

    @Test
    fun `should format text from quantity source with nested format arguments`() {
        // given
        given { resourcesMock.getQuantityString(1, 1) } willReturn { "What is Lorem Ipsum? %1\$s" }
        given {
            resourcesMock.getQuantityString(2, 1)
        } willReturn { "Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry." }
        given { resourcesMock.getQuantityString(3, 1) } willReturn { "Ipsum" }
        given { resourcesMock.getQuantityString(4, 1) } willReturn { "dummy" }
        given { resourcesMock.getQuantityString(5, 1) } willReturn { "printing" }
        given { resourcesMock.getQuantityString(6, 1) } willReturn { "typesetting" }
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromStringPluralResource(pluralResId = 1, count = 1),
                    formatArgs = listOf(
                        TextValue(
                            text = FromStringPluralResource(pluralResId = 2, count = 1),
                            formatArgs = listOf(
                                TextValue(text = FromStringPluralResource(pluralResId = 3, count = 1)),
                                TextValue(text = FromStringPluralResource(pluralResId = 4, count = 1)),
                                TextValue(text = FromStringPluralResource(pluralResId = 5, count = 1)),
                                TextValue(text = FromStringPluralResource(pluralResId = 6, count = 1))
                            )
                        )
                    )
                )
            )
        )

        // when
        val text = textCombineRenderer.render(textCombine).toString()

        // then
        assertEquals(
            "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            text
        )
    }

    @Test
    fun `should apply span for text combine`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromString("Paragraph one"),
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
            textSpans = listOf(
                TextCombineRenderer.PhraseSpan(
                    start = 0,
                    end = 13,
                    spans = listOf(Quote())
                )
            )
        )
    }

    @Test
    fun `should apply span for multiple text combine`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromString("Paragraph one"),
                    spans = listOf(Quote())
                ),
                TextValue(
                    text = FromString("\n")
                ),
                TextValue(
                    text = FromString("Paragraph two"),
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
                TextCombineRenderer.PhraseSpan(start = 0, end = 13, spans = listOf(Quote())),
                TextCombineRenderer.PhraseSpan(start = 14, end = 27, spans = listOf(Quote()))
            )
        )
    }

    @Test
    fun `should apply span for multiple text combine with format arguments`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromString("Paragraph %s"),
                    spans = listOf(Quote()),
                    formatArgs = listOf(
                        TextValue(
                            text = FromString("one"),
                            spans = listOf(Underline)
                        )
                    )
                ),
                TextValue(
                    text = FromString("\n")
                ),
                TextValue(
                    text = FromString("Paragraph two"),
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
                TextCombineRenderer.PhraseSpan(start = 0, end = 13, spans = listOf(Quote())),
                TextCombineRenderer.PhraseSpan(
                    start = 10,
                    end = 13,
                    spans = listOf(Underline)
                ),
                TextCombineRenderer.PhraseSpan(start = 14, end = 27, spans = listOf(Quote()))
            )
        )
    }

    @Test
    fun `should apply main span for text combine`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(TextValue(text = FromString("Paragraph one"))),
            spans = listOf(Quote())
        )

        // when
        textCombineRenderer.render(textCombine = textCombine)

        // then
        verify(spanCreator).createSpan(
            context = context,
            text = "Paragraph one",
            textSpans = listOf(
                TextCombineRenderer.PhraseSpan(
                    start = 0,
                    end = 13,
                    spans = listOf(Quote())
                )
            )
        )
    }

    @Test
    fun `should apply main and sibling spans for text combine`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    text = FromString("Paragraph one"),
                    spans = listOf(Underline)
                )
            ),
            spans = listOf(Quote())
        )

        // when
        textCombineRenderer.render(textCombine = textCombine)

        // then
        verify(spanCreator).createSpan(
            context = context,
            text = "Paragraph one",
            textSpans = listOf(
                TextCombineRenderer.PhraseSpan(
                    start = 0,
                    end = 13,
                    spans = listOf(Quote())
                ),
                TextCombineRenderer.PhraseSpan(
                    start = 0,
                    end = 13,
                    spans = listOf(Underline)
                )
            )
        )
    }
}
