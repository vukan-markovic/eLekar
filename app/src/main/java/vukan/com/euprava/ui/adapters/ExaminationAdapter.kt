package vukan.com.euprava.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vukan.com.euprava.data.model.Examination
import vukan.com.euprava.databinding.ExaminationItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ExaminationAdapter(listener: ExaminationItemClickListener) :
    RecyclerView.Adapter<ExaminationAdapter.ExaminationViewHolder>() {

    private var examinations: List<Examination> = ArrayList()
    private var sfdDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    private var sfdTime = SimpleDateFormat("HH:mm", Locale.getDefault())
    val onClickListener: ExaminationItemClickListener = listener

    fun setExaminations(examinations: List<Examination>) {
        this.examinations = examinations
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExaminationViewHolder {
        return ExaminationViewHolder(
            ExaminationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExaminationViewHolder, position: Int) {
        holder.viewBinding.doctorName.text = examinations[position].doctorName
        val date = examinations[position].dateTime?.toDate()

        if (date != null) {
            holder.viewBinding.examinationDate.text = sfdDate.format(date)
            holder.viewBinding.examinationTime.text = sfdTime.format(date)
        }
    }

    override fun getItemCount(): Int {
        return examinations.size
    }

    inner class ExaminationViewHolder(var viewBinding: ExaminationItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root), View.OnClickListener {

        init {
            viewBinding.cancelExamination.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickListener.onListItemClick(examinations[adapterPosition].examinationID)
        }
    }

    interface ExaminationItemClickListener {
        fun onListItemClick(examinationID: String)
    }
}