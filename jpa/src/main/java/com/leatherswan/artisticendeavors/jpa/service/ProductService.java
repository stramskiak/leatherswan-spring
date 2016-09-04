package com.leatherswan.artisticendeavors.jpa.service;

import java.util.List;

import com.leatherswan.artisticendeavors.jpa.dto.ProductDTO;
import com.leatherswan.artisticendeavors.jpa.exceptions.GeoLocationException;
import com.leatherswan.artisticendeavors.jpa.exceptions.ProductNotFoundException;
import com.leatherswan.artisticendeavors.jpa.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

	Iterable<Product> getAvailableProducts();

	Iterable<Product> getAllRecords();

	List<Product> getProductsByFilter();

	List<Product> getProducts();

	Product getProduct(Long Id);

	Product getProductById(Long Id) throws ProductNotFoundException;

//	void updateProductName(Product product);

	Product updateProduct(ProductDTO productDTO) throws ProductNotFoundException;

	Iterable<Product> getProductsByNameOrCategory(String searchTerm);

	List<Product> getProductsByStartOfName(String nameStart);

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByLocation(String LatLng)  throws GeoLocationException;

}
