package com.cvsingh.chamademo.model

data class ResponseList (
    val results : List<PlaceModel>,
    val status : String
)