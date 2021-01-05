package vukan.com.euprava.ui.login.lbo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import vukan.com.euprava.R

class LoginLBOFragment : Fragment() {

    private lateinit var loginLBOViewModel: LoginLBOViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginLBOViewModel =
            ViewModelProvider(this).get(LoginLBOViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login_lbo, container, false)

        loginLBOViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}