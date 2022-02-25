package com.example.mscoment.controllers;

import com.example.mscoment.dtos.ComentDto;
import com.example.mscoment.models.ComentModel;
import com.example.mscoment.services.ComentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ComentController {
    @Autowired
    ComentService comentService;

    @PostMapping("/sending-coment")
    public ResponseEntity<ComentModel> sendingComent(@RequestBody @Valid ComentDto comentDto) {
        ComentModel comentModel = new ComentModel();
        BeanUtils.copyProperties(comentDto, comentModel);
        comentService.sendComent(comentModel);
        return new ResponseEntity<>(comentModel, HttpStatus.CREATED);
    }

    @GetMapping("/coments")
    public ResponseEntity<Page<ComentModel>> getAllComents(@PageableDefault(page = 0, size = 5) Pageable pageable){
        return new ResponseEntity<>(comentService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/coments/{comentId}")
    public ResponseEntity<Object> getOneComent(@PathVariable(value="comentDto") UUID comentId){
        Optional<ComentModel> comentModelOptional = comentService.findById(comentId);
        Optional<Object> comentlModelOptional = null;
        if(!comentlModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coment not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(comentModelOptional.get());
        }
    }
}
