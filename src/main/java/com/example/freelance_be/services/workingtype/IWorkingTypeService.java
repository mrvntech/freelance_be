package com.example.freelance_be.services.workingtype;

import com.example.freelance_be.dto.request.workingtype.CreateWorkingTypeRequestBody;
import com.example.freelance_be.dto.response.workingtype.GetAllWorkingTypeResponseBody;
import com.example.freelance_be.dto.response.workingtype.GetWorkingTypeDetailResponseBody;

public interface IWorkingTypeService {
    boolean createWorkingType(CreateWorkingTypeRequestBody body);
    GetWorkingTypeDetailResponseBody getWorkingTypeDetail(Long id);
    GetAllWorkingTypeResponseBody getAllWorkingType();
}
