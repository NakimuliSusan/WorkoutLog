package dev.pinky.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.pinky.workoutlog.models.ExerciseCategory

@Dao
interface ExerciseCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExerciseCategory(exerciseCategory:ExerciseCategory)
    @Query ( "SELECT * FROM Exercisecategories")
    fun getExerciseCategories (): LiveData<List<ExerciseCategory>>
}