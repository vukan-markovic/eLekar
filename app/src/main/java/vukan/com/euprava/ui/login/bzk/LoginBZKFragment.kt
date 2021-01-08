package vukan.com.euprava.ui.login.bzk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import vukan.com.euprava.databinding.FragmentLoginBzkBinding

class LoginBZKFragment : Fragment() {
    private val loginBZKViewModel by viewModels<LoginBZKViewModel>()
    private lateinit var binding: FragmentLoginBzkBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBzkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBZKViewModel.text.observe(viewLifecycleOwner, {

        })
    }
}