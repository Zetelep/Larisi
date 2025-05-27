package com.zulfa.larisi.core.domain.repository

import com.zulfa.larisi.core.data.Resource
import com.zulfa.larisi.core.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface IMenuRepository {
        fun getAllMenuItems(): Flow<Resource<List<MenuItem>>>
}