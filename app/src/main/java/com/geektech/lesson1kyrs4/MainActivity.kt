package com.geektech.lesson1kyrs4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.geektech.lesson1kyrs4.data.Pref
import com.geektech.lesson1kyrs4.databinding.ActivityMainBinding
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = Pref(this)
        auth = FirebaseAuth.getInstance()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (auth.currentUser == null) {
            navController.navigate(R.id.authFragment)
        }
        if (!pref.isUserSeen())
            navController.navigate(R.id.onBoardingFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.profileFragment,
                R.id.taskFragment
            )
        )

        FirebaseMessaging.getInstance().token.addOnCompleteListener {task->
            Log.e("ololo","onCreate: "+task.result)
        }
       //FirebaseMessaging.getInstance().token.addOnCompleteListener {task->
           //Log.e("ololo","onCreate: "+task.result)
       // }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val bottomNavigationFragment = arrayListOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.profileFragment,
            R.id.taskFragment

        )

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = bottomNavigationFragment.contains(destination.id)
            if (destination.id == R.id.onBoardingFragment || destination.id == R.id.splashFragment) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }
    }
}


