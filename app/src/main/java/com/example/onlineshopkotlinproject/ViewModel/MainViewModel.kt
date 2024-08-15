package com.example.onlineshopkotlinproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshopkotlinproject.Model.BrandModel
import com.example.onlineshopkotlinproject.Model.ItemsModel
import com.example.onlineshopkotlinproject.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
  //This  used to hold and manage UI-related data in a lifecycle-conscious way.
class MainViewModel (): ViewModel(){

  //Creates an instance of FirebaseDatabase to interact with the Firebase Realtime Database.
    private val firebaseDatabase=FirebaseDatabase.getInstance()

  /*  LiveDate to hold a list of SliderModel, BrandModel and ItemsModel object
      Used for updating and observing banner, brand and  popular items data.
      LiveData allows observers to be notified when the data changes.
      _banner is private to the ViewModel, meaning only the ViewModel can modify its value.
   */
    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brand = MutableLiveData<MutableList<BrandModel>>()
    private val _popular = MutableLiveData<MutableList<ItemsModel>>()

      /*By assigning _brand to brands,
       we are exposing a read-only LiveData to external observers.
       */
    val brands:LiveData<MutableList<BrandModel>> = _brand
    val banners:LiveData<List<SliderModel>> = _banner
    val popular:LiveData<MutableList<ItemsModel>> = _popular

    fun loadBanners(){
             val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object :ValueEventListener{
            /*
            This method is called whenever the data at the reference Ref changes.
             It provides a DataSnapshot containing the updated data.
             */
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                /*
                the snapshot is the data retrieved from the Firebase Database.
                It can be used to read the data in various formats.
                 */
                /*
                snapshot.children: Iterates over all child nodes of the snapshot.
                 Each child node represents an individual item (e.g., each banner in the list).
                 */
                for (childSnapshot in snapshot.children) {
                    /*
                    Converts the data from the DataSnapshot into an instance of SliderModel.
                     If the data format in Firebase matches SliderModel, this will work correctly.
                     */
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    /*
                    Adds the non-null SliderModel instance to the lists collection(of the sliderModel).
                     */
                    if (list !=null ){
                        lists.add(list)
                    }
                }
                // we added the data of the lists (from the firebase) to liveData to can manage theme
                // Update _banner LiveData with the retrieved list.
                _banner.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors here
            }

        })

            }
      // same as loadBanner
    fun loadBrand(){
        val Ref=firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(BrandModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                _brand.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
      //same as loadBanner
    fun loadPopular(){
        val Ref=firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(ItemsModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                _popular.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}
