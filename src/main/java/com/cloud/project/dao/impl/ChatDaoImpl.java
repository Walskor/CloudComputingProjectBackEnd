package com.cloud.project.dao.impl;
import com.cloud.project.dao.ChatDao;
import com.cloud.project.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ChatDaoImpl implements ChatDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Integer> relatedId(int id) {
        Set<Integer> relatedIds = new HashSet<>();
        String sql = "SELECT c_buyer_u_id, c_guide_u_id FROM Chat WHERE c_buyer_u_id = ? OR c_guide_u_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setInt(2, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    relatedIds.add(resultSet.getInt("c_buyer_u_id"));
                    relatedIds.add(resultSet.getInt("c_guide_u_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        relatedIds.remove(id); // Remove the input id from the set
        return new ArrayList<>(relatedIds);
    }

    @Override
    public Chat findByChatId(int id) {
        String sql = "SELECT * FROM Chat WHERE c_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Chat chat = new Chat();
                    chat.setId(resultSet.getInt("c_id"));
                    chat.setVisitor_id(resultSet.getInt("c_buyer_u_id"));
                    chat.setGuide_id(resultSet.getInt("c_guide_u_id"));
                    chat.setContent(resultSet.getString("c_content"));
                    return chat;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Chat findByUsers(int a_id, int b_id) {
        String sql = "SELECT * FROM Chat WHERE (c_buyer_u_id = ? AND c_guide_u_id = ?) OR (c_buyer_u_id = ? AND c_guide_u_id = ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, a_id);
            statement.setInt(2, b_id);
            statement.setInt(3, b_id);
            statement.setInt(4, a_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Chat chat = new Chat();
                    chat.setId(resultSet.getInt("c_id"));
                    chat.setVisitor_id(resultSet.getInt("c_buyer_u_id"));
                    chat.setGuide_id(resultSet.getInt("c_guide_u_id"));
                    chat.setContent(resultSet.getString("c_content"));
                    return chat;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Chat newMessage(int id, int sender, String message) {
        Chat chat = findByChatId(id);
        if (chat != null) {
            String senderTitle;
            if (sender == chat.getGuide_id()){
                senderTitle = "Guide";
            } else if (sender == chat.getVisitor_id()) {
                senderTitle = "Visitor";
            }
            String updatedContent = chat.getContent() + "\n" + sender + ": " + message;
            chat.setContent(updatedContent);
            String sql = "UPDATE Chat SET c_content = ? WHERE c_id = ?";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, updatedContent);
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chat;
    }
}

