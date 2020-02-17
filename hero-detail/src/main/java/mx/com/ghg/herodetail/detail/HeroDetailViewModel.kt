package mx.com.ghg.herodetail.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mx.com.ghg.herodetail.detail.HeroDetailUiModel.HeroDetail
import mx.com.ghg.herodetail.detail.HeroDetailUiModel.Comic
import mx.com.ghg.herodetail.detail.HeroDetailUiModel.HeroesResult
import mx.com.ghg.interactors.character.GetCharacterByIdInteractor
import mx.com.ghg.interactors.character.GetCharacterByIdInteractor.Result as GetByIdResult
import mx.com.ghg.interactors.character.GetCharacterByIdInteractor.Param as GetByIdParam
import javax.inject.Inject

class HeroDetailViewModel @Inject constructor(
  private val getCharacterByIdInteractor: GetCharacterByIdInteractor
) : ViewModel() {

  val heroDetailResult = MutableLiveData<HeroesResult>(HeroesResult.IsLoading)
  val heroDetail = MutableLiveData<HeroDetail?>()
  var heroId: Int = -1

  fun getHeroById(heroId: Int) {
    CoroutineScope(viewModelScope.coroutineContext).launch {
      val param = GetByIdParam(heroId)
      val result = getCharacterByIdInteractor.characterById(param)
      heroDetailResult.postValue(tranformHeroDetail(result))
    }
  }

  private fun tranformHeroDetail(result: GetByIdResult): HeroesResult {
    return when (result) {
      is GetByIdResult.Success -> {

        val hero = result.characters.first()
        val comics = hero.comics.items.map { comic -> Comic(comic.name) }
        val heroValue = HeroDetail(
          id = hero.id,
          name = hero.name,
          description = hero.description,
          thumbnail ="${hero.thumbnail.path}.${hero.thumbnail.extension}",
          comics = comics
        )

        heroDetail.postValue(heroValue)
        HeroesResult.Success
      }
      is GetByIdResult.Failed -> {
        HeroesResult.Failed(result.message)
      }
      is GetByIdResult.Error -> {
        HeroesResult.Error(result.e)
      }
    }
  }

  fun retryGetHero() {
    heroDetailResult.postValue(HeroesResult.IsLoading)
    getHeroById(heroId)
  }
}
