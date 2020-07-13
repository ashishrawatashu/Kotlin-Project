package com.app.petitionatlas.response.loginreponse

class MainCampaign {
    private var mainCampaignId: String? = null;

    private var usesPrecinctWalkSheets: String? = null;

    private var usesMapWalkSheets: String? = null;

    private var name: String? = null;

    private lateinit var subCampaigns: List<SubCampaigns?>;

    private var id: String? = null;

    private var petitionSheetLineCount: String? = null;

    private var petitionSheets: String? = null;

    override fun toString(): String {
        return "ClassPojo [mainCampaignId = $mainCampaignId, usesPrecinctWalkSheets = $usesPrecinctWalkSheets, usesMapWalkSheets = $usesMapWalkSheets, name =$name, subCampaigns = $subCampaigns, id =$id, petitionSheetLineCount = $petitionSheetLineCount, petitionSheets = $petitionSheets]";
    }

}