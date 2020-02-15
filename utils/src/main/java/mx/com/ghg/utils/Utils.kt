package mx.com.ghg.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Date

object Utils {

  private val _timestamp = Date().time

  private val _privateApiKey = "be070edba844cb30283e19675a83d046740d2bcd"

  val apiKey = "bef86d35edf51affefc6b04e2ccd4c7c"

  val timestamp = _timestamp.toString()

  val hash: String = md5(timestamp + _privateApiKey + apiKey)

  private fun md5(toEncrypt: String): String {
    return try {

      val digest = MessageDigest.getInstance("md5")
      digest.update(toEncrypt.toByteArray())
      val bytes = digest.digest()
      val sb = StringBuilder()

      for (i in bytes.indices) {
        sb.append(String.format("%02X", bytes[i]))
      }

      sb.toString().toLowerCase()
    } catch (e: Exception) {
      e.printStackTrace()
      ""
    }
  }
}