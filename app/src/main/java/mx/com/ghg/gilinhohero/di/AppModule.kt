package mx.com.ghg.gilinhohero.di

import android.app.Application
import dagger.Module
import mx.com.ghg.api.ApiModule
import mx.com.ghg.heroes.HeroesModule

@Module(
  includes = [
    NetModule::class,
    ApiModule::class,
    ViewModelModule::class,
    HeroesModule::class
  ]
)
class AppModule(private val application: Application) {

}