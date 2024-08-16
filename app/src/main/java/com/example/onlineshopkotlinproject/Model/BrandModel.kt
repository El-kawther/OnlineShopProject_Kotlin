package com.example.onlineshopkotlinproject.Model


/**
 * Data class representing a brand in the online shop.
 *
 * @property title The name of the brand. Defaults to an empty string if not provided.
 * @property id A unique identifier for the brand. Defaults to 0 if not provided.
 * @property picUrl The URL of the brand's picture or logo. Defaults to an empty string if not provided.
 */
data class BrandModel(
    val title : String="",
    val id :Int=0,
    val picUrl:String=""
)
