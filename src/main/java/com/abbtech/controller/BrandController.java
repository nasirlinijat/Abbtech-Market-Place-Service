package com.abbtech.controller;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.request.RequestBrandItemDto;
import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.model.enums.SortDirectionEnum;
import com.abbtech.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public Page<ResponseBrandDto> getAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                         @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
                                         @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection,
                                         @RequestParam(value = "sortField", defaultValue = "name") String sortField) {
        return brandService.getAll(pageNumber, pageSize, SortDirectionEnum.valueOf(sortDirection), sortField);
    }

    @GetMapping("/{id}")
    public ResponseBrandDto getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBrandDto add(@RequestBody @Valid RequestBrandDto request) {
        return brandService.add(request);
    }

    @PutMapping("/{id}")
    public ResponseBrandDto updateById(@PathVariable Long id, @RequestBody RequestBrandDto request) {
        return brandService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
    }


    @GetMapping("/{id}/items")
    public List<ResponseItemDto> getItemsByBrand(@PathVariable Long id) {
        return brandService.getItemsByBrand(id);
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBrandAndItems(@RequestBody RequestBrandItemDto request) {
        brandService.saveBrandAndItems(request);
    }

    @PutMapping("/{id}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateBrandItems(@PathVariable("id") Long id, @RequestBody List<RequestItemDto> items) {
        brandService.updateBrandItems(id, items);
    }

}

