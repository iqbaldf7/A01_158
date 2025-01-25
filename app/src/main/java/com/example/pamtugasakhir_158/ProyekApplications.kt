package com.example.pamtugasakhir_158

import android.app.Application
import com.example.pamtugasakhir_158.depedenciesinjection.AppContainer
import com.example.pamtugasakhir_158.depedenciesinjection.ProyekContainer


class ProyekApplications : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = ProyekContainer()
    }
}