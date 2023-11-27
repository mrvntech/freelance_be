package com.example.freelance_be.services.level.impl;

import com.example.freelance_be.dto.request.level.CreateLevelRequestBody;
import com.example.freelance_be.dto.response.level.GetAllLevelResponseBody;
import com.example.freelance_be.dto.response.level.GetLevelDetailResponseBody;
import com.example.freelance_be.entities.Level;
import com.example.freelance_be.repositories.LevelRepository;
import com.example.freelance_be.services.level.ILevelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelService implements ILevelService {
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    public LevelService(LevelRepository levelRepository, ModelMapper modelMapper) {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createLevel(CreateLevelRequestBody body) {
        Level level = new Level();
        level.setName(body.getName());
        levelRepository.save(level);
        return true;
    }

    @Override
    public GetLevelDetailResponseBody getLevelDetail(Long id) {
        Optional<Level> level = levelRepository.findById(id);
        GetLevelDetailResponseBody responseBody = new GetLevelDetailResponseBody();
        level.ifPresentOrElse((value) -> responseBody.setLevel(modelMapper.map(value, GetLevelDetailResponseBody.Level.class)), () -> responseBody.setLevel(null));
        return responseBody;
    }

    @Override
    public GetAllLevelResponseBody getAllLevel() {
        List<Level> levels = levelRepository.findAll();
        GetAllLevelResponseBody responseBody = new GetAllLevelResponseBody();
        responseBody.setLevels(levels.stream().map(level -> modelMapper.map(level, GetAllLevelResponseBody.Level.class)).toList());
        return responseBody;
    }
}
