package com.cloud.project.dao.impl;
import com.cloud.project.dao.ArticleDao;
import com.cloud.project.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public Article findById(int id) {
        String sql = "SELECT * FROM Article WHERE a_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Article article = new Article();
                    article.setId(resultSet.getInt("a_id"));
                    article.setTitle(resultSet.getString("a_title"));
                    article.setContent(resultSet.getString("a_content"));
                    article.setAuthorId(resultSet.getInt("a_guide_u_id"));
                    article.setCount(resultSet.getInt("a_sell_count"));
                    return article;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> findByTitle(String title) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM Article WHERE a_title LIKE ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + title + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Article article = new Article();
                    article.setId(resultSet.getInt("a_id"));
                    article.setTitle(resultSet.getString("a_title"));
                    article.setContent(resultSet.getString("a_content"));
                    article.setAuthorId(resultSet.getInt("a_guide_u_id"));
                    article.setCount(resultSet.getInt("a_sell_count"));
                    articles.add(article);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public List<Article> findByAuthor(int authorId) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM Article WHERE a_guide_u_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, authorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Article article = new Article();
                    article.setId(resultSet.getInt("a_id"));
                    article.setTitle(resultSet.getString("a_title"));
                    article.setContent(resultSet.getString("a_content"));
                    article.setAuthorId(resultSet.getInt("a_guide_u_id"));
                    article.setCount(resultSet.getInt("a_sell_count"));
                    articles.add(article);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public int createArticle(Article article) {
        String sql = "INSERT INTO Article(a_title, a_content, a_guide_u_id, a_sell_count) VALUES(?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getContent());
            statement.setInt(3, article.getAuthorId());
            statement.setInt(4, 0);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        article.setId(generatedKeys.getInt(1));
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
    public int addCount(int id) {
        String sql = "UPDATE Article SET a_sell_count = a_sell_count + 1 WHERE a_id = ?";
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
