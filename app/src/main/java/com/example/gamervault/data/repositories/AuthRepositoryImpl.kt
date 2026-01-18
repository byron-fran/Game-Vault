package com.example.gamervault.data.repositories

import com.example.gamervault.data.source.remote.firebase.FirebaseAuthDataSource
import com.example.gamervault.domain.models.AuthFailureException
import com.example.gamervault.domain.models.User
import com.example.gamervault.domain.repositories.AuthRepository
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestoreException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: FirebaseAuthDataSource
) : AuthRepository {

    override suspend fun signIn(email: String, password: String) {
        try {
            authDataSource.signIn(email, password)

        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw AuthFailureException("Invalid email or password.", e)
        } catch (e: FirebaseAuthException) {
            when (e.errorCode) {
                "ERROR_USER_NOT_FOUND" -> throw AuthFailureException("User not found.", e)
                "ERROR_INVALID_EMAIL" -> throw AuthFailureException("Invalid email.", e)
                "ERROR_USER_DISABLED" -> throw AuthFailureException("Your account has been disabled.", e)
                "ERROR_WRONG_PASSWORD" -> throw AuthFailureException("Invalid password.", e)
                else -> throw AuthFailureException("Error: ${e.message}", e)
            }
        } catch (e: Exception) {
            throw AuthFailureException("Error of connection, try later.", e)
        }
    }

    override suspend fun signUp(email: String, password: String, username: String?) {
        try {

            authDataSource.signUp(email, password, username)
        } catch (e: FirebaseAuthUserCollisionException) {
            throw AuthFailureException("Email is already in use.", e)
        } catch (e: FirebaseAuthException) {
            when (e.errorCode) {
                "ERROR_WEAK_PASSWORD" -> throw AuthFailureException("Password is to week.", e)
                "ERROR_INVALID_EMAIL" -> throw AuthFailureException("Format email is invalid.", e)
                else -> throw AuthFailureException("Error de of register: ${e.message}", e)
            }
        } catch (e: Exception) {
            throw AuthFailureException("Error of connection, try later.", e)
        }
    }

    override suspend fun getCurrentUser(): User? {
        return try {
            authDataSource.getCurrentUser()
        } catch (e: FirebaseFirestoreException) {
            when (e.code) {
                FirebaseFirestoreException.Code.PERMISSION_DENIED ->
                    throw AuthFailureException("You don't have permission to access this data.", e)

                FirebaseFirestoreException.Code.NOT_FOUND ->
                    throw AuthFailureException("User profile not found.", e)

                FirebaseFirestoreException.Code.UNAVAILABLE ->
                    throw AuthFailureException("Service temporarily unavailable, try later.", e)

                FirebaseFirestoreException.Code.UNAUTHENTICATED ->
                    throw AuthFailureException("Session expired, please sign in again.", e)

                FirebaseFirestoreException.Code.CANCELLED -> null
                FirebaseFirestoreException.Code.DEADLINE_EXCEEDED ->
                    throw AuthFailureException("Request timed out, try again.", e)

                else ->
                    throw AuthFailureException("Error loading user: ${e.message}", e)
            }
        } catch (e: FirebaseNetworkException) {
            throw AuthFailureException("No internet connection.", e)
        } catch (e: Exception) {
            throw AuthFailureException("Unexpected error loading user profile.", e)
        }
    }

    override suspend fun isAuthenticated(): Boolean {
        return try {

            authDataSource.isAuthenticated()
        }catch (e: FirebaseAuthException){
            when(e.errorCode){
                "ERROR_USER_TOKEN_EXPIRED" -> throw AuthFailureException("Session expired, please sign in again.", e)
                "ERROR_USER_MISMATCH" -> throw AuthFailureException("User profile not found.", e)
            }
            false
        }catch (e: Exception){
            throw AuthFailureException("Unexpected error.", e)
        }
    }

    override suspend fun signOut() {
        try {
            authDataSource.signOut()
        } catch (e: FirebaseAuthException) {
            when (e.errorCode) {
                else -> throw AuthFailureException("Failed to sign out: ${e.message}", e)
            }
        } catch (e: FirebaseNetworkException) {
            throw AuthFailureException("No internet connection. Failed to sign out.", e)
        } catch (e: Exception) {
            throw AuthFailureException("An unexpected error occurred during sign out.", e)
        }
    }
}