package sankovskij.api.nasaapiapp.settings

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import kotlinx.android.synthetic.main.fragment_picture.*
import kotlinx.android.synthetic.main.fragment_settings.*
import ru.terrakok.cicerone.Router
import sankovskij.api.nasaapiapp.App
import sankovskij.api.nasaapiapp.R
import sankovskij.api.nasaapiapp.picture.model.PictureOfTheDayData
import javax.inject.Inject

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val viewModel: SettingsViewModel by lazy {
        ViewModelProviders.of(this).get(SettingsViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.instance.appComponent.inject(this)
        return View.inflate(context, R.layout.fragment_settings, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radio_button_theme_1.setOnCheckedChangeListener { button, b ->
            val editor = sharedPreferences.edit()
            editor.putString("THEME", "default")
            editor.apply()
            activity?.recreate()
        }
        radio_button_theme_2.setOnCheckedChangeListener { button, b ->
            val editor = sharedPreferences.edit()
            editor.putString("THEME", "moon")
            editor.apply()
            activity?.recreate()
        }
    }




    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

}



