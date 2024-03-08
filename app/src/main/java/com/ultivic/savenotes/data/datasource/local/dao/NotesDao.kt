package com.ultivic.savenotes.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ultivic.savenotes.domain.model.NoteDetailsModel

@Dao
interface NotesDao {

    @Insert
    fun insert(insertFruit: NoteDetailsModel): Long

    @Delete
    fun delete(deleteFruit: NoteDetailsModel): Int

    @Update
    fun update(updateFruit: NoteDetailsModel)

    @Query("select * from Fruits_Table ")
    fun getAllFruits(): List<NoteDetailsModel>

    @Query("select * from Fruits_Table where id = :id")
    fun fetchFruitId(id: String): List<NoteDetailsModel>
}