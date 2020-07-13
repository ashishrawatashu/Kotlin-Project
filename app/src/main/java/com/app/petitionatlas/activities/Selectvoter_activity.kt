package com.app.petitionatlas.activities

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.petitionatlas.R
import com.app.petitionatlas.SelectVoterParams
import com.app.petitionatlas.adapters.SelectVoterRecycler
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.mvp.walksheetstreetmvp.model.StreetInteractorImpl
import com.app.petitionatlas.mvp.walksheetstreetmvp.presentor.StreetPresentor
import com.app.petitionatlas.mvp.walksheetstreetmvp.presentor.StreetPresentorImpl
import com.app.petitionatlas.mvp.walksheetstreetmvp.view.StreetView
import com.app.petitionatlas.params.StreetParams
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse
import com.app.petitionatlas.sharedvalues.SharedValues
import kotlinx.android.synthetic.main.activity_selectvoter_activity.*


class Selectvoter_activity : AppCompatActivity(), StreetView {

    companion object {
        var selectVoterParams: SelectVoterParams? = null
    }

    var streetParams: StreetParams? = null
    lateinit var context: Activity
    lateinit var linear: LinearLayoutManager
    lateinit var adapter: SelectVoterRecycler
    var responselist: ArrayList<StreetResponse>? = null
    lateinit var streetPresentor: StreetPresentor
    lateinit var sharedValues: SharedValues

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectvoter_activity)
        context = this@Selectvoter_activity
        linear = LinearLayoutManager(context)
        sharedValues = SharedValues(context)

        var intent = getIntent()
//        responselist = intent.getParcelableArrayListExtra(GlobalVariabels.RESPONSELIST)
        streetParams = intent.getSerializableExtra(GlobalVariabels.STREETPARAMS) as StreetParams?

        if (GlobalVariabels.isNetworkConnected(context)) {
            if (streetParams != null) {
                setSreetPresentor(streetParams!!)
            }
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }

//        setAdapter(responselist)

        iv_selectback.setOnClickListener { view ->
            finish()
        }

        et_searchbar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                filter(p0.toString())
            }
        })

        swipe_refresh.setOnRefreshListener {
            responselist?.clear()
            adapter.notifyDataSetChanged()
            swipe_refresh.isRefreshing = false
            if (GlobalVariabels.isNetworkConnected(context)) {
                if (streetParams != null) {
                    setSreetPresentor(streetParams!!)
                }
            } else {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun filter(text: String) {
        var filteredlist: ArrayList<StreetResponse> = ArrayList<StreetResponse>()
        if (responselist != null && responselist!!.size > 0) {
            for (data in responselist!!) {
                if (data.getFullName() != null && data.getFullName()?.trim()?.length!! > 0) {
                    if (data.getFullName()!!.contains(text.toUpperCase())) {
                        tv_nodatafound.visibility = View.GONE
                        filteredlist.add(data)
                    }
                }
            }
        }
        if (filteredlist.size > 0) {
            tv_nodatafound.visibility = View.GONE
        } else {
            tv_nodatafound.visibility = View.VISIBLE
        }
        adapter.filterlist(filteredlist)
    }

    private fun setSreetPresentor(streetParams: StreetParams) {
        streetPresentor = StreetPresentorImpl()
        streetPresentor.attachview(this, StreetInteractorImpl())
        if (SharedValues.MAINCAMPAIGNID != null && SharedValues.MAINCAMPAIGNID?.trim()?.length > 0) {
            streetPresentor.street(
                sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID),
                streetParams
            )
        }
    }

    private fun setAdapter(responselist: ArrayList<StreetResponse>?) {
        this.responselist = responselist
        adapter = SelectVoterRecycler(context, responselist)
        rv_addresselectorvoters.layoutManager = linear
        rv_addresselectorvoters.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        if (selectVoterParams != null) {
            if (responselist != null && responselist?.size!! > 0) {
                if (selectVoterParams?.adapterposition != null && selectVoterParams.toString()
                        .trim().length > 0
                ) {
                    if (responselist!!.get(selectVoterParams!!.adapterposition)
                            .getSupport() != null
                    ) {
                        responselist!!.get(selectVoterParams!!.adapterposition).getSupport()
                            ?.setNotes(selectVoterParams!!.notes)
                        responselist!!.get(selectVoterParams!!.adapterposition).getSupport()
                            ?.setVoterSupportLevel(selectVoterParams!!.voterSupportLevel.toString())
                        responselist!!.get(selectVoterParams!!.adapterposition).getSupport()
                            ?.setSignatureStatus(selectVoterParams!!.signatureStatus.toString())
                        responselist!!.get(selectVoterParams!!.adapterposition).getSupport()
                            ?.setEmail(
                                selectVoterParams!!.email
                            )
                        responselist!!.get(selectVoterParams!!.adapterposition).getSupport()
                            ?.setPhone(
                                selectVoterParams!!.phone
                            )
                        responselist!!.get(selectVoterParams!!.adapterposition).getSupport()
                            ?.setSignature(
                                selectVoterParams!!.signatureimage
                            )
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun OnStreetSuccess(streetResponse: ArrayList<StreetResponse>?) {
        if (streetResponse!=null)
        setAdapter(streetResponse)
    }

    override fun OnStreetFailure(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onStreetshowprogress() {
        if (GlobalVariabels.mProgressDialog == null) GlobalVariabels.showProgress(
            context,
            "Loading"
        )
    }

    override fun onStreethideprogress() {
        if (GlobalVariabels.mProgressDialog != null) GlobalVariabels.dismissProgress()
    }
}
