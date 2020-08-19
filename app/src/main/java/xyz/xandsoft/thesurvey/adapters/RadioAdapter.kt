package xyz.xandsoft.thesurvey.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.xandsoft.thesurvey.R

class RadioAdapter(
    private val context: Context,
    private val stringList: MutableList<String>
) : RecyclerView.Adapter<RadioAdapter.MyViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        return MyViewholder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_radio, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {

    }

    override fun getItemCount(): Int {
        return stringList.size
    }

    class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}