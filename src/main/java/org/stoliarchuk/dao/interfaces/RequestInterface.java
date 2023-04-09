package org.stoliarchuk.dao.interfaces;

import org.stoliarchuk.entities.Request;

import java.util.List;

public interface RequestInterface {
    Request findById(long request_id);
    List<Request> findAll();
    List<Request> findAllByUserId(long user_id);
    List<Request> findAllByStatus(String status_name);
    void createRequest(Request request);
    void updateRequestById(long request_id, Request request);
    void deleteById(long request_id);
}
