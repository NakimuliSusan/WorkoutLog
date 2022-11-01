package dev.pinky.workoutlog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.pinky.workoutlog.models.ExerciseCategory
import dev.pinky.workoutlog.models.Exercises
import dev.pinky.workoutlog.models.WorkoutPlan
import dev.pinky.workoutlog.models.WorkoutPlanItem
import java.security.AccessControlContext

@Database(entities = arrayOf(ExerciseCategory::class, Exercises::class, WorkoutPlan::class, WorkoutPlanItem::class),  version = 6)
@TypeConverters(Converters::class)
abstract class WorkoutDb : RoomDatabase() {
   abstract fun exerciseCategoryDao(): ExerciseCategoryDao
   abstract fun exerciseDao(): ExerciseDao
   abstract fun WorkoutPlanDao(): WorkoutPlanDao
   abstract fun WorkPlanItemDao(): WorkPlanItemDao

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