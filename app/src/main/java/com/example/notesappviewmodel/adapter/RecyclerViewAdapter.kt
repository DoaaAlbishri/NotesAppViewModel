package com.example.notesappviewmodel.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappviewmodel.MainActivity
import com.example.notesappviewmodel.dataBase.Note
import com.example.notesappviewmodel.R
import kotlinx.android.synthetic.main.item_row.view.*
//, private val words: List<Note>
class RecyclerViewAdapter(private val mainActivity: MainActivity) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)
    var ctx : Context? = null
    private var words =  listOf<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        ctx = parent.context
        return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_row
                        ,parent
                        ,false
                )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val word=words[position]

        holder.itemView.apply {
            //color
            if(position%2==0){llItem.setBackgroundColor(Color.GRAY)}
            tv.text = word.note
            editBtn.setOnClickListener {
                mainActivity.update(word.ID)
            }
            delBtn.setOnClickListener {
                mainActivity.delete(word.ID,word.note)
            }
        }
    }

    override fun getItemCount(): Int =words.size

    fun update(notes: List<Note>){
        this.words = notes
        notifyDataSetChanged()
    }

}