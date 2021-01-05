package vukan.com.euprava.ui.login.bzk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vukan.com.euprava.R

class LoginBZKFragment : Fragment() {

    private lateinit var loginBZKViewModel: LoginBZKViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBZKViewModel =
            ViewModelProvider(this).get(LoginBZKViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login_bzk, container, false)

        loginBZKViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}