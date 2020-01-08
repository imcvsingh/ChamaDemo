package com.cvsingh.chamademo.model

data class PlaceModel (

	val geometry : Geometry,
	val icon : String,
	val id : String,
	val name : String,
	val opening_hours : Opening_hours,
	val photos : List<Photos>,
	val place_id : String,
	val price_level : Int,
	val rating : Double,
	val reference : String,
	val scope : String,
	val types : List<String>,
	val user_ratings_total : Int,
	val vicinity : String
)