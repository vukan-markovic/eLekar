package vukan.com.euprava.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import vukan.com.euprava.DrawerNavigation
import vukan.com.euprava.R
import vukan.com.euprava.databinding.FragmentChosenDoctorBinding
import vukan.com.euprava.ui.adapters.DoctorAdapter
import java.text.SimpleDateFormat
import java.util.*

class ChosenDoctorFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    DoctorAdapter.DoctorItemClickListener {

    private val chosenDoctorViewModel by viewModels<ChosenDoctorViewModel>()
    private lateinit var binding: FragmentChosenDoctorBinding
    private lateinit var adapter: DoctorAdapter
    private var sfd = SimpleDateFormat("dd-MM-yyyy, HH:mm", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChosenDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DrawerNavigation).setDrawerEnabled(true)
        binding.recyclerViewDoctor.setHasFixedSize(true)
        binding.recyclerViewDoctor.layoutManager = LinearLayoutManager(context)
        adapter = DoctorAdapter(this, requireContext())
        binding.recyclerViewDoctor.adapter = adapter
        binding.swipeContainer.setOnRefreshListener(this)

        binding.swipeContainer.setColorSchemeResources(
            R.color.purple_200,
            R.color.purple_500,
            R.color.purple_700,
            R.color.teal_200
        )

        binding.swipeContainer.post {
            loadRecyclerViewData()
        }
    }

    override fun onRefresh() {
        loadRecyclerViewData()
    }

    private fun loadRecyclerViewData() {
        binding.swipeContainer.isRefreshing = true

        chosenDoctorViewModel.getUser().observe(viewLifecycleOwner) { user ->
            chosenDoctorViewModel.getDoctors(arrayOf(user.lbo, user.bzk))
                .observe(viewLifecycleOwner) { doctors ->
                    doctors.forEach {
                        chosenDoctorViewModel.getInstitution(it.institutionID)
                            .observe(viewLifecycleOwner) { institution ->
                                adapter.setDoctors(doctors, institution.name)
                                binding.recyclerViewDoctor.adapter = adapter
                            }
                    }

                    binding.swipeContainer.isRefreshing = false
                    binding.lastRefreshTime.text =
                        getString(R.string.last_refresh, sfd.format(Calendar.getInstance().time))
                }
        }
    }

    override fun onListItemClick(institutionID: String) {
        var flag = true

        chosenDoctorViewModel.getInstitution(institutionID)
            .observe(viewLifecycleOwner) { institution ->
                if (flag && institution.institutionID == institutionID) {
                    AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.institution))
                        .setMessage(
                            "Naziv: " + institution.name + "\n" +
                                    "Mesto: " + institution.place + "\n" +
                                    "Adresa: " + institution.address
                        )
                        .setPositiveButton(android.R.string.ok, null)
                        .setIcon(R.drawable.ic_hospital)
                        .show()
                }

                flag = false
            }
    }
}