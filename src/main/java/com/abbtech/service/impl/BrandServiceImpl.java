package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.request.RequestBrandItemDto;
import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Brand;
import com.abbtech.model.Item;
import com.abbtech.repository.BrandRepository;
import com.abbtech.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseBrandDto> getAll() {
        var brands = brandRepository.findAll();
        return brands.stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseBrandDto getById(Long id) {

        var optionalBrand = brandRepository.findById(id);
        var optionalBrand1 = brandRepository.findById(id);
        var optionalBrand2 = brandRepository.findById(id);

        return toResponseDto(optionalBrand.orElseThrow());
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

    @Override
    @Transactional(readOnly = true)
    public List<ResponseItemDto> getItemsByBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ProductException(ProductErrorEnum.BRAND_NOT_FOUND));

        var brands = brandRepository.findAllById(List.of(1L, 2L, 3L));

        var categories = brand.getCategories();

        List<ResponseItemDto> itemsOfAllBrands = new ArrayList<>();

        for (Brand brand1 : brands) {

            var items = brand1.getItems();

            itemsOfAllBrands.addAll(items.stream().map(item -> new ResponseItemDto(
                            item.getId() == null ? null : item.getId().longValue(),
                            item.getName(),
                            item.getPrice(),
                            item.getImage(),
                            item.getDescription()))
                    .toList());
        }


        return itemsOfAllBrands;
    }

    @Override
    @Transactional
    public void saveBrandAndItems(RequestBrandItemDto request) {

        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setImage(request.getImage());
        brand.setIsActive(request.getActive());
        brand.setIsDeleted(request.getDeleted());

        var items = request.getItems().stream()
                .map(item -> {
                    Item newItem = new Item();
                    newItem.setName(item.getName());
                    newItem.setPrice(item.getPrice());
                    newItem.setImage(item.getImage());
                    newItem.setDescription(item.getDescription());
                    newItem.setIsActive(true);
                    newItem.setIsDeleted(false);
                    newItem.setBrand(brand);
                    return newItem;
                }).toList();
        brand.setItems(items);

        brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void updateBrandItems(Long id, List<RequestItemDto> items) {
        var brand = brandRepository.findById(id).orElseThrow(() -> new ProductException(ProductErrorEnum.BRAND_NOT_FOUND));

        for (Item existingItem : brand.getItems()) {
            existingItem.setBrand(null);
        }

        brand.getItems().clear();

        for (RequestItemDto item : items) {
            Item newItem = new Item();
            newItem.setName(item.getName());
            newItem.setPrice(item.getPrice());
            newItem.setImage(item.getImage());
            newItem.setDescription(item.getDescription());
            newItem.setIsActive(true);
            newItem.setIsDeleted(false);
            newItem.setBrand(brand);

            brand.getItems().add(newItem);
        }
        brandRepository.save(brand);
    }

    private Brand findBrandByIdOrThrow(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.ITEM_NOT_FOUND));
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

