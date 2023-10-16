package com.trais2.neighborvegetablegarden.service;

import dto.RetailCounterDTO;
import payload.request.retailcounter.CreateRetailCounterRequest;
import payload.request.retailcounter.UpdateRetailCounterRequest;

import java.util.List;

public interface RetailCounterService {
    public List<RetailCounterDTO> getAllRetailCounter();
    public String createRetailCounter(CreateRetailCounterRequest request);
    public String updateRetailCounter(UpdateRetailCounterRequest request);
}
