package sankovskij.api.nasaapiapp.tape

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.fragment_picture.*
import kotlinx.android.synthetic.main.item_picture.view.*
import sankovskij.api.nasaapiapp.R
import sankovskij.api.nasaapiapp.picture.model.PODServerResponseData

class PicturesAdapter (val onItemClick: ((PODServerResponseData) -> Unit)? = null) : RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {
    var pictures: List<PODServerResponseData> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(pictures[position])

    override fun getItemCount() = pictures.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(picture: PODServerResponseData) = with(itemView as CardView) {
            if (picture.mediaType == "video") {
                item_web_view.isVisible = true
                item_web_view.clearCache(true)
                item_web_view.clearHistory()
                item_web_view.settings.javaScriptEnabled = true
                item_web_view.settings.javaScriptCanOpenWindowsAutomatically = true
                picture.url?.let { item_web_view.loadUrl(it) }
            } else {
                item_web_view.isVisible = false
                item_image_view.load(picture.url) {
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.ic_no_photo_vector)
                }
            }
            itemView.setOnClickListener {
            }
        }
    }
}

