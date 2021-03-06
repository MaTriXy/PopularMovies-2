package com.popularmovies.vpaliy.popularmoviesapp.ui.more

import com.popularmovies.vpaliy.domain.entity.MediaType
import com.popularmovies.vpaliy.domain.interactor.params.TypePage
import com.popularmovies.vpaliy.popularmoviesapp.ui.model.MediaModel
import com.popularmovies.vpaliy.popularmoviesapp.ui.model.PagerFacade
import com.popularmovies.vpaliy.popularmoviesapp.ui.more.MoreContract.View
import com.popularmovies.vpaliy.popularmoviesapp.ui.more.MoreContract.Presenter
import com.popularmovies.vpaliy.popularmoviesapp.di.scope.ViewScope

@ViewScope
class MorePresenter<T>(private val interactor:PagerFacade<T>) :Presenter{

    private lateinit var type:MediaType
    private lateinit var view:View
    private val page by lazy { TypePage(type) }

    override fun attachView(view:View) {
        this.view=view
    }

    override fun attachType(type:MediaType) {
        this.type=type
    }

    override fun more() {
        view.showLoading()
        page.next()
        interactor.execute(this::onSuccess,this::onError,page)
    }

    override fun start() {
        page.current=1
        view.showLoading()
        interactor.execute(this::onSuccess, this::onError, page)
    }

    override fun stop(){}

    private fun onSuccess(page:TypePage,data:List<MediaModel>){
        view.hideLoading()
        if(data.isNotEmpty()){
            if(page.current > 1)
                view.append(data)
            else
                view.show(data)
        }else view.empty()
    }

    private fun onError(ex:Throwable){
        ex.printStackTrace()
        view.hideLoading()
        view.error()
    }

}