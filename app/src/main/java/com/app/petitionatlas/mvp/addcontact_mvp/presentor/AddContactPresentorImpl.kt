package com.app.petitionatlas.mvp.addcontact_mvp.presentor

import com.app.petitionatlas.mvp.addcontact_mvp.model.AddContactInteractor
import com.app.petitionatlas.mvp.addcontact_mvp.model.AddContactInteractorImpl
import com.app.petitionatlas.mvp.addcontact_mvp.view.AddContactView
import com.app.petitionatlas.params.AddContactParams

class AddContactPresentorImpl:AddContactPresentor,AddContactInteractor.OnFinishListener{
    var addContactInteractorImpl: AddContactInteractorImpl?=null
    var addContactView: AddContactView?=null

    override fun attachview(
        addContactView: AddContactView?,
        addContactInteractorImpl: AddContactInteractorImpl
    ) {
        this.addContactView = addContactView
        this.addContactInteractorImpl = addContactInteractorImpl
    }

    override fun dettachview() {
        this.addContactView = null
        this.addContactInteractorImpl = null
    }

    override fun addcontact(voterid: String?, addContactParams: AddContactParams?) {
        this.addContactView?.onAddContactshowprogress()
        this.addContactInteractorImpl?.addcontact(voterid,addContactParams,this)
    }

    override fun onAddContactSuccess(string: String?) {
        this.addContactView?.onAddContacthideprogress()
        this.addContactView?.onAddContactSuccess(string)
    }

    override fun onAddContactFailure(error: String?) {
        this.addContactView?.onAddContacthideprogress()
        this.addContactView?.onAddContactFailure(error)
    }
}