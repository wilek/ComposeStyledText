package com.example.myapplication

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.string.combine.TextCombineRenderer

class MainActivity : AppCompatActivity() {

    private val textCombineRenderer by lazy { TextCombineRenderer(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)

        setContentView(textView)

        val aa = SpannableString("Paragraph one\nParagraph two")

        aa.setSpan(BulletSpan(), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //aa.setSpan(UnderlineSpan(), 13, 28, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        aa.setSpan(BulletSpan(), 14, 27, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        /*val textCombine = TextCombine(
            texts = listOf(
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("Paragraph one\n%s"),
                    span = listOf(
                        TextCombine.StyleSpan.ParagraphStyle.Quote(stripeWidth = TextCombine.DimensionValue.FromPx(10))
                    ),
                    formatArgs = listOf(
                        TextCombine.TextValue(
                            source = TextCombine.TextSource.FromString("Paragraph two"),
                            span = listOf(TextCombine.StyleSpan.CharacterStyle.Underline)
                        ),
                    )
                ),
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("\nParagraph three"),
                    span = listOf(
                        TextCombine.StyleSpan.ParagraphStyle.Quote(stripeWidth = TextCombine.DimensionValue.FromPx(10))
                    )
                )
            )
        )*/

        /*val textCombine2 = TextCombine(
            texts = listOf(
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("Paragraph one"),
                    span = listOf(
                        TextCombine.StyleSpan.ParagraphStyle.Bullet()
                    )
                ),
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("\n")
                ),
                TextCombine.TextValue(
                    source = TextCombine.TextSource.FromString("Paragraph two"),
                    span = listOf(
                        TextCombine.StyleSpan.ParagraphStyle.Bullet()
                    )
                )
            )
        )*/


        //textView.text = textCombineRenderer.render(textCombine2)
        //textView.text = aa


        //LineBackgroundSpan()

        //textView.text = spannableString

        //setContentView(textView)
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
}
