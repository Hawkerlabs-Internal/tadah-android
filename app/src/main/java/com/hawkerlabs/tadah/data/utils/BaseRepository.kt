package com.hawkerlabs.tadah.data.utils

import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


abstract class BaseRepository {
    suspend fun <T> invoke(apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is NoSuchElementException -> {
                        ResultWrapper.GenericError(null, null)
                    }
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.GenericError(code, errorResponse)
                    }
                    else -> {
                        ResultWrapper.GenericError(null, null)
                    }
                }
            }
        }
    }


    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}