package com.example.propple.activities.cliente


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.propple.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity2 : AppCompatActivity() {

    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, findNavController(R.id.nav_host))
            findNavController(R.id.nav_host).popBackStack(item.itemId, inclusive = false)

            true
        }
        //navHostFragment.popBackStack();
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //call super
        super.onActivityResult(requestCode, resultCode, data)
        bottomNavigationView.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, findNavController(R.id.nav_host))
            findNavController(R.id.nav_host).popBackStack(item.itemId, inclusive = false)

            true
        }
    }
    override fun onStart() {
        super.onStart()
        bottomNavigationView.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, findNavController(R.id.nav_host))
            findNavController(R.id.nav_host).popBackStack(item.itemId, inclusive = false)

            true
        }
    }
    public override fun onDestroy() {
        super.onDestroy()
    }
}