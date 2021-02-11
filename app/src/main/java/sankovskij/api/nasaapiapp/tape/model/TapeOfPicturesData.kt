package sankovskij.api.nasaapiapp.tape.model

import sankovskij.api.nasaapiapp.picture.model.PODServerResponseData

sealed class TapeOfPicturesData {
    data class Success(val serverResponseData: List<PODServerResponseData>) : TapeOfPicturesData()
    data class Error(val error: Throwable) : TapeOfPicturesData()
    data class Loading(val progress: Int?) : TapeOfPicturesData()
}
