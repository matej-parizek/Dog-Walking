package cz.ctu.fit.bi.and.parizmat.semestral.core.data

import com.google.firebase.crashlytics.FirebaseCrashlytics
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.EmptyDatabaseException
import cz.ctu.fit.bi.and.parizmat.semestral.core.data.db.NotFoundDatabaseException
import retrofit2.HttpException
import java.io.IOException

/**
 * Handles exceptions that occur during API calls and logs relevant information to Firebase Crashlytics.
 * This function returns a Response object encapsulating the error type.
 *
 * @param e The exception that occurred during the API call and device network problem.
 * @return A Response object with the corresponding DataError.Api type.
 */
fun handleExceptionApi(e: Exception): Response<Nothing, DataError> {
    return when (e) {
        is IOException -> {
//            FirebaseCrashlytics.getInstance().log("WARNING: Device is not connect to the network")
            Response.Error(DataError.Api.NO_INTERNET)
        }
        is HttpException -> {
            when (e.code()) {
                400 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: BAD REQUEST")
                    Response.Error(DataError.Api.BAD_REQUEST)
                }

                401 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: UNAUTHORIZED")
                    Response.Error(DataError.Api.UNAUTHORIZED)
                }

                403 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: FORBIDDEN")
                    Response.Error(DataError.Api.FORBIDDEN)
                }

                404 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: NOT FOUND")
                    Response.Error(DataError.Api.NOT_FOUND)
                }

                405 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: METHOD NOT ALLOWED")
                    Response.Error(DataError.Api.METHOD_NOT_ALLOWED)
                }

                406 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: NOT ACCEPTABLE")
                    Response.Error(DataError.Api.NOT_ACCEPTABLE)
                }

                409 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: CONFLICT")
                    Response.Error(DataError.Api.CONFLICT)
                }

                410 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: GONE")
                    Response.Error(DataError.Api.GONE)
                }

                411 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: LENGTH REQUIRED")
                    Response.Error(DataError.Api.LENGTH_REQUIRED)
                }

                412 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: PRECONDITION FAILED")
                    Response.Error(DataError.Api.PRECONDITION_FAILED)
                }

                413 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: PAYLOAD TOO LARGE")
                    Response.Error(DataError.Api.PAYLOAD_TOO_LARGE)
                }

                414 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: URI TOO LONG")
                    Response.Error(DataError.Api.URI_TOO_LONG)
                }

                415 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: UNSUPPORTED MEDIA TYPE")
                    Response.Error(DataError.Api.UNSUPPORTED_MEDIA_TYPE)
                }

                416 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: RANGE NOT SATISFIABLE")
                    Response.Error(DataError.Api.RANGE_NOT_SATISFIABLE)
                }

                417 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: EXPECTATION FAILED")
                    Response.Error(DataError.Api.EXPECTATION_FAILED)
                }

                418 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: I'M A TEAPOT")
                    Response.Error(DataError.Api.IM_A_TEAPOT)
                }

                421 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: MISDIRECTED REQUEST")
                    Response.Error(DataError.Api.MISDIRECTED_REQUEST)
                }

                422 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: UNPROCESSABLE ENTITY")
                    Response.Error(DataError.Api.UNPROCESSABLE_ENTITY)
                }

                423 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: LOCKED")
                    Response.Error(DataError.Api.LOCKED)
                }

                424 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: FAILED DEPENDENCY")
                    Response.Error(DataError.Api.FAILED_DEPENDENCY)
                }

                425 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: TOO EARLY")
                    Response.Error(DataError.Api.TOO_EARLY)
                }

                426 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: UPGRADE REQUIRED")
                    Response.Error(DataError.Api.UPGRADE_REQUIRED)
                }

                428 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: PRECONDITION REQUIRED")
                    Response.Error(DataError.Api.PRECONDITION_REQUIRED)
                }

                429 -> {
//                    FirebaseCrashlytics.getInstance().log("API error: TOO MANY REQUESTS")
                    Response.Error(DataError.Api.TOO_MANY_REQUESTS)
                }

                431 -> {
//                    FirebaseCrashlytics.getInstance()
//                        .log("API error: REQUEST HEADER FIELDS TOO LARGE")
                    Response.Error(DataError.Api.REQUEST_HEADER_FIELDS_TOO_LARGE)
                }

                451 -> {
//                    FirebaseCrashlytics.getInstance()
//                        .log("API error: UNAVAILABLE FOR LEGAL REASONS")
                    Response.Error(DataError.Api.UNAVAILABLE_FOR_LEGAL_REASONS)
                }

                else -> {
//                    FirebaseCrashlytics.getInstance().log("API error code: UNKNOWN ERROR with status code ${e.code()}")
                    e.let {
//                        FirebaseCrashlytics.getInstance().log("Exception message: ${it.message}")
//                        FirebaseCrashlytics.getInstance().recordException(it)
                    }
                    Response.Error(DataError.Api.UNKNOWN_ERROR)
                }
            }
        }
        else -> {
//            FirebaseCrashlytics.getInstance().log("API error code: UNKNOWN ERROR network exception")
            e.let {
//                FirebaseCrashlytics.getInstance().log("Exception message: ${it.message}")
//                FirebaseCrashlytics.getInstance().recordException(it)
            }
            Response.Error(DataError.Api.UNKNOWN_ERROR)
        }
    }
}

fun handleExceptionDatabase(e: Exception): Response<Nothing,DataError>{
    return when(e){
        is EmptyDatabaseException -> Response.Error(DataError.Database.EMPTY)
        is NotFoundDatabaseException -> Response.Error(DataError.Database.NOT_FOUND)
        else -> Response.Error(DataError.Database.UNKNOWN_ERROR)
    }
}