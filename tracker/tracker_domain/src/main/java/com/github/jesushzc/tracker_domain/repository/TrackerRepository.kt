package com.github.jesushzc.tracker_domain.repository

import com.github.jesushzc.tracker_domain.model.TrackableFood
import com.github.jesushzc.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        searchTerms: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(date: LocalDate): Flow<List<TrackedFood>>

}