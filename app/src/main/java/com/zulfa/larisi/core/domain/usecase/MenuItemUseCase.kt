package com.zulfa.larisi.core.domain.usecase

import com.zulfa.larisi.core.data.Resource
import com.zulfa.larisi.core.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuItemUseCase {
    fun getAllMenuItems(): Flow<Resource<List<MenuItem>>>
}