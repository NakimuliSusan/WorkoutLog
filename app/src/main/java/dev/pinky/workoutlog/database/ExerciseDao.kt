package dev.pinky.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.pinky.workoutlog.models.Exercises

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise:Exercises)

    @Query( "SELECT * FROM Exercises")
    fun getExercises (): LiveData<List<Exercises>>
    @Query("SELECT * FROM Exercises WHERE categoryId=  :categoryId ")
    fun getExercisesByCategoryId(categoryId: String): LiveData<List<Exercises>>
}