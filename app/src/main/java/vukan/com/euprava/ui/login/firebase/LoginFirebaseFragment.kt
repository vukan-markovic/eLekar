package vukan.com.euprava.ui.login.firebase

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import vukan.com.euprava.DrawerNavigation
import vukan.com.euprava.R
import vukan.com.euprava.databinding.FragmentLoginFirebaseBinding

class LoginFirebaseFragment : Fragment() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var authStateListener: AuthStateListener? = null
    private val loginFirebaseViewModel by viewModels<LoginFirebaseViewModel>()
    private lateinit var binding: FragmentLoginFirebaseBinding

    companion object {
        private const val RC_SIGN_IN = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginFirebaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DrawerNavigation).setDrawerEnabled(false)

        authStateListener = AuthStateListener {
            if (it.currentUser == null) {
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                            listOf(
                                AuthUI.IdpConfig.EmailBuilder().build(),
                                AuthUI.IdpConfig.PhoneBuilder().build()
                            )
                        )
                        .setLogo(R.mipmap.ic_launcher)
                        .setTosAndPrivacyPolicyUrls(
                            "https://sites.google.com/view/elekar-terms-and-conditions",
                            "https://sites.google.com/view/elekar-privacy-policy"
                        ).build(), RC_SIGN_IN
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                val lboBzk = LoginFirebaseFragmentArgs.fromBundle(requireArguments()).lboBzk
                (activity as DrawerNavigation).setHeaderData(lboBzk)
                loginFirebaseViewModel.addUser(lboBzk)
                findNavController().navigate(LoginFirebaseFragmentDirections.actionNavLoginFirebaseToNavHome())
            }

            if (IdpResponse.fromResultIntent(data) == null) activity?.onBackPressed()
        }
    }

    override fun onPause() {
        super.onPause()
        if (authStateListener != null) firebaseAuth.removeAuthStateListener(authStateListener!!)
    }

    override fun onResume() {
        super.onResume()
        firebaseAuth.addAuthStateListener(authStateListener!!)
    }
}