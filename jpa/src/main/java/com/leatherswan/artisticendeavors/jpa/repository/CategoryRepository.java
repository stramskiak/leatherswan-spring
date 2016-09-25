package com.leatherswan.artisticendeavors.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.leatherswan.artisticendeavors.jpa.model.Category;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByCategoryValueIgnoreCase(String categoryValue);

	Category findByCategoryId(Long categoryId);

/*
	@Query("select distinct c from Category c left join fetch " +
			"c.products p where p.id = ?1")
	public Set<Category> findByCategory(Long productId);
*/

}
