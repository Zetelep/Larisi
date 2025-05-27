package com.zulfa.larisi.core.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.zulfa.larisi.core.domain.model.MenuItem
import com.zulfa.larisi.core.utils.DataMapper.toDomain
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class FirestoreDataSource(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    fun getAllMenuItems(): Flow<Resource<List<MenuItem>>> = callbackFlow {
        trySend(Resource.Loading())

        val userId = auth.currentUser?.uid
        if (userId == null) {
            trySend(Resource.Error("User not logged in"))
            close()
            return@callbackFlow
        }

        val listener = firestore.collection("users")
            .document(userId)
            .collection("menuItems")
            .addSnapshotListener { menuSnapshot, error ->

                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Unknown error"))
                    return@addSnapshotListener
                }

                if (menuSnapshot != null) {
                    val allItems = mutableListOf<MenuItem>()

                    // For each menuId document, get the fields of the items
                    menuSnapshot.documents.forEach { menuDoc ->
                        val itemName = menuDoc.getString("itemName")
                        val price = menuDoc.getDouble("price")
                        val imageUrl = menuDoc.getString("imageUrl")

                        // Map to MenuItem and add it to allItems
                        if (itemName != null && price != null && imageUrl != null) {
                            val menuItemResponse = MenuItemResponse(itemName, price, imageUrl)
                            val menuItem = menuItemResponse.toDomain(menuDoc.id)
                            allItems.add(menuItem)
                        }
                    }

                    trySend(Resource.Success(allItems.toList())) // Emit the immutable list
                }
            }

        // Close listener when flow is closed
        awaitClose { listener.remove() }
    }.distinctUntilChanged()
}