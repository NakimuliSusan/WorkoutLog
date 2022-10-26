package dev.pinky.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.pinky.workoutlog.databinding.FragmentProfileBinding
import dev.pinky.workoutlog.viewmodel.ExerciseViewModel
import dev.pinky.workoutlog.viewmodel.ProfileViewModel
import java.util.EnumSet.of


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPrefs = activity?.applicationContext!!.getSharedPreferences("WORKOUTLOG_PREFS", AppCompatActivity.MODE_PRIVATE)
        binding.tvLogout.setOnClickListener {
          Logoutrequest()
        }
//        return inflater.inflate(R.layout.fragment_profile, container, false)
        return  binding.root
    }

//    override fun onResume() {
//        super.onResume()
//        profileViewModel.profileResponseLiveData.observe(this, Observer { profileresponse ->
//            Toast.makeText(context, profileresponse.userId, Toast.LENGTH_LONG).show()
//        })
//        profileViewModel.errorLiveData.observe(this, Observer { error->
//            Toast.makeText(context,error, Toast.LENGTH_LONG).show()
//        })
//    }

    fun Logoutrequest () {
        sharedPrefs.edit().clear().apply()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }


}