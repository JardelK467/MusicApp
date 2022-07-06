package com.example.musicapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.MusicApp
import com.example.musicapp.adapter.MusicAdapter
import com.example.musicapp.databinding.FragmentPopBinding
import com.example.musicapp.domain.DomainMusic
import com.example.musicapp.presenter.PopPresenterContract
import com.example.musicapp.presenter.PopViewContract
import javax.inject.Inject


class PopFragment : Fragment(), PopViewContract {

    @Inject
    lateinit var popPresenterContract : PopPresenterContract

    @Inject
    lateinit var musicAdapter: MusicAdapter

    private val binding by lazy{
        FragmentPopBinding.inflate(layoutInflater)

    }

    private fun initRecyclerView(){
        binding.recyclerViewPop.apply {
            this.layoutManager = LinearLayoutManager (requireContext(),
                LinearLayoutManager.VERTICAL,false)
            adapter = musicAdapter

        }
    }

    private fun popPresenter() {
        popPresenterContract.init(this)
        popPresenterContract.getPop()
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
        popPresenter()
        initRecyclerView()
        return binding.root
    }


    override fun loadingPop(isLoading: Boolean) {
        //indicate that the music selected is loading
    }

    override fun successResponse(songs: List<DomainMusic>, isOffline: Boolean) {
        musicAdapter.updateMusicList(songs)
    }

    override fun error(error: Throwable) {
        //Shows an error message
    }

    override fun onDestroy() {
        super.onDestroy()
        popPresenterContract.destroyPresenter()
    }
}