package com.example.firebase_example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.person_list_item.view.*

class PersonAdapter(private val list: ArrayList<Person>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }

    class PersonViewHolder(itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_list_item, parent, false)
        return PersonViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.itemView.apply {
            person_item_tv.text = list[position].personName
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}