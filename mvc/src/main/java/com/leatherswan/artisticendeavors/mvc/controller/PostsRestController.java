package com.leatherswan.artisticendeavors.mvc.controller;

import com.leatherswan.artisticendeavors.jpa.dto.TagDTO;
import com.leatherswan.artisticendeavors.jpa.model.CurrentUser;
import com.leatherswan.artisticendeavors.jpa.model.Post;
import com.leatherswan.artisticendeavors.jpa.service.PostService;
import com.leatherswan.artisticendeavors.jpa.utils.Pair;
import com.leatherswan.artisticendeavors.jpa.utils.PostUtils;
import com.leatherswan.artisticendeavors.mail.service.TemplateService;
import com.leatherswan.artisticendeavors.mvc.annotations.JsonRequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;


@RestController
@JsonRequestMapping(value = "/json/posts")
public class PostsRestController {

    private static final Logger logger = LoggerFactory.getLogger(PostsRestController.class);

    PostService postService;
    TemplateService templateService;

    @Autowired
    public PostsRestController(PostService postService, TemplateService templateService) {
        this.postService = postService;
        this.templateService = templateService;
    }

    // region get all Posts

    @RequestMapping(value = "/page/{pageNumber}", produces = "text/html;charset=UTF-8")
    public String getPosts(@PathVariable Integer pageNumber, HttpServletRequest request, CurrentUser currentUser) {
        Slice<Post> posts = postService.getPosts(pageNumber, 10);
        String result = StringUtils.EMPTY;
        for (Post post : posts) {
            post.setIsOwner(PostUtils.isPostOwner(currentUser, post.getUserId()));
            result += templateService.createPostHtml(post);
        }
        WebUtils.setSessionAttribute(request, "posts", posts);
        return result;
    }

    // endregion

// region get Posts by Tag

    @RequestMapping(value = "/tag/{tagid}/page/{pageNumber}",
            produces = "text/html;charset=UTF-8")
    public String getPostsByTagId(@PathVariable long tagid,
                                  @PathVariable int pageNumber,
                                  HttpServletRequest request,
                                  CurrentUser currentUser) {
        Slice<Post> posts = postService.getPostsByTagId(tagid, pageNumber, 10);
        String result = StringUtils.EMPTY;
        for (Post post : posts) {
            post.setIsOwner(PostUtils.isPostOwner(currentUser, post.getUserId()));
            result += templateService.createPostHtml(post);
        }
        WebUtils.setSessionAttribute(request, "taggedposts", posts);
        return result;
    }

    // endregion


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/tag/{tagid}/more")
    public String getHasNext(@PathVariable int tagid, HttpServletRequest request) {
        Slice<Post> posts = (Slice<Post>) WebUtils.getSessionAttribute(request, "taggedposts");
        if (posts != null)
            return Boolean.toString(posts.hasNext());
        else
            return "true";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/more")
    public String getHasNext(HttpServletRequest request) {
        Slice<Post> posts = (Slice<Post>) WebUtils.getSessionAttribute(request, "posts");
        if (posts != null)
            return Boolean.toString(posts.hasNext());
        else
            return "true";
    }

    @RequestMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<TagDTO> getAllTagDTOs() {
        return postService.getTagDTOsByPostId();
    }

    @RequestMapping(value = "/tagvalues", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getTagValues() {
        return postService.getTagValues();
    }


    // region Key-Value Json

    //
    // --- demo for NixMash Post "Variations on JSON Key-Value Pairs in Spring MVC"  http://goo.gl/0hhnZg

    private String key = "key";
    private String value = "Json Key-Value Demo";

    /*
    *           Returns:  {  "key" : "Json Key-Value Demo"  }
     */
    @RequestMapping(value = "/map", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> returnMap() {
        Map<String, String> keyvalues = new HashMap<>();
        keyvalues.put(key, value);
        return keyvalues;
    }

    /*
    *           Returns:  {  "key" : "Json Key-Value Demo"  }
     */
    @RequestMapping(value = "/simpleentry")
    public SimpleEntry<String, String> returnSimpleEntry() {
        return new SimpleEntry<>(key, value);
    }

    /*
    *           Returns:  {  "key" : "Json Key-Value Demo"  }
     */
    @RequestMapping(value = "/singleton")
    public Map<String, String> returnSingletonMapFromCollection() {
        return Collections.singletonMap(key, value);
    }

    /*
    *           Returns:
    *           {
    *                    "key" : "key",
    *                     "value" : "Json Key-Value Demo"
    *           }
     */
    @RequestMapping(value = "/pair")
    public Pair<String, String> returnPair() {
        return new Pair<>(key, value);
    }

    // endregion

}
