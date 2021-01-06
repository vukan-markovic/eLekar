package vukan.com.euprava.ui.login.lbo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vukan.com.euprava.R

class LoginLBOFragment : Fragment() {
    private lateinit var loginLBOViewModel: LoginLBOViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_lbo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginLBOViewModel = ViewModelProvider(this).get(LoginLBOViewModel::class.java)

        loginLBOViewModel.text.observe(viewLifecycleOwner, {

        })
    }
}