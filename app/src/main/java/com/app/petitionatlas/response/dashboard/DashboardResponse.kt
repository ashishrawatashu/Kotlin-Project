package com.app.petitionatlas.response.dashboard

class DashboardResponse {

    private var collectedSignaturesCount: String? = null

    private var registeredVotersCount: String? = null

    private var targetedSignaturesCount: String? = null

    fun getCollectedSignaturesCount(): String? {
        return collectedSignaturesCount
    }

    fun setCollectedSignaturesCount(collectedSignaturesCount: String?) {
        this.collectedSignaturesCount = collectedSignaturesCount
    }

    fun getVoterCount(): String? {
        return registeredVotersCount
    }

    fun setVoterCount(voterCount: String?) {
        this.registeredVotersCount = voterCount
    }

    fun getTargetedSignaturesCount(): String? {
        return targetedSignaturesCount
    }

    fun setTargetedSignaturesCount(targetedSignaturesCount: String?) {
        this.targetedSignaturesCount = targetedSignaturesCount
    }

    override fun toString(): String {
        return "ClassPojo [collectedSignaturesCount = $collectedSignaturesCount, registeredVotersCount = $registeredVotersCount, targetedSignaturesCount = $targetedSignaturesCount]"
    }

}