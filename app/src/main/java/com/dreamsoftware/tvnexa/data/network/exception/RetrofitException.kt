package com.dreamsoftware.tvnexa.data.network.exception

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Retrofit Exception
 */
class RetrofitException(
    message: String,
    /** The request URL which produced the response.  */
    val url: String? = null,
    /** Response object containing status code, headers, body, etc.  */
    val response: Response<*>? = null,
    /** The event kind which triggered this response.  */
    val kind: Kind,
    exception: Throwable? = null) : RuntimeException(message, exception) {


    /** COMPANION OBJETC, CONST ENUM, INNER CLASS **/

    companion object {

        private const val serialVersionUID = 1L

        @JvmStatic
        fun httpError(url: String, response: Response<*>): RetrofitException {
            val message = response.code().toString() + " " + response.message()
            return RetrofitException(message, url, response, Kind.HTTP)
        }

        @JvmStatic
        fun networkError(exception: IOException): RetrofitException {
            return RetrofitException(
                message = exception.message.orEmpty(),
                kind = Kind.NETWORK
            )
        }

        @JvmStatic
        fun unexpectedError(exception: Throwable): RetrofitException {
            return RetrofitException(
                message = exception.message.orEmpty(),
                kind = Kind.UNEXPECTED,
                exception = exception)
        }


        /**
         * As Retrofit Exception
         * @param throwable
         * @return
         */
        @JvmStatic
        fun asRetrofitException(throwable: Throwable): RetrofitException {
            // We had non-200 http response
            if (throwable is HttpException) {
                return throwable.response()?.let {
                    httpError(it.raw().request().url().toString(), it)
                }?: unexpectedError(throwable)
            }
            // A network response happened
            return if (throwable is IOException) {
                networkError(throwable)
            }
            else unexpectedError(throwable)

        }
    }


    /** Identifies the event kind which triggered a [RetrofitException].  */
    enum class Kind {
        /** An [IOException] occurred while communicating to the server.  */
        NETWORK,
        /** A non-200 HTTP status code was received from the server.  */
        HTTP,
        /**
         * An internal response occurred while attempting to wrapNetworkCall a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

}