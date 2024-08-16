package com.example.onlineshopkotlinproject.Model

import android.os.Parcel
import android.os.Parcelable
    /**
     * Data class representing an item in the online shop(from firebase).
     *
     * @property title The title or name of the item.
     * @property description A brief description of the item.
     * @property picUrl A list of URLs representing the images associated with the item.
     * @property size A list of available sizes for the item.
     * @property price The price of the item.
     * @property rating The rating of the item, typically between 0.0 and 5.0.
     * @property numberInCart The quantity of this item currently in the user's cart.
     */
data class ItemsModel(
    var title:String="",
    var description:String="",
    var picUrl:ArrayList<String> = ArrayList(),
    var size: ArrayList<String> = ArrayList(),
    var price:Double=0.0,
    var rating:Double=0.0,
    var numberInCart:Int=0
):Parcelable  /**Parcelable: This makes the class implement the Parcelable interface,
                 allowing it to be passed between Android components.*/
    {
        //Constructor to recreate an ItemsModel from a Parcel.
    constructor(parcel: Parcel):this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readDouble()
    )

        /**
         * This method is required by the Parcelable interface and returns a bitmask. Here, it returns 0,
         * indicating no special contents.
         */
    override fun describeContents(): Int {
       return 0
    }

        /**
         * This method writes the properties of the ItemsModel object into a Parcel so that it can
         * be serialized.
         */
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeStringList(picUrl)
        dest.writeStringList(size)
        dest.writeDouble(price)
        dest.writeDouble(rating)
    }

    companion object CREATOR : Parcelable.Creator<ItemsModel> {
        /**
         * This method creates a new ItemsModel instance from a Parcel,
         * using the constructor defined earlier.
         */
        override fun createFromParcel(parcel: Parcel): ItemsModel {
            return ItemsModel(parcel)
        }

        /**
         * This method creates a new array of ItemsModel objects,
         * with a size determined by the parameter size.
         * The array elements are initialized to null.
         */
        override fun newArray(size: Int): Array<ItemsModel?> {
            return arrayOfNulls(size)
        }
    }


}


