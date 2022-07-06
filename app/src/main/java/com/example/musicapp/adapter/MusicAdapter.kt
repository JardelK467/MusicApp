package com.example.musicapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.databinding.RowItemsBinding
import com.example.musicapp.domain.DomainMusic
import com.example.musicapp.model.Music
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MusicAdapter@Inject constructor(): RecyclerView.Adapter<Viewholder>() {

    private lateinit var musicList: List<DomainMusic>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder =
        Viewholder(
            RowItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    fun updateMusicList(domainMusic: List<DomainMusic>) {
        this.musicList = domainMusic
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(musicList[position])
    }

    override fun getItemCount(): Int = musicList.size


}

class Viewholder(private val binding: RowItemsBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(music: DomainMusic) {
        binding.artistName.text = music.artistName
        binding.collectionName.text = music.collectionName
        binding.trackPrice.text = music.trackPrice.toString()

        Picasso.get()
            .load(music.artworkUrl60)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit()
            .into(binding.image)
    }

}