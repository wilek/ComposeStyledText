package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.table.content.adapter.TableOfContentsAdapter
import com.example.myapplication.table.content.viewmodel.TableContentViewModel
import eu.wilek.textcombine.TextCombine
import eu.wilek.textcombine.config.TextCombineRendererInstance
import eu.wilek.textcombine.dsl.blurMaskFilterType
import eu.wilek.textcombine.dsl.colorFromInt
import eu.wilek.textcombine.dsl.colorFromString
import eu.wilek.textcombine.dsl.dimensionFromDp
import eu.wilek.textcombine.dsl.dimensionFromSp
import eu.wilek.textcombine.dsl.imageFromDrawable
import eu.wilek.textcombine.dsl.stringCombine
import eu.wilek.textcombine.dsl.typefaceFromAssets
import eu.wilek.textcombine.util.px
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: TableContentViewModel by viewModels()
    private val textCombineRenderer by lazy { TextCombineRendererInstance.withContext(context = this).get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tables_of_content_activity)

        val tablesOfContents = findViewById<RecyclerView>(R.id.tablesOfContentRecyclerView)
        val tableOfContentsAdapter = TableOfContentsAdapter(textCombineRenderer = textCombineRenderer)
        tablesOfContents.adapter = tableOfContentsAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.contentsTable.collect {
                    tableOfContentsAdapter.setItems(items = it)
                }
            }
        }
    }

    fun main(args: Array<String>) {

        val first = 10
        val second = 20

        val sum = first + second

        println("The sum is: $sum")
    }

    private fun test() = stringCombine {
        appendString("one") { setSpans { bullet() } }
        appendString("\n")
        appendString("dwa") { setSpans { bullet() } }
    }

    private fun image() = stringCombine {
        appendString("My app %s logo is beauty") {
            setSpans {
                absoluteSize(size = dimensionFromSp(value = 24f))
            }
            formatWithString("image") {
                setSpans {
                    image(
                        image = imageFromDrawable(drawableResId = R.drawable.moto),
                        alignType = TextCombine.ImageAlignType.ALIGN_BOTTOM
                    )
                }
            }
        }

        appendString("\n")
        appendString("Lol fajne te gowno i co dsf sdfg ds g gd fg d fgd fg dfg fd gd fh df hd fh dfh fd h hf h f h cx hfd  f hdf h df h fdg df hd fh d h d h  hd fhd ") {
            setSpans {
                lineBackground(color = colorFromInt(Color.BLUE))
                lineHeight(height = dimensionFromDp(80f))
            }
        }
        appendString("\n")
        appendString("Lol HAHHA")
        appendString("\n")
        appendString("CO TO ZA SZAJS")
    }

    private fun codeBlock() = stringCombine {
        setSpans {
            lineBackground(color = colorFromString(hexColor = "#1E1F22"))
            leadingMargin(first = 16.px)
            quote {
                gapWidth = dimensionFromDp(value = 4f)
                color = colorFromString("#333333")
                stripeWidth = dimensionFromDp(1f)
            }
            typeface(typeface = typefaceFromAssets("fonts/JetBrainsMono-Regular.ttf"))
            tabStop(offset = dimensionFromDp(value = 32f))
        }
        appendString("fun") { setSpans { foregroundColor(color = colorFromString(hexColor = "#CF8E6D")) } }
        appendString(" main") { setSpans { foregroundColor(color = colorFromString(hexColor = "#56A8F5")) } }
        appendString("(args: Array<String>) {") { setSpans { foregroundColor(color = colorFromString(hexColor = "#BCBEC4")) } }
        appendString("\n")
        appendString("\tval ") { setSpans { foregroundColor(color = colorFromString(hexColor = "#CF8E6D")) } }
        appendString("first = ") { setSpans { foregroundColor(color = colorFromString(hexColor = "#BCBEC4")) } }
        appendString("10") { setSpans { foregroundColor(color = colorFromString(hexColor = "#2AACB8")) } }
        appendString("\n")
        appendString("\tval ") { setSpans { foregroundColor(color = colorFromString(hexColor = "#CF8E6D")) } }
        appendString("second = ") { setSpans { foregroundColor(color = colorFromString(hexColor = "#BCBEC4")) } }
        appendString("20") { setSpans { foregroundColor(color = colorFromString(hexColor = "#2AACB8")) } }
        appendString("\n")
        appendString("\tval ") { setSpans { foregroundColor(color = colorFromString(hexColor = "#CF8E6D")) } }
        appendString("sum = first + second") { setSpans { foregroundColor(color = colorFromString(hexColor = "#BCBEC4")) } }
        appendString("\n\n")
        appendString("\tprintln(") { setSpans { foregroundColor(color = colorFromString(hexColor = "#BCBEC4")) } }
        appendString("\"The sum is: ") { setSpans { foregroundColor(color = colorFromString(hexColor = "#6AAB73")) } }
        appendString("$") { setSpans { foregroundColor(color = colorFromString(hexColor = "#CF8E6D")) } }
        appendString("sum") { setSpans { foregroundColor(color = colorFromString(hexColor = "#BCBEC4")) } }
        appendString("\"") { setSpans { foregroundColor(color = colorFromString(hexColor = "#6AAB73")) } }
        appendString(")") { setSpans { foregroundColor(color = colorFromString(hexColor = "#BCBEC4")) } }
        appendString("\n") { setSpans { foregroundColor(color = colorFromString(hexColor = "#6AAB73")) } }
        appendString("}") { setSpans { foregroundColor(color = colorFromString(hexColor = "#BCBEC4")) } }
    }

    private fun bullet() = stringCombine {
        appendString("If your items are words or phrases that do not form a full sentence by themselves, style guides suggest that they begin with a lowercase letter and are punctuated as follows:\n\n") {}
        appendString("no punctuation after the first item") {
            setSpans {
                leadingMargin(first = 40.px)
                bullet()
            }
        }
        appendString("\n")
        appendString("no punctuations after the second, third, etc., items") {
            setSpans {
                leadingMargin(first = 40.px)
                bullet()
            }
        }
        appendString("\n")
        appendString("a full stop after the last item.") {
            setSpans {
                leadingMargin(first = 40.px)
                bullet()
            }
        }
    }

    private fun blur() = stringCombine {
        appendString("My password %s is invisible for you!") {
            formatWithString("dupa") {
                setSpans {
                    maskFilter(filterType = blurMaskFilterType(blurType = TextCombine.BlurType.SOLID, radius = 3f))
                }
            }
        }
    }

    private fun math() = stringCombine {
        appendString("3")
        appendString("2") {
            setSpans {
                superscript()
                absoluteSize(size = dimensionFromSp(value = 10f))
            }
        }
        appendString(" = ")
        appendString("9")
    }

    /*private fun testStringCombine2() = TextCombine(
        texts = listOf(
            TextCombine.TextValue(
                source = TextCombine.TextSource.FromString("Dupa%s\n"),
                span = listOf(
                    TextCombine.StyleSpan.ParagraphStyle.Quote(),
                ),
                formatArgs = listOf(
                    TextCombine.TextValue(
                        source = TextCombine.TextSource.FromString("test"),
                        span = listOf(
                            TextCombine.StyleSpan.CharacterStyle.ForegroundColor(color = TextCombine.ColorSource.FromInt(Color.RED))
                        ),
                    )
                )
            ),
            TextCombine.TextValue(
                source = TextCombine.TextSource.FromString("Dupa2"),
                span = listOf(
                    TextCombine.StyleSpan.ParagraphStyle.Quote(),
                )
            ),
            *//*TextCombine.TextValue(
                source = TextCombine.TextSource.FromString("Dupa3"),
                span = listOf(
                    TextCombine.StyleSpan.ParagraphStyle.Quote(),
                )
            )*//*


        )
    )*/

    /*private fun testStringResCombine() =  TextCombine(
        texts = listOf(
            TextCombine.TextValue(
                source = TextCombine.TextSource.FromStringResource(resourceId = R.string.main_text),
                span = listOf(
                    TextCombine.StyleSpan.CharacterStyle.ForegroundColor(color = TextCombine.ColorSource.FromString("#FF0000"))
                ),
                formatArgs = listOf(
                    TextCombine.TextValue(
                        source = TextCombine.TextSource.FromStringResource(resourceId = R.string.replacement_on),
                        span = listOf(
                            TextCombine.StyleSpan.CharacterStyle.Style(typefaceStyle = TextCombine.TypefaceStyle.BOLD),
                            TextCombine.StyleSpan.CharacterStyle.ForegroundColor(color = TextCombine.ColorSource.FromString("#00FF00"))
                        )
                    ),
                    TextCombine.TextValue(source = TextCombine.TextSource.FromStringResource(resourceId = R.string.replacement_two)),
                    TextCombine.TextValue(source = TextCombine.TextSource.FromStringResource(resourceId = R.string.replacement_three)),
                    TextCombine.TextValue(
                        source = TextCombine.TextSource.FromString("ddss"),
                        span = listOf(
                            TextCombine.StyleSpan.CharacterStyle.Style(typefaceStyle = TextCombine.TypefaceStyle.BOLD),
                            TextCombine.StyleSpan.CharacterStyle.Style(typefaceStyle = TextCombine.TypefaceStyle.ITALIC)
                        )
                    )
                )
            )
        )
    )*/

    private fun letter() = stringCombine {
        appendString("I") {
            setSpans {
                relativeSize(proportion = 2f)
            }
        }
        appendString("f your items are words or phrases that do not form a full sentence by themselves, style guides suggest that they begin with a lowercase letter and are punctuated as follows")
    }
}
