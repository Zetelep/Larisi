package com.zulfa.larisi.core.domain.usecase

import com.zulfa.larisi.core.data.Resource
import com.zulfa.larisi.core.domain.model.MenuItem
import com.zulfa.larisi.core.domain.repository.IMenuRepository
import kotlinx.coroutines.flow.Flow

class MenuItemInteractor(
    private val repository: IMenuRepository
):MenuItemUseCase {
    override fun getAllMenuItems(): Flow<Resource<List<MenuItem>>> {
        return repository.getAllMenuItems()
    }


}