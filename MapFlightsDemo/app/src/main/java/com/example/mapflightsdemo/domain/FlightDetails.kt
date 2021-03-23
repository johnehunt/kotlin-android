package com.example.mapflightsdemo.domain

import com.google.gson.annotations.SerializedName

data class FlightDetails(@SerializedName("pagination") val pagination: Pagination,
                         @SerializedName("data")val data: List<FlightInformation>)

data class Pagination(@SerializedName("limit") val limit: Int,
                      @SerializedName("offset") val offset: Int,
                      @SerializedName("count") val count: Int,
                      @SerializedName("total") val total: Int)

data class FlightInformation(@SerializedName("flight_date") val flightDate: String,
                             @SerializedName("flight_status") val flightStatus: String,
                             @SerializedName("departure") val departure: Departure,
                             @SerializedName("arrival") val arrival: Arrival,
                             @SerializedName("airline") val airline: Airline,
                             @SerializedName("flight") val flight: Flight,
                             @SerializedName("aircraft") val aircraft: Aircraft,
                             @SerializedName("live") val live: Live)

data class Departure(@SerializedName("airport") val airport: String,
                     @SerializedName("timezone") val timezone: String,
                     @SerializedName("iata") val iata: String,
                     @SerializedName("icao") val icao: String,
                     @SerializedName("terminal") val terminal: String,
                     @SerializedName("gate") val gate: String,
                     @SerializedName("delay") val delay: Int,
                     @SerializedName("scheduled") val scheduled: String,
                     @SerializedName("estimated") val estimated: String,
                     @SerializedName("actual") val actual: String,
                     @SerializedName("estimated_runway") val estimatedRunway: String,
                     @SerializedName("actual_runway") val actualRunway : String)

data class Arrival(@SerializedName("airport") val airport: String,
                   @SerializedName("timezone") val timezone: String,
                   @SerializedName("iata") val iata: String,
                   @SerializedName("icao") val icao: String,
                   @SerializedName("terminal") val terminal: String,
                   @SerializedName("gate") val gate: String,
                   @SerializedName("baggage") val baggage: String,
                   @SerializedName("delay") val delay: Int,
                   @SerializedName("scheduled") val scheduled: String,
                   @SerializedName("estimated") val estimated: String,
                   @SerializedName("actual") val actual: String,
                   @SerializedName("estimated_runway") val estimatedRunway: String,
                   @SerializedName("actual_runway") val actualRunway : String)

data class Airline(@SerializedName("name") val name: String,
                   @SerializedName("iata") val iata: String,
                   @SerializedName("icao") val icao: String)

data class Flight(@SerializedName("number") val number: Int,
                  @SerializedName("iata") val iata: String,
                  @SerializedName("icao") val icao: String,
                  @SerializedName("codeshared") val codeshared: CodeShared?)

data class CodeShared(@SerializedName("airline_name") val airlineName: String,
                      @SerializedName("airline_iata") val airlineIata: String,
                      @SerializedName("airline_icao") val airlineIcao: String,
                      @SerializedName("flight_number") val flightNumber: String,
                      @SerializedName("flight_iata") val flightIata: String,
                      @SerializedName("flight_icao") val flightIcao: String)

data class Aircraft(@SerializedName("registration") val registration: String,
                    @SerializedName("iata") val iata: String,
                    @SerializedName("icao") val icao: String,
                    @SerializedName("icao24") val icao24: String)

data class Live(@SerializedName("updated") val updated: String,
                @SerializedName("latitude") val latitude: String,
                @SerializedName("longitude") val longitude: String,
                @SerializedName("altitude") val altitude: String,
                @SerializedName("direction") val direction: String,
                @SerializedName("speed_horizontal") val speedHorizontal: String,
                @SerializedName("speed_vertical") val speedVertical: String,
                @SerializedName("is_ground") val isGround: Boolean)

