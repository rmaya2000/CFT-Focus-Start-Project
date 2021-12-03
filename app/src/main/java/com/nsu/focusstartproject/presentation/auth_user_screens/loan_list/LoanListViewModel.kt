package com.nsu.focusstartproject.presentation.auth_user_screens.loan_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsu.focusstartproject.domain.Loan
import com.nsu.focusstartproject.domain.loan_network.GetAllLoansUseCase
import com.nsu.focusstartproject.utils.DataStatus
import com.nsu.focusstartproject.utils.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoanListViewModel @Inject constructor(
    private val getAllLoansUseCase: GetAllLoansUseCase
): ViewModel() {

    private val _loadDataState = MutableLiveData<DataStatus<List<Loan>>>()
    val loadDataState: LiveData<DataStatus<List<Loan>>> = _loadDataState

    private val _navigateToNewLoan = LiveEvent()
    val navigateToNewLoan: LiveData<Unit> = _navigateToNewLoan

    fun loadData(){
        _loadDataState.value = DataStatus.Loading
        viewModelScope.launch {
            val data = getAllLoansUseCase()
            _loadDataState.value = data
        }
    }
}