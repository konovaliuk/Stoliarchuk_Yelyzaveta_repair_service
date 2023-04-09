package org.stoliarchuk.dao.interfaces;

import org.stoliarchuk.entities.Status;

import java.util.List;

public interface StatusInterface {
    Status findById(long status_id);
    Status findByName(String name);
    List<Status> findAll();
    void createStatus(Status status);
    void deleteById(long status_id);
}
