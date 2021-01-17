package vukan.com.euprava.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import vukan.com.euprava.data.Repository
import vukan.com.euprava.data.model.Doctor
import vukan.com.euprava.databinding.ChosenDoctorItemBinding
import vukan.com.euprava.ui.doctor.ChosenDoctorFragmentDirections

class DoctorAdapter(listener: DoctorItemClickListener) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {
    private var doctors: List<Doctor> = ArrayList()
    private val repository = Repository()
    val onClickListener: DoctorItemClickListener = listener

    fun setDoctors(doctors: List<Doctor>) {
        this.doctors = doctors
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        return DoctorViewHolder(
            ChosenDoctorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        (doctors[position].name + " " + doctors[position].surname).also {
            holder.viewBinding.doctorName.text = it
        }

        holder.viewBinding.institutionName.text =
            repository.getInstitutionName(doctors[position].institutionID)

        holder.viewBinding.doctorSpecialization.text =
            repository.getDoctorSpecialization(doctors[position].doctorID)

        holder.viewBinding.choseTerm.setOnClickListener {
            val action = ChosenDoctorFragmentDirections.actionNavDoctorToNavSchedulingExamination()
            action.doctorId = doctors[position].doctorID
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return doctors.size
    }

    inner class DoctorViewHolder(var viewBinding: ChosenDoctorItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root), View.OnClickListener {

        init {
            viewBinding.institutionName.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickListener.onListItemClick(doctors[adapterPosition].institutionID)
        }
    }

    interface DoctorItemClickListener {
        fun onListItemClick(institutionID: String)
    }
}