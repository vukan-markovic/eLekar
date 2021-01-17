package vukan.com.euprava

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import vukan.com.euprava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DrawerNavigation {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_doctor, R.id.nav_help),
            binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun setDrawerEnabled(enabled: Boolean) {
        val lockMode =
            if (enabled) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED

        binding.drawerLayout.setDrawerLockMode(lockMode)

        binding.appBarMain.toolbar.navigationIcon =
            if (enabled) ContextCompat.getDrawable(this, R.drawable.ic_menu) else null
    }

    override fun setIcon() {
        binding.appBarMain.toolbar.navigationIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
    }

    override fun setHeaderData(lboBzk: Array<String>) {
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.lbo).text =
            lboBzk[0]

        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.bzk).text =
            lboBzk[1]
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}