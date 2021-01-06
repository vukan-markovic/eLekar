package vukan.com.euprava.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vukan.com.euprava.R

class ExaminationAdapter : RecyclerView.Adapter<ExaminationAdapter.ExaminationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExaminationViewHolder {
        return ExaminationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_home,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExaminationViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

    class ExaminationViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}