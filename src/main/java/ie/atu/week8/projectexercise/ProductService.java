package ie.atu.week8.projectexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Fetch all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Fetch a product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Save product with custom validation and business logic
    public Product saveProduct(Product product) {
        validateProduct(product);
        applyDiscount(product);
        return productRepository.save(product);
    }

    // Delete a product by ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Business logic: Apply a discount to products with price > 1000
    private void applyDiscount(Product product) {
        if (product.getPrice() > 1000) {
            product.setPrice(product.getPrice() * 0.9); // 10% discount
        }
    }

    // Validation logic: Ensure the product price is positive and name is not empty
    private void validateProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
    }
}