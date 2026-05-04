package com.abbtech.controller;

import com.abbtech.dto.request.RequestBasketDto;
import com.abbtech.dto.response.ResponseBasketDto;
import com.abbtech.service.BasketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baskets")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping
    public Page<ResponseBasketDto> getAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return basketService.getAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseBasketDto getById(@PathVariable Long id) {
        return basketService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<ResponseBasketDto> getByUserId(@PathVariable Long userId) {
        return basketService.getByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBasketDto add(@RequestBody @Valid RequestBasketDto request) {
        return basketService.add(request);
    }

    @PutMapping("/{id}")
    public ResponseBasketDto updateById(@PathVariable Long id, @RequestBody @Valid RequestBasketDto request) {
        return basketService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        basketService.deleteById(id);
    }
}
