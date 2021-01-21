package sankovskij.api.nasaapiapp.common

import ru.terrakok.cicerone.android.support.SupportAppScreen
import sankovskij.api.nasaapiapp.picture.PictureFragment

class Screens {
    class Picturescreen() : SupportAppScreen() {
        override fun getFragment() = PictureFragment.newInstance()
    }

}
