package com.leatherswan.artisticendeavors.jpa.utils;

import com.github.slugify.Slugify;
import com.leatherswan.artisticendeavors.jpa.dto.PostDTO;
import com.leatherswan.artisticendeavors.jpa.dto.TagDTO;
import com.leatherswan.artisticendeavors.jpa.model.CurrentUser;
import com.leatherswan.artisticendeavors.jpa.model.Post;
import com.leatherswan.artisticendeavors.jpa.model.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Modified by stramskiak
 */
public class PostUtils {

    private static final Logger logger = LoggerFactory.getLogger(PostUtils.class);
    private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");

    public static Post postDtoToPost(PostDTO dto) {

        return Post.getBuilder(dto.getUserId(),
                dto.getPostTitle(),
                dto.getPostName(),
                dto.getPostLink(),
                dto.getPostContent(),
                dto.getPostType(),
                dto.getDisplayType())
                .postSource(dto.getPostSource())
                .postImage(dto.getPostImage())
                .build();
    }

    public static PostDTO postToPostDTO(Post post) {

        return PostDTO.getBuilder(post.getUserId(),
                post.getPostTitle(),
                post.getPostName(),
                post.getPostLink(),
                post.getPostContent(),
                post.getPostType(),
                post.getDisplayType())
                .postSource(post.getPostSource())
                .postImage(post.getPostImage())
                .postId(post.getPostId())
                .build();
    }

    public static String createPostSource(String url) {
        String domain = null;
        if (StringUtils.isEmpty(url))
            return null;
        else {
            try {
                URL linkURL = new URL(url);
                domain = linkURL.getHost();
            } catch (MalformedURLException e) {
                logger.error("Malformed Url: " + e.getMessage());
            }
        }
        return domain;
    }

    public static String createSlug(String title) {
        Slugify slugify;
        String slug = null;
        try {
            slugify = new Slugify();
            slug = slugify.slugify(title);
        } catch (IOException e) {
            logger.error(String.format("IOException for title: %s -- Exception: %s", title, e.getMessage()));
        }
        return slug;
    }

    public static String removeTags(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        Matcher m = REMOVE_TAGS.matcher(string);
        return m.replaceAll("");
    }

    public static Boolean isPostOwner(CurrentUser currentUser, Long userId) {
        if (currentUser == null) {
            return false;
        }
        return currentUser.getId().equals(userId);
    }

    public static String formatPostContent(Post post) {
        String content = post.getPostContent();
        String imageHtml = "<img alt=\"\" src=\"%s\"  class=\"%s-image\"/>\n";
        String thumbnail = String.format(imageHtml, post.getPostImage(), "thumbnail");
        String feature = String.format(imageHtml, post.getPostImage(), "feature");

        switch (post.getDisplayType()) {
            case LINK_SUMMARY:
                content = StringUtils.prependIfMissing(content, thumbnail);
                break;
            case LINK_FEATURE:
                content = StringUtils.appendIfMissing(content, feature);
                break;
            case LEATHERSWAN_POST:
                content = StringUtils.appendIfMissing(content, feature);
                String nixMashHtml = "<div class=\"nixmash-tag\">" +
                        "<a href=\"http://nixmash.com\" target=\"_blank\">\n" +
                        "<img src=\"/images/posts/nixmashtag.png\" alt=\"\"/></a></div>";
                content = StringUtils.appendIfMissing(content, nixMashHtml);
                break;
            case LINK:
                break;
        }
        return content;
    }

    public static Set<TagDTO> tagsToTagDTOs(Set<Tag> tags) {
        Set<TagDTO> tagDTOs = new LinkedHashSet<>();
        for (Tag tag : tags) {
            tagDTOs.add(new TagDTO(tag.getTagId(), tag.getTagValue()));
        }
        return tagDTOs;
    }

    public static List<String> tagsToTagValues(Set<Tag> tags) {
        List<String> tagValues = new ArrayList<>();
        for (Tag tag : tags) {
            tagValues.add(tag.getTagValue());
        }
        return tagValues;
    }

}
