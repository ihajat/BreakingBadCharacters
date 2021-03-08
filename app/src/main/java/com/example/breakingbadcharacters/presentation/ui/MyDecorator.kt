package com.example.breakingbadcharacters.presentation.ui

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class MyDecorator(private val padding: Int):RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.top = padding
    }
}