package com.leatherswan.artisticendeavors.jpa.service;

import com.leatherswan.artisticendeavors.jpa.dto.PostDTO;
import com.leatherswan.artisticendeavors.jpa.dto.ProductDTO;
import com.leatherswan.artisticendeavors.jpa.dto.TagDTO;
import com.leatherswan.artisticendeavors.jpa.exceptions.DuplicatePostNameException;
import com.leatherswan.artisticendeavors.jpa.exceptions.PostNotFoundException;
import com.leatherswan.artisticendeavors.jpa.exceptions.TagNotFoundException;
import com.leatherswan.artisticendeavors.jpa.model.Post;
import com.leatherswan.artisticendeavors.jpa.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Modified to provide reviews by stramskiak on 8/15/16.
 */
public interface PostService {

    Post add(PostDTO postDTO) throws DuplicatePostNameException;

    Post getPost(String postName) throws PostNotFoundException;

    Page<Post> getPosts(Integer pageNumber, Integer pageSize);

    @Transactional
    Post update(PostDTO postDTO) throws PostNotFoundException;

    Post getPostById(Long postId) throws PostNotFoundException;

    @Transactional(readOnly = true)
    List<Post> getAllPosts();

    @Transactional(readOnly = true)
    List<Post> getPostsWithDetail();

    @Transactional(readOnly = true)
    Set<TagDTO> getTagDTOsByPostId();

    boolean canUpdatePost(Authentication authentication, Long postId);

    @Transactional(readOnly = true)
    List<String> getTagValues();

    Set<TagDTO> getTagDTOsByPostId(Long postId);
    Tag getTag(String tagValue) throws TagNotFoundException;

    Page<Post> getPostsByTagId(long tagId, int pageNumber, int pageSize);

    List<ProductDTO> getProductDTOsByPostId(Long postId);

    Page<Post> getPostsByProductId(long productId, int pageNumber, int pageSize);
}
