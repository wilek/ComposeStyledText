package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.string.combine.StringCombine
import com.example.myapplication.ui.string.combine.StringCombineRenderer

class MainActivity : AppCompatActivity() {

    private val stringCombineRenderer by lazy { StringCombineRenderer(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)

        setContentView(textView)

        textView.text = stringCombineRenderer.render(testStringCombine())
    }

    private fun testStringCombine() = StringCombine(
        texts = listOf(
            StringCombine.TextValue(
                source = StringCombine.TextSource.FromString("Lorem %1\$s is simply %2\$s text of the %3\$s and %4\$s industry."),
                span = listOf(
                    StringCombine.TextSpan.ForegroundColorSpan(color = StringCombine.ColorSource.FromString("#FF0000"))
                ),
                formatArgs = listOf(
                    StringCombine.TextValue(
                        source = StringCombine.TextSource.FromString("Ipsum"),
                        span = listOf(
                            StringCombine.TextSpan.TypefaceSpan.Bold,
                            StringCombine.TextSpan.ForegroundColorSpan(color = StringCombine.ColorSource.FromString("#00FF00"))
                        )
                    ),
                    StringCombine.TextValue(source = StringCombine.TextSource.FromString("dummy")),
                    StringCombine.TextValue(source = StringCombine.TextSource.FromString("printing")),
                    StringCombine.TextValue(
                        source = StringCombine.TextSource.FromString("typesetting"),
                        span = listOf(StringCombine.TextSpan.TypefaceSpan.Bold, StringCombine.TextSpan.TypefaceSpan.Italic)
                    ),
                )
            )
        )
    )
}
