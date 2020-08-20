package xyz.xandsoft.thesurvey.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import xyz.xandsoft.thesurvey.R
import xyz.xandsoft.thesurvey.interfaces.OnItemClicked


class DropdownAdapter(
    private val context: Context,
    private val stringList: Array<String>,
    private val itemClicked: OnItemClicked
) : RecyclerView.Adapter<DropdownAdapter.MyViewholder>() {

    private var lastChecked: RadioButton? = null
    private var lastCheckedPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        return MyViewholder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_dropdown, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {

        // https://stackoverflow.com/questions/28972049/single-selection-in-recyclerview
        holder.dropOptions.text = stringList[position]
        holder.dropOptions.tag = position

        if (position == 0 && holder.dropOptions.isChecked) {
            lastChecked = holder.dropOptions
            lastCheckedPos = 0
        }

        holder.dropOptions.setOnClickListener {

            val cb: RadioButton = it as RadioButton
            val clickedPos: Int = cb.tag as Int

            if (cb.isChecked) {
                if (lastChecked != null) {
                    lastChecked!!.isChecked = false
                }
                lastChecked = cb
                lastCheckedPos = clickedPos
                itemClicked.itemClicked(
                    "How frequent you use our product? Ans: ${stringList[clickedPos]}")
            } else {
                lastChecked = null
            }
        }
    }

    override fun getItemCount(): Int {
        return stringList.size
    }

    class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dropOptions: RadioButton = itemView.findViewById(R.id.drop_options)
    }

}