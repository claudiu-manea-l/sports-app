package com.codez.sportsapp.presentation.fixture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codez.sportsapp.domain.usecase.GetFixtures
import com.codez.sportsapp.presentation.fixture.viewdata.FixtureViewData
import com.codez.sportsapp.presentation.fixture.viewdata.toViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixturesViewModel @Inject constructor(
    private val getFixtures: GetFixtures
): ViewModel() {

    private val _viewState = MutableStateFlow<FixturesViewState>(FixturesViewState.Loading)
    val viewState : StateFlow<FixturesViewState> = _viewState

    init {
        loadFixtures()
    }

    fun loadFixtures() = viewModelScope.launch {
        _viewState.value = FixturesViewState.Loading
        try {
            getFixtures()
                .let {
                    _viewState.value = FixturesViewState.ViewState(it.toViewData())
                }
        } catch (e:Exception) {
            e.printStackTrace()
            _viewState.value = FixturesViewState.Error
        }

    }
}

sealed class FixturesViewState {
    data object Loading : FixturesViewState()
    data class ViewState(
        val viewData: FixturesViewData
    ) : FixturesViewState()

    data object Error : FixturesViewState()
}

class FixturesViewData(
    val fixtures : List<FixtureViewData>
)
