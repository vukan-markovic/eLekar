package vukan.com.euprava.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import vukan.com.euprava.databinding.FragmentHomeBinding
import vukan.com.euprava.ui.adapters.ExaminationAdapter
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ExaminationAdapter
    private var sfd = SimpleDateFormat("dd-MM-yyyy, HH:mm", Locale.getDefault())
    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (firebaseUser == null)
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToNavLoginLbo())

        binding.recyclerViewHome.setHasFixedSize(true)
        binding.recyclerViewHome.layoutManager = LinearLayoutManager(context)
        adapter = ExaminationAdapter()
        binding.recyclerViewHome.adapter = adapter
        binding.swipeContainer.setOnRefreshListener(this)

        binding.swipeContainer.setColorSchemeResources(
            android.R.color.holo_purple,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_light,
            android.R.color.holo_green_dark
        )

        binding.swipeContainer.post {
            binding.swipeContainer.isRefreshing = true
            loadRecyclerViewData()
        }
    }

    override fun onRefresh() {
        loadRecyclerViewData()
    }

    private fun loadRecyclerViewData() {
        binding.swipeContainer.isRefreshing = true

        homeViewModel.getExaminations()

        binding.swipeContainer.isRefreshing = false
        binding.lastRefreshTime.text = sfd.format(Calendar.getInstance().time)
    }
}