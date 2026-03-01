package com.abbtech.service.concretes;

import com.abbtech.dto.ResponseItemDto;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.abstracts.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ResponseItemDto> getAllItems() {
        return itemRepository.getAllItems()
                .stream()
                .map(item -> new ResponseItemDto(
                        item.getName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription()))
                .toList();
    }


}
