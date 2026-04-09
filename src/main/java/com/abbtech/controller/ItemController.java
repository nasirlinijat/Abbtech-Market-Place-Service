package com.abbtech.controller;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.scopes.RequestScopedClass;
import com.abbtech.scopes.SessionScopedClass;
import com.abbtech.service.ItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    private final ItemService itemService;
    private final ApplicationContext applicationContext;

    public ItemController(ItemService itemService, ApplicationContext applicationContext) {
        this.itemService = itemService;
        this.applicationContext = applicationContext;
    }


    @GetMapping
    public List<ResponseItemDto> getAll(@RequestHeader(value = "x-custom-header",required = false) String customHeader) {
        System.out.println("RequestScopedClass: " + applicationContext.getBean(RequestScopedClass.class));
        System.out.println("SessionScopedClass: " + applicationContext.getBean(SessionScopedClass.class));

        return itemService.getAll();
    }

    @GetMapping("/{name}")
    public ResponseItemDto getByName(@PathVariable String name,
                                     @RequestHeader("x-custom-header") String customHeader) {
        return itemService.getByName(name);
    }

    @GetMapping("/filter")
    public List<ResponseItemDto> getPriceRange(@RequestParam double min,
                                               @RequestParam double max,
                                               @RequestHeader("x-custom-header") String customHeader) {
        return itemService.getPriceRange(min, max);
    }

    @PostMapping
    public ResponseItemDto add(@RequestBody RequestItemDto request,
                               @RequestHeader("x-custom-header") String customHeader) {
        return itemService.add(request);
    }

    @PutMapping("/{name}")
    public ResponseItemDto updateByName(@PathVariable String name,
                                        @RequestBody RequestItemDto request,
                                        @RequestHeader("x-custom-header") String customHeader) {
        return itemService.updateByName(name, request);
    }

    @PatchMapping("/{name}")
    public ResponseItemDto partialUpdateByName(@PathVariable String name,
                                               @RequestParam String itemDescription,
                                               @RequestHeader("x-custom-header") String customHeader) {

        return itemService.partialUpdateByName(name, itemDescription);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name,
                       @RequestHeader("x-custom-header") String customHeader) {
        itemService.deleteByName(name);
    }
}
