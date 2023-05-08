package dao.interfaces;

import entities.Status;

import java.util.List;

public interface StatusInterface {
    Status findById(long statusId);
    Status findByName(String name);
    List<Status> findAll();
    Status create(Status status);
    void delete(long statusId);
}
