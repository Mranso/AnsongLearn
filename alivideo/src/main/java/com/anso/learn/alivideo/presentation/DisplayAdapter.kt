package com.anso.learn.alivideo.presentation

import com.anso.learn.alivideo.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * author：Yuantian
 * time：2023/3/27 16:35
 */
class DisplayAdapter : BaseQuickAdapter<DisplayInfoEntity, BaseViewHolder>(R.layout.display_item_info_layout) {
    override fun convert(holder: BaseViewHolder, item: DisplayInfoEntity) {
        holder.setText(R.id.display_info, item.displayInfo)
    }
}