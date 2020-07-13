package com.app.petitionatlas.mvp.addcontact_mvp.model

import com.app.petitionatlas.params.AddContactParams


interface AddContactInteractor {

    fun addcontact(id: String?,addContactParams: AddContactParams?, onFinishListener: OnFinishListener?)

    interface OnFinishListener {
        fun onAddContactSuccess(string:String?)
        fun onAddContactFailure(error: String?)
    }

}