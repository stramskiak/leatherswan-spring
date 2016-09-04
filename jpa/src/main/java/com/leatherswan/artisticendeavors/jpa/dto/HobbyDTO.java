package com.leatherswan.artisticendeavors.jpa.dto;

import com.leatherswan.artisticendeavors.jpa.model.Hobby;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Modified by stramskiak
 */
public class HobbyDTO {

    private Long hobbyId;

    @NotEmpty
    @Length(max = Hobby.MAX_LENGTH_HOBBY_TITLE)
    private String hobbyTitle;

    private Boolean isUserHobby = false;

    public HobbyDTO() {}

    public HobbyDTO(String hobbyTitle) {
        this.hobbyTitle = hobbyTitle;
    }

    public HobbyDTO(Long hobbyId, String hobbyTitle) {
        this.hobbyId = hobbyId;
        this.hobbyTitle = hobbyTitle;
    }

    public HobbyDTO(Hobby hobby) {
        this.hobbyId = hobby.getHobbyId();
        this.hobbyTitle = hobby.getHobbyTitle();
    }

    public Long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyTitle() {
        return hobbyTitle;
    }

    public void setHobbyTitle(String hobbyTitle) {
        this.hobbyTitle = hobbyTitle;
    }

    public Boolean getIsUserHobby() {
        return isUserHobby;
    }

    public void setIsUserHobby(Boolean isUserHobby) {
        this.isUserHobby = isUserHobby;
    }

    @Override
    public String toString() {
        return "HobbyDTO{" +
                "hobbyId=" + hobbyId +
                ", hobbyTitle='" + hobbyTitle + '\'' +
                ", isUserHobby=" + isUserHobby +
                '}';
    }
}
