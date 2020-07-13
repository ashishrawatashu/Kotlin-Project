package com.app.petitionatlas.response.loginreponse

class Campaigns {

    private var mainCampaignId: String? = null;

    private var usesPrecinctWalkSheets: String? = null;

    private var usesMapWalkSheets: String? = null;

    private var name: String? = null;

    private var subCampaigns: List<SubCampaigns>?=null;

    private var id: String? = null;

    private var petitionSheetLineCount: String? = null;

    private var petitionSheets: String? = null;

    override fun toString(): String {
        return "ClassPojo [mainCampaignId = $mainCampaignId,petitionSheets = $petitionSheets̥, usesPrecinctWalkSheets = $usesPrecinctWalkSheets, usesMapWalkSheets = $usesMapWalkSheets, name = $name, subCampaigns =$subCampaigns, id =$id , petitionSheetLineCount =$petitionSheetLineCount, petitionSheets = $petitionSheets̥]";
    }
}