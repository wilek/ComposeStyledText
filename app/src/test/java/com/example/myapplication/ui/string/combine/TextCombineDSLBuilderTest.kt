package com.example.myapplication.ui.string.combine

import com.example.myapplication.ui.string.combine.TextCombine.BlurType
import com.example.myapplication.ui.string.combine.TextCombine.ColorSource
import com.example.myapplication.ui.string.combine.TextCombine.DimensionValue
import com.example.myapplication.ui.string.combine.TextCombine.ImageAlignType
import com.example.myapplication.ui.string.combine.TextCombine.ImageSource
import com.example.myapplication.ui.string.combine.TextCombine.Margin
import com.example.myapplication.ui.string.combine.TextCombine.MaskFilterType
import com.example.myapplication.ui.string.combine.TextCombine.Size
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.BackgroundColor
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Clickable
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.ForegroundColor
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Image
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.MaskFilter
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.RelativeSize
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.ScaleX
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Strikethrough
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Style
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Subscript
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Superscript
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Typeface
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Underline
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Alignment
import com.example.myapplication.ui.string.combine.TextCombine.TextAlignmentType
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringResource
import com.example.myapplication.ui.string.combine.TextCombine.TypefaceSource
import com.example.myapplication.ui.string.combine.TextCombine.TypefaceStyle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TextCombineDSLBuilderTest {

    @Test
    fun `should build text value with string`() {
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
    fun `should build text combine with string resource`() {
        // given
        val stringCombine = stringCombine {
            appendStringResource(resourceId = 1)
        }

        // then
        assertEquals(
            TextCombine(texts = listOf(TextCombine.TextValue(source = FromStringResource(stringResId = 1)))),
            stringCombine
        )
    }

    @Test
    fun `should build text combine with string plural resource`() {
        // given
        val stringCombine = stringCombine {
            appendStringPluralResource(resourceId = 1, count = 1)
        }

        // then
        assertEquals(
            TextCombine(texts = listOf(TextCombine.TextValue(source = FromStringPluralResource(pluralResId = 1, count = 1)))),
            stringCombine
        )
    }

    @Test
    fun `should build text value with string with format argument`() {
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
    fun `should build text combine with string resource with format argument`() {
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
                        source = FromStringResource(stringResId = 1),
                        formatArgs = listOf(TextCombine.TextValue(source = FromStringResource(stringResId = 2)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text combine with string plural resource with format argument`() {
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
                        source = FromStringPluralResource(pluralResId = 1, count = 1),
                        formatArgs = listOf(TextCombine.TextValue(source = FromStringPluralResource(pluralResId = 2, count = 2)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with absolute size span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    absoluteSize(size = dimensionFromPx(value = 1))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(AbsoluteSize(size = DimensionValue.FromPx(value = 1)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with background color span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    backgroundColor(color = colorFromInt(color = 1))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(BackgroundColor(color = ColorSource.FromInt(color = 1)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with clickable span`() {
        // given
        val onClick: (String?) -> Unit = { }
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    clickable(onClick = onClick, id = "id")
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(Clickable(onClick = onClick, id = "id"))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with foreground color span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    foregroundColor(color = colorFromInt(color = 1))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(ForegroundColor(color = ColorSource.FromInt(color = 1)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with image span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    image(
                        image = imageFromDrawable(drawableResId = 1),
                        alignType = ImageAlignType.ALIGN_BOTTOM
                    ) {
                        size {
                            width = dimensionFromPx(value = 2)
                            height = dimensionFromPx(value = 3)
                        }
                        margin {
                            start = dimensionFromPx(value = 4)
                            top = dimensionFromPx(value = 5)
                            bottom = dimensionFromPx(value = 6)
                            end = dimensionFromPx(value = 7)

                        }
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
                        spans = listOf(
                            Image(
                                image = ImageSource.FromDrawable(drawableResId = 1),
                                alignType = ImageAlignType.ALIGN_BOTTOM,
                                size = Size(
                                    width = DimensionValue.FromPx(value = 2),
                                    height = DimensionValue.FromPx(value = 3)
                                ),
                                margin = Margin(
                                    start = DimensionValue.FromPx(value = 4),
                                    top = DimensionValue.FromPx(value = 5),
                                    bottom = DimensionValue.FromPx(value = 6),
                                    end = DimensionValue.FromPx(value = 7)
                                )
                            )
                        )
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with mask filter span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    maskFilter(filterType = blurMaskFilterType(blurType = BlurType.NORMAL, radius = 1.0f))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(MaskFilter(filterType = MaskFilterType.Blur(blurType = BlurType.NORMAL, radius = 1.0f)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with relative size span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    relativeSize(proportion = 1.0f)
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(RelativeSize(proportion = 1.0f))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with scale X span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    scaleX(proportion = 1.0f)
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(ScaleX(proportion = 1.0f))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with strikethrough span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    strikethrough()
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(Strikethrough)
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with style span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    style(typefaceStyle = TypefaceStyle.NORMAL)
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(Style(typefaceStyle = TypefaceStyle.NORMAL))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with subscript span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    subscript()
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(Subscript)
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with superscript span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    superscript()
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(Superscript)
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with typeface span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    typeface(typeface = typefaceFromAssets(fileName = "fileName"))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(Typeface(typeface = TypefaceSource.FromAssets(fileName = "fileName")))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with underline span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    underline()
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(Underline)
                    )
                )
            ),
            stringCombine
        )
    }


    @Test
    fun should_build_text_with_alignment_span() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    alignment(alignment = TextAlignmentType.ALIGN_NORMAL)
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        source = FromString(text = "String text"),
                        spans = listOf(Alignment(alignment = TextAlignmentType.ALIGN_NORMAL))
                    )
                )
            ),
            stringCombine
        )
    }
}
