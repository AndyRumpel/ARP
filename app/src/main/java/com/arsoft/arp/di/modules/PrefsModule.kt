package com.arsoft.arp.di.modules

import android.content.Context
import com.arsoft.arp.helpers.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PrefsModule {

    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): Prefs {
        return Prefs(context = context)
    }

}