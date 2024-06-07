package cz.ctu.fit.bi.and.parizmat.semestral.core.data.db

/**
 * Exception thrown when a requested item is not found in the database.
 * This exception is typically used in scenarios where a database query fails to locate an entry that is expected to exist,
 * indicating perhaps a logical error in either data handling or query formulation.
 */
class NotFoundDatabaseException : RuntimeException()
/**
 * Exception thrown when a database is unexpectedly empty.
 * This is used to signal situations where a database operation such as retrieval is attempted on an empty dataset,
 * where the expectation is to have one or more entries available. This can help in diagnosing issues with data loading
 * or initialization processes.
 */
class EmptyDatabaseException : RuntimeException()
