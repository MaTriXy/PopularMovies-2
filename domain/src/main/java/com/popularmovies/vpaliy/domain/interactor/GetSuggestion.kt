package com.popularmovies.vpaliy.domain.interactor

import com.popularmovies.vpaliy.domain.error
import com.popularmovies.vpaliy.domain.executor.BaseScheduler
import com.popularmovies.vpaliy.domain.interactor.params.Suggestion
import com.popularmovies.vpaliy.domain.repository.MediaRepository
import com.vpaliy.kotlin_extensions.then
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSuggestion<T> @Inject constructor(val repository: MediaRepository<T>, scheduler: BaseScheduler)
    :RequestInteractor<Suggestion<T>,List<T>>(scheduler){

    override fun buildUseCase(params: Suggestion<T>?)
            =params then (repository::fetchSuggested)?:error()
}