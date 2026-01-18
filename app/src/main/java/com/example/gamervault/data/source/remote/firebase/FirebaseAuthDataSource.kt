package com.example.gamervault.data.source.remote.firebase

import android.util.Log
import com.example.gamervault.domain.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) {
    suspend fun signIn(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun signUp(email: String, password: String, username: String?) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val email = task.result?.user?.email
                    val uid = task.result?.user?.uid
                    createUser(email, username, uid)
                }
            }.await()
    }

    fun createUser(email: String?, username: String?, uid: String?) {
        if (email == null || uid == null) {
            Log.w("FirebaseAuthDataSource", "Cannot create user: email or UID is null.")
            return
        }
        firebaseFirestore
            .collection("users")
            .document(uid)
            .set(
                User(uid, email = email, username = username ?: "")
            ).addOnFailureListener { e ->
                Log.e(
                    "FirebaseAuthDataSource",
                    "Error creating user document for UID $uid: ${e.message}",
                    e
                )
            }

    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun isAuthenticated() = firebaseAuth.currentUser != null

    suspend fun getCurrentUser(): User? {
        val currentFirebaseUser = firebaseAuth.currentUser
        if (currentFirebaseUser?.uid == null) {
            return null
        }
        val userId = currentFirebaseUser.uid
        try {
            val documentSnapShot = firebaseFirestore
                .collection("users")
                .document(userId)
                .get()
                .await()
            if (documentSnapShot.exists()) {

                val user = documentSnapShot.toObject(User::class.java)

                return user
            } else {
                return null
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}