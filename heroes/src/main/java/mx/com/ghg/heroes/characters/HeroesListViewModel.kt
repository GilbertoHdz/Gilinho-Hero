package mx.com.ghg.heroes.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mx.com.ghg.androidutils.SingleLiveEvent
import mx.com.ghg.heroes.characters.HeroesListUiModel.*
import mx.com.ghg.interactors.character.GetCharactersInteractor
import mx.com.ghg.interactors.character.GetCharactersInteractor.Result as GetCharacterResult
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(
  private val getCharactersInteractor: GetCharactersInteractor
) : ViewModel() {

  val heroesResult: MutableLiveData<HeroesResult?> = MutableLiveData()
  val heroItemAction: SingleLiveEvent<HeroDetail?> = SingleLiveEvent()

  fun getListHeroes() {
    CoroutineScope(viewModelScope.coroutineContext).launch {
      val result = getCharactersInteractor.characters()
      heroesResult.postValue(transformCharactersResult(result))
    }
  }

  private fun transformCharactersResult(
    result: GetCharacterResult
  ): HeroesResult {
    return when (result) {
      is GetCharacterResult.Success -> {
        val map = result.characters.map {
          HeroDetail(
            id = it.id,
            name = it.name,
            description = it.description,
            thumbnail = "${it.thumbnail.path}.${it.thumbnail.extension}"
          )
        }

        HeroesResult.Success(heroes = map)
      }
      is GetCharacterResult.Failed -> {
        HeroesResult.Failed("")
      }
      is GetCharacterResult.Error -> {
        HeroesResult.Error("")
      }
    }
  }

  fun retryGetHeroes() {
    heroesResult.postValue(HeroesResult.IsLoading)
    getListHeroes()
  }

  fun heroItemClicked(hero: HeroDetail) {
    heroItemAction.postValue(hero)
  }
}
