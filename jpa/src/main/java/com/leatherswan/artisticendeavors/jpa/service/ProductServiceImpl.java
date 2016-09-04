package com.leatherswan.artisticendeavors.jpa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.leatherswan.artisticendeavors.jpa.dto.CategoryDTO;
import com.leatherswan.artisticendeavors.jpa.dto.ProductDTO;
import com.leatherswan.artisticendeavors.jpa.enums.ProductDocType;
import com.leatherswan.artisticendeavors.jpa.exceptions.GeoLocationException;
import com.leatherswan.artisticendeavors.jpa.exceptions.ProductNotFoundException;
import com.leatherswan.artisticendeavors.jpa.model.Category;
import com.leatherswan.artisticendeavors.jpa.model.Product;
import com.leatherswan.artisticendeavors.jpa.repository.CategoryRepository;
import com.leatherswan.artisticendeavors.jpa.repository.ContactRepository;
import com.leatherswan.artisticendeavors.jpa.repository.PostRepository;
import com.leatherswan.artisticendeavors.jpa.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private static final Pattern IGNORED_CHARS_PATTERN = Pattern.compile("\\p{Punct}");

    @Resource
    private ProductRepository productRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private PostRepository postRepository;

    @Resource
    private ContactRepository contactRepository;

    @Override
    public List<Product> getProductsByCategory(String category) {
        logger.info("Retrieving products by category: {}", category);
        return productRepository.findByCategory(category);
    }

    @Override
    public Iterable<Product> getAvailableProducts() {
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> getAllRecords() {
        logger.info("Retrieving all records in index");
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByFilter() {
        logger.info("Retrieving all records and filtering out by 'doctype:product'");
        List<Product> products = Lists.newArrayList(productRepository.findAll());
        return products.stream().filter(p -> p.getDoctype().equals(ProductDocType.PRODUCT)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProducts() {
        logger.info("Retrieving all products by @Query");
        return productRepository.findAllProducts();
//        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProduct(Long Id) {
        return null;
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long Id) throws ProductNotFoundException {
        logger.info("Finding contact by id: {}", Id);

        Product found = productRepository.findOne(Id);

        if (found == null) {
            logger.info("No contact found with id: {}", Id);
            throw new ProductNotFoundException("No contact found with id: " + Id);
        }

        return found;
    }

    @Override
    public List<Product> getProductsByLocation(String LatLng) throws GeoLocationException {
        List<Product> found;
        try {
            Collection<String> ps = splitSearchTermAndRemoveIgnoredCharacters(LatLng);
            List<Double> dbls = new ArrayList<Double>();
            for (String string : ps) {
                dbls.add(Double.valueOf(string));
            }
             Point point = new Point(dbls.get(1), dbls.get(2));
            found =
                    productRepository.findByLocationNear(new Point(point.getX(), point.getY()), new Distance(30));
        } catch (Exception e) {
            logger.info("No location found with coordinates: {}", LatLng);
            throw new GeoLocationException("Error in mapping latLng: " + LatLng);
        }
//        return found;
        return null;
    }

    @Override
    public List<Product> getProductsByStartOfName(String nameStart) {
        logger.info("Named Method Query -  findByNameStartingWith()");
        return productRepository.findByNameStartingWith(nameStart);
    }

    @Override
    public Iterable<Product> getProductsByNameOrCategory(String searchTerm) {
        logger.info("Using 'Product.findByNameOrCategory' named query - ('name:*?0* OR cat:*?0*')");
//        return productRepository.findByNameOrCategory(searchTerm);
        return null;
    }


/*
    @Override
    public void updateProductName(Product product) {
        productRepository.updateProductName(product);
    }
*/

    @Transactional(rollbackFor = ProductNotFoundException.class)
    @Override
    public Product updateProduct(ProductDTO productDTO) throws ProductNotFoundException {
        logger.info("Updating contact with information: {}", productDTO);

        Product found = getProductById(productDTO.getId());

        // Update the product information
        found.update(productDTO.getName(), productDTO.getFeatures(), productDTO.getClickCount(), productDTO.getProductImage(), productDTO.getLikesCount());
        // Update the product categories if updateChildren(true)

        if (productDTO.isUpdateChildren()) {

 /*           if (productDTO.getCategories() != null) {
                for (CategoryDTO categoryDTO : productDTO.getCategories()) {
                    Category category =
                            categoryRepository.findByCategoryId(categoryDTO.getCategoryId());
                    if (category != null) {
                        category.update(categoryDTO.getCategoryValue());
                    } else {
//                        category = saveCategory(found, categoryDTO);
                        found.getCategories().add(category);
                    }
                }
            }
*/
/*
            if (productDTO.getCategories() != null) {
                saveNewCategoriesToDatabase(productDTO);
            }

            if (productDTO.getCategories() != null) {
                found.getCategories().clear();
                for (CategoryDTO categoryDTO : productDTO.getCategories()) {
                    Category category = categoryRepository.findByCategoryTitleIgnoreCase(categoryDTO.getCategoryTitle());

                    if (!found.getCategories().contains(category))
                        found.getCategories().add(category);
                }
            }
*/
        }
        return found;
    }

    private Collection<String> splitSearchTermAndRemoveIgnoredCharacters(String searchTerm) {
        String[] searchTerms = StringUtils.split(searchTerm, " ");
        List<String> result = new ArrayList<String>(searchTerms.length);
        for (String term : searchTerms) {
            if (StringUtils.isNotEmpty(term)) {
                result.add(IGNORED_CHARS_PATTERN.matcher(term).replaceAll(" "));
            }
        }
        return result;
    }

    @Transactional
    private void saveNewCategoriesToDatabase(ProductDTO added) {
        for (CategoryDTO categoryDTO : added.getCategories()) {
            Category category = categoryRepository.findByCategoryValueIgnoreCase(categoryDTO.getCategoryValue());
            if (category == null) {
                category = new Category(categoryDTO.getCategoryValue());
                categoryRepository.save(category);
            }
        }
    }


}
