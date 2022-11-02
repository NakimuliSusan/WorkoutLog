package dev.pinky.workoutlog.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dev.pinky.workoutlog.R
import dev.pinky.workoutlog.databinding.FragmentPlanBinding
import dev.pinky.workoutlog.databinding.FragmentTrackBinding
import dev.pinky.workoutlog.viewmodel.WorkoutPlanViewModel

class TrackFragment : Fragment() {
    var binding: FragmentTrackBinding? = null
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels ( )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentTrackBinding.inflate(layoutInflater,container,false)
        return view.root
    }

    override fun onResume() {
        super.onResume()
    }
}