package mx.com.ghg.gilinhohero.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import mx.com.ghg.gilinhohero.HeroApp
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidInjectionModule::class,
    AppModule::class
  ]
)
interface AppComponent: AndroidInjector<HeroApp> { }