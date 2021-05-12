package com.example.kidsindata_spaceinvader


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kidsindata_spaceinvader.vm.UserViewModel
import com.example.numberskotlin.R
import com.example.numberskotlin.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        titleToggler()
    }

    
    //toggles the title at register process
    private fun titleToggler() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.startFragment2)) {
                binding.registerTitle.text = getString(R.string.home_title)
            } else if (destination.id in arrayOf(R.id.avatarFragment)) {
                binding.registerTitle.text = getString(R.string.sign_up)
            } else if (destination.id in arrayOf(R.id.avatarFragment)) {

            }
        }
    }
}