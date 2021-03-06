package com.leatherswan.artisticendeavors.mvc.controller;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import com.leatherswan.artisticendeavors.jpa.exceptions.GeoLocationException;
import com.leatherswan.artisticendeavors.jpa.model.Product;
import com.leatherswan.artisticendeavors.jpa.service.ProductService;
import com.leatherswan.artisticendeavors.mvc.AbstractContext;
//import com.leatherswan.artisticendeavors.solr.exceptions.GeoLocationException;
//import com.leatherswan.artisticendeavors.solr.model.Product;
//import com.leatherswan.artisticendeavors.solr.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(DataConfigProfile.H2)
public class ProductControllerTests extends AbstractContext {

	private static final String PRODUCT_ID = "LOMAX7";

	private static final String AUTOCOMPLETE_FRAGMENT = "ph";

	private ProductService mockProductService;
	private ProductController productController;
	private MockMvc mockMvc;
	private MockMvc integrationMvc;
	private List<Product> allProducts;
	private Product product;

	@Autowired
	private ProductService productService;

	@Before
	public void setUp() throws GeoLocationException {

		final ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver();
		final StaticApplicationContext applicationContext = new StaticApplicationContext();
		applicationContext.registerBeanDefinition("productController",
				new RootBeanDefinition(ProductController.class, null, null));
		exceptionResolver.setApplicationContext(applicationContext);
		exceptionResolver.afterPropertiesSet();

		mockProductService = mock(ProductService.class);

		String badLocation = "35.453487-97.5184727";
		when(mockProductService.getProductsByLocation(badLocation)).thenThrow(new GeoLocationException());

		productController = new ProductController(mockProductService);
		mockMvc = standaloneSetup(productController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setHandlerExceptionResolvers(exceptionResolver).build();

		product = createProduct(1000);
		when(mockProductService.getProduct(Long.getLong(PRODUCT_ID))).thenReturn(product);

		allProducts = createProductList(10);
		when(mockProductService.getProducts()).thenReturn(allProducts);

	}

	@Test
	public void testProductsRedirectionPage() throws Exception {
		mockMvc.perform(get("/products")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void getPagedProducts() throws Exception {
		mockMvc.perform(get("/products/page/1")).andExpect(status().isOk())
				.andExpect(model().attributeExists("products"))
				.andExpect(model().attribute("products", isA(PagedListHolder.class)));
	}

	@Test
	public void getProductById() throws Exception {
		mockMvc.perform(get(String.format("/products/%s", PRODUCT_ID))).andExpect(view().name("products/view"))
				.andExpect(model().attributeExists("product")).andExpect(model().attribute("product", product));
	}

	@Test
	public void autoCompleteShouldReturnJson() throws Exception {

		integrationMvc = standaloneSetup(new ProductController(productService))
				.setSingleView(new InternalResourceView("/products/search.html")).build();

		MvcResult result = integrationMvc
				.perform(get(String.format("/products/autocomplete?term=%s", AUTOCOMPLETE_FRAGMENT))
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		// Using Jackson ObjectMapper on JSON
		//
		// ObjectMapper mapper = new ObjectMapper();
		// List<String> fragments = Arrays.asList(mapper.readValue(body, String[].class));

		String body = result.getResponse().getContentAsString();
		String[] fragments = body.replaceAll("[\\p{Ps}\\p{Pe}\\\"]", "").split(",");

		for (String string : fragments) {
			assert(string.contains(AUTOCOMPLETE_FRAGMENT));
		}
	}

	@Test
	public void badSimplyQueryShouldDisplayError() throws Exception {

		integrationMvc = standaloneSetup(new ProductController(productService))
				.setSingleView(new InternalResourceView("/WEB-INF/views/products/list.html")).build();

		integrationMvc.perform(get("/products/list").param("query", "name1:memory")).andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrorCode("userQuery", "query", "product.search.error"))
				.andExpect(view().name("products/search"));

	}

	@Test
	public void badLocationShouldThrowGeoLocationException() {
		
		integrationMvc = standaloneSetup(new ProductController(productService))
				.setSingleView(new InternalResourceView("/WEB-INF/views/products/map.html")).build();
		
		try {
			integrationMvc.perform(get("/products/map/bad")).andDo(print()).andReturn();
		} catch (Exception ex) {
			Assert.assertTrue(ex.getCause() instanceof GeoLocationException);
		}
	}

	@Test
	public void goodLocationDisplaysAMap() throws Exception {
		mockMvc.perform(get("/products/map")).andExpect(status().isOk())
				.andExpect(model().attributeDoesNotExist(GlobalController.LOCATION_ERROR_ATTRIBUTE));
	}

	private Product createProduct(int id) {
		Product product = new Product();
		product.setId(Integer.toUnsignedLong(id));
		product.setAvailable(id % 2 == 0);
		product.setName("product-" + id);
		product.setPopularity(id * 10);
		product.setPrice((float) id * 100);
		product.setWeight((float) id * 2);
		return product;
	}

	private List<Product> createProductList(int nrProducts) {
		List<Product> products = new ArrayList<Product>(nrProducts);
		for (int i = 0; i < nrProducts; i++) {
			products.add(createProduct(i));
		}
		return products;
	}
}