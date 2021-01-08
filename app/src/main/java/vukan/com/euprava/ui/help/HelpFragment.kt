package vukan.com.euprava.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import vukan.com.euprava.R
import vukan.com.euprava.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private val helpViewModel by viewModels<HelpViewModel>()
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
        val textView: TextView = view.findViewById(R.id.text_help)

        helpViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
    }
}