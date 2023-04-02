package com.project.mycomerce.controller.v1;

import com.project.mycomerce.dto.custom.APIResponse;
import com.project.mycomerce.dto.product.CreateProductDto;
import com.project.mycomerce.dto.product.ReadProductDto;
import com.project.mycomerce.dto.product.UpdateProductDto;
import com.project.mycomerce.service.master.IProductService;
import com.project.mycomerce.utils.SD;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<APIResponse> register(@RequestBody @Valid CreateProductDto request){
        var result = productService.create(request);

        var response = APIResponse
                .<ReadProductDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<APIResponse> update(@RequestBody @Valid UpdateProductDto request){
        var result = productService.update(request);

        var response = APIResponse
                .<ReadProductDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable @Positive Long id){
        productService.deletById(id);

        var response = APIResponse
                .builder()
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    @GetMapping()
    public ResponseEntity<APIResponse> getAll(){
        var result = productService.getAll();
        var response  =APIResponse
                .<List<ReadProductDto>>builder()
                .results(result)
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable @Positive Long Id){
        var result = productService.findById(Id);
        var response  =APIResponse
                .<ReadProductDto>builder()
                .results(result)
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
