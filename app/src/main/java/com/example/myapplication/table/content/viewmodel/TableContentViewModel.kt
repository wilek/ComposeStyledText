package com.example.myapplication.table.content.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.table.content.model.TableOfContentActionUi
import com.example.myapplication.table.content.model.TableOfContentItemUi
import eu.wilek.textcombine.dsl.stringCombine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class TableContentViewModel : ViewModel() {

    private val _contentsTable = MutableStateFlow<List<TableOfContentItemUi>>(emptyList())
    val contentsTable: StateFlow<List<TableOfContentItemUi>> = _contentsTable.asStateFlow()

    init {
        _contentsTable.value = createTextCreatingSection() + createParagraphSpansSection() + createCharacterSpansSection()
    }

    private fun createTextCreatingSection() = listOf(
        TableOfContentItemUi.Header(headerText = stringCombine(stringResId = R.string.table_of_content_header_text_creating)),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_creating_text_example),
            action = TableOfContentActionUi.OpenTextCreatingExample
        )
    )

    private fun createParagraphSpansSection() = listOf(
        TableOfContentItemUi.Header(headerText = stringCombine(stringResId = R.string.table_of_content_header_paragraph_spans)),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_alignment),
            action = TableOfContentActionUi.OpenAlignmentSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_bullet),
            action = TableOfContentActionUi.OpenBulletSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_leading_image),
            action = TableOfContentActionUi.OpenLeadingImageSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_leading_margin),
            action = TableOfContentActionUi.OpenLeadingMarginSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_line_background),
            action = TableOfContentActionUi.OpenLineBackgroundSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_line_height),
            action = TableOfContentActionUi.OpenLineHeightSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_quote),
            action = TableOfContentActionUi.OpenQuoteSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_tab_stop),
            action = TableOfContentActionUi.OpenTabStopSpanExample
        )
    )

    private fun createCharacterSpansSection() = listOf(
        TableOfContentItemUi.Header(headerText = stringCombine(stringResId = R.string.table_of_content_header_character_spans)),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_absolute_size),
            action = TableOfContentActionUi.OpenAbsoluteSizeSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_background_color),
            action = TableOfContentActionUi.OpenBackgroundColorSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_clickable),
            action = TableOfContentActionUi.OpenClickableSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_foreground_color),
            action = TableOfContentActionUi.OpenForegroundColorSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_image),
            action = TableOfContentActionUi.OpenImageSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_mask_filter),
            action = TableOfContentActionUi.OpenMaskFilterSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_relative_size),
            action = TableOfContentActionUi.OpenRelativeSizeSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_scale_x_span),
            action = TableOfContentActionUi.OpenScaleXSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_strikethrough_span),
            action = TableOfContentActionUi.OpenStrikethroughSpanExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_style_span),
            action = TableOfContentActionUi.OpenStyleExample
        ), TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_subscript_span),
            action = TableOfContentActionUi.OpenSubscriptExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_superscript_span),
            action = TableOfContentActionUi.OpenSuperscriptExample
        ), TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_text_appearance_span),
            action = TableOfContentActionUi.OpenTextAppearanceExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_typeface_span),
            action = TableOfContentActionUi.OpenTypefaceExample
        ),
        TableOfContentItemUi.Title(
            titleText = stringCombine(stringResId = R.string.table_of_content_title_underline_span),
            action = TableOfContentActionUi.OpenUnderlineExample
        )
    )
}
