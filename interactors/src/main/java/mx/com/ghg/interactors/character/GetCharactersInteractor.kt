package mx.com.ghg.interactors.character

import mx.com.ghg.api.CharacterService
import mx.com.ghg.models.Result as Characters
import mx.com.ghg.utils.Utils
import javax.inject.Inject

open class GetCharactersInteractor @Inject constructor(
  private val characterService: CharacterService
) {

  suspend fun characters(): Result {

    return try {

      val result = characterService.getCharacters(
        ts = Utils.timestamp,
        hash = Utils.hash,
        apikey = Utils.apiKey
      )

      Result.Success(result.data.results)
    } catch (e: Exception) {
      e.printStackTrace()
      Result.Error(e)
    }
  }

  sealed class Result {
    data class Success(val characters: List<Characters>): Result()
    data class Failed(val message: String): Result()
    data class Error(val e: Exception): Result()
  }
}