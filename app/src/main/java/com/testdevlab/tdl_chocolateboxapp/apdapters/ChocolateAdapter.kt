package com.testdevlab.tdl_chocolateboxapp.apdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testdevlab.tdl_chocolateboxapp.models.Chocolate
import com.testdevlab.tdl_chocolateboxapp.interfaces.ChocolateClickListener
import com.testdevlab.tdl_chocolateboxapp.R
import kotlinx.android.synthetic.main.row_chocolate_item.view.*


class ChocolateAdapter
    (private val chocolates: ArrayList<Chocolate>,
     val listener: ChocolateClickListener
)
    : RecyclerView.Adapter<ChocolateAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.row_chocolate_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chocolates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = chocolates[position].text
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val text = view.chocolate_text

        init {

            view.setOnLongClickListener {
                listener.onChocolateLongClicked(adapterPosition)
                true
            }
        }
    }
}