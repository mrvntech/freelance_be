package com.example.freelance_be.services.workingtype.impl;

import com.example.freelance_be.dto.request.workingtype.CreateWorkingTypeRequestBody;
import com.example.freelance_be.dto.response.workingtype.GetAllWorkingTypeResponseBody;
import com.example.freelance_be.dto.response.workingtype.GetWorkingTypeDetailResponseBody;
import com.example.freelance_be.entities.WorkingType;
import com.example.freelance_be.repositories.WorkingTypeRepository;
import com.example.freelance_be.services.workingtype.IWorkingTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkingTypeService implements IWorkingTypeService {
    private final WorkingTypeRepository workingTypeRepository;
    private final ModelMapper modelMapper;

    public WorkingTypeService(WorkingTypeRepository workingTypeRepository, ModelMapper modelMapper) {
        this.workingTypeRepository = workingTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createWorkingType(CreateWorkingTypeRequestBody body) {
        WorkingType workingType = new WorkingType();
        workingType.setName(body.getName());
        workingTypeRepository.save(workingType);
        return true;
    }

    @Override
    public GetWorkingTypeDetailResponseBody getWorkingTypeDetail(Long id) {
        GetWorkingTypeDetailResponseBody responseBody = new GetWorkingTypeDetailResponseBody();
        Optional<WorkingType> workingType = workingTypeRepository.findById(id);
        workingType.ifPresentOrElse((value) -> responseBody.setWorkingType(modelMapper.map(value, GetWorkingTypeDetailResponseBody.WorkingType.class)), () -> responseBody.setWorkingType(null));
        return responseBody;
    }

    @Override
    public GetAllWorkingTypeResponseBody getAllWorkingType() {
        List<WorkingType> workingTypes = workingTypeRepository.findAll();
        GetAllWorkingTypeResponseBody responseBody = new GetAllWorkingTypeResponseBody();
        responseBody.setWorkingTypes(workingTypes.stream().map(workingType -> modelMapper.map(workingType, GetAllWorkingTypeResponseBody.WorkingType.class)).toList());
        return responseBody;
    }
}
