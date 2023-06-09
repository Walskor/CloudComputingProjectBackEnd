package com.cloud.project.dao.impl;
import com.cloud.project.dao.OrderDao;
import com.cloud.project.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public Order findById(int id) {
        String sql = "SELECT * FROM Orders WHERE o_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("o_id"));
                    order.setArticleId(resultSet.getInt("o_a_id"));
                    order.setBuyerId(resultSet.getInt("o_buyer_u_id"));
                    order.setTimeStamp(resultSet.getTimestamp("o_time"));
                    order.setCount(resultSet.getInt("o_count"));
                    return order;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findByBuyer(int buyerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE o_buyer_u_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, buyerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("o_id"));
                    order.setArticleId(resultSet.getInt("o_a_id"));
                    order.setBuyerId(resultSet.getInt("o_buyer_u_id"));
                    order.setTimeStamp(resultSet.getTimestamp("o_time"));
                    order.setCount(resultSet.getInt("o_count"));
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public int createOrder(Order order) {
        String sql = "INSERT INTO Orders(o_a_id, o_buyer_u_id, o_time, o_count) VALUES(?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getArticleId());
            statement.setInt(2, order.getBuyerId());
            statement.setTimestamp(3, order.getTimeStamp());
            statement.setInt(4, order.getCount());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setId(generatedKeys.getInt(1));
                        return affectedRows;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrder(int id) {
        String sql = "DELETE FROM Orders WHERE o_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
