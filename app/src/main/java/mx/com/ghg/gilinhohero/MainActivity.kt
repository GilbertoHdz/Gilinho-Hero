package mx.com.ghg.gilinhohero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import mx.com.ghg.heroes.list.HeroesListFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    // AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .add(android.R.id.content, HeroesListFragment.newInstance())
        .commitAllowingStateLoss()
    }
  }
}
