package space.besh.beka_back.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.besh.beka_back.entity.ProductType;
import space.besh.beka_back.model.Response;
import space.besh.beka_back.repos.ProductTypeRepository;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product-type")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductTypeController {
    final ProductTypeRepository productTypeRepository;

    @GetMapping(produces = "application/json")
    @Operation(summary = "GET", description = "Получение всех типов продукта, или по названию")
    public ResponseEntity<Response> getAll(@RequestParam(required = false) String name){
        List<ProductType> productTypeList=productTypeRepository.findAll(name);
        return ResponseEntity.ok(new Response(productTypeList));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary= "POST", description = "Создание типа продукта")
    public ResponseEntity<Response> create(@RequestBody ProductType productType){
        ProductType createdProduct=this.productTypeRepository.save(productType);
        return ResponseEntity.ok(new Response(createdProduct));
    }

}
