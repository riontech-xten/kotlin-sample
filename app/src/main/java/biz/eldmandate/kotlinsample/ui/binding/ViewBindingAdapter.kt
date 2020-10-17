package biz.eldmandate.kotlinsample.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import biz.eldmandate.kotlinsample.R
import com.bumptech.glide.Glide


/**
 * Created by Vaghela Mithun R. on 15/10/20.
 * Smart Call Center Solution Pvt. Ltd.
 * mithun@smartadvancedtech.com
 */

class ViewBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("imgUrl")
        fun bindImage(view: ImageView, url: String?) {
            Glide.with(view.context)
                .load(url)
                .placeholder(R.drawable.default_image)
                .into(view)
        }
    }
}