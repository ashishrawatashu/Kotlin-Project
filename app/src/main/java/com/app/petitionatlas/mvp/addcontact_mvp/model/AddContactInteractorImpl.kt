package com.app.petitionatlas.mvp.addcontact_mvp.model

import com.app.petitionatlas.params.AddContactParams
import com.app.petitionatlas.retrofit.WebServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddContactInteractorImpl : AddContactInteractor {
    override fun addcontact(id: String?,addContactParams: AddContactParams?, onFinishListener: AddContactInteractor.OnFinishListener?) {
        val signaturestatuscall: Call<Void> =
            WebServiceFactory.instance!!.addcontact("contacts/new/"+id,addContactParams)
        signaturestatuscall.enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                onFinishListener?.onAddContactFailure("Error Occurred")
            }
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response != null) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            onFinishListener?.onAddContactSuccess("Contact Added Successfully")
                        } else {
                            onFinishListener?.onAddContactFailure("Some Thing Went Wrong")
                        }
                    } else {
                        if (response.code() == 404) {
                            onFinishListener?.onAddContactFailure("Wrong Voter Id")
                        } else if (response.code() == 500) {
                            onFinishListener?.onAddContactFailure("Internal Server Error")
                        }
                    }
                } else {
                    onFinishListener?.onAddContactFailure("Some Thing Went Wrong")
                }
            }
        })
    }
}