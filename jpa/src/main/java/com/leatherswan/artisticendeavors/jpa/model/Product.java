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
package com.leatherswan.artisticendeavors.jpa.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.ZonedDateTime;

import com.leatherswan.artisticendeavors.jpa.enums.ProductDisplayType;
import com.leatherswan.artisticendeavors.jpa.enums.ProductType;
import com.leatherswan.artisticendeavors.jpa.utils.ProductUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * modified from NixMash SpringData by Stramskiak
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int MAX_PRODUCT_TITLE_LENGTH = 200;
    public static final int MAX_PRODUCT_NAME_LENGTH = 200;
    public static final int MAX_PRODUCT_IMAGE_LENGTH = 200;
    public static final int MAX_PRODUCT_FEATURE_LENGTH = 2048;

    public static final int MIN_LENGTH_TITLE = 3;
    public static final int MIN_LENGTH_FILENAME = 3;

	private Long id;
	private String name;
	private String features;
//    private Set<Contact> contacts;
//    private Set<Post> posts;
//    private Set<Category> categories;
	private Float weight = 0f;
	private Float price = 0f;
	private Integer popularity = 10;
    private Boolean isSold;
	private String doctype = "product";
	private String location = "47.6062,-122.3321";
	private Point point = new Point(47.6062, -122.3321);
    private String productLink;
    private ZonedDateTime productDate;
    private ZonedDateTime productModified;
    private ProductType productType;
    private ProductDisplayType displayType;
    private String productSource;
    private String productImage;
    private String createdByUser;
    private String modifiedByUser;
    private Boolean isPublished = true;
    private int clickCount = 0;
    private int likesCount = 0;
    private int valueRating = 0;

    @Version
    @Column(name = "version", nullable = false, insertable = true, updatable = true)
    private int version = 0;

    // region Product Constructors

    public Product() {
	}

	public Product(String name) {
		setName(name);
	}

	public Product(long id, String name) {
		setId(id);
		setName(name);
	}

    // endregion

    //region Base Product Info Getter Setters

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Column(name = "product_title")
    @Size(min = MIN_LENGTH_TITLE, max = MAX_PRODUCT_TITLE_LENGTH, message = "Title must be between 3 and 240 characters")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

//    @Column(name = "product_content", nullable = false, length = MAX_PRODUCT_FEATURE_LENGTH)
    @Column(name = "product_content", nullable = false, columnDefinition = "TEXT")
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}

    @Transient
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}

    @Transient
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

    @Transient
	public Integer getPopularity() {
		return popularity;
	}
	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

    @Column(name = "is_sold", nullable = false)
    public Boolean getSold() {
        return isSold;
    }
    public void setSold(Boolean isSold) {
        this.isSold = isSold;
    }

    @Transient
    public boolean isAvailable() {
		return !isSold;
	}
	public void setAvailable(Boolean available) {
		this.isSold = !available;
	}

    @Column(name = "is_published", nullable = false)
    public Boolean getPublished() {
        return isPublished;
    }
    public void setPublished(Boolean published) {
        isPublished = published;
    }

    @Column(name = "version", nullable = false)
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @Transient
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

    @Transient
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

    @Transient
	public Point getPoint() {
        return this.point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}

 /*   public boolean hasCategories() {
        return (this.categories != null);
    }
*/
	public boolean hasFeatures() {
	        return (this.features != null);
	    }
	   
	public boolean hasLocation() {
		   return (this.getLocation() != null);
	   }

    @Column(name = "product_link")
    public String getProductLink() {
        return productLink;
    }
    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    @Column(name = "created_by_user", nullable = false)
    public String getCreatedByUser() {
        return createdByUser;
    }
    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    @Column(name = "modified_by_user", nullable = false)
    public String getModifiedByUser() {
        return modifiedByUser;
    }
    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    @Column(name = "product_date", nullable = false, columnDefinition = "DATE")
//    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    @CreatedDate
    public ZonedDateTime getProductDate() {
        return productDate;
    }
    public void setProductDate(ZonedDateTime productDate) {
        this.productDate = productDate;
    }

    @Column(name = "product_modified", nullable = false)
//    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    @LastModifiedDate
    public ZonedDateTime getProductModified() {
        return productModified;
    }
    public void setProductModified(ZonedDateTime productModified) {
        this.productModified = productModified;
    }

    @Column(name = "product_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public ProductType getProductType() {
        return productType;
    }
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Column(name = "display_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public ProductDisplayType getDisplayType() {
        return displayType;
    }
    public void setDisplayType(ProductDisplayType displayType) {
        this.displayType = displayType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Column(name = "product_source", nullable = false)
    public String getProductSource() {
        return productSource;
    }
    public void setProductSource(String productSource) {
        this.productSource = productSource;
    }

    @Column(name = "product_image", nullable = false)
    public String getProductImage() {
        return productImage;
    }
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Column(name = "click_count", nullable = false)
    public int getClickCount() {
        return clickCount;
    }
    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    @Column(name = "likes_count", nullable = false)
    public int getLikesCount() {
        return likesCount;
    }
    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    @Column(name = "value_rating", nullable = false)
    public int getValueRating() {
        return valueRating;
    }
    public void setValueRating(int valueRating) {
        this.valueRating = valueRating;
    }

    // endregion

    //region Product/Contacts (Art/Artists)

/*
    @ManyToMany(mappedBy = "products")
    public Set<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
    public void addContact(Contact contact) {
        this.getContacts().add(contact);
    }
*/

    // endregion

    //region Product/Categories (Art/Medium)

/*
    @ManyToMany
    @JoinTable(name = "product_category_ids",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false))
    public Set<Category> getCategories() {
        return categories;
    }
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    public void addCategory(Category category) {
        this.getCategories().add(category);
    }
*/

    // endregion

    //region Product/Posts
    // Artist/Reviews by Users

/*
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    public Set<Post> getPosts() {
        return posts;
    }
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
*/

    // endregion

    //region Builders and Strings

    public static Builder getBuilder(String productTitle, String productLink, String productContent, ProductType productType, ProductDisplayType displayType) {
        return new Product.Builder(productTitle, productLink, productContent, productType, displayType);
    }

    public static class Builder {

        private Product built;

        public Builder(String title, String productLink, String productContent, ProductType productType, ProductDisplayType displayType) {
            built = new Product();
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

        public Product build() {
            return built;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Product productO = (Product) o;
        if (!name.equals(productO.name)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public void update(String name, String features, Integer clickCount, String productImage, Integer likesCount) {
        this.name = name;
        this.features = features;
        this.clickCount = clickCount;
        this.productImage = productImage;
        this.likesCount = likesCount;
    }

    @Override

    public String toString() {
        return "Product{" +
                "productId=" + id +
                ", productTitle='" + name + '\'' +
                ", productLink='" + productLink + '\'' +
                ", productDate=" + productDate +
                ", productModified=" + productModified +
                ", productType='" + productType + '\'' +
                ", displayType='" + displayType + '\'' +
                ", isPublished=" + isPublished +
                ", productContent='" + features + '\'' +
                ", productSource='" + productSource + '\'' +
                ", clickCount=" + clickCount +
                ", likesCount=" + likesCount +
                ", valueRating=" + valueRating +
                ", version=" + version +
                '}';
    }

	public String toString2() {
		return "Product [id=" + id + ", name=" + name + ", features=" + features + ", weight=" + weight + ", price="
				+ price + ", popularity=" + popularity + ", available=" + !isSold + ", doctype=" + doctype + "]";
	}

    // endregion

}
