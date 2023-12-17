package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByIsAvailableAndType(boolean value, ProductType type);
    Product findById(int id);
}
