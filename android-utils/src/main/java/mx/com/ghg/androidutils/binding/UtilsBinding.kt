package mx.com.ghg.androidutils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import mx.com.ghg.androidutils.R

object UtilsBinding {
  @BindingAdapter("util:loadimage")
  @JvmStatic fun loadImage(view: ImageView, imageUrl: String?) {

    val requestOptions = RequestOptions().apply {
      placeholder(R.drawable.android_utils_no_image_available)
    }

    Glide.with(view.context)
      .asBitmap()
      .load(imageUrl)
      .apply(requestOptions)
      .into(view)

    view.clipToOutline = true
  }
}