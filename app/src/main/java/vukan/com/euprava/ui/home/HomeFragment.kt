package vukan.com.euprava.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import vukan.com.euprava.DrawerNavigation
import vukan.com.euprava.R
import vukan.com.euprava.databinding.FragmentHomeBinding
import vukan.com.euprava.ui.adapters.ExaminationAdapter
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    ExaminationAdapter.ExaminationItemClickListener {

    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ExaminationAdapter
    private var sfd = SimpleDateFormat("dd-MM-yyyy, HH:mm", Locale.getDefault())
    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        (activity as DrawerNavigation).setDrawerEnabled(true)

        if (firebaseUser == null)
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToNavLoginLbo())

        binding.recyclerViewHome.setHasFixedSize(true)
        binding.recyclerViewHome.layoutManager = LinearLayoutManager(context)
        adapter = ExaminationAdapter(this)
        binding.recyclerViewHome.adapter = adapter
        binding.swipeContainer.setOnRefreshListener(this)

        binding.swipeContainer.setColorSchemeResources(
            R.color.purple_200,
            R.color.purple_500,
            R.color.purple_700,
            R.color.teal_200
        )

        binding.swipeContainer.post {
            binding.swipeContainer.isRefreshing = true
            loadRecyclerViewData()
        }

        binding.fab.setOnClickListener { v ->
            Snackbar.make(v, "", Snackbar.LENGTH_LONG)
                .setAction("", null).show()
        }
    }

    override fun onRefresh() {
        loadRecyclerViewData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_account)
                .setPositiveButton(android.R.string.ok) { _: DialogInterface?, _: Int ->
                    AuthUI.getInstance()
                        .signOut(requireContext())
                        .addOnCompleteListener {
                            findNavController().navigate(HomeFragmentDirections.actionNavHomeToNavLoginLbo())
                            Toast.makeText(context, R.string.logout, Toast.LENGTH_SHORT)
                                .show()
                        }
                }
                .setNegativeButton(android.R.string.cancel, null)
                .setIcon(R.drawable.ic_person_remove)
                .show()

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadRecyclerViewData() {
        binding.swipeContainer.isRefreshing = true

        homeViewModel.getExaminations().observe(viewLifecycleOwner) { examinations ->
            adapter.setExaminations(examinations)
            binding.recyclerViewHome.adapter = adapter
            binding.swipeContainer.isRefreshing = false
            binding.lastRefreshTime.text = sfd.format(Calendar.getInstance().time)
        }
    }

    override fun onListItemClick(examinationID: String) {
        homeViewModel.cancelExamination(examinationID)
        loadRecyclerViewData()
    }
}