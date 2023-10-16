
package com.trais2.neighborvegetablegarden.serviceImpl;

import com.trais2.neighborvegetablegarden.model.entity.Status;
import com.trais2.neighborvegetablegarden.model.entity.store.RetailCounter;
import com.trais2.neighborvegetablegarden.model.enums.EStatus;
import com.trais2.neighborvegetablegarden.repository.RetailCounterRepository;
import com.trais2.neighborvegetablegarden.repository.StatusRepository;
import com.trais2.neighborvegetablegarden.service.RetailCounterService;
import dto.RetailCounterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payload.request.retailcounter.CreateRetailCounterRequest;
import payload.request.retailcounter.UpdateRetailCounterRequest;

import java.util.List;
import java.util.Optional;

@Service
public class RetailCounterImpl implements RetailCounterService {

    private final RetailCounterRepository retailCounterRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public RetailCounterImpl(RetailCounterRepository retailCounterRepository, StatusRepository statusRepository) {
        this.retailCounterRepository = retailCounterRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public List<RetailCounterDTO> getAllRetailCounter() {
        try {
            List<RetailCounter> retailCounters = retailCounterRepository.findAll();
            ModelMapper modelMapper = new ModelMapper();
            return retailCounters.stream().map(
                    retailCounter -> modelMapper.map(retailCounter, RetailCounterDTO.class)
            ).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String createRetailCounter(CreateRetailCounterRequest request) {
        try {
            Optional<Status> status = statusRepository.findByName(EStatus.INACTIVE.toString());
            if (status.isPresent()) {
                RetailCounter retailCounter = new RetailCounter();
                retailCounter.setName(request.getName());
                retailCounter.setStatus(status.get());
                retailCounterRepository.save(retailCounter);
            } else {
                return "Status not found";
            }
        } catch (Exception e) {
            return "Create retail counter failed";
        }
        return null;
    }

    @Override
    public String updateRetailCounter(UpdateRetailCounterRequest request) {
        try {
            Optional<RetailCounter> retailCounter = retailCounterRepository.findById(request.getId());
            Optional<Status> status = statusRepository.findById(request.getStatusId());
            if (retailCounter.isPresent()) {
                if (status.isPresent()) {
                    retailCounter.get().setName(request.getName());
                    retailCounter.get().setStatus(status.get());
                    retailCounterRepository.save(retailCounter.get());
                } else {
                    return "Status not found";
                }
            } else {
                return "Retail counter not found";
            }
        } catch (Exception e) {
            return "Update retail counter failed";
        }
        return null;
    }
}
