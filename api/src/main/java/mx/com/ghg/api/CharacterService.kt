package mx.com.ghg.api

import mx.com.ghg.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

  /**
   * @Get List of heroes
   * @ts timestamp
   * @hash combinations of (ts + privateKey + publicKey) to MD5 encrypt
   * @apikey your project api key
   */
  @GET("public/characters")
  suspend fun getCharacters(
    @Query("ts") ts: String,
    @Query("hash") hash: String,
    @Query("apikey") apikey: String
  ): ApiResponse

  /**
   * @Get Hero detail by id
   * @ts timestamp
   * @hash combinations of (ts + privateKey + publicKey) to MD5 encrypt
   * @apikey your project api key
   */
  @GET("public/characters/{characterId}")
  suspend fun getCharacterById(
    @Path("characterId") characterId: Int,
    @Query("ts") ts: String,
    @Query("hash") hash: String,
    @Query("apikey") apikey: String
  ): ApiResponse
}