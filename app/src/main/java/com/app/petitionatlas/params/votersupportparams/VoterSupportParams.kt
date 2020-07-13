package com.app.petitionatlas.params.votersupportparams

class VoterSupportParams {
     private var VoterSupportLevel: Int=0

//     private var SignatureSupport: SignatureSupport? = null
private var SignatureStatus: Int=0
     private var Notes: String? = null
     private var Email: String? = null
     private var Phone: String? = null
     private var Signature: String? = null

     fun getVoterSupportLevel(): Int {
          return VoterSupportLevel
     }

     fun setVoterSupportLevel(VoterSupportLevel: Int) {
          this.VoterSupportLevel = VoterSupportLevel
     }
     fun getEmail(): String? {
          return Email
     }

     fun setEmail(email: String) {
          this.Email = email
     }
     fun getPhone(): String? {
          return Phone
     }

     fun setPhone(phone: String) {
          this.Phone = phone
     }

//     fun getSignatureSupport(): SignatureSupport? {
//          return SignatureSupport
//     }
//
//     fun setSignatureSupport(SignatureSupport: SignatureSupport?) {
//          this.SignatureSupport = SignatureSupport
//     }

     fun getNotes(): String? {
          return Notes
     }

     fun setNotes(Notes: String?) {
          this.Notes = Notes
     }

     fun getSignatureStatus(): Int {
          return SignatureStatus
     }

     fun setSignatureStatus(SignatureStatus: Int) {
          this.SignatureStatus = SignatureStatus
     }

     fun getsignature(): String? {
          return Signature
     }

     fun setSignature(Signature: String?) {
          this.Signature = Signature
     }

     override fun toString(): String {
          return "ClassPojo [VoterSupportLevel = $VoterSupportLevel, SignatureStatus = $SignatureStatus, Notes = $Notes, Email = $Email, Phone = $Phone, Signature = $Signature]"
     }
}