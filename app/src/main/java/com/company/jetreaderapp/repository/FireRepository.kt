package com.company.jetreaderapp.repository

import com.company.jetreaderapp.data.DataOrException
import com.company.jetreaderapp.model.MBook
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.Exception

class FireRepository @Inject constructor(private val queryBook: Query) {

    suspend fun getAllBooksFromDatabase(): DataOrException<List<MBook>, Boolean, Exception> {
        val data = DataOrException<List<MBook>, Boolean, Exception>()

        try {
            data.loading = true
            data.data = queryBook.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MBook::class.java)!!
            }
            if(!data.data.isNullOrEmpty()) data.loading = false

        } catch (ex: FirebaseFirestoreException) {
            data.e = ex
        }

        return data
    }

}