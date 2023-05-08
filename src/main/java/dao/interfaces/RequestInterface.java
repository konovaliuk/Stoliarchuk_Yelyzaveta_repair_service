package dao.interfaces;

import entities.Request;

import java.util.List;

public interface RequestInterface {
    Request findById(long requestId);
    List<Request> findAll();
    List<Request> findAllByUserId(long userId);
    List<Request> findAllByStatus(String statusName);
    Request create(Request request);
    void update(Request request);
    void delete(long requestId);
}
