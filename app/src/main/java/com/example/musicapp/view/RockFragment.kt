package com.example.musicapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.MusicApp
import com.example.musicapp.adapter.MusicAdapter
import com.example.musicapp.databinding.FragmentPopBinding
import com.example.musicapp.model.MusicResponse
import com.example.musicapp.databinding.FragmentRockBinding
import com.example.musicapp.domain.DomainMusic
import com.example.musicapp.presenter.PopPresenterContract
import com.example.musicapp.presenter.RockPresenterContract
import com.example.musicapp.presenter.RockViewContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RockFragment : Fragment(), RockViewContract {


    @Inject
    lateinit var rockPresenterContract : RockPresenterContract

    @Inject
    lateinit var musicAdapter: MusicAdapter

    private val binding by lazy{
        FragmentRockBinding.inflate(layoutInflater)

    }

    private fun initRecyclerView(){
        binding.recyclerViewRock.apply {
            this.layoutManager = LinearLayoutManager (requireContext(),
                LinearLayoutManager.VERTICAL,false)
            adapter = musicAdapter

        }
    }

    private fun rockPresenter() {
        rockPresenterContract.init(this)
        rockPresenterContract.getRock()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MusicApp.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rockPresenter()
        initRecyclerView()
        return binding.root
    }


    override fun loadingRock(isLoading: Boolean) {
        //TODO("Not yet implemented")
    }

    override fun successResponse(songs: List<DomainMusic>, isOffline: Boolean) {
        musicAdapter.updateMusicList(songs)
    }

    override fun error(error: Throwable) {
        // TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        rockPresenterContract.destroyPresenter()
    }
}
}