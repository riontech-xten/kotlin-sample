package biz.eldmandate.kotlinsample.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
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
        fun bindImage(view: ImageView, url: String) {
            if (url != null) {
                Glide.with(view.context)
                    .load(url)
                    .into(view)
            }
        }
    }
}