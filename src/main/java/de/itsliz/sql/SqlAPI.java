package de.itsliz.sql;

import de.itsliz.sql.api.connector.SqlConnector;

/**
 * @author kleinLisaa
 * @created 31.10.2021
 * <p>
 * Do not edit without permission.
 */
public class SqlAPI {

    private static SqlAPI instance;
    private final SqlConnector connector;

    public SqlAPI(String host, int port, String username, String password, String database){
        instance = this;
        connector = new SqlConnector(host, port, username, password, database);
    }

    public static SqlAPI getInstance() {
        return instance;
    }

    public SqlConnector getConnector() {
        return connector;
    }
}
