package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.level.CreateLevelRequestBody;
import com.example.freelance_be.dto.response.level.GetAllLevelResponseBody;
import com.example.freelance_be.dto.response.level.GetLevelDetailResponseBody;
import com.example.freelance_be.services.level.ILevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/level")
@RestController
public class LevelController {
    private final ILevelService levelService;

    public LevelController(ILevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping("")
    public boolean createLevel(@RequestBody CreateLevelRequestBody body){
        return levelService.createLevel(body);
    }

    @GetMapping("")
    public ResponseEntity<GetAllLevelResponseBody> getAllLevel(){
        return ResponseEntity.ok().body(levelService.getAllLevel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLevelDetailResponseBody> getLevelDetail(@PathVariable Long id){
        return ResponseEntity.ok().body(levelService.getLevelDetail(id));
    }
}
