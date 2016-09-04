package com.leatherswan.artisticendeavors.solr.repository.simple;

import java.util.List;

import com.leatherswan.artisticendeavors.solr.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.solr.core.query.result.FacetPage;

public interface SimpleBaseProductRepository extends CrudRepository<Product, String> {

	List<Product> findByAvailableTrue();

	FacetPage<Product> findByFacetOnAvailable();

	FacetPage<Product> findByFacetOnCategory();
}
