package com.example.myapplication.ui.string.combine

import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringResource
import org.junit.Assert.assertEquals
import org.junit.Test

internal class TextCombineDSLBuilderTest {

    @Test
    fun should_build_text_value_with_string() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text")
        }

        // then
        assertEquals(
            TextCombine(texts = listOf(TextCombine.TextValue(source = FromString(text = "String text")))),
            stringCombine
        )
    }

    @Test
    fun should_build_text_combine_with_string_resource() {
        // given
        val stringCombine = stringCombine {
            appendStringResource(resourceId = 1)
        }

        // then
        assertEquals(
            TextCombine(texts = listOf(TextCombine.TextValue(source = FromStringResource(resourceId = 1)))),
            stringCombine
        )
    }

    @Test
    fun should_build_text_combine_with_string_plural_resource() {
        // given
        val stringCombine = stringCombine {
            appendStringPluralResource(resourceId = 1, count = 1)
        }

        // then
        assertEquals(
            TextCombine(texts = listOf(TextCombine.TextValue(source = FromStringPluralResource(resourceId = 1, count = 1)))),
            stringCombine
        )
    }

    @Test
    fun should_build_text_value_with_string_with_format_argument() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                formatWithString(text = "format")
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        formatArgs = listOf(TextCombine.TextValue(source = FromString(text = "format")))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun should_build_text_combine_with_string_resource_with_format_argument() {
        // given
        val stringCombine = stringCombine {
            appendStringResource(resourceId = 1) {
                formatWithStringResource(resourceId = 2)
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromStringResource(resourceId = 1),
                        formatArgs = listOf(TextCombine.TextValue(source = FromStringResource(resourceId = 2)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun should_build_text_combine_with_string_plural_resource_with_format_argument() {
        // given
        val stringCombine = stringCombine {
            appendStringPluralResource(resourceId = 1, count = 1) {
                formatWithStringPluralResource(resourceId = 2, count = 2)
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromStringPluralResource(resourceId = 1, count = 1),
                        formatArgs = listOf(TextCombine.TextValue(source = FromStringPluralResource(resourceId = 2, count = 2)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun should_build_text_with_bold_span() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    bold()
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        span = listOf(TextCombine.StyleSpan.CharacterStyle.Bold)
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun should_build_text_with_foreground_color_from_string() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    foregroundColor {
                        fromString("#FF0000")
                    }
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        span = listOf(
                            TextCombine.StyleSpan.ForegroundColorSpan(color = TextCombine.ColorSource.FromString("#FF0000"))
                        )
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun should_build_text_with_foreground_color_from_int() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    foregroundColor {
                        fromInt(color = 1)
                    }
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        span = listOf(
                            TextCombine.StyleSpan.ForegroundColorSpan(color = TextCombine.ColorSource.FromInt(color = 1))
                        )
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun should_build_text_with_foreground_color_from_resources() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    foregroundColor {
                        fromResource(resourceId = 1)
                    }
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        span = listOf(
                            TextCombine.StyleSpan.ForegroundColorSpan(color = TextCombine.ColorSource.FromResources(resourceId = 1))
                        )
                    )
                )
            ),
            stringCombine
        )
    }
}
