package mx.com.ghg.interactors.utils

import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorUtils @Inject constructor() {

  fun error(e: Exception): ErrorType {
    return when (e) {
      is HttpException -> {
        val jsonString = e.response()?.errorBody()?.string() ?: "message not found"
        ErrorType.Failed(getMessageCode(jsonString))
      }
      else -> {
        ErrorType.Error(e)
      }
    }
  }

  private fun getMessageCode(jsonString: String): String {
    val jsonObject = JSONObject(jsonString)

    return try {
      return jsonObject.getString("message")
    } catch (e: Exception) {
      e.printStackTrace()
      "error_to_cast_json"
    }
  }

  sealed class ErrorType {
    data class Failed(val message: String): ErrorType()
    data class Error(val error: Exception): ErrorType()
  }
}