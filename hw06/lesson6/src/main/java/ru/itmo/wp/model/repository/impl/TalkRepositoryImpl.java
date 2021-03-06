package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.TalkRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TalkRepositoryImpl implements TalkRepository {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();
    @Override
    public void save(Talk talk) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `Talk` (`sourceUserId`, `targetUserId`, `text`, `creationTime`) VALUES (?, ?, ?, NOW())",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, talk.getSourceUserId());
                statement.setLong(2, talk.getTargetUserId());
                statement.setString(3, talk.getText());
                if (statement.executeUpdate() != 1) {
                    throw new RepositoryException("Can't save Talk.");
                } else {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        talk.setId(generatedKeys.getLong(1));
                        talk.setCreationTime(find(talk.getId()).getCreationTime());
                    } else {
                        throw new RepositoryException("Can't save Talk [no autogenerated fields].");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save Event.", e);
        }
    }

    private Talk find(long id) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Talk WHERE id=?")) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return toTalk(statement.getMetaData(), resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Talk.", e);
        }
    }

    /*public Talk[] findBySourceUserId(long id) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Talk WHERE sourceUserId=? ORDER BY creationTime")) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return toTalks(statement.getMetaData(), resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Event.", e);
        }
    }

    public Talk[] findByTargetUserId(long id) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Talk WHERE targetUserId=? ORDER BY creationTime")) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return toTalks(statement.getMetaData(), resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Event.", e);
        }
    }*/

    @Override
    public Talk[] findByUserId(long id) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Talk WHERE targetUserId=? OR sourceUserId=? ORDER BY creationTime ")) {
                statement.setLong(1, id);
                statement.setLong(2, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return toTalks(statement.getMetaData(), resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Event.", e);
        }
    }

    private Talk[] toTalks(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        List<Talk> talks = new ArrayList<Talk>();
        while (true) {
            Talk curTalk = toTalk(metaData, resultSet);
            if (curTalk == null) {
                break;
            }
            talks.add(curTalk);
        }
        Talk[] result = new Talk[talks.size()];
        result = talks.toArray(result);
        return result;
    }

    private Talk toTalk(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Talk event = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    event.setId(resultSet.getLong(i));
                    break;
                case "sourceUserId":
                    event.setSourceUserId(resultSet.getLong(i));
                case "targetUserId":
                    event.setTargetUserId(resultSet.getLong(i));
                    break;
                case "text":
                    event.setText(resultSet.getString(i));
                    break;
                case "creationTime":
                    event.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return event;
    }

}
