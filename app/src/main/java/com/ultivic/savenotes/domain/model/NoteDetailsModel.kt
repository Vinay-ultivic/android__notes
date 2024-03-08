package com.ultivic.savenotes.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Fruits_Table")
data class NoteDetailsModel(
    @ColumnInfo(name = "Title") val titleName: String,
    @ColumnInfo(name = "noteDescription")val titleDescription:String,
    @ColumnInfo(name = "timestamp")val timeStamp :String?="dljjdfja",

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( name ="id" )var id: Int = 0
): Parcelable
