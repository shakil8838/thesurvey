package xyz.xandsoft.thesurvey.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.xandsoft.thesurvey.R

class DropdownAdapter(
    private val context: Context,
    private val stringList: Array<String>
) :
    RecyclerView.Adapter<DropdownAdapter.MyViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        return MyViewholder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_dropdown, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.ddText.text = stringList[position]
    }

    override fun getItemCount(): Int {
        return stringList.size
    }

    class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ddText: TextView = itemView.findViewById(R.id.dd_text)
    }
}