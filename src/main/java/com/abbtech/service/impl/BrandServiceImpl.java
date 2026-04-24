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
import com.abbtech.model.enums.SortDirectionEnum;
import com.abbtech.repository.BrandRepository;
import com.abbtech.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseBrandDto> getAll(int pageNumber, int pageSize, SortDirectionEnum sortDirection, String sortField) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection.toString()), sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return brandRepository.findAll(pageable).map(this::toResponseDto);
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
        existingBrand.setName(request.name());
        existingBrand.setDescription(request.description());
        existingBrand.setImage(request.image());
        existingBrand.setIsActive(request.isActive());
        existingBrand.setIsDeleted(request.isDeleted());
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

            itemsOfAllBrands.addAll(items.stream().map(item -> ResponseItemDto.builder()
                            .id(item.getId() == null ? null : item.getId().longValue())
                            .name(item.getName())
                            .price(item.getPrice())
                            .image(item.getImage())
                            .description(item.getDescription())
                            .build())
                    .toList());
        }


        return itemsOfAllBrands;
    }

    @Override
    @Transactional
    public void saveBrandAndItems(RequestBrandItemDto request) {

        Brand brand = new Brand();
        brand.setName(request.name());
        brand.setDescription(request.description());
        brand.setImage(request.image());
        brand.setIsActive(request.active());
        brand.setIsDeleted(request.deleted());

        var items = request.items().stream()
                .map(item -> {
                    Item newItem = new Item();
                    newItem.setName(item.name());
                    newItem.setPrice(item.price());
                    newItem.setImage(item.image());
                    newItem.setDescription(item.description());
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
            newItem.setName(item.name());
            newItem.setPrice(item.price());
            newItem.setImage(item.image());
            newItem.setDescription(item.description());
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
        brand.setName(request.name());
        brand.setDescription(request.description());
        brand.setImage(request.image());
        brand.setIsActive(request.isActive());
        brand.setIsDeleted(request.isDeleted());

        return brand;
    }

    private ResponseBrandDto toResponseDto(Brand brand) {
        return ResponseBrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .image(brand.getImage())
                .isActive(brand.getIsActive())
                .isDeleted(brand.getIsDeleted())
                .createdAt(brand.getCreatedAt())
                .updatedAt(brand.getUpdatedAt())
                .build();
    }
}

