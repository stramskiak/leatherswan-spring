/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leatherswan.artisticendeavors.jpa.repository;

import java.util.Collection;
import java.util.List;

import com.leatherswan.artisticendeavors.jpa.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * LeatherSwan Notes: ---------------------------------------------------
 *
 * Inspiration based on NixMash Spring
 *
 * On GitHub: https://github.com/mintster/spring-data
 *
 */
@Service
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

//    @Query("name:*?0* AND doctype:product")
    @Query("select distinct p from Product p where p.name like %:name%")
    List<Product> findByNameStartingWith(String name);

//    @Query("select distinct p from Product p where p.is_sold = false")
//    List<Product> findByIsSoldFalse();

//    @Query("select distinct p from Product p left join p.categories c")
    @Query("select p from Product p ")
    List<Product> findAllProducts();

//    @Query("select distinct p from Product p left join p.categories c")
    @Query("select distinct p from Product p")
    public List<Product> findByLocationNear(Point location, Distance distance);

//    void updateProductCategory(String productId, List<String> categories);

//    void updateProductName(Product product);

//    public List<Product> findByNameContainsOrCategoriesContains(String title, List<String> categories, Sort sort);

//    @Query(name = "Product.findByNameOrCategory")
//    public List<Product> findByNameOrCategory(String searchTerm);

//    @Query("cat:*?0* AND doctype:product")
/*
    @Query("select distinct p from Product p left join fetch " +
        "p.categories c where c.categoryValue = ?1")
    public List<Product> findByCategory(String categoryValue);
*/

/*
    @Query("select distinct p from Product p left join fetch " +
            "p.contacts c where c.contactId = ?1")
    public List<Product> findByContactId(Long contactId);
*/

//    @Query("select distinct p from Product p where p.is_sold = false")
//    public List<Product> findAllIsSoldFalse();

}
