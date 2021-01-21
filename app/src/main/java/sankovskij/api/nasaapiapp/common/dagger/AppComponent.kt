package sankovskij.test.ramtest.common.dagger

import dagger.Component
import sankovskij.api.nasaapiapp.common.dagger.CiceroneModule
import sankovskij.api.nasaapiapp.main.MainActivity
import sankovskij.api.nasaapiapp.picture.PictureFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class
    ]
)

interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(pictureFragment: PictureFragment)


}
