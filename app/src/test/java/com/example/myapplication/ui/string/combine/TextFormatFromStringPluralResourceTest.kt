package com.example.myapplication.ui.string.combine

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.TextCombine.TextValue
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.willReturn

@RunWith(AndroidJUnit4::class)
internal class TextFormatFromStringPluralResourceTest {

    private val resourcesMock: Resources = mock()
    private val context = spy(ApplicationProvider.getApplicationContext<Context>()) {
        on { resources } doAnswer { resourcesMock }
    }
    private val textCombineRenderer = TextCombineRenderer(context = context)

    @Test
    fun should_format_simple_text() {
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
    fun should_format_text_with_nested_format_arguments() {
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
        assertEquals("What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.", text)
    }
}
