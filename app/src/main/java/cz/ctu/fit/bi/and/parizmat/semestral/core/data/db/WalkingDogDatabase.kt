package cz.ctu.fit.bi.and.parizmat.semestral.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.DogEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.dao.DogDao
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.SettingEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.dao.SettingDao

@Database(entities = [DogEntity::class, SettingEntity::class], version = 1, exportSchema = false)
abstract class WalkingDogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
    abstract fun settingPersonDao(): SettingDao

    companion object {
        private const val DATABASE_NAME = "walking_dog_database"

        fun newInstance(context: Context): WalkingDogDatabase =
            Room.databaseBuilder(
                context = context,
                klass = WalkingDogDatabase::class.java,
                name = DATABASE_NAME
            ).build()
    }
}