package sankovskij.api.nasaapiapp.picture.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sankovskij.api.nasaapiapp.picture.model.PODServerResponseData

interface PictureOfTheDayAPI {

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PODServerResponseData>
}
