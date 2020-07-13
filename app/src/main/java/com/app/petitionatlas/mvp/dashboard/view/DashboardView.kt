package com.app.petitionatlas.mvp.dashboard.view

import com.app.petitionatlas.response.dashboard.DashboardResponse
import com.app.petitionatlas.response.loginreponse.LoginResponse

interface DashboardView {

    fun OnSuccess(dashboardResponse: DashboardResponse?)
    fun OnFailure(error: String?)
    fun showprogress()
    fun hideprogress()

}