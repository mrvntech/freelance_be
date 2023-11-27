package com.example.freelance_be.controllers;

import com.example.freelance_be.dto.request.workingtype.CreateWorkingTypeRequestBody;
import com.example.freelance_be.dto.response.workingtype.GetAllWorkingTypeResponseBody;
import com.example.freelance_be.dto.response.workingtype.GetWorkingTypeDetailResponseBody;
import com.example.freelance_be.services.workingtype.IWorkingTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/working-type")
@RestController
public class WorkingTypeController {
    private final IWorkingTypeService workingTypeService;

    public WorkingTypeController(IWorkingTypeService workingTypeService) {
        this.workingTypeService = workingTypeService;
    }

    @PostMapping("")
    public boolean createWorkingType(@RequestBody CreateWorkingTypeRequestBody body){
        return workingTypeService.createWorkingType(body);
    }

    @GetMapping("")
    public ResponseEntity<GetAllWorkingTypeResponseBody> getAllWorkingType(){
        return ResponseEntity.ok().body(workingTypeService.getAllWorkingType());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetWorkingTypeDetailResponseBody> getWorkingTypeDetail(@PathVariable Long id){
        return ResponseEntity.ok().body(workingTypeService.getWorkingTypeDetail(id));
    }
}
