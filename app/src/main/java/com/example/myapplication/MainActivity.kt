package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.string.combine.StringCombine
import com.example.myapplication.ui.string.combine.StringCombineRenderer
import com.example.myapplication.ui.string.combine.stringCombine
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val stringCombineRenderer by lazy { StringCombineRenderer(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)


        /*setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(stringCombineRenderer)
                }
            }
        }*/
        setContentView(textView)

        val string = StringCombine(
            texts = listOf(
                StringCombine.TextValue(
                    source = StringCombine.TextSource.FromString("Lol %s"),
                    formatArgs = listOf(
                        StringCombine.TextValue(
                            source = StringCombine.TextSource.FromString("DUPA %s"),
                            formatArgs = listOf(StringCombine.TextValue(source = StringCombine.TextSource.FromString("2")))
                        )
                    )
                )
            )
        )


        val stringRes = StringCombine(
            texts = listOf(
                StringCombine.TextValue(
                    source = StringCombine.TextSource.FromStringResource(R.string.quantity_res),
                    formatArgs = listOf(
                        StringCombine.TextValue(source = StringCombine.TextSource.FromString("1")),
                        StringCombine.TextValue(source = StringCombine.TextSource.FromString("2"))
                    )
                ),
            )
        )

        val stringPlural = StringCombine(
            texts = listOf(
                StringCombine.TextValue(
                    source = StringCombine.TextSource.FromString("DUPA"),
                    formatArgs = listOf(
                        StringCombine.TextValue(source = StringCombine.TextSource.FromString("1")),
                        StringCombine.TextValue(source = StringCombine.TextSource.FromString("2"))
                    )
                ),
            )
        )


        val text = stringCombine {
            appendString("DUPA %s") {
                formatWithString("lol")
            }
            appendString(" ")
            appendStringResource(R.string.app_name)
            appendString( " ")
            appendStringPluralResource(R.plurals.productCount, 2)
        }

        textView.text = stringCombineRenderer.render(text)

    }
}

