package com.app.petitionatlas.response.dashboard

class DashboardResponse {

    private var collectedSignaturesCount: String? = null

    private var voterCount: String? = null

    private var targetedSignaturesCount: String? = null

    fun getCollectedSignaturesCount(): String? {
        return collectedSignaturesCount
    }

    fun setCollectedSignaturesCount(collectedSignaturesCount: String?) {
        this.collectedSignaturesCount = collectedSignaturesCount
    }

    fun getVoterCount(): String? {
        return voterCount
    }

    fun setVoterCount(voterCount: String?) {
        this.voterCount = voterCount
    }

    fun getTargetedSignaturesCount(): String? {
        return targetedSignaturesCount
    }

    fun setTargetedSignaturesCount(targetedSignaturesCount: String?) {
        this.targetedSignaturesCount = targetedSignaturesCount
    }

    override fun toString(): String {
        return "ClassPojo [collectedSignaturesCount = $collectedSignaturesCount, voterCount = $voterCount, targetedSignaturesCount = $targetedSignaturesCount]"
    }

}