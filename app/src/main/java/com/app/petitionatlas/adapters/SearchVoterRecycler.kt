package com.app.petitionatlas.adapters

import android.app.Activity
import android.content.Intent
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.petitionatlas.R
import com.app.petitionatlas.activities.Supportvoter_Activity
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.mvp.signaturestatus.view.SignaturestatusView
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SignatureStatusInteractorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.presentor.SignatureStatusPresentorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.presentor.SignaturestatusPresentor
import com.app.petitionatlas.params.SignaturestatusParams
import com.app.petitionatlas.response.walksheetstreetresponse.StreetResponse
import kotlinx.android.synthetic.main.layout_adapter_selectvoters.view.*

class SearchVoterRecycler(
    var context: Activity,
    var responselist: ArrayList<StreetResponse>? = null
) : RecyclerView.Adapter<SearchVoterRecycler.MyViewHolder>(), SignaturestatusView {

    lateinit var signaturestatusPresentor: SignaturestatusPresentor
    var adapterposition: Int = 0
    var signaturestatus: Int = 0

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_in = view.tv_in
        var tv_nh = view.tv_nh
        var selectvoter_username = view.selectvoter_username
        var tv_selectorvoteraddress = view.tv_selectorvoteraddress
        var cl_userselectorinfo = view.cl_userselectorinfo
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_adapter_searchvoter, parent, false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return responselist!!.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (responselist != null) {
            if (responselist!!.get(position).getFullName() != null && responselist!!.get(position)
                    .getFullName()?.trim()!!.length > 0
            ) {
                holder.selectvoter_username.text =
                    responselist!!.get(position).getFullName()?.trim()
            }
            if (responselist!!.get(position)
                    .getFullAddress() != null && responselist!!.get(position).getFullAddress()
                    ?.trim()!!.length > 0
            ) {
                holder.tv_selectorvoteraddress.text =
                    responselist!!.get(position).getFullAddress()?.trim()
            }
            if (responselist!!.get(position).getSupport() != null) {
                if (responselist!!.get(position).getSupport()!!.getSignatureStatus() != null
                ) {
                    Log.d("DATALOG","check1=> "+responselist!!.get(position).getSupport()!!.getSignatureStatus())
                    if (responselist!!.get(position).getSupport()!!.getSignatureStatus()!!.equals(
                            GlobalVariabels.NH_CODE
                        )
                    ) {
                        holder.tv_nh.setBackgroundResource(R.drawable.red_background_button)
                        holder.tv_in.setBackgroundResource(R.drawable.backgroundbutton)
                    } else if (responselist!!.get(position).getSupport()!!.getSignatureStatus()!!
                            .equals(
                                GlobalVariabels.IN_CODE
                            )
                    ) {
                        holder.tv_in.setBackgroundResource(R.drawable.red_background_button)
                        holder.tv_nh.setBackgroundResource(R.drawable.backgroundbutton)
                    } else {
                        holder.tv_in.setBackgroundResource(R.drawable.backgroundbutton)
                        holder.tv_nh.setBackgroundResource(R.drawable.backgroundbutton)
                    }

                    if (!responselist!!.get(position).getSupport()?.getSignatureStatus()!!.equals("0")
                    ) {
                        holder.cl_userselectorinfo.setBackgroundResource(R.drawable.golden_background_button)
                    } else {
                        holder.cl_userselectorinfo.setBackgroundResource(R.drawable.backgroundbutton)
//                }
                    }
                }
            }

            holder.tv_in.setOnClickListener { view ->
                if (!(responselist!!.get(position).getSupport()?.getSignatureStatus()).equals(
                        GlobalVariabels.IN_CODE
                    )
                ) {
                    holder.tv_in.setBackgroundResource(R.drawable.red_background_button)
                    holder.tv_nh.setBackgroundResource(R.drawable.backgroundbutton)
                    var voterid = responselist!!.get(position).getId()
                    if (voterid != null) {
                        adapterposition = position
                        setpresentor(voterid!!, GlobalVariabels.IN_CODE)
                    }
                }
            }

            holder.tv_nh.setOnClickListener { view ->
                if (!(responselist!!.get(position).getSupport()?.getSignatureStatus()).equals(
                        GlobalVariabels.NH_CODE
                    )
                ) {
                    holder.tv_nh.setBackgroundResource(R.drawable.red_background_button)
                    holder.tv_in.setBackgroundResource(R.drawable.backgroundbutton)
                    var voterid = responselist!!.get(position).getId()
                    if (voterid != null) {
                        adapterposition = position
                        setpresentor(voterid!!, GlobalVariabels.NH_CODE)
                    }
                }
            }

            holder.cl_userselectorinfo.setOnClickListener { view ->
                var intent = Intent(context, Supportvoter_Activity::class.java)
                intent.putExtra(
                    GlobalVariabels.FULLNAME,
                    responselist!!.get(position).getFullName()?.trim()
                )
                intent.putExtra(
                    GlobalVariabels.FULLADDRESS,
                    responselist!!.get(position).getFullAddress()?.trim()
                )
                intent.putExtra(
                    GlobalVariabels.SIGNATURE_STATUS,
                    responselist!!.get(position).getSupport()?.getSignatureStatus()?.toInt()
                )
                intent.putExtra(
                    GlobalVariabels.VOTER_SUPPORT_LEVEL,
                    responselist!!.get(position).getSupport()?.getVoterSupportLevel()?.toInt()
                )
                intent.putExtra(
                    GlobalVariabels.NOTES,
                    responselist!!.get(position).getSupport()?.getNotes()?.trim()
                )
                intent.putExtra(
                    GlobalVariabels.EMAIL,
                    responselist!!.get(position).getSupport()?.getEmail()?.trim()
                )
                intent.putExtra(
                    GlobalVariabels.PHONE,
                    responselist!!.get(position).getSupport()?.getPhone()?.trim()
                )
                intent.putExtra(
                    GlobalVariabels.VOTER_ID,
                    responselist!!.get(position).getId()?.trim()
                )

                val imgBytes: ByteArray = Base64.decode(
                    responselist!!.get(position).getSupport()?.getSignature()?.trim(),
                    Base64.DEFAULT
                )
                intent.putExtra(
                    GlobalVariabels.SIGNATURE_IMAGE,
                    imgBytes
                )

                intent.putExtra(GlobalVariabels.ADAPTER_POSITION, position)
                intent.putExtra(GlobalVariabels.KEY, "1")
                context.startActivity(intent)
            }
        }
    }

    private fun setpresentor(voterid: String, code: String) {
//        signaturestatus = code.toInt()
        signaturestatusPresentor = SignatureStatusPresentorImpl()
        signaturestatusPresentor.attachview(this, SignatureStatusInteractorImpl())
        var signaturestatusParams = SignaturestatusParams()
        signaturestatusParams.SignatureStatus = code.toInt()
        signaturestatus = signaturestatusParams.SignatureStatus
        signaturestatusPresentor.signaturestatus(voterid, signaturestatusParams)
    }

    override fun onSignaturestatusSuccess(string: String?) {
        Log.d("DATALOGSSS", "success")
        if (responselist != null && responselist?.size!! > 0) {
            if (responselist!!.get(adapterposition).getSupport() != null
            ) {
                if (responselist!!.get(adapterposition).getSupport()
                        ?.getSignatureStatus() != null
                ) {
                    Log.d("DATALOG","check2=> "+signaturestatus.toString())
                    responselist!!.get(adapterposition).getSupport()
                        ?.setSignatureStatus(signaturestatus.toString())
                    notifyDataSetChanged()
                }
            }
        }
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }

    override fun onSignaturestatusFailure(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onSignaturestatusshowprogress() {
        if (GlobalVariabels.mProgressDialog == null) GlobalVariabels.showProgress(
            context,
            "Processing"
        )
    }

    override fun onSigntaurestatushideprogress() {
        if (GlobalVariabels.mProgressDialog != null) GlobalVariabels.dismissProgress()
    }
}