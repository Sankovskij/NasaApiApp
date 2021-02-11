package sankovskij.api.nasaapiapp.tape

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sankovskij.api.nasaapiapp.BuildConfig
import sankovskij.api.nasaapiapp.picture.model.PODServerResponseData
import sankovskij.api.nasaapiapp.picture.model.PictureOfTheDayData
import sankovskij.api.nasaapiapp.tape.model.TapeOfPicturesData
import sankovskij.api.nasaapiapp.tape.retrofit.TapeRetrofitImpl

class TapeViewModel(
        private val liveDataForViewToObserve: MutableLiveData<TapeOfPicturesData> = MutableLiveData(),
        private val retrofitImpl: TapeRetrofitImpl = TapeRetrofitImpl()
) : ViewModel() {

    fun getData(): LiveData<TapeOfPicturesData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = TapeOfPicturesData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getTapeOfPictures(apiKey).enqueue(object : Callback<List<PODServerResponseData>> {

                override fun onResponse(call: Call<List<PODServerResponseData>>,
                                        response: Response<List<PODServerResponseData>>) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                                TapeOfPicturesData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                    TapeOfPicturesData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                    TapeOfPicturesData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<List<PODServerResponseData>>,
                                       t: Throwable) {
                    liveDataForViewToObserve.value = TapeOfPicturesData.Error(t)
                }
            })
        }
    }
}


