package mx.com.ghg.api

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

  @Provides
  @Singleton
  fun provideLoginService(retrofit: Retrofit): CharacterService {
    return retrofit.create(CharacterService::class.java)
  }
}
