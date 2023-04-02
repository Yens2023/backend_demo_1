package com.project.mycomerce.controller.v1;

import com.project.mycomerce.dto.brand.CreateBrandDto;
import com.project.mycomerce.dto.brand.ReadBrandDto;
import com.project.mycomerce.dto.brand.UpdateBrandDto;
import com.project.mycomerce.dto.custom.APIResponse;
import com.project.mycomerce.service.master.IBrandService;
import com.project.mycomerce.utils.SD;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    private IBrandService brandService;

    public BrandController(IBrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping()
    public ResponseEntity<APIResponse> register(@RequestBody @Valid CreateBrandDto request){
        var result = brandService.create(request);

        var response = APIResponse
                .<ReadBrandDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<APIResponse> update(@RequestBody @Valid UpdateBrandDto request){
        var result = brandService.update(request);

        var response = APIResponse
                .<ReadBrandDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable @Positive Long id){
        brandService.deleteById(id);

        var response = APIResponse
                .builder()
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    @GetMapping()
    public ResponseEntity<APIResponse> getAll(){
        var result = brandService.getAll();
        var response  =APIResponse
                .<List<ReadBrandDto>>builder()
                .results(result)
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable @Positive Long id){
        var result = brandService.findById(id);
        var response  =APIResponse
                .<ReadBrandDto>builder()
                .results(result)
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
