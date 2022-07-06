package com.example.musicapp.presenter

import com.example.musicapp.database.LocalDataRepository
import com.example.musicapp.domain.DomainMusic
import com.example.musicapp.domain.mapToDomainMusic
import com.example.musicapp.rest.MusicRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClassicPresenter@Inject constructor(private val musicRepository: MusicRepository,
                                          private val compositeDisposable: CompositeDisposable,
                                          private val localClassicRepository: LocalDataRepository
) : ClassicPresenterContract {

    private var classicViewContract: ClassicViewContract? = null

    override fun init(viewContract: ClassicViewContract) {
        classicViewContract = viewContract
    }

    override fun getClassic() {
        classicViewContract?.loadingClassic(true)
        getClassicFromNetwork()
    }

    override fun destroyPresenter() {
        classicViewContract = null
        compositeDisposable.dispose()
    }

    private fun getClassicFromNetwork() {
        musicRepository.getClassic()
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                localClassicRepository.insertMusic(it.musicResults.mapToDomainMusic())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { getClassicFromDb() },
                { error ->
                    classicViewContract?.error(error)
                    getClassicFromDb()
                }
            )
            .also { compositeDisposable.add(it) }
    }

    private fun getClassicFromDb() {
        localClassicRepository.getAllMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {classicViewContract?.successResponse(it, true) },
                {classicViewContract?.error(it)})
            .also { compositeDisposable.add(it) }
    }
}

interface ClassicPresenterContract {
    fun init(viewContract: ClassicViewContract)
    fun getClassic()
    fun destroyPresenter()
}
interface ClassicViewContract {
    fun loadingClassic(isLoading: Boolean = false)
    fun successResponse(music: List<DomainMusic>, isOffline: Boolean = false)
    fun error(error: Throwable)
}
