package com.example.freelance_be.services.level;

import com.example.freelance_be.dto.request.level.CreateLevelRequestBody;
import com.example.freelance_be.dto.response.level.GetAllLevelResponseBody;
import com.example.freelance_be.dto.response.level.GetLevelDetailResponseBody;

public interface ILevelService {
    boolean createLevel(CreateLevelRequestBody body);
    GetLevelDetailResponseBody getLevelDetail(Long id);
    GetAllLevelResponseBody getAllLevel();
}
