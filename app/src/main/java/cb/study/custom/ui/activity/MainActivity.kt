package cb.study.custom.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import cb.study.custom.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeUi()
    }

    private fun subscribeUi() {
        val navController = Navigation.findNavController(this, R.id.main_fragment)
        //Set up ActionBar
        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this,navController,drawer_layout)
        //Set up navigation menu
        navigation_view.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(drawer_layout,Navigation.findNavController(this,
            R.id.main_fragment
        ))
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}
