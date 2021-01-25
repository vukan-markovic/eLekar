@file:Suppress("DEPRECATION")

package vukan.com.euprava.ui.bot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.kommunicate.KmChatBuilder
import io.kommunicate.callbacks.KmCallback
import vukan.com.euprava.databinding.FragmentBotBinding

class BotFragment : Fragment() {
    private lateinit var binding: FragmentBotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botList: List<String> = arrayListOf("alex-eskbb")

        binding.start.setOnClickListener {
            KmChatBuilder(context)
                .setChatName("eSavetnik")
                .setBotIds(botList)
                .launchChat(object : KmCallback {
                    override fun onSuccess(message: Any) {}
                    override fun onFailure(error: Any) {}
                })
        }
    }
}