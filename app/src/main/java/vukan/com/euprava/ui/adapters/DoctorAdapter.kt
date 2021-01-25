package vukan.com.euprava.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import vukan.com.euprava.R
import vukan.com.euprava.data.model.Doctor
import vukan.com.euprava.databinding.ChosenDoctorItemBinding
import vukan.com.euprava.ui.doctor.ChosenDoctorFragmentDirections

class DoctorAdapter(listener: DoctorItemClickListener, private val context: Context) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {
    private var doctors: List<Doctor> = ArrayList()
    private var institution: String = ""
    val onClickListener: DoctorItemClickListener = listener

    fun setDoctors(doctors: List<Doctor>, institution: String) {
        this.doctors = doctors
        this.institution = institution
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
            context.getString(R.string.institution_name, institution)

        holder.viewBinding.doctorSpecialization.text = doctors[position].specialization

        holder.viewBinding.choseTerm.setOnClickListener {
            val action = ChosenDoctorFragmentDirections.actionNavDoctorToNavSchedulingExamination()
            action.doctorId = doctors[position].doctorID
            Navigation.findNavController(it).navigate(action)
        }

        holder.viewBinding.sendMessage.setOnClickListener {
            val action = ChosenDoctorFragmentDirections.actionNavDoctorToNavEmailDoctor()
            action.doctorId = doctors[position].doctorID
            Navigation.findNavController(it).navigate(action)
        }

        if (FirebaseAuth.getInstance().currentUser?.email == null)
            holder.viewBinding.sendMessage.visibility = View.GONE
        else holder.viewBinding.sendMessage.visibility = View.VISIBLE
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