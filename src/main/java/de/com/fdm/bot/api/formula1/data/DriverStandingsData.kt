package de.com.fdm.bot.api.formula1.data

import com.google.gson.annotations.SerializedName


data class DriverStandingsData(
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
    @SerializedName("DriverStandings")
    val driverStandings : List<DriverStandings>
)

data class DriverStandings(
    @SerializedName("position")
    val position : String,
    @SerializedName("points")
    val points : String,
    @SerializedName("Driver")
    val driver : Driver,
    @SerializedName("Constructors")
    val constructor : List<Constructors>
)

data class Driver(
    @SerializedName("givenName")
    val givenName : String,
    @SerializedName("familyName")
    val familyName : String
)

data class Constructors(
    @SerializedName("naem")
    val name : String
)
