package com.app.petitionatlas.response.loginreponse

class LoginResponse {

     var mainCampaignId: String? = null

     var walkSheetOptions: WalkSheetOptions? = null

     var mainCampaignName: String? = null

     var campaigns: List<Campaigns?>?=null

     var id: String? = null

     var isAdmin: String? = null

     var username: String? = null

    override fun toString(): String {
        return "ClassPojo [mainCampaignId = $mainCampaignId, walkSheetOptions = $walkSheetOptions, mainCampaignName = $mainCampaignName, campaigns = $campaigns, id = $id, isAdmin = $isAdmin, username = $username]"
    }
}