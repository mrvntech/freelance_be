package com.example.freelance_be.dto.response.profile;

public class GetProfileDetailResponseBody {
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public static class Profile {
        private GetAllProfileResponseBody.Level level;
        private GetAllProfileResponseBody.Category category;
        private GetAllProfileResponseBody.WorkingType workingType;
        private String skill;
        private String workExperience;
        private String aboutMe;
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public GetAllProfileResponseBody.Level getLevel() {
            return level;
        }

        public void setLevel(GetAllProfileResponseBody.Level level) {
            this.level = level;
        }

        public GetAllProfileResponseBody.Category getCategory() {
            return category;
        }

        public void setCategory(GetAllProfileResponseBody.Category category) {
            this.category = category;
        }

        public GetAllProfileResponseBody.WorkingType getWorkingType() {
            return workingType;
        }

        public void setWorkingType(GetAllProfileResponseBody.WorkingType workingType) {
            this.workingType = workingType;
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

    public static class Level{
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

    public static class Category {
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

    public static class WorkingType{
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
    public static class User {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
