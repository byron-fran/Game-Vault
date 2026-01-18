package com.example.gamervault.data.source.remote.firebase

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
        if (email == null  || uid == null) return
        firebaseFirestore
            .collection("users")
            .document(uid)
            .set(
                User(uid, email, username)
            )

    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun isAuthenticated() = firebaseAuth.currentUser != null

    suspend fun getCurrentUser() : User? {
        if(firebaseAuth.currentUser?.uid == null) return null
        val documentSnapShot = firebaseFirestore
            .collection("users")
            .document(firebaseAuth.currentUser?.uid!!)
            .get()
            .await()
        return documentSnapShot.toObject(User::class.java)
    }
}