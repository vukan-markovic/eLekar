package vukan.com.euprava.ui.login.phone_number

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vukan.com.euprava.R

class LoginPhoneNumberFragment : Fragment() {

    private lateinit var loginPhoneNumberViewModel: LoginPhoneNumberViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginPhoneNumberViewModel =
            ViewModelProvider(this).get(LoginPhoneNumberViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login_phone_number, container, false)

        loginPhoneNumberViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}