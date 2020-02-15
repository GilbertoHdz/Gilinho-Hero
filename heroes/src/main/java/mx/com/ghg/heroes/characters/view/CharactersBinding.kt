package mx.com.ghg.heroes.characters.view

import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.com.ghg.heroes.characters.HeroesListUiModel.HeroesResult

object CharactersBinding {

  @BindingAdapter("app:items")
  @JvmStatic fun setItems(
    recyclerView: RecyclerView,
    result: HeroesResult?
  ) {
    with(recyclerView.adapter as CharactersAdapter) {
      if (result is HeroesResult.Success) {
        updateData(result.heroes)
      }
    }
  }

  @BindingAdapter("app:recyclerVisibilityState")
  @JvmStatic fun recyclerVisibilityState(recycler: RecyclerView, result: HeroesResult?) {
    recycler.isVisible = result is HeroesResult.Success
  }

  @BindingAdapter("app:showProgress")
  @JvmStatic fun showProgress(progress: ProgressBar, result: HeroesResult?) {
    progress.isVisible = result is HeroesResult.IsLoading
  }

  @BindingAdapter("app:showRetryState")
  @JvmStatic fun showRetryState(frameLayout: FrameLayout, result: HeroesResult?) {
    frameLayout.isVisible = result?.let {
      it is HeroesResult.Error || it is HeroesResult.Failed
    } ?: true
  }
}