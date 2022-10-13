package dev.pinky.workoutlog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.pinky.workoutlog.models.ExerciseCategory
import dev.pinky.workoutlog.models.Exercises
import java.security.AccessControlContext

@Database(entities = arrayOf(ExerciseCategory::class, Exercises::class),  version = 3)
abstract class WorkoutDb : RoomDatabase() {
   abstract fun exerciseCategoryDao(): ExerciseCategoryDao
   abstract fun exerciseDao(): ExerciseDao

   companion object{
    private  var database :WorkoutDb? = null

    fun getDatabase (context: Context) :WorkoutDb {
     if (database == null) {
      database = Room
       .databaseBuilder(context,WorkoutDb::class.java,"WorkoutDb")
       .fallbackToDestructiveMigration()
       .build()
     }
     return database as WorkoutDb
    }
   }
}