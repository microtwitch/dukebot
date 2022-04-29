package de.com.fdm.bot.api.formula1.data.constructor

import com.google.gson.annotations.SerializedName


data class ConstructorStandingsData(
    @SerializedName("MRData")
    val mRData: MRData
)

data class MRData(
    @SerializedName("StandingsTable")
    val standingsTable : StandingsTable
)

data class StandingsTable(
    @SerializedName("StandingsLists")
    val standingsLists : List<StandingsLists>
)

data class StandingsLists(
    @SerializedName("ConstructorStandings")
    val constructorStandings : List<ConstructorStandings>
)

data class ConstructorStandings(
    @SerializedName("position")
    val position : String,
    @SerializedName("points")
    val points : String,
    @SerializedName("Constructor")
    val constructor: Constructor,
)

data class Constructor(
    @SerializedName("name")
    val name : String,
)
