package com.Expert.resources;
import com.Expert.Response.BillResponseModel;
import com.Expert.dto.BillDto;
import com.Expert.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillResources {
    private final BillService billService;

    @PostMapping
    public ResponseEntity<BillResponseModel> save (@RequestBody BillDto billDto) {

        BillResponseModel billResponseModel =billService.save(billDto);
        return new ResponseEntity<BillResponseModel>(billResponseModel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BillDto>> get() {
        return ResponseEntity.ok(billService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get_id(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(billService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        billService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable(value = "id" ) Long id, @RequestBody BillDto billDto){
        billService.update(id, billDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
