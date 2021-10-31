package de.itsliz.sql.api.executor;

import de.itsliz.sql.api.function.ISqlFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author kleinLisaa
 * @created 31.10.2021
 * <p>
 * Do not edit without permission.
 */
public class SqlExecutor {

    private final Connection connection;

    public SqlExecutor(Connection connection){
        this.connection = connection;
    }

    public <T> T executeQuery(String query, ISqlFunction<ResultSet, T> function, T defaultValue){
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return function.apply(resultSet);
            }catch (Exception exception){
                return defaultValue;
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return defaultValue;
    }

    public int executeUpdate(String query){
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return -1;
        }
    }
}
