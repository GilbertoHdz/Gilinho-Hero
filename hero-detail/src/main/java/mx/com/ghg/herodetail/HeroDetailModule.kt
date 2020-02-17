package mx.com.ghg.herodetail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import mx.com.ghg.androidutils.di.ViewModelKey
import mx.com.ghg.herodetail.detail.HeroDetailFragment
import mx.com.ghg.herodetail.detail.HeroDetailViewModel

@Module
abstract class HeroDetailModule {

  @ContributesAndroidInjector
  internal abstract fun contributeHeroDetailFragment(): HeroDetailFragment

  @Binds
  @IntoMap
  @ViewModelKey(HeroDetailViewModel::class)
  internal abstract fun bindHeroDetailViewModel(viewModel: HeroDetailViewModel): ViewModel
}