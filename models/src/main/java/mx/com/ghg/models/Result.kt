package mx.com.ghg.models

data class Result(
  val id: Int,
  val name: String,
  val description: String,
  val resourceURI: String,
  val urls: List<Url>,
  val thumbnail: Thumbnail,
  val comics: Comic
)