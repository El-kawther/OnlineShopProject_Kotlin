package com.example.onlineshopkotlinproject.Model

data class ItemModel(
    var title:String="",
    var descreption:String="",
    var picUrl:ArrayList<String> = ArrayList(),
    var size:ArrayList<String> = ArrayList(),
    var price:Double=0.0,
    var rating:Double=0.0,
    var numberInCart:Int=0
)
