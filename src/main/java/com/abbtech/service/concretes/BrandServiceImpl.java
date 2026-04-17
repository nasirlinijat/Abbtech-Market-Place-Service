package com.abbtech.service.concretes;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.model.Brand;
import com.abbtech.repository.BrandRepository;
import com.abbtech.service.abstracts.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public ResponseBrandDto create(RequestBrandDto request) {
        Brand created = brandRepository.create(toBrand(request));
        return toResponse(created);
    }

    @Override
    public ResponseBrandDto update(Long id, RequestBrandDto request) {
        Brand updated = brandRepository.update(id, toBrand(request));
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        brandRepository.delete(id);
    }

    @Override
    public List<ResponseBrandDto> getAll() {
        return brandRepository.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ResponseBrandDto getById(Long id) {
        return brandRepository.getById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    @Override
    public List<ResponseBrandDto> bulkCreate(List<RequestBrandDto> requests) {
        List<Brand> brands = requests.stream().map(this::toBrand).toList();
        return brandRepository.bulkCreate(brands).stream()
                .map(this::toResponse)
                .toList();
    }

    private Brand toBrand(RequestBrandDto request) {
        return new Brand(
                null,
                request.getName(),
                request.getDescription(),
                request.getImage(),
                request.getIsActive(),
                request.getIsDeleted(),
                null,
                null
        );
    }

    private ResponseBrandDto toResponse(Brand brand) {
        return new ResponseBrandDto(
                brand.getId(),
                brand.getName(),
                brand.getDescription(),
                brand.getImage(),
                brand.getIsActive(),
                brand.getIsDeleted(),
                brand.getCreatedAt(),
                brand.getUpdatedAt()
        );
    }
}
