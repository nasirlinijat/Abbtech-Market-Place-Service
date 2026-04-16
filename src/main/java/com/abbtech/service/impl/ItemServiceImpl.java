package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Brand;
import com.abbtech.model.Item;
import com.abbtech.repository.BrandRepository;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.ItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    private final BrandRepository brandRepository;

    public ItemServiceImpl(@Qualifier("itemRepositoryImpl") ItemRepository itemRepository, BrandRepository brandRepository) {
        this.itemRepository = itemRepository;
        this.brandRepository = brandRepository;
    }


    @Override
    @Transactional
    public ResponseItemDto add(RequestItemDto request) {
        Item item = new Item();
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setImage(request.getImage());
        item.setPrice(request.getPrice());
        item.setIsActive(true);
        item.setIsDeleted(false);

        itemRepository.add(item);

        Long id = item.getId() == null ? null : item.getId().longValue();
        return new ResponseItemDto(
                id,
                item.getName(),
                item.getPrice(),
                item.getImage(),
                item.getDescription()
        );
    }

    @Override
    @Transactional
    public void saveAll(List<RequestItemDto> requestItems) {
        itemRepository.saveAll(requestItems
                .stream()
                .map(item -> {
                    Item newItem = new Item(item.getName(), item.getPrice(), item.getImage(), item.getDescription());
                    newItem.setIsActive(true);
                    newItem.setIsDeleted(false);
                    return newItem;
                })
                .toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseItemDto> getAll() {
        return itemRepository.getAll()
                .stream()
                .map(item -> new ResponseItemDto(
                        item.getId() == null ? null : item.getId().longValue(),
                        item.getName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription()))
                .toList();
    }

    public ResponseItemDto getById(Long id) {
        return itemRepository.getById(id)
                .map(item -> new ResponseItemDto(
                        item.getId() == null ? null : item.getId().longValue(),
                        item.getName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription()
                ))
                .orElseThrow(() -> new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND));

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public ResponseItemDto updateByName(String name, RequestItemDto requestItemDto) {
        Item existingItem = itemRepository.getByName(name)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND));

        existingItem.setName(requestItemDto.getName());
        existingItem.setPrice(requestItemDto.getPrice());
        existingItem.setImage(requestItemDto.getImage());
        existingItem.setDescription(requestItemDto.getDescription());

        itemRepository.updateByName(name, existingItem);

        Long id = existingItem.getId() == null ? null : existingItem.getId().longValue();
        return new ResponseItemDto(
                id,
                existingItem.getName(),
                existingItem.getPrice(),
                existingItem.getImage(),
                existingItem.getDescription()
        );
    }

    @Override
    public List<ResponseItemDto> getPriceRange(double min, double max) {
        BigDecimal minPrice = BigDecimal.valueOf(min);
        BigDecimal maxPrice = BigDecimal.valueOf(max);
        return itemRepository.getAll().stream()
                .filter(item -> item.getPrice().compareTo(minPrice) >= 0 &&
                        item.getPrice().compareTo(maxPrice) <= 0)
                .map(item -> new ResponseItemDto(
                        item.getId() == null ? null : item.getId().longValue(),
                        item.getName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription()
                ))
                .toList();
    }

    @Override
    public ResponseItemDto partialUpdateByName(String name, String itemDescription) {
        Item existingItem = itemRepository.getByName(name)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND));

        existingItem.setDescription(itemDescription);
        itemRepository.updateDescriptionByName(name, itemDescription);

        Long id = existingItem.getId() == null ? null : existingItem.getId().longValue();
        return new ResponseItemDto(
                id,
                existingItem.getName(),
                existingItem.getPrice(),
                existingItem.getImage(),
                existingItem.getDescription()
        );
    }


}
