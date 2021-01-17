package vukan.com.euprava.ui.login.lbo

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import vukan.com.euprava.DrawerNavigation
import vukan.com.euprava.R
import vukan.com.euprava.databinding.FragmentLoginLboBinding
import vukan.com.euprava.ui.login.afterTextChanged

class LoginLBOFragment : Fragment() {
    private val loginLBOViewModel by viewModels<LoginLBOViewModel>()
    private lateinit var binding: FragmentLoginLboBinding
    private var isDataValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginLboBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (isDataValid) {
            binding.next.isEnabled = true
            binding.next.isClickable = true
        }

        binding.lboInput.afterTextChanged {
            loginLBOViewModel.checkLBO(binding.lboInput.text.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DrawerNavigation).setDrawerEnabled(false)

        loginLBOViewModel.formState.observe(viewLifecycleOwner, Observer {
            val state = it ?: return@Observer
            isDataValid = state.isDataValid
            binding.next.isEnabled = isDataValid
            binding.next.isClickable = isDataValid

            if (state.lboError != null && state.lboError != 0)
                binding.outlinedLbo.error = getString(state.lboError)
            else binding.outlinedLbo.error = null
        })

        binding.next.setOnClickListener {
            val action: LoginLBOFragmentDirections.ActionNavLoginLboToNavLoginBZK =
                LoginLBOFragmentDirections.actionNavLoginLboToNavLoginBZK()
            action.lbo = binding.lboInput.text.toString()
            findNavController().navigate(action)
        }

        binding.lboHelp.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle(R.string.lbo_help_title)
                .setMessage(R.string.lbo_help_text)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(R.drawable.ic_info)
                .show()
        }
    }
}