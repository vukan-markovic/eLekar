package vukan.com.euprava

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar
import vukan.com.euprava.databinding.ActivityMainBinding
import vukan.com.euprava.ui.login.firebase.LoginFirebaseViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val loginFirebaseViewModel by viewModels<LoginFirebaseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_doctor, R.id.nav_help, R.id.nav_logout, R.id.nav_delete),
            binding.drawerLayout
        )

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                .setAction("", null).show()
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_logout) {
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        Toast.makeText(this, R.string.logout, Toast.LENGTH_SHORT)
                            .show()
                    }
            } else if (destination.id == R.id.nav_delete) {
                AlertDialog.Builder(this)
                    .setTitle(R.string.lbo_help)
                    .setMessage(R.string.delete_account)
                    .setPositiveButton(android.R.string.ok) { _: DialogInterface?, _: Int ->
                        AuthUI.getInstance()
                            .delete(this)
                            .addOnCompleteListener {
                                Toast.makeText(this, R.string.account_deleted, Toast.LENGTH_SHORT)
                                    .show()
                            }

                        loginFirebaseViewModel.deleteUser("")
                    }
                    .setNegativeButton(android.R.string.cancel, null)
                    .setIcon(android.R.drawable.ic_menu_info_details)
                    .show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}