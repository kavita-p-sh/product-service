package com.ecommerce.product.service.impl;


import com.ecommerce.common.entity.ProductEntity;
import com.ecommerce.common.exception.BadRequestException;
import com.ecommerce.common.exception.ResourceNotFoundException;
import com.ecommerce.common.repository.ProductRepository;
import com.ecommerce.product.dto.ProductRequestDTO;
import com.ecommerce.product.dto.ProductResponseDTO;
import com.ecommerce.product.dto.ProductUpdateDTO;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ProductServiceImpl.
 *
 * This test class uses Mockito only.
 * No Spring context is loaded and @Autowired is not used.
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void addProduct_success() {
        ProductRequestDTO request = new ProductRequestDTO();
        request.setName("Laptop");
        request.setDescription("Dell Laptop");
        request.setPrice(BigDecimal.valueOf(55000));
        request.setQuantity(10);

        ProductEntity savedEntity = new ProductEntity();
        savedEntity.setProductId(1L);
        savedEntity.setName("Laptop");
        savedEntity.setDescription("Dell Laptop");
        savedEntity.setPrice(BigDecimal.valueOf(55000));
        savedEntity.setQuantity(10);

        ProductResponseDTO response = new ProductResponseDTO();
        response.setProductId(1L);
        response.setName("Laptop");
        response.setDescription("Dell Laptop");
        response.setPrice(BigDecimal.valueOf(55000));
        response.setQuantity(10);

        when(productRepository.existsByName("Laptop")).thenReturn(false);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(savedEntity);
        when(productMapper.toDTO(savedEntity)).thenReturn(response);

        ProductResponseDTO result = productService.addProduct(request);

        assertNotNull(result);
        assertEquals(1L, result.getProductId());
        assertEquals("Laptop", result.getName());
        assertEquals(BigDecimal.valueOf(55000), result.getPrice());
        assertEquals(10, result.getQuantity());

        verify(productRepository).existsByName("Laptop");
        verify(productRepository).save(any(ProductEntity.class));
        verify(productMapper).toDTO(savedEntity);
    }

    @Test
    void addProduct_duplicateName_shouldThrowBadRequestException() {
        ProductRequestDTO request = new ProductRequestDTO();
        request.setName("Laptop");

        when(productRepository.existsByName("Laptop")).thenReturn(true);

        BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> productService.addProduct(request)
        );

        assertTrue(exception.getMessage().contains("Laptop"));

        verify(productRepository).existsByName("Laptop");
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    void getProducts_byName_success() {
        ProductEntity product = new ProductEntity();
        product.setProductId(1L);
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(55000));
        product.setQuantity(10);

        ProductResponseDTO response = new ProductResponseDTO();
        response.setProductId(1L);
        response.setName("Laptop");
        response.setPrice(BigDecimal.valueOf(55000));
        response.setQuantity(10);

        when(productRepository.findByName("Laptop")).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(response);

        List<ProductResponseDTO> result =
                productService.getProducts("Laptop", null, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getName());

        verify(productRepository).findByName("Laptop");
        verify(productMapper).toDTO(product);
    }

    @Test
    void getProducts_byName_notFound_shouldThrowResourceNotFoundException() {
        when(productRepository.findByName("Laptop")).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.getProducts("Laptop", null, null)
        );

        verify(productRepository).findByName("Laptop");
    }

    @Test
    void getProducts_byPrice_success() {
        BigDecimal price = BigDecimal.valueOf(55000);

        ProductEntity product = new ProductEntity();
        product.setProductId(1L);
        product.setName("Laptop");
        product.setPrice(price);
        product.setQuantity(10);

        ProductResponseDTO response = new ProductResponseDTO();
        response.setProductId(1L);
        response.setName("Laptop");
        response.setPrice(price);
        response.setQuantity(10);

        when(productRepository.findByPrice(price)).thenReturn(List.of(product));
        when(productMapper.toDTO(product)).thenReturn(response);

        List<ProductResponseDTO> result =
                productService.getProducts(null, price, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(price, result.get(0).getPrice());

        verify(productRepository).findByPrice(price);
        verify(productMapper).toDTO(product);
    }

    @Test
    void getProducts_byQuantity_success() {
        ProductEntity product = new ProductEntity();
        product.setProductId(1L);
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(55000));
        product.setQuantity(10);

        ProductResponseDTO response = new ProductResponseDTO();
        response.setProductId(1L);
        response.setName("Laptop");
        response.setPrice(BigDecimal.valueOf(55000));
        response.setQuantity(10);

        when(productRepository.findByQuantity(10)).thenReturn(List.of(product));
        when(productMapper.toDTO(product)).thenReturn(response);

        List<ProductResponseDTO> result =
                productService.getProducts(null, null, 10);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getQuantity());

        verify(productRepository).findByQuantity(10);
        verify(productMapper).toDTO(product);
    }

    @Test
    void getAllProducts_success() {
        ProductEntity product = new ProductEntity();
        product.setProductId(1L);
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(55000));
        product.setQuantity(10);

        ProductResponseDTO response = new ProductResponseDTO();
        response.setProductId(1L);
        response.setName("Laptop");
        response.setPrice(BigDecimal.valueOf(55000));
        response.setQuantity(10);

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toDTO(product)).thenReturn(response);

        List<ProductResponseDTO> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getName());

        verify(productRepository).findAll();
        verify(productMapper).toDTO(product);
    }

    @Test
    void getProductById_success() {
        ProductEntity product = new ProductEntity();
        product.setProductId(1L);
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(55000));
        product.setQuantity(10);

        ProductResponseDTO response = new ProductResponseDTO();
        response.setProductId(1L);
        response.setName("Laptop");
        response.setPrice(BigDecimal.valueOf(55000));
        response.setQuantity(10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(response);

        ProductResponseDTO result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getProductId());
        assertEquals("Laptop", result.getName());

        verify(productRepository).findById(1L);
        verify(productMapper).toDTO(product);
    }

    @Test
    void getProductById_notFound_shouldThrowResourceNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.getProductById(1L)
        );

        verify(productRepository).findById(1L);
    }

    @Test
    void updateProductById_success() {
        ProductUpdateDTO request = new ProductUpdateDTO();
        request.setName("Updated Laptop");
        request.setDescription("Updated description");
        request.setPrice(BigDecimal.valueOf(60000));
        request.setQuantity(15);

        ProductEntity existingProduct = new ProductEntity();
        existingProduct.setProductId(1L);
        existingProduct.setName("Laptop");
        existingProduct.setDescription("Old description");
        existingProduct.setPrice(BigDecimal.valueOf(55000));
        existingProduct.setQuantity(10);

        ProductEntity savedProduct = new ProductEntity();
        savedProduct.setProductId(1L);
        savedProduct.setName("Updated Laptop");
        savedProduct.setDescription("Updated description");
        savedProduct.setPrice(BigDecimal.valueOf(60000));
        savedProduct.setQuantity(15);

        ProductResponseDTO response = new ProductResponseDTO();
        response.setProductId(1L);
        response.setName("Updated Laptop");
        response.setDescription("Updated description");
        response.setPrice(BigDecimal.valueOf(60000));
        response.setQuantity(15);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.existsByName("Updated Laptop")).thenReturn(false);
        when(productRepository.save(existingProduct)).thenReturn(savedProduct);
        when(productMapper.toDTO(savedProduct)).thenReturn(response);

        ProductResponseDTO result = productService.updateProductById(1L, request);

        assertNotNull(result);
        assertEquals("Updated Laptop", result.getName());
        assertEquals(BigDecimal.valueOf(60000), result.getPrice());
        assertEquals(15, result.getQuantity());

        verify(productRepository).findById(1L);
        verify(productRepository).existsByName("Updated Laptop");
        verify(productRepository).save(existingProduct);
        verify(productMapper).toDTO(savedProduct);
    }

    @Test
    void updateProductById_notFound_shouldThrowResourceNotFoundException() {
        ProductUpdateDTO request = new ProductUpdateDTO();
        request.setName("Updated Laptop");

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.updateProductById(1L, request)
        );

        verify(productRepository).findById(1L);
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    void updateProductById_duplicateName_shouldThrowBadRequestException() {
        ProductUpdateDTO request = new ProductUpdateDTO();
        request.setName("Mouse");

        ProductEntity existingProduct = new ProductEntity();
        existingProduct.setProductId(1L);
        existingProduct.setName("Laptop");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.existsByName("Mouse")).thenReturn(true);

        assertThrows(
                BadRequestException.class,
                () -> productService.updateProductById(1L, request)
        );

        verify(productRepository).findById(1L);
        verify(productRepository).existsByName("Mouse");
        verify(productRepository, never()).save(any(ProductEntity.class));
    }

    @Test
    void deleteProductById_success() {
        ProductEntity product = new ProductEntity();
        product.setProductId(1L);
        product.setName("Laptop");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProductById(1L);

        verify(productRepository).findById(1L);
        verify(productRepository).delete(product);
    }

    @Test
    void deleteProductById_notFound_shouldThrowResourceNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.deleteProductById(1L)
        );

        verify(productRepository).findById(1L);
        verify(productRepository, never()).delete(any(ProductEntity.class));
    }


}