package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.ResponseBrandDto;
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
    public List<ResponseBrandDto> getAll() {
        return brandRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public ResponseBrandDto getById(Long id) {
        return toResponseDto(findBrandByIdOrThrow(id));
    }

    @Override
    @Transactional
    public ResponseBrandDto add(RequestBrandDto request) {
        Brand brand = toBrand(request);
        return toResponseDto(brandRepository.save(brand));
    }

    @Override
    @Transactional
    public ResponseBrandDto updateById(Long id, RequestBrandDto request) {
        Brand existingBrand = findBrandByIdOrThrow(id);
        existingBrand.setName(request.getName());
        existingBrand.setDescription(request.getDescription());
        existingBrand.setImage(request.getImage());
        existingBrand.setIsActive(request.getIsActive());
        existingBrand.setIsDeleted(request.getIsDeleted());
        return toResponseDto(brandRepository.save(existingBrand));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    private Brand findBrandByIdOrThrow(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND));
    }

    private Brand toBrand(RequestBrandDto request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setImage(request.getImage());
        brand.setIsActive(request.getIsActive());
        brand.setIsDeleted(request.getIsDeleted());
        return brand;
    }

    private ResponseBrandDto toResponseDto(Brand brand) {
        return new ResponseBrandDto(
                brand.getId(),
                brand.getName(),
                brand.getDescription(),
                brand.getImage(),
                brand.getIsActive(),
                brand.getIsDeleted(),
                brand.getCreatedAt(),
                brand.getUpdatedAt()
        );
    }
}

