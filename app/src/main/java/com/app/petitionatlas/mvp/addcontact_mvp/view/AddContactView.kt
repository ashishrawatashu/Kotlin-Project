package com.app.petitionatlas.mvp.addcontact_mvp.view

interface AddContactView {
    fun onAddContactSuccess(string:String?)
    fun onAddContactFailure(error: String?)
    fun onAddContactshowprogress()
    fun onAddContacthideprogress()
}
