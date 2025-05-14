package com.codez.sportsapp.presentation.match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codez.sportsapp.domain.usecase.FilterOutGoals
import com.codez.sportsapp.domain.usecase.GetMatch
import com.codez.sportsapp.presentation.match.viewdata.MatchDetailsViewData
import com.codez.sportsapp.presentation.match.viewdata.toViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val getMatch: GetMatch,
    private val filterOutGoals: FilterOutGoals
) : ViewModel() {

    private val _viewState = MutableStateFlow<MatchDetailsViewState>(MatchDetailsViewState.Loading)
    val viewState: StateFlow<MatchDetailsViewState> = _viewState

    init {
        loadMatchDetails(1,true)
    }

    fun loadMatchDetails(matchId: Int, onlyGoals:Boolean) {
        _viewState.value = MatchDetailsViewState.Loading
        kotlin.runCatching {
            if(onlyGoals){
                withFilter(matchId)
            } else {
                fetchMatchDetails(matchId)
            }
        }.onFailure {
            _viewState.value = MatchDetailsViewState.Error
        }
    }

    private fun fetchMatchDetails(matchId: Int) = viewModelScope.launch {
        getMatch(matchId)
            .collect { match ->
                _viewState.value = MatchDetailsViewState.ViewState(match.toViewData())
            }
    }

    private fun withFilter(matchId: Int) = viewModelScope.launch {
        getMatch(matchId)
            .let { filterOutGoals(it) }
            .collect { match ->
                _viewState.value = MatchDetailsViewState.ViewState(match.toViewData())
            }
    }
}

sealed class MatchDetailsViewState {
    data object Loading : MatchDetailsViewState()
    data class ViewState(
        val viewData: MatchDetailsViewData
    ) : MatchDetailsViewState()

    data object Error : MatchDetailsViewState()
}

