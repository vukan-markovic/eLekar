package vukan.com.euprava.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import vukan.com.euprava.databinding.FragmentHomeBinding
import vukan.com.euprava.ui.adapters.ExaminationAdapter
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ExaminationAdapter
    private var sfd: SimpleDateFormat? = null

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
        binding.recyclerViewHome.setHasFixedSize(true)
        binding.recyclerViewHome.layoutManager = GridLayoutManager(context, 2)
        adapter = ExaminationAdapter()
        binding.recyclerViewHome.adapter = adapter
        binding.swipeContainer.setOnRefreshListener(this)
        sfd = SimpleDateFormat("dd-MM-yyyy, HH:mm", Locale.getDefault())
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

        homeViewModel.text.observe(viewLifecycleOwner, {

        })
    }

    override fun onRefresh() {
        loadRecyclerViewData()
    }

    private fun loadRecyclerViewData() {
        binding.swipeContainer.isRefreshing = true

        binding.swipeContainer.isRefreshing = false
        binding.lastRefreshTime.text = sfd?.format(Calendar.getInstance().time)
    }
}