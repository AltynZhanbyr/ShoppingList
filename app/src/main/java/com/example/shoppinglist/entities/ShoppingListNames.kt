package com.example.shoppinglist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="shopping_list_names")
data class ShoppingListNames(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Long?,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "time")
    val time:String,

    @ColumnInfo(name = "allItemCounter")
    val allItemCounter:Int,

    @ColumnInfo(name = "checkedItemsCounter")
    val checkedItemsCounter:Int,

    @ColumnInfo(name = "itemsId")
    val itemId:String
):java.io.Serializable