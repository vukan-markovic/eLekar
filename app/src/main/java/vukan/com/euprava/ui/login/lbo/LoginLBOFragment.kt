package vukan.com.euprava.ui.login.lbo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import vukan.com.euprava.databinding.FragmentLoginLboBinding

class LoginLBOFragment : Fragment() {
    private val loginLBOViewModel by viewModels<LoginLBOViewModel>()
    private lateinit var binding: FragmentLoginLboBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginLboBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginLBOViewModel.text.observe(viewLifecycleOwner, {

        })
    }
}