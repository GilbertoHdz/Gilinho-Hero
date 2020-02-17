package mx.com.ghg.herodetail.detail

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import mx.com.ghg.herodetail.R
import mx.com.ghg.herodetail.databinding.HeroDetailFragmentBinding
import javax.inject.Inject

class HeroDetailFragment : Fragment() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: HeroDetailViewModel
  private lateinit var binding: HeroDetailFragmentBinding

  private var _heroId: Int = -1

  companion object {
    private const val KEY_HERO_ID = "hero.detail.id.hero"

    fun newInstance(heroId: Int) = HeroDetailFragment().apply {
      arguments = bundleOf(KEY_HERO_ID to heroId)
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    AndroidSupportInjection.inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    with(arguments!!) {
      _heroId = getInt(KEY_HERO_ID, -1)
    }
    viewModel = provideViewModel()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = HeroDetailFragmentBinding.inflate(inflater, container, false)
    binding.viewModel = viewModel
    binding.lifecycleOwner = viewLifecycleOwner
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel.apply {
      if (heroId == -1) {
        heroId = _heroId
        getHeroById(_heroId)
      }
    }
  }

  private fun provideViewModel(): HeroDetailViewModel {
    return ViewModelProviders.of(this, viewModelFactory).get(HeroDetailViewModel::class.java)
  }
}
