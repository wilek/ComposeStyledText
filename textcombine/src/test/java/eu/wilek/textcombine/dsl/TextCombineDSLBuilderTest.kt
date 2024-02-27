package eu.wilek.textcombine.dsl

import eu.wilek.textcombine.TextCombine
import eu.wilek.textcombine.TextCombine.BlurType
import eu.wilek.textcombine.TextCombine.ColorSource
import eu.wilek.textcombine.TextCombine.DimensionValue
import eu.wilek.textcombine.TextCombine.ImageAlignType
import eu.wilek.textcombine.TextCombine.ImageSource
import eu.wilek.textcombine.TextCombine.Margin
import eu.wilek.textcombine.TextCombine.MaskFilterType
import eu.wilek.textcombine.TextCombine.Size
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.BackgroundColor
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Clickable
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ForegroundColor
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Image
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.MaskFilter
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.RelativeSize
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ScaleX
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Strikethrough
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Style
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Subscript
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Superscript
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.TextAppearance
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Typeface
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Underline
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Alignment
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Bullet
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingImage
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingMargin
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LineBackground
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LineHeight
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Quote
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.TabStop
import eu.wilek.textcombine.TextCombine.TextAlignmentType
import eu.wilek.textcombine.TextCombine.TextSource.FromString
import eu.wilek.textcombine.TextCombine.TextSource.FromStringPluralResource
import eu.wilek.textcombine.TextCombine.TextSource.FromStringResource
import eu.wilek.textcombine.TextCombine.TypefaceSource
import eu.wilek.textcombine.TextCombine.TypefaceStyle
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
            TextCombine(texts = listOf(TextCombine.TextValue(text = FromString(text = "String text")))),
            stringCombine
        )
    }

    @Test
    fun `should build text combine with string resource`() {
        // given
        val stringCombine = stringCombine {
            appendStringResource(stringResId = 1)
        }

        // then
        assertEquals(
            TextCombine(texts = listOf(TextCombine.TextValue(text = FromStringResource(stringResId = 1)))),
            stringCombine
        )
    }

    @Test
    fun `should build text combine with string plural resource`() {
        // given
        val stringCombine = stringCombine {
            appendStringPluralResource(pluralResId = 1, count = 1)
        }

        // then
        assertEquals(
            TextCombine(texts = listOf(TextCombine.TextValue(text = FromStringPluralResource(pluralResId = 1, count = 1)))),
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
                        text = FromString(text = "String text"),
                        formatArgs = listOf(TextCombine.TextValue(text = FromString(text = "format")))
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
            appendStringResource(stringResId = 1) {
                formatWithStringResource(stringResId = 2)
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromStringResource(stringResId = 1),
                        formatArgs = listOf(TextCombine.TextValue(text = FromStringResource(stringResId = 2)))
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
            appendStringPluralResource(pluralResId = 1, count = 1) {
                formatWithStringPluralResource(pluralResId = 2, count = 2)
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromStringPluralResource(pluralResId = 1, count = 1),
                        formatArgs = listOf(TextCombine.TextValue(text = FromStringPluralResource(pluralResId = 2, count = 2)))
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
                    absoluteSize(size = dimensionFromPx(value = 1f))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromString(text = "String text"),
                        spans = listOf(AbsoluteSize(size = DimensionValue.FromPx(value = 1f)))
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
                        text = FromString(text = "String text"),
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
                        text = FromString(text = "String text"),
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
                        text = FromString(text = "String text"),
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
                            width = dimensionFromPx(value = 2f)
                            height = dimensionFromPx(value = 3f)
                        }
                        margin {
                            start = dimensionFromPx(value = 4f)
                            top = dimensionFromPx(value = 5f)
                            bottom = dimensionFromPx(value = 6f)
                            end = dimensionFromPx(value = 7f)

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
                        text = FromString(text = "String text"),
                        spans = listOf(
                            Image(
                                image = ImageSource.FromDrawable(drawableResId = 1),
                                alignType = ImageAlignType.ALIGN_BOTTOM,
                                size = Size(
                                    width = DimensionValue.FromPx(value = 2f),
                                    height = DimensionValue.FromPx(value = 3f)
                                ),
                                margin = Margin(
                                    start = DimensionValue.FromPx(value = 4f),
                                    top = DimensionValue.FromPx(value = 5f),
                                    bottom = DimensionValue.FromPx(value = 6f),
                                    end = DimensionValue.FromPx(value = 7f)
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
                        text = FromString(text = "String text"),
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
                        text = FromString(text = "String text"),
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
                        text = FromString(text = "String text"),
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
                        text = FromString(text = "String text"),
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
                        text = FromString(text = "String text"),
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
                        text = FromString(text = "String text"),
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
                        text = FromString(text = "String text"),
                        spans = listOf(Superscript)
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with text appearance  span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    textAppearance(appearanceResId = 1) {
                        colorListResId = 2
                    }
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromString(text = "String text"),
                        spans = listOf(TextAppearance(appearanceResId = 1, colorListResId = 2))
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
                        text = FromString(text = "String text"),
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
                        text = FromString(text = "String text"),
                        spans = listOf(Underline)
                    )
                )
            ),
            stringCombine
        )
    }


    @Test
    fun `should build text with alignment span`() {
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
                        text = FromString(text = "String text"),
                        spans = listOf(Alignment(alignment = TextAlignmentType.ALIGN_NORMAL))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with bullet span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    bullet {
                        gapWidth = dimensionFromPx(value = 1f)
                        color = colorFromInt(color = 1)
                        radius = dimensionFromPx(value = 2f)
                    }
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromString(text = "String text"),
                        spans = listOf(
                            Bullet(
                                gapWidth = DimensionValue.FromPx(value = 1f),
                                color = ColorSource.FromInt(color = 1),
                                radius = DimensionValue.FromPx(value = 2f)
                            )
                        )
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with leading image span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    leadingImage(image = imageFromDrawable(drawableResId = 1)) {
                        size {
                            width = dimensionFromPx(value = 2f)
                            height = dimensionFromPx(value = 3f)
                        }
                        margin {
                            start = dimensionFromPx(value = 4f)
                            top = dimensionFromPx(value = 5f)
                            bottom = dimensionFromPx(value = 6f)
                            end = dimensionFromPx(value = 7f)

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
                        text = FromString(text = "String text"),
                        spans = listOf(
                            LeadingImage(
                                image = ImageSource.FromDrawable(drawableResId = 1),
                                size = Size(
                                    width = DimensionValue.FromPx(value = 2f),
                                    height = DimensionValue.FromPx(value = 3f)
                                ),
                                margin = Margin(
                                    start = DimensionValue.FromPx(value = 4f),
                                    top = DimensionValue.FromPx(value = 5f),
                                    bottom = DimensionValue.FromPx(value = 6f),
                                    end = DimensionValue.FromPx(value = 7f)
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
    fun `should build text with leading margin span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    leadingMargin(first = dimensionFromPx(value = 1f), rest = dimensionFromPx(value = 2f))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromString(text = "String text"),
                        spans = listOf(
                            LeadingMargin(
                                first = DimensionValue.FromPx(value = 1f),
                                rest = DimensionValue.FromPx(value = 2f)
                            )
                        )
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with line background span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    lineBackground(color = colorFromInt(color = 1))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromString(text = "String text"),
                        spans = listOf(LineBackground(color = ColorSource.FromInt(color = 1)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with line height span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    lineHeight(height = dimensionFromPx(value = 1f))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromString(text = "String text"),
                        spans = listOf(LineHeight(height = DimensionValue.FromPx(value = 1f)))
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with quote span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    quote {
                        color = colorFromInt(color = 1)
                        stripeWidth = dimensionFromPx(value = 1f)
                        gapWidth = dimensionFromPx(value = 2f)
                    }
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromString(text = "String text"),
                        spans = listOf(
                            Quote(
                                color = ColorSource.FromInt(color = 1),
                                stripeWidth = DimensionValue.FromPx(value = 1f),
                                gapWidth = DimensionValue.FromPx(value = 2f)
                            )
                        )
                    )
                )
            ),
            stringCombine
        )
    }

    @Test
    fun `should build text with tab stop span`() {
        // given
        val stringCombine = stringCombine {
            appendString(text = "String text") {
                setSpans {
                    tabStop(offset = dimensionFromPx(value = 1f))
                }
            }
        }

        // then
        assertEquals(
            TextCombine(
                texts = listOf(
                    TextCombine.TextValue(
                        text = FromString(text = "String text"),
                        spans = listOf(TabStop(offset = DimensionValue.FromPx(value = 1f)))
                    )
                )
            ),
            stringCombine
        )
    }

}