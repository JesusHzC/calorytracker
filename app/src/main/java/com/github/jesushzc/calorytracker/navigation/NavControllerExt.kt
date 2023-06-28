package com.github.jesushzc.calorytracker.navigation

import androidx.navigation.NavController
import com.github.jesushzc.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}