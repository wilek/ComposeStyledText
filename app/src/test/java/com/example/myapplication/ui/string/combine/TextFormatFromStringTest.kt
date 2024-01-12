package com.example.myapplication.ui.string.combine

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.TextCombine.TextValue
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class TextFormatFromStringTest {

    private val textCombineRenderer = TextCombineRenderer(context = ApplicationProvider.getApplicationContext())

    @Test
    fun should_format_simple_text() {
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
    fun should_format_text_with_nested_format_arguments() {
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
        assertEquals("What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.", text)
    }
}
