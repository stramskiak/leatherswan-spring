package com.leatherswan.artisticendeavors.jpa.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name="categories")
public class Category implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    public static final int MAX_LENGTH_CATEGORY_STRING = 20;

    private long categoryId;
    private String categoryValue;
    private Set<Contact> contacts;
    private Set<Product> products;

    public Category() {
    }

    public Category(String categoryValue) {
        this.categoryValue = categoryValue;
    }


    // endregion

    //region Getter Setters

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "category_id", nullable = false)
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "category_value", nullable = false, length = MAX_LENGTH_CATEGORY_STRING)
    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    // endregion

    //region Contacts/Categories
    // Artist/Medium

    @ManyToMany(mappedBy = "categories")
    private Set<Contact> getContacts() {
	    return contacts;
    }
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    // endregion

    //region Product/Categories (Art/Medium)

    @ManyToMany(mappedBy = "categories")
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public void addProduct(Product product) {
	    this.getProducts().add(product);
    }

    // endregion

	//region Builders and Strings

    @Override
    public String toString() {
        return getCategoryValue();
    }

    public void update(final String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public static Category.Builder getBuilder(Long categoryId, String categoryValue) {
        return new Category.Builder(categoryId, categoryValue);
    }

    public static class Builder {

        private Category built;

        public Builder(Long categoryId, String categoryValue) {
            built = new Category();
            built.categoryId = categoryId;
            built.categoryValue = categoryValue;
        }

        public Category build() {
            return built;
        }
    }

    // endregion

}
