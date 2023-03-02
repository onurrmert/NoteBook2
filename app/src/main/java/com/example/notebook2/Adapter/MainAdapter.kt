package com.example.notebook2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook2.Model.NotesModel
import com.example.notebook2.R
import com.example.notebook2.databinding.MainRecyclerRowBinding

class MainAdapter (
    private val noteList: ArrayList<NotesModel>,
    private val listener: IOnItemClickListener
    ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    class MainViewHolder(layout: View) : RecyclerView.ViewHolder(layout){
        val binding = MainRecyclerRowBinding.bind(layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_recycler_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        holder.binding.historyText.text = noteList.get(position).history

        holder.binding.titleText.text = noteList.get(position).title

        holder.binding.imagePopup.setOnClickListener {
            listener.itemClick(noteList.get(position), it)
        }
    }
}