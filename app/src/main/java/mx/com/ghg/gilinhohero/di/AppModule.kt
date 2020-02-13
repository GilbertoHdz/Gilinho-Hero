package mx.com.ghg.gilinhohero.di

import android.app.Application
import dagger.Module
import mx.com.ghg.api.ApiModule

@Module(
  includes = [
    NetModule::class,
    ApiModule::class
  ]
)
class AppModule(private val application: Application) {

}