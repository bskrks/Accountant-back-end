package com.Expert.resources;

import com.Expert.dto.UserDto;
import com.Expert.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserResources {
    private final UserService userService;

    @PostMapping
    public ResponseEntity save (@RequestBody UserDto userDto) {
        userService.save(userDto);
        return new ResponseEntity(HttpStatus.CREATED);   //200
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> get() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get_id(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable(value = "id" ) Long id, @RequestBody UserDto userDto){
        userService.update(id, userDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);   //200
    }
}
