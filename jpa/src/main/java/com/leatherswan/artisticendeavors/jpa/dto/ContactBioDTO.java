package com.leatherswan.artisticendeavors.jpa.dto;

import com.leatherswan.artisticendeavors.jpa.model.Contact;
import com.leatherswan.artisticendeavors.jpa.model.ContactBio;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.ZonedDateTime;

public class ContactBioDTO {

    private Long bioId;
//    private Contact contact;
    private Long contactId;
    private String imageUrl;
    private int version;
    private String createdByUser;
    private ZonedDateTime creationTime;
    private String modifiedByUser;
    private ZonedDateTime modificationTime;

    @NotEmpty
    @Length(max=ContactBio.MAX_LENGTH_BIO)
    private String bio;

    public ContactBioDTO() {
    }

    public ContactBioDTO(String bio, String imageUrl) {
        this.bio=bio;
        this.imageUrl=imageUrl;
    }

    public ContactBioDTO(ContactBio contactBio) {
        this.bioId =contactBio.getBioId();
        this.contactId=contactBio.getContactId();
        this.bio=contactBio.getBio();
        this.imageUrl=contactBio.getImageUrl();
        this.createdByUser=contactBio.getCreatedByUser();
        this.creationTime=contactBio.getCreationTime();
        this.modifiedByUser=contactBio.getModifiedByUser();
        this.modificationTime=contactBio.getModificationTime();
        this.version=contactBio.getVersion();
    }

    public Long getBioId() {
        return bioId;
    }

    public void setBioId(Long bioId) {
        this.bioId = bioId;
    }

 /*   public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
*/
    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Contact contact) {
        this.contactId = contact.getContactId();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

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


	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean isNew() {
        return (this.bioId == null);
    }

 	public Long getId() {
		return bioId;
	}


}