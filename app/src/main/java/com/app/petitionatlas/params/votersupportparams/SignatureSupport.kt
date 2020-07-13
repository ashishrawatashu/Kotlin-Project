package com.app.petitionatlas.params.votersupportparams

class SignatureSupport {
    private var SignatureStatus: Int=0

    fun getSignatureStatus(): Int {
        return SignatureStatus
    }

    fun setSignatureStatus(SignatureStatus: Int) {
        this.SignatureStatus = SignatureStatus
    }

    override fun toString(): String {
        return "ClassPojo [SignatureStatus = $SignatureStatus]"
    }
}