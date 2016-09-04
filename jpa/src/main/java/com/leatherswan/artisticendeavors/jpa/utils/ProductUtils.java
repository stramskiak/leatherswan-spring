package com.leatherswan.artisticendeavors.jpa.utils;

import com.leatherswan.artisticendeavors.jpa.dto.CategoryDTO;
import com.leatherswan.artisticendeavors.jpa.dto.ProductDTO;
import com.leatherswan.artisticendeavors.jpa.model.Category;
import com.leatherswan.artisticendeavors.jpa.model.CurrentUser;
import com.leatherswan.artisticendeavors.jpa.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by stramskiak
 */
public class ProductUtils {

    private static final Logger logger = LoggerFactory.getLogger(ProductUtils.class);
    private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");

    public static Product productDtoToProduct(ProductDTO dto) {

        return Product.getBuilder(
                dto.getName(),
                dto.getProductLink(),
                dto.getFeatures(),
                dto.getProductType(),
                dto.getDisplayType())
                .productSource(dto.getProductSource())
                .productImage(dto.getProductImage())
                .build();
    }

    public static ProductDTO productToProductDTO(Product product) {

        return ProductDTO.getBuilder(
                product.getName(),
                product.getProductLink(),
                product.getFeatures(),
                product.getProductType(),
                product.getDisplayType())
                .productSource(product.getProductSource())
                .productImage(product.getProductImage())
                .productId(product.getId())
                .build();
    }

    public static String createProductSource(String url) {
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

    public static Boolean isProductOwner(CurrentUser currentUser, Long userId) {
        if (currentUser == null) {
            return false;
        }
        return currentUser.getId().equals(userId);
    }

    public static String formatProductContent(Product product) {
        String content = product.getFeatures();
        String imageHtml = "<img alt=\"\" src=\"%s\"  class=\"%s-image\"/>\n";
        String thumbnail = String.format(imageHtml, product.getProductImage(), "thumbnail");
        String feature = String.format(imageHtml, product.getProductImage(), "feature");

        switch (product.getDisplayType()) {
            case LINK_SUMMARY:
                content = StringUtils.prependIfMissing(content, thumbnail);
                break;
            case LINK_FEATURE:
                content = StringUtils.appendIfMissing(content, feature);
                break;
            case LEATHERSWAN_POST:
                content = StringUtils.appendIfMissing(content, feature);
                String leatherSwanHtml = "<div class=\"leatherswan-tag\">" +
                        "<a href=\"http://leatherswan.com\" target=\"_blank\">\n" +
                        "<img src=\"/images/products/LeatherSwanLogoPlay.jpg\" alt=\"\"/></a></div>";
                content = StringUtils.appendIfMissing(content, leatherSwanHtml);
                break;
            case LINK:
                break;
        }
        return content;
    }

    public static Set<CategoryDTO> categoriesToCategoryDTOs(Set<Category> categories) {
        Set<CategoryDTO> categoryDTOs = new LinkedHashSet<>();
        for (Category category : categories) {
            categoryDTOs.add(new CategoryDTO(category.getCategoryId(), category.getCategoryValue()));
        }
        return categoryDTOs;
    }

    public static List<String> categoriesToCategoryValues(Set<Category> categories) {
        List<String> categoryValues = new ArrayList<>();
        for (Category category : categories) {
            categoryValues.add(category.getCategoryValue());
        }
        return categoryValues;
    }
}
