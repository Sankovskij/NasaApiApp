package sankovskij.test.ramtest.common.dagger

import dagger.Component
import sankovskij.api.nasaapiapp.common.dagger.CiceroneModule
import sankovskij.api.nasaapiapp.common.dagger.StorageModule
import sankovskij.api.nasaapiapp.main.MainActivity
import sankovskij.api.nasaapiapp.picture.PictureFragment
import sankovskij.api.nasaapiapp.settings.SettingsFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        StorageModule::class
    ]
)

interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(pictureFragment: PictureFragment)
    fun inject(settingsFragment: SettingsFragment)


}
