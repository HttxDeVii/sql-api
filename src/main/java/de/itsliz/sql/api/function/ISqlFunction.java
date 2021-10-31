package de.itsliz.sql.api.function;

import de.itsliz.sql.api.executor.SqlExecutor;

import java.sql.SQLException;

/**
 * @author kleinLisaa
 * @created 31.10.2021
 * <p>
 * Do not edit without permission.
 */
public interface ISqlFunction<I, O> {

    O apply(I i) throws SQLException;
}
