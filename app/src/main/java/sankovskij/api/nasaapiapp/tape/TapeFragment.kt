package sankovskij.api.nasaapiapp.tape

import android.content.SharedPreferences
import android.graphics.Picture
import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_picture.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_tape.*
import ru.terrakok.cicerone.Router
import sankovskij.api.nasaapiapp.App
import sankovskij.api.nasaapiapp.R
import sankovskij.api.nasaapiapp.picture.model.PictureOfTheDayData
import sankovskij.api.nasaapiapp.tape.model.TapeOfPicturesData
import javax.inject.Inject

class TapeFragment : Fragment() {

    companion object {
        fun newInstance() = TapeFragment()
    }

    lateinit var adapter: PicturesAdapter

    @Inject
    lateinit var router: Router

    private val viewModel: TapeViewModel by lazy {
        ViewModelProviders.of(this).get(TapeViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      //  App.instance.appComponent.inject(this)
        return View.inflate(context, R.layout.fragment_tape, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getData()
                .observe(viewLifecycleOwner, Observer<TapeOfPicturesData> {
                    when (it) {
                        is TapeOfPicturesData.Success -> {
                            adapter.pictures = it.serverResponseData
                        }
                        is TapeOfPicturesData.Loading -> {
                                //showLoading()
                            }
                        is TapeOfPicturesData.Error -> {
                        }
                    }
                })
        rv_pictures.layoutManager = GridLayoutManager(context, 2)
        adapter = PicturesAdapter{
        }
        rv_pictures.adapter = adapter

    }



}



