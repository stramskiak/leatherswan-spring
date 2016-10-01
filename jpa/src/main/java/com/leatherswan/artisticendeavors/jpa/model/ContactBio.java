package com.leatherswan.artisticendeavors.jpa.model;

import com.leatherswan.artisticendeavors.jpa.dto.ContactBioDTO;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.ZonedDateTime;

import static javax.persistence.AccessType.PROPERTY;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "contact_bio")
public class ContactBio implements Serializable {
    private static final long serialVersionUID = 447728202717826028L;

    public static final int MAX_LENGTH_BIO = 2048;
    public static final int MAX_LENGTH_EMAIL_ADDRESS = 100;
    public static final int MAX_LENGTH_FIRST_NAME = 40;
    public static final int MAX_LENGTH_LAST_NAME = 40;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_bio_id", nullable = false)
    private Long contactBioId;

    @OneToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

//    @Column(name="contact_id")
//    private Long contactId;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;
	
    @Column(name = "image_url")
    private String imageUrl;

    @Version
    @Column(name = "version", nullable = false, insertable = true, updatable = true, columnDefinition = "int default 0")
    private int version;
    
    @Column(name = "created_by_user", nullable = false)
    @CreatedBy
    private String createdByUser;
    
    @Column(name = "creation_time", nullable = false)
//    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
    @CreatedDate
    private ZonedDateTime creationTime;
    
    @Column(name = "modified_by_user", nullable = false)
    @LastModifiedBy
    private String modifiedByUser;
    
    @Column(name = "modification_time")
//    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
    @LastModifiedDate
    private ZonedDateTime modificationTime;

    public ContactBio() {}

    public ContactBio(ContactBioDTO contactBioDTO) {
            this.contactBioId=contactBioDTO.getContactBioId();
            this.bio=contactBioDTO.getBio();
            this.imageUrl=contactBioDTO.getImageUrl();
            this.createdByUser=contactBioDTO.getCreatedByUser();
            this.creationTime=contactBioDTO.getCreationTime();
            this.modifiedByUser=contactBioDTO.getModifiedByUser();
            this.modificationTime=contactBioDTO.getModificationTime();
            this.version=contactBioDTO.getVersion();
    }

    @Transient
    public boolean isNew() {
        return (this.contactBioId == 0);
    }

    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
//        this.contactId = contact.getContactId();
    }
 /*   public Long getContactId() {
        return contactId;
    }
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
*/

    public Long getContactBioId() {
        return contactBioId;
    }

    public void setContactBioId(Long contactBioId) {
        this.contactBioId = contactBioId;
    }

    public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public void setModificationTime(ZonedDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public ZonedDateTime getModificationTime() {
        return modificationTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("contactBioId", this.getContactBioId())
                .append("contactId", this.contact.getContactId())
                .append("new", this.isNew())
                .append("createdByUser", this.getCreatedByUser())
                .append("creationTime", this.getCreationTime())
                .append("modifiedByUser", this.getModifiedByUser())
                .append("modificationTime", this.getModificationTime())
                .toString();
    }

    public static Builder getBuilder(Contact contact, String bio, String imageUrl) {
        return new Builder(contact, bio, imageUrl);
    }

    public static class Builder {

        private ContactBio built;

        public Builder(Contact contact, String bio, String imageUrl) {
            built = new ContactBio();
            built.contact = contact;
            built.bio = bio;
            built.imageUrl = imageUrl;
        }


        public ContactBio build() {
            return built;
        }
    }


}