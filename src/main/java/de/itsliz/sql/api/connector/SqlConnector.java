package de.itsliz.sql.api.connector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.itsliz.sql.api.executor.SqlExecutor;

import java.sql.SQLException;

/**
 * @author kleinLisaa
 * @created 31.10.2021
 * <p>
 * Do not edit without permission.
 */
public class SqlConnector {

    private final HikariDataSource hikariDataSource;
    private SqlExecutor executor;

    public SqlConnector(String host, int port, String username, String password, String database){
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        String[] TRUE_CONNECT_ARGUMENTS = new String[]{"cachePrepStmts", "useServerPrepStmts", "useLocalSessionState",
                "rewriteBatchedStatements", "cacheResultSetMetadata", "cacheServerConfiguration", "elideSetAutoCommits"};
        for (String true_connect_argument : TRUE_CONNECT_ARGUMENTS) {
            hikariConfig.addDataSourceProperty(true_connect_argument, "true");
        }
        hikariConfig.addDataSourceProperty("maintainTimeStats", "false");

        hikariDataSource = new HikariDataSource(hikariConfig);

        try {
            executor = new SqlExecutor(hikariDataSource.getConnection());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void disconnect(){
        hikariDataSource.close();
    }

    public SqlExecutor getExecutor() {
        return executor;
    }
}
