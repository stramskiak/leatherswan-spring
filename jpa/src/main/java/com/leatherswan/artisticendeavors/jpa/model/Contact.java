package com.leatherswan.artisticendeavors.jpa.model;

import com.leatherswan.artisticendeavors.jpa.model.validators.ExtendedEmailValidator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.AccessType.PROPERTY;

/**
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "contacts")
@Access(PROPERTY)
public class Contact implements Serializable {
    private static final long serialVersionUID = 447728202717826028L;

    public static final int MAX_LENGTH_EMAIL_ADDRESS = 100;
    public static final int MAX_LENGTH_FIRST_NAME = 40;
    public static final int MAX_LENGTH_LAST_NAME = 40;

    private Long contactId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private int version;

//    private ContactBio contactBio;
    private Set<ContactPhone> contactPhones;
    private Set<Hobby> hobbies;
    private Set<Post> posts;
//    private Set<Product> products;
    private Set<Category> categories;

    private String createdByUser;

    @Column(name = "creation_time", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
    @CreatedDate
    private ZonedDateTime creationTime;

    private String modifiedByUser;

    @Column(name = "modification_time")
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
    @LastModifiedDate
    private ZonedDateTime modificationTime;

    // region: Contact Constructors

    public Contact() {
    }

    public Contact(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    // endregion

    //region Basic Contact Info Getters/Setters

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "contact_id")
    public Long getContactId() {
        return contactId;
    }
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true,
            length = MAX_LENGTH_FIRST_NAME)
    @NotEmpty
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, insertable = true, updatable = true,
            length = MAX_LENGTH_LAST_NAME)
    @NotEmpty
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transient
    public String getFullName() {
        return this.firstName + ' ' + this.lastName;
    }

    @Transient
    public boolean isNew() {
        return (this.contactId == null);
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @NotNull @Past
    @Column(name = "birth_date", nullable = true, insertable = true, updatable = true)
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @ExtendedEmailValidator
    @Length(max = Contact.MAX_LENGTH_EMAIL_ADDRESS)
    @Column(name = "email", nullable = false, insertable = true, updatable = true)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "created_by_user", nullable = false)
    @CreatedBy
    public String getCreatedByUser() {
        return createdByUser;
    }
    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Column(name = "modified_by_user", nullable = false)
    @LastModifiedBy
    public String getModifiedByUser() {
        return modifiedByUser;
    }
    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public ZonedDateTime getModificationTime() {
        return modificationTime;
    }
    public void setModificationTime(ZonedDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }

    @Version
    @Column(name = "version", nullable = false, insertable = true, updatable = true, columnDefinition = "int default 0")
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    // endregion

    // region Contact/Bio (Artist/Bio)

//    @OneToOne(mappedBy="contact", cascade = CascadeType.ALL)
//    public ContactBio getContactBio() {
//        return contactBio;
//    }
//    public void setContactBio(ContactBio contactBio) {
//        this.contactBio = contactBio;
//    }

    // endregion

    // region Contact/Phones

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    public Set<ContactPhone> getContactPhones() {
        return contactPhones;
    }
    public void setContactPhones(Set<ContactPhone> contactPhones) {
        this.contactPhones = contactPhones;
    }
    public void addContactPhone(ContactPhone contactPhone) {
        contactPhone.setContact(this);
        getContactPhones().add(contactPhone);
    }

    // endregion

    // region Contacts/Hobbies (Artist/Interests)

    @ManyToMany
    @JoinTable(name = "contact_hobby_ids",
            joinColumns=@JoinColumn(name="contact_id", referencedColumnName="contact_id", nullable=false),
            inverseJoinColumns=@JoinColumn(name="hobby_id", referencedColumnName="hobby_id", nullable=false))
    public Set<Hobby> getHobbies() {
        return hobbies;
    }
    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
    public void addHobby(Hobby hobby) {
        getHobbies().add(hobby);
    }

    // endregion

    // region Contact/Posts (Artist/Reviews by Users)

    @OneToMany(mappedBy = "contact")
    public Set<Post> getPosts() {
        return posts;
    }
    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
    public void addPost(Post post) {
        post.setContact(this);
        getPosts().add(post);
    }

    // endregion

    // region Contact/Product (Artist/Art solo or combined effort)

/*
    @ManyToMany
    @JoinTable(name = "contact_product_ids",
            joinColumns=@JoinColumn(name="contact_id", referencedColumnName="contact_id", nullable=false),
            inverseJoinColumns=@JoinColumn(name="product_id", referencedColumnName="product_id", nullable=false))
    public Set<Product> getProducts() {
        return products;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    public void addProduct(Product product) {
        product.getContacts().add(this);
        getProducts().add(product);
    }
*/

    // endregion

    // region Contact/Category (Artist/Art Medium)

    @ManyToMany
    @JoinTable(name = "contact_category_ids",
            joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contact_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false))
    public Set<Category> getCategories() {
        return categories;
    }
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    public void addCategory(Category category) {
        this.categories.add(category);
    }

    // endregion

    //region Builders, Update, and String

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getContactId())
                .append("new", this.isNew())
                .append("lastName", this.getLastName())
                .append("firstName", this.getFirstName())
                .append("email", this.getEmail())
                .append("birthDate", this.getBirthDate())
                .append("createdByUser", this.getCreatedByUser())
                .append("creationTime", this.getCreationTime())
                .append("modifiedByUser", this.getModifiedByUser())
                .append("modificationTime", this.getModificationTime())
                .toString();
    }

    public void update(final String firstName, final String lastName, final String emailAddress, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = emailAddress;
        this.birthDate = birthDate;
    }

    public static Builder getBuilder(String firstName, String lastName, String email) {
        return new Builder(firstName, lastName, email);
    }

    public static class Builder {

        private Contact built;

        public Builder(String firstName, String lastName, String email) {
            built = new Contact();
            built.firstName = firstName;
            built.lastName = lastName;
            built.email = email;
        }


        public Builder birthDate(Date birthDate) {
            built.birthDate = birthDate;
            return this;
        }

        public Contact build() {
            return built;
        }
    }

    // endregion

}
