package com.leatherswan.artisticendeavors.jpa.repository;

import com.leatherswan.artisticendeavors.jpa.model.Post;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Modified by stramskiak
 */
@Service
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    Post findByPostId(Long postId) throws DataAccessException;

    @Query("select distinct p from Post p left join fetch p.tags t")
    List<Post> findAllWithDetail();

    Post findByPostNameIgnoreCase(String postName) throws DataAccessException;

    @Query("select distinct p from Post p left join p.product i where i.id = ?1")
    Page<Post> findPostsByProductId(long id, Pageable pageable);

    @Query("select distinct p from Post p left join p.contact i where i.id = ?1")
    Page<Post> findPostsByContactId(long id, Pageable pageable);

    @Query("select distinct p from Post p left join p.tags t where t.tagId = ?1")
    Page<Post> findByTagId(long tagId, Pageable pageable);

    List<Post> findAll(Sort sort);
}
