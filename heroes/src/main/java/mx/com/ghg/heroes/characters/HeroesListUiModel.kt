package mx.com.ghg.heroes.characters

class HeroesListUiModel {

  data class HeroDetail(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String
  )

  sealed class HeroesResult {
    object IsLoading : HeroesResult()
    data class Success(val heroes: List<HeroDetail>) : HeroesResult()
    data class Failed(val message: String) : HeroesResult()
    data class Error(val error: Exception) : HeroesResult()
  }
}
