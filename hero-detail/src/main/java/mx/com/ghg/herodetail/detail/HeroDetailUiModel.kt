package mx.com.ghg.herodetail.detail

class HeroDetailUiModel {

  data class HeroDetail(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val comics: List<Comic>
  )

  sealed class HeroesResult {
    object IsLoading : HeroesResult()
    object Success : HeroesResult()
    data class Failed(val message: String) : HeroesResult()
    data class Error(val error: Exception) : HeroesResult()
  }

  data class Comic(
    val name: String
  )
}
