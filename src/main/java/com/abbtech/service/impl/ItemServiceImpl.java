package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Item;
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

    public ItemServiceImpl(@Qualifier("itemRepositoryImpl") ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    @Transactional
    public ResponseItemDto add(RequestItemDto request) {
        Item item = new Item();
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setImage(item.getImage());
        item.setPrice(request.getPrice());

        itemRepository.add(item);

        return new ResponseItemDto(
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
                .map(item -> new Item(item.getName(), item.getPrice(), item.getImage(), item.getDescription()))
                .toList());
    }

    @Override
    public List<ResponseItemDto> getAll() {
        return itemRepository.getAll()
                .stream()
                .map(item -> new ResponseItemDto(
                        item.getName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription()))
                .toList();
    }

    public ResponseItemDto getById(Long id) {
        return itemRepository.getById(id)
                .map(item -> new ResponseItemDto(item.getName(), item.getPrice(), item.getImage(), item.getDescription()))
                .orElseThrow(() -> new ProductException(ProductErrorEnum.PRODUCT_NOT_FOUND));

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public ResponseItemDto updateByName(String name, RequestItemDto requestItemDto) {
        //Item item = itemRepository.getById(name).orElseThrow(() -> new RuntimeException("No element with name found!"));

//        item.setName(requestItemDto.getName());
//        item.setPrice(requestItemDto.getPrice());
//        item.setImage(requestItemDto.getImage());
//        item.setDescription(requestItemDto.getDescription());
//
//        return new ResponseItemDto(
//                item.getName(),
//                item.getPrice(),
//                item.getImage(),
//                item.getDescription()
        //       );

        return null;
    }

    @Override
    public List<ResponseItemDto> getPriceRange(double min, double max) {
        BigDecimal minPrice = BigDecimal.valueOf(min);
        BigDecimal maxPrice = BigDecimal.valueOf(max);
        return itemRepository.getAll().stream()
                .filter(item -> item.getPrice().compareTo(minPrice) >= 0 &&
                        item.getPrice().compareTo(maxPrice) <= 0)
                .map(item -> new ResponseItemDto(
                        item.getName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription()
                ))
                .toList();
    }

    @Override
    public ResponseItemDto partialUpdateByName(String name, String itemDescription) {

        return null;
    }


}
