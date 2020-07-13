package com.app.petitionatlas.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.petitionatlas.R
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.mvp.walksheet_mvp.model.WalksheetInteractorImpl
import com.app.petitionatlas.mvp.walksheet_mvp.presentor.WalksheetPresentor
import com.app.petitionatlas.mvp.walksheet_mvp.presentor.WalksheetPresentorImpl
import com.app.petitionatlas.mvp.walksheet_mvp.view.WalksheetView
import com.app.petitionatlas.mvp.walksheetstreetmvp.model.StreetInteractorImpl
import com.app.petitionatlas.mvp.walksheetstreetmvp.presentor.StreetPresentor
import com.app.petitionatlas.mvp.walksheetstreetmvp.presentor.StreetPresentorImpl
import com.app.petitionatlas.mvp.walksheetstreetmvp.view.StreetView
import com.app.petitionatlas.params.StreetParams
import com.app.petitionatlas.response.walksheetresponse.WalksheetResponse
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse
import com.app.petitionatlas.sharedvalues.SharedValues
import kotlinx.android.synthetic.main.layout_walksheet.*
import java.util.*
import kotlin.collections.ArrayList

class WalksheetActivity : AppCompatActivity(), WalksheetView, StreetView {


    lateinit var context: Activity
    var streetParams: StreetParams? = null
    lateinit var walksheetPresentor: WalksheetPresentor
    lateinit var streetPresentor: StreetPresentor
    lateinit var sharedValues: SharedValues
    var responselist: ArrayList<StreetResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_walksheet)
        context = this@WalksheetActivity
//        Utils.hideKeyboard(context)
        sharedValues = SharedValues(context)

        if (GlobalVariabels.isNetworkConnected(context)) {
            setPresentor()
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
        iv_backpress.setOnClickListener { View ->
            finish()
        }
        tv_gotoaddress.setOnClickListener { view ->
            selectvoter()
        }
    }

    private fun setPresentor() {
        walksheetPresentor = WalksheetPresentorImpl()
        walksheetPresentor.attachview(this, WalksheetInteractorImpl())
        if (sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID) != null && sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID)?.trim()?.length!! > 0) {
            walksheetPresentor.walksheet(sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID))
        }
    }

    private fun setSreetPresentor(streetParams: StreetParams) {
        streetPresentor = StreetPresentorImpl()
        streetPresentor.attachview(this, StreetInteractorImpl())
        if (sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID) != null && sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID)?.trim()?.length!! > 0) {
            streetPresentor.street(
                sharedValues.getpreferance(SharedValues.MAINCAMPAIGNID),
                streetParams
            )
        }
    }

    private fun selectvoter() {

        if (et_county.selectedItemPosition != 0) {
            if (et_city.selectedItemPosition != 0) {
                if (et_street.text.toString() != null && et_street.text.toString().trim().length > 0) {
                    streetParams = StreetParams()
                    streetParams?.county = et_county.selectedItem.toString()
                    streetParams?.city = et_city.selectedItem.toString()
                    streetParams?.street = et_street.text.toString().trim()
                    if (et_ward.selectedItemPosition != 0) {
                        streetParams?.ward = et_ward.selectedItem.toString()
                    } else {
                        streetParams?.ward = ""
                    }
                    if (et_pct.selectedItemPosition != 0) {
                        streetParams?.precinct = et_pct.selectedItem.toString()
                    } else {
                        streetParams?.precinct = ""
                    }

                    if (et_startblock.text.toString() != null && et_startblock.text.toString().trim().length > 0) {
                        streetParams?.startBlock = et_startblock.text.toString().trim().toInt()
                    } else {
                        streetParams?.startBlock = 0
                    }
                    if (et_endblock.text.toString() != null && et_endblock.text.toString().trim().length > 0) {
                        streetParams?.endBlock = et_endblock.text.toString().trim().toInt()
                    } else {
                        streetParams?.endBlock = 0
                    }

                    if (GlobalVariabels.isNetworkConnected(context)) {
                        setSreetPresentor(streetParams!!)
                    } else {
                        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Please Fill Street", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please Select City", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please Select County", Toast.LENGTH_SHORT).show()
        }
    }

    override fun OnSuccess(walksheetResponse: WalksheetResponse?) {
        if (walksheetResponse != null) {
            if (walksheetResponse.getCountyOptions() != null && walksheetResponse.getCountyOptions().size > 0) {
                for (name in walksheetResponse.getCountyOptions()) {

                    var countyname = ArrayList<String>()
                    var cityname = ArrayList<String?>()
                    var ward = ArrayList<String?>()
                    var pct = ArrayList<String?>()
                    var startblcok = ArrayList<String?>()
                    var endblock = ArrayList<String?>()
                    var street = ArrayList<String?>()
                    countyname.add(GlobalVariabels.COUNTY)
                    cityname.add(GlobalVariabels.CITY)
                    ward.add(GlobalVariabels.WARD)
                    pct.add(GlobalVariabels.PCT)
                    startblcok.add(GlobalVariabels.STARTBLOCK)
                    endblock.add(GlobalVariabels.ENDBLOCK)
//                    street.add(GlobalVariabels.STREET)
                    if (name?.getName() != null && name.getName()?.trim()!!.length > 0
                    ) {
                        countyname.add(name.getName())
                    }
                    var countyspinner: ArrayAdapter<String> =
                        ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countyname)
                    countyspinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                    et_county.adapter = countyspinner

                    var cityspinner: ArrayAdapter<String?> =
                        ArrayAdapter<String?>(
                            context,
                            android.R.layout.simple_spinner_item,
                            cityname
                        )
                    cityspinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                    et_city.adapter = cityspinner

                    var wardspinner: ArrayAdapter<String?> =
                        ArrayAdapter<String?>(context, android.R.layout.simple_spinner_item, ward)
                    wardspinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                    et_ward.adapter = wardspinner

                    var pctspinner: ArrayAdapter<String?> =
                        ArrayAdapter<String?>(context, android.R.layout.simple_spinner_item, pct)
                    pctspinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                    et_pct.adapter = pctspinner
                    et_county.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {

                        }

                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long
                        ) {
                            if (p2 == 0) {
                                cityname.clear()
                                cityname.add(GlobalVariabels.CITY)
                                et_city.setSelection(0)
                                ward.clear()
                                ward.add(GlobalVariabels.WARD)
                                et_ward.setSelection(0)
                                pct.clear()
                                pct.add(GlobalVariabels.PCT)
                                et_pct.setSelection(0)
                            } else {
                                for (name1 in name?.getCityOptions()!!) {
                                    cityname.clear()
                                    cityname.add(GlobalVariabels.CITY)
                                    cityname.add(name1?.getName())

                                    et_city.onItemSelectedListener =
                                        object : AdapterView.OnItemSelectedListener {
                                            override fun onNothingSelected(p0: AdapterView<*>?) {
                                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                            }

                                            override fun onItemSelected(
                                                p0: AdapterView<*>?,
                                                p1: View?,
                                                p2: Int,
                                                p3: Long
                                            ) {
                                                if (p2 == 0) {
                                                    ward.clear()
                                                    ward.add(GlobalVariabels.WARD)
                                                    et_ward.setSelection(0)
                                                    pct.clear()
                                                    pct.add(GlobalVariabels.PCT)
                                                    et_pct.setSelection(0)
                                                } else {
                                                    for (name2 in name1?.getWardOptions()!!) {
                                                        ward.clear()
                                                        ward.add(GlobalVariabels.WARD)
                                                        ward.add(name2?.getName())

                                                        et_ward.onItemSelectedListener = object :
                                                            AdapterView.OnItemSelectedListener {
                                                            override fun onNothingSelected(p0: AdapterView<*>?) {
                                                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                                            }

                                                            override fun onItemSelected(
                                                                p0: AdapterView<*>?,
                                                                p1: View?,
                                                                p2: Int,
                                                                p3: Long
                                                            ) {
                                                                if (p2 == 0) {
                                                                    pct.clear()
                                                                    pct.add(GlobalVariabels.PCT)
                                                                    et_pct.setSelection(0)
                                                                } else {
//                                                                    for (name3 in name2?.getPrecinctNames()!!) {
                                                                    pct.clear()
                                                                    pct.add(GlobalVariabels.PCT)
                                                                    var list = name2?.getPrecinctNames()!!
//                                                                    Log.d("DATALOG","check1223=> " + list.sortedWith(compareBy({ it })))
                                                                    pct.addAll(list.sortedWith(compareBy({ it })))

//                                                                    et_pct.onItemSelectedListener =
//                                                                        object :
//                                                                            AdapterView.OnItemSelectedListener {
//                                                                            override fun onNothingSelected(
//                                                                                p0: AdapterView<*>?
//                                                                            ) {
//                                                                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                                                                            }
//
//                                                                            override fun onItemSelected(
//                                                                                p0: AdapterView<*>?,
//                                                                                p1: View?,
//                                                                                p2: Int,
//                                                                                p3: Long
//                                                                            ) {
//                                                                                if (p2 == 0) {
////                                                                                        street.clear()
////                                                                                        street.add(
////                                                                                            GlobalVariabels.STREET
////                                                                                        )
////                                                                                        et_street.setSelection(
////                                                                                            0
////                                                                                        )
//                                                                                } else {
////                                                                                        for (name3 in name2?.getPrecinctNames()!!) {
////                                                                                        street.clear()
////                                                                                        street.add(
////                                                                                            GlobalVariabels.STREET
////                                                                                        )
////                                                                                        street.add(
////                                                                                                name3
////                                                                                            )
////                                                                                        }
//                                                                                }
//                                                                            }
//                                                                        }
//                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun OnFailure(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showprogress() {
        if (GlobalVariabels.mProgressDialog == null) GlobalVariabels.showProgress(
            context,
            "Loading"
        )
    }

    override fun hideprogress() {
        if (GlobalVariabels.mProgressDialog != null) GlobalVariabels.dismissProgress()
    }

    override fun OnStreetSuccess(streetResponse: ArrayList<StreetResponse>?) {
        if (streetResponse != null && streetResponse.size > 0) {
            responselist = streetResponse
            val intent = Intent(context, Selectvoter_activity::class.java)
            if (streetParams != null) {
                intent.putExtra(GlobalVariabels.STREETPARAMS, streetParams);
            }
//            if (responselist != null) {
//                intent.putParcelableArrayListExtra(GlobalVariabels.RESPONSELIST, responselist);
//                Log.d("DATALOGSSS", "DATA=> " + responselist?.get(1)?.getSupport()?.getNotes())
//            }
            startActivity(intent)
        } else {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
        }

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

//    override fun onResume() {
//        super.onResume()
//        if (GlobalVariabels.isNetworkConnected(context)) {
//            setPresentor()
//        } else {
//            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
//        }
//    }

}
