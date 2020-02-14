package mx.com.ghg.heroes

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import mx.com.ghg.androidutils.di.ViewModelKey
import mx.com.ghg.heroes.list.HeroesListFragment
import mx.com.ghg.heroes.list.HeroesListViewModel

@Module
abstract class HeroesModule {

  @ContributesAndroidInjector
  internal abstract fun contributeHeroesListFragment(): HeroesListFragment

  @Binds
  @IntoMap
  @ViewModelKey(HeroesListViewModel::class)
  internal abstract fun bindHeroesListViewModel(heroesListViewModel: HeroesListViewModel): ViewModel
}