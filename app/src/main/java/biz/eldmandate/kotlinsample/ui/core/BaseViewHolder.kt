package biz.eldmandate.kotlinsample.ui.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Vaghela Mithun R. on 14/10/20.
 * Smart Call Center Solution Pvt. Ltd.
 * mithun@smartadvancedtech.com
 */

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private lateinit var viewHolderClickListener: ViewHolderClickListener

    interface ViewHolderClickListener {
        fun onViewHolderViewClicked(view: View?, position: Int)
    }

    fun setViewClickListener(holderClickObserver: BaseViewHolder.ViewHolderClickListener) {
        this.viewHolderClickListener = holderClickObserver
    }

    override fun onClick(view: View?) {
        viewHolderClickListener.onViewHolderViewClicked(view, adapterPosition)
    }
}