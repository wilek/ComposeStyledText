package com.example.myapplication.table.content.model

internal sealed interface TableOfContentActionUi {

    data object OpenTextCreatingExample : TableOfContentActionUi

    data object OpenAlignmentSpanExample : TableOfContentActionUi

    data object OpenBulletSpanExample : TableOfContentActionUi

    data object OpenLeadingImageSpanExample : TableOfContentActionUi

    data object OpenLeadingMarginSpanExample : TableOfContentActionUi

    data object OpenLineBackgroundSpanExample : TableOfContentActionUi

    data object OpenLineHeightSpanExample : TableOfContentActionUi

    data object OpenQuoteSpanExample : TableOfContentActionUi

    data object OpenTabStopSpanExample : TableOfContentActionUi

    data object OpenAbsoluteSizeSpanExample : TableOfContentActionUi

    data object OpenBackgroundColorSpanExample : TableOfContentActionUi

    data object OpenClickableSpanExample : TableOfContentActionUi

    data object OpenForegroundColorSpanExample : TableOfContentActionUi

    data object OpenImageSpanExample : TableOfContentActionUi

    data object OpenMaskFilterSpanExample : TableOfContentActionUi

    data object OpenRelativeSizeSpanExample : TableOfContentActionUi

    data object OpenScaleXSpanExample : TableOfContentActionUi

    data object OpenStrikethroughSpanExample : TableOfContentActionUi

    data object OpenStyleExample : TableOfContentActionUi

    data object OpenSubscriptExample : TableOfContentActionUi

    data object OpenSuperscriptExample : TableOfContentActionUi

    data object OpenTextAppearanceExample : TableOfContentActionUi

    data object OpenTypefaceExample : TableOfContentActionUi

    data object OpenUnderlineExample : TableOfContentActionUi
}
