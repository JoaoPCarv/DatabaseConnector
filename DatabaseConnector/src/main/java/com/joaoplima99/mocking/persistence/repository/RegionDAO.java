package com.joaoplima99.mocking.persistence.repository;

import com.joaoplima99.mocking.model.Region;
import com.joaoplima99.persistence.interfaces.Repository;
import com.joaoplima99.service.RepositoryService;

import javax.persistence.EntityManager;
import java.util.List;

public class RegionDAO implements Repository<Region> {

    private RegionDAO() {
    }

    @Override
    public void createEntry(Region region) {

    }

    @Override
    public Region readEntry(Class<Region> clazz, int id) {
        return null;
    }

    @Override
    public void updateEntry(Region region) {

    }

    @Override
    public void deleteEntry(Region region) {

    }

    @Override
    public List<Region> selectAll() {
        return null;
    }
}
