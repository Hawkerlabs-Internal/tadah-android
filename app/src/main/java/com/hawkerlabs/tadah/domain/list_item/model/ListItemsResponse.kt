package com.hawkerlabs.tadah.domain.list_item.model

import com.hawkerlabs.tadah.data.database.model.relations.ListItems


sealed class ListItemsResponse {
    data class Success(val data: ListItems) : ListItemsResponse()
    data class Error(val error: Throwable?) : ListItemsResponse()
}