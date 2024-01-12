package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.text.style.LineBackgroundSpan
import android.text.style.QuoteSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.string.combine.TextCombine
import com.example.myapplication.ui.string.combine.TextCombineRenderer

class MainActivity : AppCompatActivity() {

    private val textCombineRenderer by lazy { TextCombineRenderer(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)

        setContentView(textView)


        textView.text = textCombineRenderer.render(testStringCombine2())


        //LineBackgroundSpan()

        //textView.text = spannableString

        //setContentView(textView)
    }

    private fun testStringCombine2() = TextCombine(
        texts = listOf(
            TextCombine.TextValue(
                source = TextCombine.TextSource.FromString("Dupa"),
                span = listOf(
                    TextCombine.StyleSpan.ParagraphStyle.Quote(),
                )
            ),
            TextCombine.TextValue(
                source = TextCombine.TextSource.FromString("\n")
            ),
            TextCombine.TextValue(
                source = TextCombine.TextSource.FromString("One"),
                span = listOf(
                    TextCombine.StyleSpan.ParagraphStyle.Quote(),
                )
            ),
        )
    )

    private fun testStringResCombine() =  TextCombine(
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
    )
}
