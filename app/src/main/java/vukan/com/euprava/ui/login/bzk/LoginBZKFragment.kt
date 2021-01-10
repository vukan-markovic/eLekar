package vukan.com.euprava.ui.login.bzk

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import vukan.com.euprava.R
import vukan.com.euprava.databinding.FragmentLoginBzkBinding
import vukan.com.euprava.ui.login.afterTextChanged

class LoginBZKFragment : Fragment() {
    private val loginBZKViewModel by viewModels<LoginBZKViewModel>()
    private lateinit var binding: FragmentLoginBzkBinding
    private var isDataValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBzkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (isDataValid) {
            binding.next.isEnabled = true
            binding.next.isClickable = true
        }

        binding.inputBzk.afterTextChanged {
            loginBZKViewModel.checkBZK(binding.inputBzk.text.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBZKViewModel.formState.observe(viewLifecycleOwner, Observer {
            val state = it ?: return@Observer
            isDataValid = state.isDataValid
            binding.next.isEnabled = isDataValid
            binding.next.isClickable = isDataValid

            if (state.bzkError != null && state.bzkError != 0)
                binding.outlinedBzk.error = getString(state.bzkError)
            else binding.outlinedBzk.error = null
        })

        binding.next.setOnClickListener {
            findNavController().navigate(
                LoginBZKFragmentDirections.actionNavLoginBZKToNavLoginFirebase(
                    arrayOf(
                        binding.inputBzk.text.toString(),
                        LoginBZKFragmentArgs.fromBundle(requireArguments()).lbo
                    )
                )
            )
        }

        binding.bzkHelp.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle(R.string.bzk_help_title)
                .setMessage(R.string.bzk_help_text)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_menu_info_details)
                .show()
        }
    }
}