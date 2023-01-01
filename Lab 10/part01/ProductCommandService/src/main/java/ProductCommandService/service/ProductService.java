package ProductCommandService.service;

import ProductCommandService.model.Product;
import ProductCommandService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        return this.productRepository.save(product);
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    public Product deleteProduct(Integer productNumber){
        Product productFromRepo = productRepository.findById(productNumber).orElse(null);
        if(productFromRepo != null){
            productRepository.delete(productFromRepo);
        }
        return productFromRepo;
    }
}
