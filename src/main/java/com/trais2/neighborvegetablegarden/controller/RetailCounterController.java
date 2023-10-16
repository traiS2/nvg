package com.trais2.neighborvegetablegarden.controller;

import com.trais2.neighborvegetablegarden.config.GlobalExceptionHandler;
import com.trais2.neighborvegetablegarden.service.RetailCounterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import payload.request.retailcounter.CreateRetailCounterRequest;
import payload.request.retailcounter.UpdateRetailCounterRequest;
import payload.response.MessageResponse;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/retail-counter")
public class RetailCounterController {

    private final RetailCounterService retailCounterService;
    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public RetailCounterController(RetailCounterService retailCounterService, GlobalExceptionHandler validationExceptionHandler) {
        this.retailCounterService = retailCounterService;
        this.validationExceptionHandler = validationExceptionHandler;
    }

    @PostMapping("/create-retail-counter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createRetailCounter(@Valid @RequestBody CreateRetailCounterRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if(errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            String createRetailCounterMessage = retailCounterService.createRetailCounter(request);
            if(createRetailCounterMessage != null) {
                return ResponseEntity.badRequest().body(createRetailCounterMessage);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body("Create retail counter successfully");
            }
        }
    }

    @PutMapping("/update-retail-counter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRetailCounter(@Valid @RequestBody UpdateRetailCounterRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if(errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            String updateRetailCounterMessage = retailCounterService.updateRetailCounter(request);
            if(updateRetailCounterMessage != null) {
                return ResponseEntity.badRequest().body(new MessageResponse(updateRetailCounterMessage));
            } else {
                return ResponseEntity.ok().body(new MessageResponse("Update retail counter successfully"));
            }
        }
    }

    @GetMapping("/get-all-retail-counter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllRetailCounter() {
        List<?> retailCounters = retailCounterService.getAllRetailCounter();
        if(retailCounters != null) {
            return ResponseEntity.ok().body(retailCounters);
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Get retail counter failed"));
        }
    }


}
