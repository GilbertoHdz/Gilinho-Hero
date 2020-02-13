package mx.com.ghg.models

data class ApiResponse(
  val code: Int,
  val status: String,
  val etag: String,
  val data: Data
)