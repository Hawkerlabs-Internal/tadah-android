package com.hawkerlabs.tadah.domain.list_item.model

import com.hawkerlabs.tadah.data.database.model.relations.ItemsByList


sealed class ListItemsResponse {
    data class Success(val data: ItemsByList) : ListItemsResponse()
    data class Error(val error: Throwable?) : ListItemsResponse()
}