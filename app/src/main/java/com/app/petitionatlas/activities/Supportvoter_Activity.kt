package com.app.petitionatlas.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.petitionatlas.R
import com.app.petitionatlas.SelectVoterParams
import com.app.petitionatlas.activities.SearchVoter.Companion.searchVoterParams
import com.app.petitionatlas.activities.Selectvoter_activity.Companion.selectVoterParams
import com.app.petitionatlas.globalvariables.GlobalVariabels
import com.app.petitionatlas.mvp.supportvoter_mvp.model.SupportVoterInteractorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.presentor.SupportVoterPresentor
import com.app.petitionatlas.mvp.supportvoter_mvp.presentor.SupportVoterPresentorImpl
import com.app.petitionatlas.mvp.supportvoter_mvp.view.SupportVoterView
import com.app.petitionatlas.params.votersupportparams.SignatureSupport
import com.app.petitionatlas.params.votersupportparams.VoterSupportParams
import com.github.gcacace.signaturepad.views.SignaturePad
import kotlinx.android.synthetic.main.layout_supportvoter.*
import java.io.ByteArrayOutputStream


class Supportvoter_Activity : AppCompatActivity(), SupportVoterView {

    lateinit var context: Activity
    val signaturestatuslist = listOf(
        "Not Attempted",
        "Not At Home",
        "Inaccessible",
        "At Home No Signature",
        "Signature Collected"
    )
    var signatureimage: String? = null
    lateinit var supportVoterPresentor: SupportVoterPresentor
    var adapterposition: Int = 0
    var key: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_supportvoter)

        context = this@Supportvoter_Activity

        setPresentor()

        var intent: Intent = getIntent()
        var fullname = intent.getStringExtra(GlobalVariabels.FULLNAME)
        var fulladdress = intent.getStringExtra(GlobalVariabels.FULLADDRESS)
        var signatureStatus = intent.getIntExtra(GlobalVariabels.SIGNATURE_STATUS, 0)
        var voterSupportLevel = intent.getIntExtra(GlobalVariabels.VOTER_SUPPORT_LEVEL, 0)
        var notes = intent.getStringExtra(GlobalVariabels.NOTES)
        var email = intent.getStringExtra(GlobalVariabels.EMAIL)
        var phone = intent.getStringExtra(GlobalVariabels.PHONE)
        var voterid = intent.getStringExtra(GlobalVariabels.VOTER_ID)
        var bytearray = intent.getByteArrayExtra(GlobalVariabels.SIGNATURE_IMAGE)
        key = intent.getStringExtra(GlobalVariabels.KEY)
        adapterposition = intent.getIntExtra(GlobalVariabels.ADAPTER_POSITION, 0)


//        val ratingBar = findViewById(R.id.rb_voterrating) as RatingBar
//        val stars = ratingBar.progressDrawable as LayerDrawable
//
//        stars.getDrawable(2).setColorFilter(
//            resources.getColor(R.color.colorPrimary),
//            PorterDuff.Mode.SRC_ATOP
//        )//progress
//        stars.getDrawable(1).setColorFilter(
//            resources.getColor(R.color.colorPrimary),
//            PorterDuff.Mode.SRC_ATOP
//        )//secondaryprogress
//        stars.getDrawable(0)
//            .setColorFilter(
//                resources.getColor(R.color.lightspinnercolor),
//                PorterDuff.Mode.SRC_ATOP
//            )//background
        iv_supportbackpress.setOnClickListener { view ->
            finish()
        }

        var signaturestatusspinner: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, signaturestatuslist)
        signaturestatusspinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        sp_signaturestatus.adapter = signaturestatusspinner

        if (fullname != null && fullname.trim().length > 0) {
            supportvoter_username.text = fullname
        }
        if (fulladdress != null && fulladdress.trim().length > 0) {
            supportvoter_address.text = fulladdress
        }
        if (signatureStatus != 0) {
            sp_signaturestatus.setSelection(signatureStatus)
        } else {
            sp_signaturestatus.setSelection(0)
        }
        if (voterSupportLevel != 0) {
            rb_voterrating.rating = voterSupportLevel.toFloat()
        } else {
            rb_voterrating.rating = 0F
        }
        if (notes != null && notes.trim().length > 0) {
            et_notecampaign.setText(notes.trim())
        }
        if (email != null && email.trim().length > 0) {
            et_email.setText(email.trim())
        }
        if (phone != null && phone.trim().length > 0) {
            et_mobilenumber.setText(phone.trim())
        }

        if (bytearray != null && bytearray.size > 0) {

            val bmp = BitmapFactory.decodeByteArray(bytearray, 0, bytearray.size)
            signature_pad.signatureBitmap = bmp
        }

        tv_clear.setOnClickListener { view ->
            signature_pad.clear()
        }

        signature_pad.setOnSignedListener(object : SignaturePad.OnSignedListener {
            override fun onStartSigning() { //Event triggered when the pad is touched
            }

            override fun onSigned() { //Event triggered when the pad is signed
                signatureimage = BitMapToString(signature_pad.signatureBitmap)
            }

            override fun onClear() { //Event triggered when the pad is cleared
                signatureimage = null
            }
        })

        tv_supportsave.setOnClickListener { view ->
            if (et_email.text.toString().trim() != null && et_email.text.toString()
                    .trim().length > 0
            ) {
                if (Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString().trim()).matches()) {
                    mobilenumbervalidation(voterid)
                } else {
                    et_email.requestFocus()
                    et_email.setError("Please enter valid email address")
                }
            } else {
                mobilenumbervalidation(voterid)
            }
        }
    }

    private fun mobilenumbervalidation(voterid: String?) {
        if (et_mobilenumber.text.toString().trim() != null && et_mobilenumber.text.toString()
                .trim().length > 0
        ) {
            if (et_mobilenumber.text.toString().trim().length == 10) {

                supportVoterPresentor.supportvoter(
                    voterid, getparams(
                        sp_signaturestatus.selectedItemPosition,
                        rb_voterrating.rating.toInt(),
                        et_notecampaign.text.toString().trim(),
                        et_email.text.toString().trim(),
                        et_mobilenumber.text.toString().trim(),
                        signatureimage
                    )
                )
            } else {
                et_mobilenumber.requestFocus()
                et_mobilenumber.setError("Please enter 10 digit number")
            }
        } else {
            supportVoterPresentor.supportvoter(
                voterid, getparams(
                    sp_signaturestatus.selectedItemPosition,
                    rb_voterrating.rating.toInt(),
                    et_notecampaign.text.toString().trim(),
                    et_email.text.toString().trim(),
                    et_mobilenumber.text.toString().trim(),
                    signatureimage
                )
            )
        }
    }

    private fun setPresentor() {
        supportVoterPresentor = SupportVoterPresentorImpl()
        supportVoterPresentor.attachview(this, SupportVoterInteractorImpl())
    }

    private fun getparams(
        signatureStatus: Int,
        voterSupportLevel: Int,
        notes: String,
        email: String,
        phone: String,
        imagestring: String?
    ): VoterSupportParams {
        var voterSupportParams = VoterSupportParams()
//        var signatureSupport: SignatureSupport = SignatureSupport()
        voterSupportParams.setSignatureStatus(signatureStatus)
//        voterSupportParams.setSignatureSupport(signatureSupport)
        voterSupportParams.setVoterSupportLevel(voterSupportLevel)
        voterSupportParams.setNotes(notes)
        voterSupportParams.setPhone(phone)
        voterSupportParams.setEmail(email)
        voterSupportParams.setSignature(imagestring)
        return voterSupportParams
    }

    override fun onSupportVoterSuccess(string: String?) {
        if (key != null) {
            if (key.equals("2")) {
                Selectvoter_activity.selectVoterParams = SelectVoterParams()
                selectVoterParams!!.signatureStatus = sp_signaturestatus.selectedItemPosition
                selectVoterParams!!.voterSupportLevel = rb_voterrating.rating.toInt()
                selectVoterParams!!.notes = et_notecampaign.text.toString().trim()
                selectVoterParams!!.email = et_email.text.toString().trim()
                selectVoterParams!!.phone = et_mobilenumber.text.toString().trim()
                selectVoterParams!!.signatureimage = signatureimage
                selectVoterParams!!.adapterposition = adapterposition.toInt()
//        selectVoterParams!!.signatureimage = signatureimage
            } else if (key.equals("1")) {
                searchVoterParams = SelectVoterParams()
                searchVoterParams!!.signatureStatus = sp_signaturestatus.selectedItemPosition
                searchVoterParams!!.voterSupportLevel = rb_voterrating.rating.toInt()
                searchVoterParams!!.notes = et_notecampaign.text.toString().trim()
                searchVoterParams!!.email = et_email.text.toString().trim()
                searchVoterParams!!.phone = et_mobilenumber.text.toString().trim()
                searchVoterParams!!.signatureimage = signatureimage
                searchVoterParams!!.adapterposition = adapterposition.toInt()
            }

            Toast.makeText(
                context, string, Toast.LENGTH_SHORT
            ).show()
            finish()

        }
    }

    override fun onSupportVoterFailure(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportVotershowprogress() {
        if (GlobalVariabels.mProgressDialog == null) GlobalVariabels.showProgress(
            context,
            "Processing"
        )
    }

    override fun onSupportVoterhideprogress() {
        if (GlobalVariabels.mProgressDialog != null) GlobalVariabels.dismissProgress()
    }

    fun BitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}
