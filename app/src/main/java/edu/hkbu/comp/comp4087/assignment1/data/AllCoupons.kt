package edu.hkbu.comp.comp4087.assignment1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class AllCoupons (
    @PrimaryKey
    val id: String,
    val title: String,
    val restaurant: String,
    val mall: String,
    val image: String,
    val coins: String,
    val detail: String,
    val region: String,
) { } //the curly braces can be omitted.