package edu.hkbu.comp.comp4087.assignment1.data

import androidx.room.*
@Dao
interface CouponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(allCoupons: AllCoupons)
    @Query("Select * from allCoupons")
    suspend fun findAll(): List<AllCoupons>
    @Query("Select * from allCoupons where region = :region")
    suspend fun findMall(region:String): List<AllCoupons>
    @Query("Select * from allCoupons where coins <= '300' ")
    suspend fun findLessThan(): List<AllCoupons>
    @Query("Select * from allCoupons where coins > '300' AND coins < '600' ")
    suspend fun findInBetween(): List<AllCoupons>
    @Query("Select * from allCoupons where coins >= '600' ")
    suspend fun findMoreThan(): List<AllCoupons>
    @Query("Select * from allCoupons where id = :id")
    suspend fun findid(id:String): List<AllCoupons>
    @Delete
    suspend fun delete(vararg allCoupons: AllCoupons)
    @Update
    suspend fun update(allCoupons: AllCoupons)
}