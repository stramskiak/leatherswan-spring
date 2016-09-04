package com.leatherswan.artisticendeavors.jpa.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = -4809849404139121173L;

    private long categoryId = -1;
    private String categoryValue = null;

    public CategoryDTO() {}

    public CategoryDTO(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public CategoryDTO(long categoryId, String categoryValue) {
        this.categoryId = categoryId;
        this.categoryValue = categoryValue;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}

}