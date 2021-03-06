package com.popularmovies.vpaliy.domain.interactor

import com.popularmovies.vpaliy.domain.error
import com.popularmovies.vpaliy.domain.executor.BaseScheduler
import com.popularmovies.vpaliy.domain.interactor.params.SearchPage
import com.popularmovies.vpaliy.domain.repository.SearchRepository
import com.vpaliy.kotlin_extensions.then
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchInteractor<T> @Inject constructor(var repository: SearchRepository<T>,scheduler: BaseScheduler)
    :RequestInteractor<SearchPage,List<T>>(scheduler){

    override fun buildUseCase(params: SearchPage?)
            =params then (repository::search)?: error()
}