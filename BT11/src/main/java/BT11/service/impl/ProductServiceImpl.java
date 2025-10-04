package BT11.service.impl;

import org.springframework.stereotype.Service;

import BT11.entity.Product;
import BT11.repository.ProductRepository;
import BT11.service.*;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    // Constructor injection
    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> listAll() {
        return repo.findAll();
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    public Product get(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cannot delete. Product not found with id: " + id);
        }
        repo.deleteById(id);
    }
}

