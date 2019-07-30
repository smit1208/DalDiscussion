package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.database.DatabaseConfig;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.exceptions.ErrorCode;
import com.macs.group6.daldiscussion.model.PostImage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("PostImageDAO")
public class PostImageDAO implements IPostImageDAO {
    private static final Logger logger = Logger.getLogger(PostImageDAO.class);
    Connection connection = null;
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    private DatabaseConfig databaseConfig = DatabaseConfig.getInstance();
    private static final String ADDIMAGE = "{call addImage(?,?)}";
    private static final String GETIMAGESBYPOSTID = "{call getImagesByPostId(?)}";

    @Override
    public void addImage(String imageLink, int post_id) throws DAOException {
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(ADDIMAGE);
            callableStatement.setString(1,imageLink);
            callableStatement.setInt(2,post_id);
            callableStatement.executeQuery();
        }catch (SQLException e){
            throw  new DAOException("<PostImageDAO> - INSERT IMAGE BY POST"+post_id+" - ERROR", e, ErrorCode.INSERT_INTO_DB_ERROR);

        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
    }

    @Override
    public List<PostImage> getImageByPostId(int post_id) throws DAOException {

        List<PostImage> postImages = new ArrayList<PostImage>();
        try{
            connection = this.databaseConfig.loadDatabase();
            callableStatement = connection.prepareCall(GETIMAGESBYPOSTID);
            callableStatement.setInt(1,post_id);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()){
                PostImage image = new PostImage();
                image.setId(resultSet.getInt("id_post_images"));
                image.setImageLink(resultSet.getString("image_link"));
                postImages.add(image);
            }
        }catch (SQLException e){
            throw  new DAOException("<PostImageDAO> - GET IMAGE BY POST"+post_id+" - ERROR", e, ErrorCode.RETRIVE_FROM_DB_ERROR);
        }finally {
            DatabaseConfig.getInstance().closeConnection(connection,callableStatement,resultSet);
        }
        return postImages;
    }
}
