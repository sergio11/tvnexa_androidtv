package com.dreamsoftware.tvnexa.data.network.core

import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.network.exception.RetrofitException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

internal abstract class SupportNetworkDataSource {

    private companion object {
        /**
         * Some HTTP response codes that We could get when making something request
         */
        const val BAD_REQUEST_CODE: Int = 400
        const val UNAUTHORIZED_CODE: Int = 401
        const val NOT_FOUND_CODE: Int = 404
        const val INTERNAL_SERVER_ERROR_CODE: Int = 500
        const val CONFLICT_ERROR_CODE: Int = 409
        const val FORBIDDEN_CODE: Int = 403
    }

    /**
     * Wrap for safe Network Call
     * @param onExecuted
     */
    protected suspend fun <T> safeNetworkCall(onExecuted: suspend () -> T): T =
        withContext(Dispatchers.IO) {
            try {
                onExecuted()
            } catch (exception: IOException) {
                exception.printStackTrace()
                // map interrupted I/O to Network No Internet Exception
                throw NetworkException.NoInternetException()
            } catch (ex: NetworkException) {
                throw ex
            } catch (exception: Throwable) {
                exception.printStackTrace()
                val retrofitException = RetrofitException.asRetrofitException(exception)
                if (retrofitException.kind === RetrofitException.Kind.NETWORK) {
                    throw NetworkException.ErrorException(cause = exception)
                } else {
                    try {
                        throw onApiException(retrofitException)
                    } catch (e1: IOException) {
                        e1.printStackTrace()
                        throw NetworkException.ErrorException(cause = e1)
                    }
                }
            }
        }

    /**
     * Map HTTP Error codes to exceptions to easy handler
     * @param apiException
     */
    open fun onApiException(apiException: RetrofitException): Exception =
        apiException.response?.let {
            apiException.printStackTrace()
            when (it.code()) {
                BAD_REQUEST_CODE -> NetworkException.BadRequestException(
                    message = it.message(),
                    cause = apiException
                )
                UNAUTHORIZED_CODE -> NetworkException.UnauthorizedException(
                    message = it.message(),
                    cause = apiException
                )
                FORBIDDEN_CODE -> NetworkException.ForbiddenException(
                    message = it.message(),
                    cause = apiException
                )
                NOT_FOUND_CODE -> NetworkException.NoResultException(
                    message = it.message(),
                    cause = apiException
                )
                INTERNAL_SERVER_ERROR_CODE -> NetworkException.ErrorException(
                    message = it.message(),
                    cause = apiException
                )
                CONFLICT_ERROR_CODE -> NetworkException.UnverifiedAccountException(
                    message = it.message(),
                    cause = apiException
                )
                else -> NetworkException.ErrorException()
            }
        } ?: NetworkException.ErrorException()
}