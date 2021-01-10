package vukan.com.euprava.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vukan.com.euprava.R
import vukan.com.euprava.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textHelp.text = getString(R.string.help_text)
    }
}