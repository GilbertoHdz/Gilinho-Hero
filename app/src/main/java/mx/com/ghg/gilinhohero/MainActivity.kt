package mx.com.ghg.gilinhohero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.com.ghg.heroes.list.HeroesListFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .add(android.R.id.content, HeroesListFragment.newInstance())
        .commitAllowingStateLoss()
    }
  }
}
