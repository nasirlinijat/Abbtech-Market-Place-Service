package com.abbtech.service.concretes;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.model.Item;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.abstracts.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public ResponseItemDto create(RequestItemDto request) {
        Item created = itemRepository.create(toItem(request));
        return toResponse(created);
    }

    @Override
    public ResponseItemDto update(Long id, RequestItemDto request) {
        Item updated = itemRepository.update(id, toItem(request));
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        itemRepository.delete(id);
    }

    @Override
    public List<ResponseItemDto> getAll() {
        return itemRepository.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ResponseItemDto getById(Long id) {
        return itemRepository.getById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    @Override
    public List<ResponseItemDto> bulkCreate(List<RequestItemDto> requests) {
        List<Item> items = requests.stream().map(this::toItem).toList();
        return itemRepository.bulkCreate(items).stream()
                .map(this::toResponse)
                .toList();
    }

    private Item toItem(RequestItemDto request) {
        return new Item(
                null,
                request.getName(),
                request.getDescription(),
                request.getImage(),
                request.getPrice(),
                request.getCategoryId(),
                request.getBrandId(),
                request.getIsActive(),
                request.getIsDeleted(),
                null,
                null
        );
    }

    private ResponseItemDto toResponse(Item item) {
        return new ResponseItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getImage(),
                item.getPrice(),
                item.getCategoryId(),
                item.getBrandId(),
                item.getIsActive(),
                item.getIsDeleted(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}
