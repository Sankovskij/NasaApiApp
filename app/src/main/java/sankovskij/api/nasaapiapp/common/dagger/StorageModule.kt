package sankovskij.api.nasaapiapp.common.dagger

import android.app.Activity
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import sankovskij.api.nasaapiapp.App
import javax.inject.Singleton


@Module
class StorageModule() {
    @Singleton
    @Provides
    fun SharedPrefences(app: App): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences (app)
    }





}