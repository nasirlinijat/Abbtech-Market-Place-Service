package com.abbtech.controller;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.model.enums.SortDirectionEnum;
import com.abbtech.service.BrandService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@Slf4j
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public Page<ResponseBrandDto> getAll(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber
            , @RequestParam(value = "pageSize", required = false, defaultValue = "3") int pageSize
            , @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") String sortDirection
            , @RequestParam(value = "sortField", required = false, defaultValue = "id") String sortField) {

        int nextPage = 10 / pageNumber;

        log.debug("pageNumber: {}, pageSize: {}, sortDirection: {}, sortField: {}", pageNumber, pageSize, sortDirection, sortField);

        return brandService.getAll(pageNumber, pageSize, SortDirectionEnum.valueOf(sortDirection), sortField);
    }

    @GetMapping("/{id}")
    public ResponseBrandDto getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('MANAGER','ADMİN') OR hasAuthority('WRITE_PRIVILEGE')")
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

}

