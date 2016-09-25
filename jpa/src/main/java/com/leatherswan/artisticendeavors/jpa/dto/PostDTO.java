package com.leatherswan.artisticendeavors.jpa.dto;

import com.leatherswan.artisticendeavors.jpa.enums.PostDisplayType;
import com.leatherswan.artisticendeavors.jpa.enums.PostType;
import com.leatherswan.artisticendeavors.jpa.model.Post;
import com.leatherswan.artisticendeavors.jpa.utils.PostUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;


public class PostDTO implements Serializable {

    private static final long serialVersionUID = 3533657789336113957L;

    private Long postId;
    private Long userId;

    @NotEmpty
    private Set<TagDTO> tags =new HashSet<TagDTO>();

    @NotEmpty
    @Length(max= Post.MAX_POST_TITLE_LENGTH)
    private String postTitle;

    @NotEmpty
    @Length(max= Post.MAX_POST_CONTENT_LENGTH)
    private String postContent;

    private String postName;

    private String postLink;

    @Length(max= Post.MAX_POST_IMAGE_LENGTH)
    private String postImage;

    private ZonedDateTime postDate;

    private ZonedDateTime postModified;

    private PostType postType;

    @NotNull
    private PostDisplayType displayType;

    private Boolean isPublished = true;
    private String postSource = "NA";
    private int clickCount = 0;
    private int likesCount = 0;
    private int valueRating = 0;
    private int version = 0;

    private Boolean hasImages = false;
    private int imageIndex = 1;

    // region getter setters


    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public ZonedDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(ZonedDateTime postDate) {
        this.postDate = postDate;
    }

    public ZonedDateTime getPostModified() {
        return postModified;
    }

    public void setPostModified(ZonedDateTime postModified) {
        this.postModified = postModified;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public PostDisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(PostDisplayType displayType) {
        this.displayType = displayType;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostSource() {
        return postSource;
    }

    public void setPostSource(String postSource) {
        this.postSource = postSource;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
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

    public boolean isNew() {
        return (this.postId == null);
    }

    public Boolean getHasImages() {
        return hasImages;
    }

    public void setHasImages(Boolean hasImages) {
        this.hasImages = hasImages;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    // endregion

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", postTitle='" + postTitle + '\'' +
                ", postName='" + postName + '\'' +
                ", postLink='" + postLink + '\'' +
                ", postDate=" + postDate +
                ", postModified=" + postModified +
                ", postType='" + postType + '\'' +
                ", displayType='" + displayType + '\'' +
                ", isPublished=" + isPublished +
                ", postContent='" + postContent + '\'' +
                ", postSource='" + postSource + '\'' +
                ", clickCount=" + clickCount +
                ", likesCount=" + likesCount +
                ", valueRating=" + valueRating +
                ", version=" + version +
                '}';
    }

    public static Builder getBuilder(Long userId, String postTitle, String postName, String postLink, String postContent, PostType postType, PostDisplayType displayType) {
        return new PostDTO.Builder(userId, postTitle, postName, postLink, postContent, postType, displayType);
    }

    public static Builder getUpdateFields(Long postId,
                                          String postTitle,
                                          String postContent,
                                          PostDisplayType displayType) {
        return new PostDTO.Builder(postId, postTitle, postContent, displayType);
    }

    public static class Builder {

        private PostDTO built;

        public Builder(Long userId, String postTitle, String postName, String postLink, String postContent, PostType postType, PostDisplayType displayType) {
            built = new PostDTO();
            built.userId = userId;
            built.postTitle = postTitle;
            built.postName = postName;
            built.postLink = postLink;
            built.postContent = postContent;
            built.postType = postType;
            built.displayType = displayType;
            built.postSource = PostUtils.createPostSource(postLink);
          }

        public Builder(Long postId,
                       String postTitle,
                       String postContent,
                       PostDisplayType displayType) {
            built = new PostDTO();
            built.postId = postId;
            built.postTitle = postTitle;
            built.postContent = postContent;
            built.displayType = displayType;
        }

        public Builder postImage(String postImage) {
            built.postImage = postImage;
            return this;
        }

        public Builder hasImages(Boolean hasImages) {
            built.hasImages = hasImages;
            return this;
        }

        public Builder postSource(String postSource) {
            built.postSource = postSource;
            return this;
        }

        public Builder postId(Long postId) {
            built.postId = postId;
            return this;
        }
        
        public Builder tags(Set<TagDTO> tagDTOs) {
            built.tags= tagDTOs;
            return this;
        }

        public PostDTO build() {
            return built;
        }
    }
}
