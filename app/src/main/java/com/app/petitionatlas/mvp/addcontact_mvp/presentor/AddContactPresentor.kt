package com.app.petitionatlas.mvp.addcontact_mvp.presentor

import com.app.petitionatlas.mvp.addcontact_mvp.model.AddContactInteractorImpl
import com.app.petitionatlas.mvp.addcontact_mvp.view.AddContactView
import com.app.petitionatlas.mvp.signaturestatus.view.SignaturestatusView
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SignatureStatusInteractorImpl
import com.app.petitionatlas.params.AddContactParams
import com.app.petitionatlas.params.SignaturestatusParams

interface AddContactPresentor {
    fun attachview(addContactView: AddContactView?, addContactInteractorImpl: AddContactInteractorImpl)
    fun dettachview()
    fun addcontact(maincampaignid:String?,addContactParams: AddContactParams?)
}