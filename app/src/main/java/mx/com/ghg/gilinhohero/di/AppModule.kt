package mx.com.ghg.gilinhohero.di

import android.app.Application
import dagger.Module
import mx.com.ghg.api.ApiModule
import mx.com.ghg.herodetail.HeroDetailModule
import mx.com.ghg.heroes.HeroesModule

@Module(
  includes = [
    NetModule::class,
    ApiModule::class,
    ViewModelModule::class,
    HeroesModule::class,
    HeroDetailModule::class
  ]
)
class AppModule(private val application: Application) {

}