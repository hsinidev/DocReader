package com.example.docreader.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.docreader.domain.model.DocAnnotation
import com.example.docreader.domain.model.RecentFile
import com.example.docreader.domain.model.EntitlementState
import com.example.docreader.domain.model.ReaderTheme
import com.example.docreader.domain.repository.DocRepository
import com.example.docreader.domain.usecase.LoadDocumentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DocReaderViewModel @Inject constructor(
    private val repository: DocRepository,
    private val loadDocumentUseCase: LoadDocumentUseCase
) : ViewModel() {

    private val _recentFiles = MutableStateFlow<List<RecentFile>>(emptyList())
    val recentFiles: StateFlow<List<RecentFile>> = _recentFiles.asStateFlow()

    private val _activeFile = MutableStateFlow<RecentFile?>(null)
    val activeFile: StateFlow<RecentFile?> = _activeFile.asStateFlow()

    private val _annotations = MutableStateFlow<List<DocAnnotation>>(emptyList())
    val annotations: StateFlow<List<DocAnnotation>> = _annotations.asStateFlow()

    private val _readerTheme = MutableStateFlow(ReaderTheme.DAY_PAPER)
    val readerTheme: StateFlow<ReaderTheme> = _readerTheme.asStateFlow()

    private val _entitlement = MutableStateFlow(EntitlementState.Pro)
    val entitlement: StateFlow<EntitlementState> = _entitlement.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getRecentFiles().collect {
                _recentFiles.value = it
            }
        }
        viewModelScope.launch {
            repository.getEntitlementState().collect {
                _entitlement.value = it
            }
        }
    }

    fun openDocument(uri: Uri) {
        viewModelScope.launch {
            val file = loadDocumentUseCase(uri)
            _activeFile.value = file
            repository.getAnnotations(file.id).collect {
                _annotations.value = it
            }
        }
    }

    fun addInkAnnotation(coords: String) {
        val file = _activeFile.value ?: return
        viewModelScope.launch {
            val annot = DocAnnotation(
                id = UUID.randomUUID().toString(),
                fileHash = file.id,
                type = "INK",
                data = coords,
                timestamp = System.currentTimeMillis()
            )
            repository.saveAnnotation(annot)
        }
    }

    fun setReaderTheme(theme: ReaderTheme) {
        _readerTheme.value = theme
    }

    fun purchasePremium() {
        viewModelScope.launch {
            repository.purchasePremium()
        }
    }
}
