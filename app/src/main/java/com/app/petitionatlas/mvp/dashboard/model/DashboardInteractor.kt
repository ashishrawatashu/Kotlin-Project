package com.app.petitionatlas.mvp.dashboard.model

import com.app.petitionatlas.params.LoginParams
import com.app.petitionatlas.response.dashboard.DashboardResponse
import com.app.petitionatlas.response.loginreponse.LoginResponse

interface DashboardInteractor {

    fun dashboard(id: String?, onFinishListener: OnFinishListener?)

    interface OnFinishListener {
        fun OndashboardSuccess(dashboard: DashboardResponse?)
        fun OndashboardFailure(error: String?)
    }

}