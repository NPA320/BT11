package BT11.service;

import java.util.List;
import BT11.entity.Product;

public interface ProductService {
    
    void delete(Long id);
    
    Product get(Long id);
    
    Product save(Product product);
    
    List<Product> listAll();
}
