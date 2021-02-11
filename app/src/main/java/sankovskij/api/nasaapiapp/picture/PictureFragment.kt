package sankovskij.api.nasaapiapp.picture

import android.content.Intent
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
import ru.terrakok.cicerone.Router
import sankovskij.api.nasaapiapp.App
import sankovskij.api.nasaapiapp.R
import sankovskij.api.nasaapiapp.picture.model.PictureOfTheDayData
import javax.inject.Inject

class PictureFragment : Fragment() {

    companion object {
        fun newInstance() = PictureFragment()
    }

    @Inject
    lateinit var router: Router

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfTheDayViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.instance.appComponent.inject(this)
        return View.inflate(context, R.layout.fragment_picture, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()
                .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })

        textField.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
                    toast("Link is empty")
                } else {
                    //showSuccess()
                    if (serverResponseData.mediaType == "video") {
                        item_web_view.isVisible = true
                        item_web_view.clearCache(true)
                        item_web_view.clearHistory()
                        item_web_view.settings.javaScriptEnabled = true
                        item_web_view.settings.javaScriptCanOpenWindowsAutomatically = true
                        item_web_view.loadUrl(url)
                    } else {
                        item_web_view.isVisible = false
                        item_image_view.load(url) {
                            lifecycle(this@PictureFragment)
                            error(R.drawable.ic_load_error_vector)
                            placeholder(R.drawable.ic_no_photo_vector)
                        }
                        text_of_the_day.text = serverResponseData.explanation
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
                //showLoading()
            }
            is PictureOfTheDayData.Error -> {
                //showError(data.error.message)
                toast(data.error.message)
            }
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

}



