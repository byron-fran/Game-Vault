package com.example.gamervault.data.repositories

import com.example.gamervault.data.source.remote.firebase.FirebaseFavoritesDataSource
import com.example.gamervault.domain.models.Favorite
import com.example.gamervault.domain.models.FirestoreFailureException
import com.example.gamervault.domain.repositories.FavoritesRepository
import com.google.firebase.firestore.FirebaseFirestoreException
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val firebaseFavoritesDataSource: FirebaseFavoritesDataSource
) : FavoritesRepository {

    override suspend fun insertFavorite(favorite: Favorite) {
        try {
            firebaseFavoritesDataSource.insertFavorite(favorite)
        }
        catch (e: FirebaseFirestoreException){
            when (e.code){
                FirebaseFirestoreException.Code.UNAVAILABLE -> {
                    throw FirestoreFailureException("Service unavailable", e)
                }
                FirebaseFirestoreException.Code.UNAUTHENTICATED -> {
                    throw FirestoreFailureException("Unauthenticated", e)
                }
                FirebaseFirestoreException.Code.ABORTED -> {
                    throw FirestoreFailureException("Operation aborted", e)
                }
                FirebaseFirestoreException.Code.ALREADY_EXISTS -> {
                    throw FirestoreFailureException("Document already exists", e)
                }
                FirebaseFirestoreException.Code.DATA_LOSS -> {
                    throw FirestoreFailureException("Data loss", e)
                }
                FirebaseFirestoreException.Code.DEADLINE_EXCEEDED -> {
                    throw FirestoreFailureException("Deadline exceeded", e)
                }
                FirebaseFirestoreException.Code.INTERNAL -> {
                    throw FirestoreFailureException("Internal error", e)
                }
                else -> {
                    throw FirestoreFailureException("Unknown error", e)
                }
            }
        }
        catch (e: Exception) {
            throw FirestoreFailureException(e.message.toString(), e)
        }
    }

    override suspend fun getFavorites(): List<Favorite> {
        return try {
            firebaseFavoritesDataSource.getFavorites()
        }
        catch (e: FirebaseFirestoreException){
            when(e.code){
                FirebaseFirestoreException.Code.UNAVAILABLE -> {
                    throw FirestoreFailureException("Service unavailable", e)
                }
                FirebaseFirestoreException.Code.UNAUTHENTICATED -> {
                    throw FirestoreFailureException("Unauthenticated", e)
                }
                FirebaseFirestoreException.Code.UNKNOWN -> {
                    throw FirestoreFailureException("Unknown error or error of connection", e)
                }
                else -> {
                    emptyList()
                }
            }
        }
        catch (e: Exception) {
            throw FirestoreFailureException(e.message.toString(), e)
        }
    }

    override suspend fun deleteFavorite(gameId: String) {
        try {
            firebaseFavoritesDataSource.deleteFavorite(gameId)
        }
        catch (e: FirebaseFirestoreException){
            when(e.code){
                FirebaseFirestoreException.Code.UNAVAILABLE -> {
                    throw FirestoreFailureException("Service unavailable", e)
                }
                FirebaseFirestoreException.Code.UNAUTHENTICATED -> {
                    throw FirestoreFailureException("Unauthenticated", e)
                }
                FirebaseFirestoreException.Code.PERMISSION_DENIED -> {
                    throw FirestoreFailureException("Permission denied", e)
                }
                FirebaseFirestoreException.Code.NOT_FOUND -> {
                    throw FirestoreFailureException("Document not found", e)
                }else ->{
                    throw FirestoreFailureException("Unknown error", e)
                }
            }
        }
        catch (e: Exception) {
            throw FirestoreFailureException(e.message.toString(), e)
        }

    }
}