package mx.com.ghg.interactors.character

import mx.com.ghg.api.CharacterService
import mx.com.ghg.models.Result as Characters
import mx.com.ghg.utils.Utils
import javax.inject.Inject

class GetCharacterByIdInteractor @Inject constructor(
  private val characterService: CharacterService
) {

  suspend fun characterById(param: Param): Result {

    return try {

      val result = characterService.getCharacterById(
        ts = Utils.timestamp,
        hash = Utils.hash,
        apikey = Utils.apiKey,
        characterId = param.heroId
      )

      Result.Success(result.data.results)
    } catch (e: Exception) {
      Result.Error(e)
    }
  }

  data class Param(
    val heroId: Int
  )

  sealed class Result {
    data class Success(val characters: List<Characters>): Result()
    data class Failed(val message: String): Result()
    data class Error(val e: Exception): Result()
  }
}