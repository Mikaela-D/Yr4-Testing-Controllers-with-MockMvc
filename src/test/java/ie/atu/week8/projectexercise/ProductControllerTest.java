package ie.atu.week8.projectexercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product(1L, "Pot", "10 litres", 30);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pot"));

    }

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product(null, "Pot", "10 litres", 30);
        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        ObjectMapper mapper = new ObjectMapper();
        String jsonDetails = mapper.writeValueAsString(product);

        mockMvc.perform(post("/products").contentType("application/json").content(jsonDetails))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Pot"));
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}