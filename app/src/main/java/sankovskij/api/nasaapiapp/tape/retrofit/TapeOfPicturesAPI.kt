package sankovskij.api.nasaapiapp.tape.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sankovskij.api.nasaapiapp.picture.model.PODServerResponseData

interface TapeOfPicturesAPI {

    @GET("planetary/apod")
    fun getTapeOfPictures(@Query("api_key") apiKey: String, @Query("count") count: Int = 10): Call<List<PODServerResponseData>>
}
