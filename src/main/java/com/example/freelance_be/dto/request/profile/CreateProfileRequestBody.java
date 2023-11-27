package com.example.freelance_be.dto.request.profile;

public class CreateProfileRequestBody {
    private Long categoryId;
    private Long workingTypeId;
    private Long LevelId;
    private String skill;
    private String workExperience;
    private String aboutMe;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getWorkingTypeId() {
        return workingTypeId;
    }

    public void setWorkingTypeId(Long workingTypeId) {
        this.workingTypeId = workingTypeId;
    }

    public Long getLevelId() {
        return LevelId;
    }

    public void setLevelId(Long levelId) {
        LevelId = levelId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}
