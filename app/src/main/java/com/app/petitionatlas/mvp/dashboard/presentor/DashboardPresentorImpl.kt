package com.app.petitionatlas.mvp.dashboard.presentor

import com.app.petitionatlas.mvp.dashboard.model.DashboardInteractor
import com.app.petitionatlas.mvp.dashboard.model.DashboardInteractorImpl
import com.app.petitionatlas.mvp.dashboard.view.DashboardView
import com.app.petitionatlas.mvp.login_mvp.model.LoginInteractorImpl
import com.app.petitionatlas.mvp.login_mvp.view.LoginView
import com.app.petitionatlas.response.dashboard.DashboardResponse

class DashboardPresentorImpl:DashboardPresentor,DashboardInteractor.OnFinishListener {

    var dashboardView: DashboardView? = null
    var dashboardInteractorImpl: DashboardInteractorImpl? = null

    override fun attachview(
        dashboardView: DashboardView?,
        dashboardInteractor: DashboardInteractorImpl?
    ) {
        this.dashboardView = dashboardView
        this.dashboardInteractorImpl = dashboardInteractor
    }

    override fun detachview() {
       this.dashboardView=null
        this.dashboardInteractorImpl=null
    }

    override fun dashboardid(id: String?) {
        dashboardView?.showprogress()
        dashboardInteractorImpl?.dashboard(id, this)
    }

    override fun OndashboardSuccess(dashboard: DashboardResponse?) {
        dashboardView?.hideprogress()
        dashboardView?.OnSuccess(dashboard)
    }

    override fun OndashboardFailure(error: String?) {
        dashboardView?.hideprogress()
        dashboardView?.OnFailure(error)
    }
}