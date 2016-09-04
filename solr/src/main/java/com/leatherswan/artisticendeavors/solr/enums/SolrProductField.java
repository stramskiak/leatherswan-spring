package com.leatherswan.artisticendeavors.solr.enums;

import com.leatherswan.artisticendeavors.solr.model.IProduct;
import org.springframework.data.solr.core.query.Field;

public enum SolrProductField implements Field {

	// @formatter:off
	
	ID(IProduct.ID_FIELD),
	NAME(IProduct.NAME_FIELD), 
	PRICE(IProduct.PRICE_FIELD), 
	AVAILABLE(IProduct.AVAILABLE_FIELD), 
	CATEGORY(IProduct.CATEGORY_FIELD), 
	WEIGHT(IProduct.WEIGHT_FIELD), 
	POPULARITY(IProduct.POPULARITY_FIELD),
	DOCTYPE(IProduct.DOCTYPE_FIELD);

	// @formatter:on

	private final String fieldName;

	private SolrProductField(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String getName() {
		return fieldName;
	}

}
