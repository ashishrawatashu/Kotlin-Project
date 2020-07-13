package com.app.petitionatlas.mvp.signaturestatus.view

interface SignaturestatusView {
    fun onSignaturestatusSuccess(string:String?)
    fun onSignaturestatusFailure(error: String?)
    fun onSignaturestatusshowprogress()
    fun onSigntaurestatushideprogress()
}