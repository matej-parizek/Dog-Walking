package cz.ctu.fit.bi.and.parizmat.semestral.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.DogEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.dictionaries.data.local.dao.DogDao
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.SettingEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.settings.data.local.dao.SettingDao
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.StepCountEntity
import cz.ctu.fit.bi.and.parizmat.semestral.feature.stepper.data.local.dao.StepCountDao

/**
 * Room database class for the WalkingDog application. This database contains tables for storing dog entities and settings entities and stepper entities.
 *
 * @Database marks the class as a Room database and lists the entities it manages and its version.
 * exportSchema is set to false, which avoids generating a schema JSON file in your project's output,
 * typically used for version control and schema history but can be omitted in simpler projects.
 *
 * Entities:
 * - DogEntity: Represents individual dog details.
 * - SettingEntity: Represents various settings related to the application or user preferences.
 */
@Database(
    entities = [DogEntity::class, SettingEntity::class, StepCountEntity::class],
    version = 2,
    exportSchema = false
)
abstract class WalkingDogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
    abstract fun settingPersonDao(): SettingDao

    abstract fun stepCountDao(): StepCountDao

    companion object {
        private const val DATABASE_NAME = "walking_dog_database"

        /**
         * Creates and returns a new instance of the WalkingDogDatabase.
         * This method uses Room's database builder to create a new database instance if one does not already exist.
         *
         * @param context Context used to access the filesystem or device-specific paths.
         * @return A new or existing database instance for the application.
         */
        fun newInstance(context: Context): WalkingDogDatabase =
            Room.databaseBuilder(
                context = context,
                klass = WalkingDogDatabase::class.java,
                name = DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
    }
}