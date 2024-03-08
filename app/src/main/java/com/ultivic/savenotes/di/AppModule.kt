package com.ultivic.savenotes.di

import android.content.Context
import androidx.room.Room
import com.ultivic.savenotes.data.datasource.local.dao.NotesDao
import com.ultivic.savenotes.data.datasource.local.database.NoteDatabase
import com.ultivic.savenotes.data.rerpository.NotesRepositoryImpl
import com.ultivic.savenotes.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFruitsDao(appDataBase: NoteDatabase): NotesDao {
        return appDataBase.fruitDao()
    }
    @Provides
    fun ProvideTodoDataBase(@ApplicationContext appContext: Context) : NoteDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            "fruit_database"
        )
            .build()
    }

    @Provides
    fun fruitRepository(dao: NotesDao): NotesRepository {
        return NotesRepositoryImpl(fruit = dao)
    }
}