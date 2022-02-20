package com.monsieur.cloy.data.db

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Database
import androidx.room.Room
import com.monsieur.cloy.data.storage.models.CityWeather

@Database(entities = [(CityWeather::class)], version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun cityWeatherDao(): CityWeatherDao

//    companion object{
//        @Volatile
//        private var INSTANCE: WeatherDatabase? = null
//
//        fun getDatabase(appContext: Context): WeatherDatabase{
//            val tempInstance = INSTANCE
//            if(tempInstance != null){
//                return tempInstance
//            }
//            synchronized(this){
//                val instance = Room.databaseBuilder(
//                    appContext,
//                    WeatherDatabase::class.java,
//                    "weather_database").build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}