package com.abbtech.service.impl;

import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Brand;
import com.abbtech.repository.BrandRepository;
import com.abbtech.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND));
    }

    @Override
    @Transactional
    public Brand add(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public Brand updateById(Long id, Brand brand) {
        var optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            Brand existingBrand = optionalBrand.get();
            existingBrand.setName(brand.getName());
            existingBrand.setDescription(brand.getDescription());
            existingBrand.setImage(brand.getImage());
            return brandRepository.save(existingBrand);
        } else {
            throw new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }
}

