package com.example.musicapp.presenter
import com.example.musicapp.database.LocalDataRepository
import com.example.musicapp.domain.DomainMusic
import com.example.musicapp.domain.mapToDomainMusic
import com.example.musicapp.rest.MusicRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject



class RockPresenter @Inject constructor(
    private val musicRepository: MusicRepository,
    private val compositeDisposable: CompositeDisposable,
    private val localRockRepository: LocalDataRepository
) : RockPresenterContract {

    private var rockViewContract: RockViewContract? = null

    override fun init(viewContract: RockViewContract) {
        rockViewContract = viewContract
    }

    override fun registerToNetworkState() {
        TODO("Not yet implemented")
    }

    override fun getRock() {
        rockViewContract?.loadingRock(true)
        getRockFromNetwork()
    }

    override fun destroyPresenter() {
        rockViewContract = null
        compositeDisposable.dispose()
    }

    private fun getRockFromNetwork() {
        musicRepository.getRock()
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                localRockRepository.insertMusic(it.musicResults.mapToDomainMusic())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { getRockFromDb() },
                { error ->
                    rockViewContract?.error(error)
                    getRockFromDb()
                }
            )
            .also { compositeDisposable.add(it) }
    }

    private fun getRockFromDb() {
        localRockRepository.getAllMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {rockViewContract?.successResponse(it, true) },
                {rockViewContract?.error(it)})
            .also { compositeDisposable.add(it) }
    }
}

interface RockPresenterContract {
    fun init(viewContract: RockViewContract)
    fun registerToNetworkState()
    fun getRock()
    fun destroyPresenter()
}
interface RockViewContract {
    fun loadingRock(isLoading: Boolean = false)
    fun successResponse(music: List<DomainMusic>, isOffline: Boolean = false)
    fun error(error: Throwable)
}
