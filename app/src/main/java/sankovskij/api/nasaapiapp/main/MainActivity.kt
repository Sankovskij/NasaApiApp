package sankovskij.api.nasaapiapp.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import sankovskij.api.nasaapiapp.App
import sankovskij.api.nasaapiapp.R
import sankovskij.api.nasaapiapp.common.Screens
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)


    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)

         if (sharedPreferences.getString("THEME", null) == "moon") {

             setTheme(R.style.Theme_NasaApiApp_Moon)
         }
            else { setTheme(R.style.Theme_NasaApiApp)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.replaceScreen(Screens.Picturescreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                router.navigateTo(Screens.Settingsscreen())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
