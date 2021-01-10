package vukan.com.euprava.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vukan.com.euprava.R

class DoctorAdapter : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DoctorViewHolder {
        return DoctorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.chosen_doctor_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

    class DoctorViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}