package com.app.petitionatlas.response.walksheetstreetresponse

import android.os.Parcel
import android.os.Parcelable

class SignatureSupport() :Parcelable {

    private var signatureStatus: String?=null

    constructor(parcel: Parcel) : this() {
        signatureStatus = parcel.readString()
    }

    fun getSignatureStatus(): String? {
        return signatureStatus
    }

    fun setSignatureStatus(signatureStatus: String?) {
        this.signatureStatus = signatureStatus
    }

    override fun toString(): String {
        return "ClassPojo [signatureStatus = $signatureStatus]"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(signatureStatus)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SignatureSupport> {
        override fun createFromParcel(parcel: Parcel): SignatureSupport {
            return SignatureSupport(parcel)
        }

        override fun newArray(size: Int): Array<SignatureSupport?> {
            return arrayOfNulls(size)
        }
    }

}