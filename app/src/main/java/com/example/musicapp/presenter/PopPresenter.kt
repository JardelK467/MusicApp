package com.example.musicapp.presenter

import com.example.musicapp.database.LocalDataRepository
import com.example.musicapp.domain.DomainMusic
import com.example.musicapp.domain.mapToDomainMusic
import com.example.musicapp.rest.MusicRepository
import com.example.musicapp.view.PopFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PopPresenter(private val musicRepository: MusicRepository,
                       private val compositeDisposable: CompositeDisposable,
                       private val localPopRepository: LocalDataRepository
) : PopPresenterContract {

    private var popViewContract: PopViewContract? = null

    override fun init(viewContract: PopViewContract) {
        popViewContract = viewContract
    }

    override fun getPop() {
        popViewContract?.loadingPop(true)
        getPopFromNetwork()
    }

    override fun destroyPresenter() {
        popViewContract = null
        compositeDisposable.dispose()
    }

    private fun getPopFromNetwork() {
        musicRepository.getPop()
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                localPopRepository.insertMusic(it.musicResults.mapToDomainMusic())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { getPopFromDb() },
                { error ->
                    popViewContract?.error(error)
                    getPopFromDb()
                }
            )
            .also { compositeDisposable.add(it) }
    }

    private fun getPopFromDb() {
        localPopRepository.getAllMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {popViewContract?.successResponse(it, true) },
                {popViewContract?.error(it)})
            .also { compositeDisposable.add(it) }
    }
}

interface PopPresenterContract {
    fun init(viewContract: PopViewContract)
    fun getPop()
    fun destroyPresenter()
}
interface PopViewContract {
    fun loadingPop(isLoading: Boolean = false)
    fun successResponse(cards: List<DomainMusic>, isOffline: Boolean = false)
    fun error(error: Throwable)
}