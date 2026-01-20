package com.example.gamervault.data.source.remote.firebase

import com.example.gamervault.domain.models.Favorite
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFavoritesDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) {

    suspend fun insertFavorite(favorite: Favorite) {
        val user = firebaseAuth.currentUser
        if (user?.uid.isNullOrEmpty()) return
        val userId = user.uid
        firebaseFirestore
            .collection("users")
            .document(userId)
            .collection("favorites")
            .document(favorite.gameId)
            .set(favorite)
            .await()

    }

    suspend fun getFavorites(): List<Favorite> {
        val user = firebaseAuth.currentUser
        if (user?.uid.isNullOrEmpty()) return  emptyList()
        val userId = user.uid

        val documentSnapShot = firebaseFirestore
            .collection("users")
            .document(userId)
            .collection("favorites")
            .get()
            .await()
            .documents
        val favorites  = documentSnapShot.map { doc  ->
            doc.toObject(Favorite::class.java)
        }
        return favorites.filterNotNull()
    }

    suspend fun deleteFavorite(gameId: String) {
        val user = firebaseAuth.currentUser
        if (user?.uid.isNullOrEmpty()) return
        val userId = user.uid
        firebaseFirestore
            .collection("users")
            .document(userId)
            .collection("favorites")
            .document(gameId)
            .delete()
            .await()
    }
}