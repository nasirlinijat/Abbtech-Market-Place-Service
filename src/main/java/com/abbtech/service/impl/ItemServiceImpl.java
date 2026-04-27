package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Item;
import com.abbtech.model.enums.SortDirectionEnum;
import com.abbtech.repository.BrandRepository;
import com.abbtech.repository.CategoryRepository;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ResponseItemDto add(RequestItemDto request) {
        Item item = toItem(request);
        return toResponseDto(itemRepository.save(item));
    }

    @Override
    @Transactional
    public void saveAll(List<RequestItemDto> requestItems) {
        List<Item> items = requestItems.stream().map(this::toItem).toList();
        itemRepository.saveAll(items);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseItemDto> getAll(int pageNumber, int pageSize, SortDirectionEnum sortDirection, String sortField) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection.toString()), sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return itemRepository.findAll(pageable).map(this::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseItemDto getById(Long id) {
        return toResponseDto(findItemByIdOrThrow(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findItemByIdOrThrow(id);
        itemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ResponseItemDto updateByName(String name, RequestItemDto request) {
        Item item = findItemByNameOrThrow(name);
        item.setName(request.name());
        item.setPrice(request.price());
        item.setImage(request.image());
        item.setDescription(request.description());
        if (request.brandId() != null) {
            item.setBrand(brandRepository.findById(request.brandId())
                    .orElseThrow(() -> new ProductException(ProductErrorEnum.BRAND_NOT_FOUND)));
        }
        if (request.categoryId() != null) {
            item.setCategory(categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new ProductException(ProductErrorEnum.CATEGORY_NOT_FOUND)));
        }
        return toResponseDto(itemRepository.save(item));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseItemDto> getPriceRange(double min, double max, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return itemRepository.findByPriceBetween(BigDecimal.valueOf(min), BigDecimal.valueOf(max), pageable)
                .map(this::toResponseDto);
    }

    @Override
    @Transactional
    public ResponseItemDto partialUpdateByName(String name, String itemDescription) {
        Item item = findItemByNameOrThrow(name);
        item.setDescription(itemDescription);
        return toResponseDto(itemRepository.save(item));
    }

    private Item findItemByIdOrThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.ITEM_NOT_FOUND));
    }

    private Item findItemByNameOrThrow(String name) {
        return itemRepository.findByName(name)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.ITEM_NOT_FOUND));
    }

    private Item toItem(RequestItemDto request) {
        Item item = new Item();
        item.setName(request.name());
        item.setPrice(request.price());
        item.setImage(request.image());
        item.setDescription(request.description());
        item.setIsActive(true);
        item.setIsDeleted(false);
        if (request.brandId() != null) {
            item.setBrand(brandRepository.findById(request.brandId())
                    .orElseThrow(() -> new ProductException(ProductErrorEnum.BRAND_NOT_FOUND)));
        }
        if (request.categoryId() != null) {
            item.setCategory(categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new ProductException(ProductErrorEnum.CATEGORY_NOT_FOUND)));
        }
        return item;
    }

    private ResponseItemDto toResponseDto(Item item) {
        return ResponseItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .image(item.getImage())
                .description(item.getDescription())
                .brandId(item.getBrand() != null ? item.getBrand().getId() : null)
                .categoryId(item.getCategory() != null ? item.getCategory().getId() : null)
                .isActive(item.getIsActive())
                .isDeleted(item.getIsDeleted())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }
}
