package com.monsieur.cloy.weatherapp.model.chosenCity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chosenCity")
class ChosenCity {
    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "cityId")
    @NonNull
    var cityId = 0
}