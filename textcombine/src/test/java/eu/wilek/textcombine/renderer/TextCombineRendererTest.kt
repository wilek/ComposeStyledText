package eu.wilek.textcombine.renderer

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import eu.wilek.textcombine.TextCombine
import eu.wilek.textcombine.TextCombine.TextValue
import eu.wilek.textcombine.TextCombine.TextSource.FromStringResource
import eu.wilek.textcombine.TextCombine.TextSource.FromString
import eu.wilek.textcombine.TextCombine.TextSource.FromStringPluralResource
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.willReturn

@RunWith(AndroidJUnit4::class)
internal class TextCombineRendererTest {

    private val resourcesMock: Resources = mock()
    private val context = spy(ApplicationProvider.getApplicationContext<Context>()) {
        on { resources } doAnswer { resourcesMock }
    }
    private val textCombineRenderer = TextCombineRenderer(context = context)

    @Test
    fun `should format simple text from string source`() {
        // given
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    source = FromString("Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry."),
                    formatArgs = listOf(
                        TextValue(source = FromString("Ipsum")),
                        TextValue(source = FromString("dummy")),
                        TextValue(source = FromString("printing")),
                        TextValue(source = FromString("typesetting"))
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
                    source = FromString("What is Lorem Ipsum? %1\$s"),
                    formatArgs = listOf(
                        TextValue(
                            source = FromString("Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry."),
                            formatArgs = listOf(
                                TextValue(source = FromString("Ipsum")),
                                TextValue(source = FromString("dummy")),
                                TextValue(source = FromString("printing")),
                                TextValue(source = FromString("typesetting"))
                            )
                        )
                    )
                )
            )
        )

        // when
        val text = textCombineRenderer.render(textCombine).toString()

        // then
        Assert.assertEquals(
            "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            text
        )
    }

    @Test
    fun `should format simple text from resource source`() {
        // given
        given { resourcesMock.getString(1) } willReturn { "Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry." }
        given { resourcesMock.getString(2) } willReturn { "Ipsum" }
        given { resourcesMock.getString(3) } willReturn { "dummy" }
        given { resourcesMock.getString(4) } willReturn { "printing" }
        given { resourcesMock.getString(5) } willReturn { "typesetting" }
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    source = FromStringResource(stringResId = 1),
                    formatArgs = listOf(
                        TextValue(source = FromStringResource(stringResId = 2)),
                        TextValue(source = FromStringResource(stringResId = 3)),
                        TextValue(source = FromStringResource(stringResId = 4)),
                        TextValue(source = FromStringResource(stringResId = 5))
                    )
                )
            )
        )

        // when
        val text = textCombineRenderer.render(textCombine).toString()

        // then
        Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting industry.", text)
    }

    @Test
    fun `should format text from resource source with nested format arguments`() {
        // given
        given { resourcesMock.getString(1) } willReturn { "What is Lorem Ipsum? %1\$s" }
        given { resourcesMock.getString(2) } willReturn { "Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry." }
        given { resourcesMock.getString(3) } willReturn { "Ipsum" }
        given { resourcesMock.getString(4) } willReturn { "dummy" }
        given { resourcesMock.getString(5) } willReturn { "printing" }
        given { resourcesMock.getString(6) } willReturn { "typesetting" }
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    source = FromStringResource(stringResId = 1),
                    formatArgs = listOf(
                        TextValue(
                            source = FromStringResource(stringResId = 2),
                            formatArgs = listOf(
                                TextValue(source = FromStringResource(stringResId = 3)),
                                TextValue(source = FromStringResource(stringResId = 4)),
                                TextValue(source = FromStringResource(stringResId = 5)),
                                TextValue(source = FromStringResource(stringResId = 6))
                            )
                        )
                    )
                )
            )
        )

        // when
        val text = textCombineRenderer.render(textCombine).toString()

        // then
        Assert.assertEquals(
            "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            text
        )
    }

    @Test
    fun `should format simple text from quantity source`() {
        // given
        given { resourcesMock.getQuantityString(1, 1) } willReturn { "Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry." }
        given { resourcesMock.getQuantityString(2, 1) } willReturn { "Ipsum" }
        given { resourcesMock.getQuantityString(3, 1) } willReturn { "dummy" }
        given { resourcesMock.getQuantityString(4, 1) } willReturn { "printing" }
        given { resourcesMock.getQuantityString(5, 1) } willReturn { "typesetting" }
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    source = FromStringPluralResource(pluralResId = 1, count = 1),
                    formatArgs = listOf(
                        TextValue(source = FromStringPluralResource(pluralResId = 2, count = 1)),
                        TextValue(source = FromStringPluralResource(pluralResId = 3, count = 1)),
                        TextValue(source = FromStringPluralResource(pluralResId = 4, count = 1)),
                        TextValue(source = FromStringPluralResource(pluralResId = 5, count = 1))
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
            resourcesMock.getQuantityString(
                2,
                1
            )
        } willReturn { "Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry." }
        given { resourcesMock.getQuantityString(3, 1) } willReturn { "Ipsum" }
        given { resourcesMock.getQuantityString(4, 1) } willReturn { "dummy" }
        given { resourcesMock.getQuantityString(5, 1) } willReturn { "printing" }
        given { resourcesMock.getQuantityString(6, 1) } willReturn { "typesetting" }
        val textCombine = TextCombine(
            texts = listOf(
                TextValue(
                    source = FromStringPluralResource(pluralResId = 1, count = 1),
                    formatArgs = listOf(
                        TextValue(
                            source = FromStringPluralResource(pluralResId = 2, count = 1),
                            formatArgs = listOf(
                                TextValue(source = FromStringPluralResource(pluralResId = 3, count = 1)),
                                TextValue(source = FromStringPluralResource(pluralResId = 4, count = 1)),
                                TextValue(source = FromStringPluralResource(pluralResId = 5, count = 1)),
                                TextValue(source = FromStringPluralResource(pluralResId = 6, count = 1))
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
}