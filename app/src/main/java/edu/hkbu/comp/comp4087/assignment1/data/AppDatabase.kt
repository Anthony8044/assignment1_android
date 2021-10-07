package edu.hkbu.comp.comp4087.assignment1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.hkbu.comp.comp4087.assignment1.Network

@Database(entities = arrayOf(AllCoupons::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun couponDao(): CouponDao

    companion object {
        private var instance: AppDatabase? = null
        suspend fun getInstance(context: Context): AppDatabase {
            if (instance != null)
                return instance!!
            //build an instance
            instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "Assignment1"
            ).build()
            initDB()
            return instance!!
        }

        suspend fun initDB() {
            instance?.clearAllTables() //add this line when you are still debugging
            val NEWS_URL = "https://5c2f-223-18-233-239.ngrok.io/restaurant/json"
            val json = Network.getTextFromNetwork(NEWS_URL)
            //convert the string json into List<news>
            val coupons =
                Gson().fromJson<List<AllCoupons>>(json, object : TypeToken<List<AllCoupons>>() {}.type)

            coupons.forEach { instance?.couponDao()?.insert(it) }
        }
    }
}