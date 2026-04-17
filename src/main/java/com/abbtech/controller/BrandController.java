package com.abbtech.controller;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.service.abstracts.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public List<ResponseBrandDto> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseBrandDto getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @PostMapping
    public ResponseBrandDto create(@RequestBody RequestBrandDto request) {
        return brandService.create(request);
    }

    @PostMapping("/bulk")
    public List<ResponseBrandDto> bulkCreate(@RequestBody List<RequestBrandDto> requests) {
        return brandService.bulkCreate(requests);
    }

    @PutMapping("/{id}")
    public ResponseBrandDto update(@PathVariable Long id, @RequestBody RequestBrandDto request) {
        return brandService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        brandService.delete(id);
    }
}
