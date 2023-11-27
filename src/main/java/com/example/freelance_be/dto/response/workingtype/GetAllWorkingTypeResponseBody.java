package com.example.freelance_be.dto.response.workingtype;

import java.util.List;

public class GetAllWorkingTypeResponseBody {
    private List<WorkingType> workingTypes;

    public List<WorkingType> getWorkingTypes() {
        return workingTypes;
    }

    public void setWorkingTypes(List<WorkingType> workingTypes) {
        this.workingTypes = workingTypes;
    }

    public static class WorkingType {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
