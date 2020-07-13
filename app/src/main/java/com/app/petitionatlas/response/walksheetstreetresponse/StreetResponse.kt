package com.app.petitionatlas.response.walksheetstreetresponse

import android.os.Parcel
import android.os.Parcelable

class StreetResponse():Parcelable {

    private var cityStateZip: String? = null

    private var registrationId: String? = null

    private var fullAddress: String? = null

    private var fullName: String? = null

    private var id: String? = null

    private var support: Support? = null

    constructor(parcel: Parcel) : this() {
        cityStateZip = parcel.readString()
        registrationId = parcel.readString()
        fullAddress = parcel.readString()
        fullName = parcel.readString()
        id = parcel.readString()
        support = parcel.readParcelable(Support::class.java.classLoader)
    }

    fun getCityStateZip(): String? {
        return cityStateZip
    }

    fun setCityStateZip(cityStateZip: String?) {
        this.cityStateZip = cityStateZip
    }

    fun getRegistrationId(): String? {
        return registrationId
    }

    fun setRegistrationId(registrationId: String?) {
        this.registrationId = registrationId
    }

    fun getFullAddress(): String? {
        return fullAddress
    }

    fun setFullAddress(fullAddress: String?) {
        this.fullAddress = fullAddress
    }

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String?) {
        this.fullName = fullName
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getSupport(): Support? {
        return support
    }

    fun setSupport(support: Support?) {
        this.support = support
    }

    override fun toString(): String {
        return "ClassPojo [cityStateZip = $cityStateZip, registrationId = $registrationId, fullAddress = $fullAddress, fullName = $fullName, id = $id, support = $support]"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cityStateZip)
        parcel.writeString(registrationId)
        parcel.writeString(fullAddress)
        parcel.writeString(fullName)
        parcel.writeString(id)
        parcel.writeParcelable(support, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StreetResponse> {
        override fun createFromParcel(parcel: Parcel): StreetResponse {
            return StreetResponse(parcel)
        }

        override fun newArray(size: Int): Array<StreetResponse?> {
            return arrayOfNulls(size)
        }
    }


}