package application.service;

import application.dao.impl.PositionRepository;
import application.model.Position;

import java.util.Collection;

public class PositionService implements Service<Position>{
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Collection<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position getById(Integer id) {
        return positionRepository.findById(id);
    }

    @Override
    public Position add(Position entity) {
        return positionRepository.create(entity);
    }

    @Override
    public Position update(Position entity) {
        return positionRepository.update(entity);
    }

    @Override
    public Position delete(Integer id) {
        return positionRepository.deleteById(id);
    }
}
