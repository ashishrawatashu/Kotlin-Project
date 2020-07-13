package com.app.petitionatlas.retrofit

import com.app.petitionatlas.params.AddContactParams
import com.app.petitionatlas.params.LoginParams
import com.app.petitionatlas.params.SignaturestatusParams
import com.app.petitionatlas.params.votersupportparams.VoterSupportParams
import com.app.petitionatlas.response.loginreponse.LoginResponse
import com.app.petitionatlas.response.dashboard.DashboardResponse
import com.app.petitionatlas.response.walksheetresponse.WalksheetResponse
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    @POST("users/authentication")
    fun loginResponseCall(
        @Header("Content-Type") contenttype: String?,
        @Body loginParams: LoginParams?
    ): Call<LoginResponse>

    @GET()
    fun dashboard(@Url url: String?): Call<DashboardResponse>

    @GET()
    fun walksheet(@Url url: String?): Call<WalksheetResponse>

    @GET()
    fun street(
        @Url url: String?, @Query("county") county: String,
        @Query("city") city: String,
        @Query("street") street: String,
        @Query("ward") ward: String,
        @Query("precinct") precinct: String,
        @Query("startblock") startblock: Int,
        @Query("endblock") endblock: Int
    ): Call<ArrayList<StreetResponse>>

    @POST()
    fun supportvoter(@Url url: String?, @Body votersupportparams: VoterSupportParams?): Call<Void>

    @POST()
    fun signaturestatus(
        @Url url: String?,
        @Body signaturestatusParams: SignaturestatusParams?
    ): Call<Void>

    @GET()
    fun searchvoter(
        @Url url: String?, @Query("firstName") firstName: String,
        @Query("lastName") lastName: String,
        @Query("streetName") streetName: String,
        @Query("streetNumber") streetNumber:String
    ):Call<ArrayList<StreetResponse>>

    @POST()
    fun addcontact(
        @Url url: String?,
        @Body addContactParams: AddContactParams?
    ): Call<Void>

}