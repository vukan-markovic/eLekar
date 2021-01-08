package vukan.com.euprava.ui.login.firebase

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import vukan.com.euprava.R
import vukan.com.euprava.databinding.FragmentLoginFirebaseBinding


class LoginFirebaseFragment : Fragment() {

    companion object {
        private const val RC_SIGN_IN = 1
    }

    private val mFirebaseAuth = FirebaseAuth.getInstance()
    private var mAuthStateListener: AuthStateListener? = null
    private var mFirebaseUser: FirebaseUser? = null
    private val loginFirebaseViewModel by viewModels<LoginFirebaseViewModel>()
    private lateinit var binding: FragmentLoginFirebaseBinding

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

        mAuthStateListener = AuthStateListener { firebaseAuth: FirebaseAuth ->
            mFirebaseUser = firebaseAuth.currentUser

            if (mFirebaseUser == null) {
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(listOf())
                        .setLogo(R.mipmap.ic_launcher)
                        .setTosAndPrivacyPolicyUrls(
                            "https://example.com/terms.html",
                            "https://example.com/privacy.html"
                        )
                        .build(), RC_SIGN_IN
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK)
                loginFirebaseViewModel.addUser()
            if (IdpResponse.fromResultIntent(data) == null)
                activity?.finish()
        }
    }

    override fun onPause() {
        super.onPause()
        if (mAuthStateListener != null) mFirebaseAuth.removeAuthStateListener(mAuthStateListener!!)
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener(mAuthStateListener!!)

//        Builder(this)
//            .setTitle(R.string.delete_account)
//            .setMessage(R.string.confirm)
//            .setPositiveButton(android.R.string.yes) { dialog, which ->
//                myAdsViewModel.deleteUserData(mFirebaseUser!!.uid)
//                AuthUI.getInstance().delete(this)
//                    .addOnCompleteListener { task: Task<Void?>? ->
//                        Toast.makeText(
//                            this,
//                            R.string.account_deleted,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//            }
//            .setNegativeButton(android.R.string.no, null)
//            .setIcon(R.drawable.ic_delete)
//            .show()
    }
}