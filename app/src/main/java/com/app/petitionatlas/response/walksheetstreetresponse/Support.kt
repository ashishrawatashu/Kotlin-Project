package com.app.petitionatlas.response.walksheetstreetresponse

import android.os.Parcel
import android.os.Parcelable

class Support():Parcelable {
    private var notes: String? = null
    private var email: String? = null
    private var phone: String? = null
    private var signature: String? = null

    private var voterSupportLevel: String? = null
    private var signatureStatus: String? = null

//    private var signatureSupport: SignatureSupport? = null

    constructor(parcel: Parcel) : this() {
        notes = parcel.readString()
        email = parcel.readString()
        phone = parcel.readString()
        signature = parcel.readString()
        signatureStatus = parcel.readString()
        voterSupportLevel = parcel.readString()
//        signatureSupport = parcel.readParcelable(SignatureSupport::class.java.classLoader)
    }

    fun getNotes(): String? {
        return notes
    }

    fun setNotes(notes: String?) {
        this.notes = notes
    }
    fun getSignature(): String? {
        return signature
    }

    fun setSignature(signature: String?) {
        this.signature = signature
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getVoterSupportLevel(): String? {
        return voterSupportLevel
    }

    fun setVoterSupportLevel(voterSupportLevel: String?) {
        this.voterSupportLevel = voterSupportLevel
    }

//    fun getSignatureSupport(): SignatureSupport? {
//        return signatureSupport
//    }

    fun setSignatureStatus(signatureStatus: String?) {
        this.signatureStatus = signatureStatus
    }

    fun getSignatureStatus(): String? {
        return signatureStatus
    }

//    fun setSignatureSupport(signatureSupport: SignatureSupport?) {
//        this.signatureSupport = signatureSupport
//    }

    override fun toString(): String {
        return "ClassPojo [notes = $notes, voterSupportLevel = $voterSupportLevel, email = $email, phone = $phone, signature = $signature, signatureStatus = $signatureStatus]"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(notes)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(voterSupportLevel)
//        parcel.writeParcelable(signatureSupport, flags)
        parcel.writeString(signature)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Support> {
        override fun createFromParcel(parcel: Parcel): Support {
            return Support(parcel)
        }

        override fun newArray(size: Int): Array<Support?> {
            return arrayOfNulls(size)
        }
    }
}