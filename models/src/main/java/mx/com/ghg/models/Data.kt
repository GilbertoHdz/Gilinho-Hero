package mx.com.ghg.models

data class Data(
  val offset: Int,
  val limit: Int,
  val total: Int,
  val count: Int,
  val results: List<Result>
)