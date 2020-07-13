package com.app.petitionatlas.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.petitionatlas.R
import com.app.petitionatlas.SelectVoterParams
import com.app.petitionatlas.activities.Selectvoter_activity.Companion.selectVoterParams
import com.app.petitionatlas.adapters.SearchVoterRecycler
import com.app.petitionatlas.adapters.SelectVoterRecycler
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.mvp.search_voter_mvp.model.SearchVoterInteractorImpl
import com.app.petitionatlas.mvp.search_voter_mvp.presentor.SearchVoterPresentor
import com.app.petitionatlas.mvp.search_voter_mvp.presentor.SearchVoterPresentorImpl
import com.app.petitionatlas.mvp.search_voter_mvp.view.SearchVoterView
import com.app.petitionatlas.mvp.walksheetstreetmvp.model.StreetInteractorImpl
import com.app.petitionatlas.mvp.walksheetstreetmvp.presentor.StreetPresentor
import com.app.petitionatlas.mvp.walksheetstreetmvp.presentor.StreetPresentorImpl
import com.app.petitionatlas.params.SearchvoterParams
import com.app.petitionatlas.params.StreetParams
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse
import com.app.petitionatlas.sharedvalues.SharedValues
import kotlinx.android.synthetic.main.activity_search_voter.*
import kotlinx.android.synthetic.main.activity_selectvoter_activity.*
import kotlinx.android.synthetic.main.layout_walksheet.*

class SearchVoter : AppCompatActivity(), View.OnClickListener,SearchVoterView {
    companion object {
        var searchVoterParams: SelectVoterParams? = null
    }

    lateinit var context: Activity
    lateinit var sharedValues: SharedValues
    var searchvoterParams: SearchvoterParams? = null
    lateinit var searchVoterPresentor: SearchVoterPresentor
    var responselist: ArrayList<StreetResponse>? = null
    lateinit var linear: LinearLayoutManager
    lateinit var adapter: SearchVoterRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_voter)
        context = this@SearchVoter
        sharedValues = SharedValues(context)
        linear = LinearLayoutManager(context)
        tv_search.setOnClickListener(this)
        iv_searchvoterbackpress.setOnClickListener(this)
    }

    private fun setSearchVoterPresentor(searchvoterParams: SearchvoterParams) {
        searchVoterPresentor = SearchVoterPresentorImpl()
        searchVoterPresentor.attachview(this, SearchVoterInteractorImpl())
        if (SharedValues.MAINCAMPAIGNID != null && SharedValues.MAINCAMPAIGNID?.trim()?.length > 0) {
            searchVoterPresentor.searchvoter(
                sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID),
                searchvoterParams
            )
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_search -> {
                searchvoterParams = SearchvoterParams()
                if (et_firstname.text.toString().trim()!=null&&et_firstname.text.toString().trim().length>0) {
                    searchvoterParams?.firstname=et_firstname.text.toString().trim()
                }else{
                    searchvoterParams?.firstname = ""
                }
                if (et_lastname.text.toString().trim()!=null&&et_lastname.text.toString().trim().length>0) {
                    searchvoterParams?.lastname = et_lastname.text.toString().trim()
                }else{
                    searchvoterParams?.lastname = ""
                }
                if (et_housenumber.text.toString().trim()!=null&&et_housenumber.text.toString().trim().length>0) {
                    searchvoterParams?.housenumber = et_housenumber.text.toString().trim()
                }else{
                    searchvoterParams?.housenumber = ""
                }
                if (et_streetname.text.toString().trim()!=null&&et_streetname.text.toString().trim().length>0) {
                    searchvoterParams?.street = et_streetname.text.toString().trim()
                }else{
                    searchvoterParams?.street = ""
                }

                if (searchvoterParams?.firstname?.isEmpty()!! && searchvoterParams?.lastname?.isEmpty()!! && searchvoterParams?.housenumber?.isEmpty()!! && searchvoterParams?.street?.isEmpty()!!) {
                    Toast.makeText(context,"Please fill at least one field",Toast.LENGTH_SHORT).show()
                } else {
                    setSearchVoterPresentor(searchvoterParams!!)
                }
            }

            R.id.iv_searchvoterbackpress ->{
                finish()
            }
        }
    }

    override fun OnSearchvoterSuccess(searchvoterresponse: ArrayList<StreetResponse>?) {
        if (searchvoterresponse != null && searchvoterresponse.size > 0) {
            responselist = searchvoterresponse
            rv_searchvoters.visibility=View.VISIBLE
            tv_voternodatafound.visibility=View.GONE
            setAdapter(responselist)
//            startActivity(intent)
        } else {
            rv_searchvoters.visibility=View.GONE
            tv_voternodatafound.visibility=View.VISIBLE
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun OnSearchvoterFailure(error: String?) {
        Toast.makeText(context,error,Toast.LENGTH_SHORT).show()
    }

    override fun onSearchvotershowprogress() {
        if (GlobalVariabels.mProgressDialog == null) GlobalVariabels.showProgress(
            context,
            "Loading"
        )
    }

    override fun onSearchvoterhideprogress() {
        if (GlobalVariabels.mProgressDialog != null) GlobalVariabels.dismissProgress()
    }
    private fun setAdapter(responselist: ArrayList<StreetResponse>?) {
        adapter = SearchVoterRecycler(context, responselist)
        rv_searchvoters.layoutManager = linear
        rv_searchvoters.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        if (searchVoterParams != null) {
            if (responselist != null && responselist?.size!! > 0) {
                if (searchVoterParams?.adapterposition != null && searchVoterParams.toString().trim().length > 0) {
                    if (responselist!!.get(searchVoterParams!!.adapterposition).getSupport() != null) {
                        responselist!!.get(searchVoterParams!!.adapterposition).getSupport()
                            ?.setNotes(searchVoterParams!!.notes)
                        responselist!!.get(searchVoterParams!!.adapterposition).getSupport()
                            ?.setVoterSupportLevel(searchVoterParams!!.voterSupportLevel.toString())
                        responselist!!.get(searchVoterParams!!.adapterposition).getSupport()?.setSignatureStatus(searchVoterParams!!.signatureStatus.toString())
                        responselist!!.get(searchVoterParams!!.adapterposition).getSupport()?.setEmail(
                            searchVoterParams!!.email)
                        responselist!!.get(searchVoterParams!!.adapterposition).getSupport()?.setPhone(
                            searchVoterParams!!.phone)
                        responselist!!.get(searchVoterParams!!.adapterposition).getSupport()?.setSignature(
                            searchVoterParams!!.signatureimage
                            )
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}