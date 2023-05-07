package org.stoliarchuk.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stoliarchuk.connection.DBCPDataSource;
import org.stoliarchuk.dao.implementations.RequestDao;
import org.stoliarchuk.entities.Request;

import java.sql.Connection;
import java.util.List;

public class RequestService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public List<Request> getAllRequestsByUser(long userId){
        try (Connection con = DBCPDataSource.getConnection()){
            RequestDao requestCon = new RequestDao(con);
            try {
                return requestCon.findAllByUserId(userId);
            } catch (Exception ex) {
                logger.error("Error: " + ex.getMessage());
            }
        } catch (Exception ex){
            logger.error("Error: " + ex.getMessage());
        }
        return null;
    }
}
