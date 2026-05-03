package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.repository.AiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AiUiState {
    object Idle : AiUiState()
    object Loading : AiUiState()
    data class Success(val summary: String) : AiUiState()
    data class Error(val message: String) : AiUiState()
}

class AiViewModel(private val aiRepository: AiRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<AiUiState>(AiUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun summarizeNote(content: String) {
        viewModelScope.launch {
            _uiState.value = AiUiState.Loading
            aiRepository.summarize(content)
                .onSuccess { summary ->
                    _uiState.value = AiUiState.Success(summary)
                }
                .onFailure { error ->
                    _uiState.value = AiUiState.Error(error.message ?: "Terjadi kesalahan saat meringkas")
                }
        }
    }

    fun resetState() {
        _uiState.value = AiUiState.Idle
    }
}
