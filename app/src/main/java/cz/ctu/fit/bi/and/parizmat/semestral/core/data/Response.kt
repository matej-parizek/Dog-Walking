package cz.ctu.fit.bi.and.parizmat.semestral.core.data

sealed interface Error

/**
 * DataErrors possibilities
 */
sealed interface DataError : Error {
    /**
     * API errors code
     */
    enum class Api(
    ) : DataError {
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        METHOD_NOT_ALLOWED,
        NOT_ACCEPTABLE,
        CONFLICT,
        GONE,
        LENGTH_REQUIRED,
        PRECONDITION_FAILED,
        PAYLOAD_TOO_LARGE,
        URI_TOO_LONG,
        UNSUPPORTED_MEDIA_TYPE,
        RANGE_NOT_SATISFIABLE,
        EXPECTATION_FAILED,
        IM_A_TEAPOT,
        MISDIRECTED_REQUEST,
        UNPROCESSABLE_ENTITY,
        LOCKED,
        FAILED_DEPENDENCY,
        TOO_EARLY,
        UPGRADE_REQUIRED,
        PRECONDITION_REQUIRED,
        TOO_MANY_REQUESTS,
        REQUEST_HEADER_FIELDS_TOO_LARGE,
        UNAVAILABLE_FOR_LEGAL_REASONS,
        UNKNOWN_ERROR,      //Unexpected error
        NO_INTERNET,
    }

    /**
     * Database possible errors
     */
    enum class Database : DataError {
        NOT_FOUND,
        EMPTY,
        UNKNOWN_ERROR,

    }
}

/**
 * Rename Error for collision name of value
 */
typealias ErrorState = Error

/**
 * State of screen
 */
sealed interface Response<out O, out E : ErrorState> {
    data object Loading : Response<Nothing, Nothing>
    data class Success<out O, out E : ErrorState>(val data: O) : Response<O, E>
    data class Error<out O, out E : ErrorState>(val data: E) : Response<O, E>
}