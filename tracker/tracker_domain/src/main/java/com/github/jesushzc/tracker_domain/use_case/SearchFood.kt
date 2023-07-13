package com.github.jesushzc.tracker_domain.use_case

import com.github.jesushzc.tracker_domain.model.TrackableFood
import com.github.jesushzc.tracker_domain.repository.TrackerRepository

class SearchFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        searchTerms: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if (searchTerms.isBlank()) {
            return Result.success(emptyList())
        }
        return repository.searchFood(
            searchTerms = searchTerms.trim(),
            page = page,
            pageSize = pageSize
        )
    }

}