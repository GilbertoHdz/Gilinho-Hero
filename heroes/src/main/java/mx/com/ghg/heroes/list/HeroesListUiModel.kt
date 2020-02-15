package mx.com.ghg.heroes.list

class HeroesListUiModel {

  data class HeroDetail(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String
  )

  sealed class HeroesResult {
    data class Success(val heroes: List<HeroDetail>) : HeroesResult()
    data class Failed(val message: String) : HeroesResult()
    data class Error(val message: String) : HeroesResult()
  }
}
