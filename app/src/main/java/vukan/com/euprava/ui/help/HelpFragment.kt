package vukan.com.euprava.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vukan.com.euprava.R

class HelpFragment : Fragment() {
    private lateinit var helpViewModel: HelpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.text_help)
        helpViewModel = ViewModelProvider(this).get(HelpViewModel::class.java)

        helpViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
    }
}