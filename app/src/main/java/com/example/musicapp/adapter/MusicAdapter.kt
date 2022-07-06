package com.example.musicapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.databinding.RowItemsBinding
import com.example.musicapp.model.Music

class MusicAdapter(private val musicList: List<Music>): RecyclerView.Adapter<Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder =
        Viewholder(
            RowItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(musicList[position])
    }

    override fun getItemCount(): Int = musicList.size


}

class Viewholder(private val binding: RowItemsBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(music: Music) {
        binding.artistName.text = music.artistName
        binding.collectionName.text = music.collectionName
        binding.trackPrice.text = music.trackPrice.toString()
    }

}