package sankovskij.api.nasaapiapp.common

import ru.terrakok.cicerone.android.support.SupportAppScreen
import sankovskij.api.nasaapiapp.picture.PictureFragment
import sankovskij.api.nasaapiapp.settings.SettingsFragment
import sankovskij.api.nasaapiapp.tape.TapeFragment

class Screens {
    class Picturescreen() : SupportAppScreen() {
        override fun getFragment() = PictureFragment.newInstance()
    }
    class Settingsscreen() : SupportAppScreen() {
        override fun getFragment() = SettingsFragment.newInstance()
    }
    class Tapescreen() : SupportAppScreen() {
        override fun getFragment() = TapeFragment.newInstance()
    }

}
