package com.example.myapplication.ui.string.combine

import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import com.example.myapplication.ui.string.combine.StringCombine.TextValue
import com.example.myapplication.ui.string.combine.StringCombine.TextSource.FromString
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class StringCombineRendererTextFormatTest(private val stringCombine: StringCombine, private val expected: CharSequence) {

    private val stringCombineRenderer by lazy { StringCombineRenderer(ApplicationProvider.getApplicationContext()) }

    @Test
    fun should_create_formatted_text_text() {

        // when
        val text = stringCombineRenderer.render(stringCombine)

        // then
        assertThat(text.toString()).isEqualTo(expected)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0} should be formatted to {1}")
        fun data(): List<Array<Any>> = listOf(
            arrayOf(
                StringCombine(
                    texts = listOf(
                        TextValue(
                            source = FromString("Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry."),
                            formatArgs = listOf(
                                TextValue(source = FromString("Ipsum")),
                                TextValue(source = FromString("dummy")),
                                TextValue(source = FromString("printing")),
                                TextValue(source = FromString("typesetting")),
                            )
                        )
                    )
                ),
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            ),
            arrayOf(
                StringCombine(
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
                                        TextValue(source = FromString("typesetting")),
                                    )
                                ),
                            )
                        )
                    )
                ),
                "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            ),
            arrayOf(
                StringCombine(
                    texts = listOf(
                        TextValue(
                            source = FromString(text = "What is Lorem Ipsum? %1\$s"),
                            formatArgs = listOf(
                                TextValue(
                                    source = FromString(text = "Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry."),
                                    formatArgs = listOf(
                                        TextValue(source = FromString(text = "Ipsum")),
                                        TextValue(source = FromString(text = "dummy")),
                                        TextValue(source = FromString(text = "printing")),
                                        TextValue(source = FromString(text = "typesetting")),
                                    )
                                ),
                            )
                        ),
                        TextValue(
                            source = FromString(
                                text = " Lorem Ipsum has been the %1\$s standard dummy text ever since the %2\$s, "
                                        + "when an unknown %3\$s took a galley of type and scrambled it to make a type specimen %4\$s."
                            ),
                            formatArgs = listOf(
                                TextValue(source = FromString("industry's")),
                                TextValue(source = FromString("1500s")),
                                TextValue(source = FromString("printer")),
                                TextValue(source = FromString("book")),
                            )
                        )
                    )
                ),
                "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                        "when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            )
        )
    }
}
