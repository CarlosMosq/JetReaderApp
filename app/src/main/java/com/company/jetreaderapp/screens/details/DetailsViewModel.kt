package com.company.jetreaderapp.screens.details

import androidx.lifecycle.ViewModel
import com.company.jetreaderapp.data.Resource
import com.company.jetreaderapp.model.Item
import com.company.jetreaderapp.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository) : ViewModel() {
    suspend fun getBookInfo(bookId: String) : Resource<Item> {
        return repository.getBookInfo(bookId = bookId)
    }
}