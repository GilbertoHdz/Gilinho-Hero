package mx.com.ghg.heroes.characters

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import mx.com.ghg.herodetail.detail.HeroDetailFragment
import mx.com.ghg.heroes.characters.HeroesListUiModel.*
import mx.com.ghg.heroes.characters.view.CharactersAdapter
import mx.com.ghg.heroes.databinding.HeroesListCharactersFragmentBinding
import javax.inject.Inject

class HeroesListFragment : Fragment() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: HeroesListViewModel
  private lateinit var binding: HeroesListCharactersFragmentBinding

  companion object {
    fun newInstance() = HeroesListFragment()
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    AndroidSupportInjection.inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = provideViewModel().apply {
      val fragment = this@HeroesListFragment
      heroItemAction.observe(fragment, heroItemActionObserver)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = HeroesListCharactersFragmentBinding.inflate(inflater, container, false)
    binding.viewModel = viewModel
    binding.lifecycleOwner = viewLifecycleOwner
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // viewModel.getListHeroes()
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    configureRecyclerView()
  }

  private val heroItemActionObserver = Observer<HeroDetail?> { hero ->
    hero?.let {
      openHeroDescriptionScreen(hero)
    }
  }

  private fun openHeroDescriptionScreen(hero: HeroDetail) {
    requireActivity().supportFragmentManager
      .beginTransaction()
      .add(android.R.id.content, HeroDetailFragment.newInstance(hero.id), null)
      .addToBackStack(null)
      .commitAllowingStateLoss()
  }

  private fun configureRecyclerView() {
    binding.heroesListCharactersRecycler.adapter = CharactersAdapter(viewModel)
  }

  private fun provideViewModel(): HeroesListViewModel {
    return ViewModelProviders.of(this, viewModelFactory).get(HeroesListViewModel::class.java)
  }
}
