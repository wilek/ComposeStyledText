package com.example.myapplication.ui.string.combine

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringResource
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
internal class TextFormatFromStringResourceTest {

    private val resourcesMock: Resources = mock()
    private val context = spy(ApplicationProvider.getApplicationContext<Context>()) {
        on { resources } doAnswer { resourcesMock }
    }
    private val textCombineRenderer = TextCombineRenderer(context = context)

    @Test
    fun should_format_simple_text() {
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
        assertEquals("Lorem Ipsum is simply dummy text of the printing and typesetting industry.", text)
    }

    @Test
    fun should_format_text_with_nested_format_arguments() {
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
        assertEquals("What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.", text)
    }
}
