package com.app.petitionatlas.mvp.dashboard.presentor

import com.app.petitionatlas.mvp.dashboard.model.DashboardInteractorImpl
import com.app.petitionatlas.mvp.dashboard.view.DashboardView

interface DashboardPresentor {

    fun attachview(dashboardView: DashboardView?, dashboardInteractor: DashboardInteractorImpl?)
    fun detachview()
    fun dashboardid(id:String?)

}