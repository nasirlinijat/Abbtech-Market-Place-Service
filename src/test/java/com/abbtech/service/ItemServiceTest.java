package com.abbtech.service;

import com.abbtech.dto.ResponseItemDto;
import com.abbtech.model.Item;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.concretes.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    void getAllItems_whenRepositoryReturnsItems_shouldMapToDto() {
        Item item1 = new Item(
                "iPhone 14",
                BigDecimal.valueOf(1200),
                "iphone.png",
                "Latest iPhone with A16 chip",
                BigDecimal.valueOf(900)
        );

        Item item2 = new Item(
                "MacBook Pro",
                BigDecimal.valueOf(2500),
                "macbook.png",
                "Powerful laptop for professionals",
                BigDecimal.valueOf(2000)
        );

        when(itemRepository.getAllItems())
                .thenReturn(List.of(item1, item2));

        List<ResponseItemDto> result = itemService.getAllItems();

        assertEquals(2, result.size());

        assertAll("First product mapping",
                () -> assertEquals("iPhone 14", result.get(0).getName()),
                () -> assertEquals(BigDecimal.valueOf(1200), result.get(0).getPrice()),
                () -> assertEquals("iphone.png", result.get(0).getImage()),
                () -> assertEquals("Latest iPhone with A16 chip", result.get(0).getDescription())
        );

        verify(itemRepository, times(1)).getAllItems();

    }

}
