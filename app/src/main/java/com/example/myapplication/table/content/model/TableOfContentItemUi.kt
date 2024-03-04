package com.example.myapplication.table.content.model

import eu.wilek.textcombine.TextCombine

internal sealed interface TableOfContentItemUi {

    data class Header(val headerText: TextCombine) : TableOfContentItemUi

    data class Title(val titleText: TextCombine, val action: TableOfContentActionUi) : TableOfContentItemUi
}
