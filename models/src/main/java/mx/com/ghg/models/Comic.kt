package mx.com.ghg.models

data class Comic(
  val available: Int,
  val collectionURI: String,
  val returned: Int,
  val items: List<Item>
)