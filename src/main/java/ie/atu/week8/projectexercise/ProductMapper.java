package ie.atu.week8.projectexercise;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId()); // Map the ID
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        return dto;
    }


    public static Product toEntity(ProductDTO productDTO) {
        return new Product(null, productDTO.getName(), productDTO.getDescription(), productDTO.getPrice());
    }
}
