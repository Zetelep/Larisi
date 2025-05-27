package com.zulfa.larisi.core.data

import com.zulfa.larisi.core.domain.model.MenuItem
import com.zulfa.larisi.core.domain.repository.IMenuRepository
import kotlinx.coroutines.flow.Flow

class MenuItemRepository(
    private val firestoreDataSource: FirestoreDataSource
) : IMenuRepository {
    override fun getAllMenuItems(): Flow<Resource<List<MenuItem>>> = firestoreDataSource.getAllMenuItems()

}