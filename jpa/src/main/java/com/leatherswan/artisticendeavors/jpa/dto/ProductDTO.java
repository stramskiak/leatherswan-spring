/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leatherswan.artisticendeavors.jpa.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;

import com.leatherswan.artisticendeavors.jpa.enums.ProductDisplayType;
import com.leatherswan.artisticendeavors.jpa.enums.ProductType;
import com.leatherswan.artisticendeavors.jpa.model.Product;
import com.leatherswan.artisticendeavors.jpa.utils.ProductUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.geo.Point;

public class ProductDTO implements Serializable {

	private Long id;
    @NotEmpty
	private String name;
	private Collection<CategoryDTO> categoriesDTO;
    @NotEmpty
    @Length(max= Product.MAX_PRODUCT_FEATURE_LENGTH)
	private String features;
	private Float weight;
	private Float price;
	private Integer popularity;
	private boolean available;
	private String doctype;
	private Point point;
	private String location;

    private Collection<ContactDTO> contactsDTO;
	private String productLink;
	private ZonedDateTime productDate;
	private ZonedDateTime productModified;
	private ProductType productType;
	private ProductDisplayType displayType;
    private String productImage;
    private String productSource;
	private String createdByUser;
	private String modifiedByUser;
	private Boolean isPublished;
    private boolean updateChildren = true;
    private int clickCount;
	private int likesCount;
	private int valueRating;
	private int version;

    public ProductDTO() {
    }



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

	public Collection<CategoryDTO> getCategories() {
		return categoriesDTO;
	}

	public void setCategories(Collection<CategoryDTO> categoriesDTO) {
		this.categoriesDTO = categoriesDTO;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

    public Collection<ContactDTO> getContactsDTO() {
        return contactsDTO;
    }

    public void setArtists(Collection<ContactDTO> contactsDTO) {
        this.contactsDTO = contactsDTO;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public ZonedDateTime getProductDate() {
        return productDate;
    }

    public void setProductDate(ZonedDateTime productDate) {
        this.productDate = productDate;
    }

    public ZonedDateTime getProductModified() {
        return productModified;
    }

    public void setProductModified(ZonedDateTime productModified) {
        this.productModified = productModified;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductDisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(ProductDisplayType displayType) {
        this.displayType = displayType;
    }

    public String getProductSource() {
        return productSource;
    }

    public void setProductSource(String productSource) {
        this.productSource = productSource;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getValueRating() {
        return valueRating;
    }

    public void setValueRating(int valueRating) {
        this.valueRating = valueRating;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

    public boolean isUpdateChildren() {
        return updateChildren;
    }

    public void setUpdateChildren(boolean updateChildren) {
        this.updateChildren = updateChildren;
    }

    public static Builder getBuilder(String productTitle, String productLink, String features, ProductType productType, ProductDisplayType displayType) {
        return new ProductDTO.Builder(productTitle, productLink, features, productType, displayType);
    }

    public static class Builder {

        private ProductDTO built;

        public Builder(String title, String productLink, String productContent, ProductType productType, ProductDisplayType displayType) {
            built = new ProductDTO();
            built.name = title;
            built.productLink = productLink;
            built.features = productContent;
            built.productType = productType;
            built.displayType = displayType;
            built.productSource = ProductUtils.createProductSource(productLink);
        }

        public Builder productSource(String productSource) {
            built.productSource = productSource;
            return this;
        }

        public Builder productImage(String productImage) {
            if (StringUtils.isEmpty(productImage))
                productImage = null;
            built.productImage = productImage;
            return this;
        }

        public Builder productId(Long productId) {
            built.id = productId;
            return this;
        }

        public ProductDTO build() {
            return built;
        }
    }


    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", categories=" + categoriesDTO + ", features =" + features +  ", weight=" + weight + ", price="
                + price + ", popularity=" + popularity + ", available=" + available + ", doctype=" + doctype + "]";
    }

}
