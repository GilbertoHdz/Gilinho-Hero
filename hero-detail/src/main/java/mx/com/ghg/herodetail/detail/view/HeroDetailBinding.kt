package mx.com.ghg.herodetail.detail.view

import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import mx.com.ghg.herodetail.detail.HeroDetailUiModel.Comic
import mx.com.ghg.herodetail.detail.HeroDetailUiModel.HeroesResult

object HeroDetailBinding {

  @BindingAdapter("app:comics")
  @JvmStatic fun recyclerVisibilityState(textView: TextView, result: List<Comic>?) {
    if (result is List<Comic>) {
      for (comic in result) {
        textView.append(" - ${comic.name} \n")
      }
    }
  }

  @BindingAdapter("app:showUiContent")
  @JvmStatic fun showUiContent(group: Group, result: HeroesResult?) {
    group.isVisible = result is HeroesResult.Success
  }

  @BindingAdapter("app:showProgress")
  @JvmStatic fun showProgress(progress: ProgressBar, result: HeroesResult?) {
    progress.isVisible = result is HeroesResult.IsLoading
  }

  @BindingAdapter("app:showRetryState")
  @JvmStatic fun showRetryState(frameLayout: FrameLayout, result: HeroesResult?) {
    frameLayout.isVisible = result?.let {
      it is HeroesResult.Error || it is HeroesResult.Failed
    } ?: false
  }
}