package com.example.myapplication.table.content.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.table.content.model.TableOfContentItemUi
import eu.wilek.textcombine.renderer.TextCombineRenderer

internal class TableOfContentsAdapter(
    private val textCombineRenderer: TextCombineRenderer
) : RecyclerView.Adapter<TableOfContentsAdapter.BaseViewHolder>() {

    private val items = mutableListOf<TableOfContentItemUi>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<TableOfContentItemUi>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        HEADER_VIEW_TYPE -> HeaderHolder(
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_content_header_item, parent, false)
        )

        TITLE_VIEW_TYPE -> TitleHolder(
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_content_title_item, parent, false)
        )

        else -> throw IllegalArgumentException("Unsupported view type")
    }

    override fun getItemViewType(position: Int) = when (items[position]) {
        is TableOfContentItemUi.Header -> HEADER_VIEW_TYPE
        is TableOfContentItemUi.Title -> TITLE_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bind(item: TableOfContentItemUi)
    }

    inner class HeaderHolder(itemView: View) : BaseViewHolder(itemView = itemView) {

        private val headerTextView: TextView = itemView as TextView

        override fun bind(item: TableOfContentItemUi) {
            item as TableOfContentItemUi.Header
            headerTextView.text = textCombineRenderer.render(textCombine = item.headerText)
        }
    }

    inner class TitleHolder(itemView: View) : BaseViewHolder(itemView = itemView) {

        private val titleTextView: TextView = itemView as TextView

        override fun bind(item: TableOfContentItemUi) {
            item as TableOfContentItemUi.Title
            titleTextView.text = textCombineRenderer.render(textCombine = item.titleText)
        }
    }

    private companion object {
        const val HEADER_VIEW_TYPE = 0
        const val TITLE_VIEW_TYPE = 1
    }
}
