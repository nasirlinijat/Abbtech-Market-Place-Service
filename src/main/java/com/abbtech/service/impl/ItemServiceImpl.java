package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.model.Item;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.ItemService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
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

    @Override
    public ResponseItemDto getByName(String name) {
        return itemRepository.getByName(name).stream()
                .findAny()
                .map(item -> new ResponseItemDto(
                        item.getName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription())
                )
                .orElseThrow(() -> new RuntimeException("No element with name found!"));
    }

    @Override
    public void deleteByName(String name) {
        itemRepository.deleteByName(name);
    }

    @Override
    public ResponseItemDto updateByName(String name, RequestItemDto requestItemDto) {
        Item item = itemRepository.getByName(name).orElseThrow(() -> new RuntimeException("No element with name found!"));

        item.setName(requestItemDto.getName());
        item.setPrice(requestItemDto.getPrice());
        item.setImage(requestItemDto.getImage());
        item.setDescription(requestItemDto.getDescription());

        return new ResponseItemDto(
                item.getName(),
                item.getPrice(),
                item.getImage(),
                item.getDescription()
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
                        item.getName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription()
                ))
                .toList();
    }

    @Override
    public ResponseItemDto partialUpdateByName(String name, String itemDescription) {
        Item item = itemRepository.getByName(name).orElseThrow(() -> new RuntimeException("No element with name found!"));
        item.setDescription(itemDescription);

        return new ResponseItemDto(
                item.getName(),
                item.getPrice(),
                item.getImage(),
                item.getDescription()
        );
    }


}
