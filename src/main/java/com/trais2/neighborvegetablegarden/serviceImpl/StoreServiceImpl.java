package com.trais2.neighborvegetablegarden.serviceImpl;

import com.trais2.neighborvegetablegarden.model.entity.Status;
import com.trais2.neighborvegetablegarden.model.entity.address.Address;
import com.trais2.neighborvegetablegarden.model.entity.address.Commune;
import com.trais2.neighborvegetablegarden.model.entity.address.District;
import com.trais2.neighborvegetablegarden.model.entity.address.Province;
import com.trais2.neighborvegetablegarden.model.entity.store.Store;
import com.trais2.neighborvegetablegarden.model.entity.user.Role;
import com.trais2.neighborvegetablegarden.model.entity.user.User;
import com.trais2.neighborvegetablegarden.model.enums.ERole;
import com.trais2.neighborvegetablegarden.model.enums.EStatus;
import com.trais2.neighborvegetablegarden.repository.*;
import com.trais2.neighborvegetablegarden.service.StoreService;
import org.springframework.stereotype.Service;
import payload.request.store.CreateStoreRequest;

import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final CommuneRepository communeRepository;
    private final StatusRepository statusRepository;
    private final RoleRepository roleRepository;

    public StoreServiceImpl(UserRepository userRepository, StoreRepository storeRepository, ProvinceRepository provinceRepository, DistrictRepository districtRepository, CommuneRepository communeRepository, AddressRepository addressRepository, StatusRepository statusRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.addressRepository = addressRepository;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.communeRepository = communeRepository;
        this.statusRepository = statusRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String createStore(CreateStoreRequest request) {
        try {
            Optional<User> user = userRepository.findById(request.getUserId());
            Optional<Province> province = provinceRepository.findById(request.getProvinceId());
            Optional<District> district = districtRepository.findById(request.getDistrictId());
            Optional<Commune> commune = communeRepository.findById(request.getCommuneId());
            Optional<Status> status = statusRepository.findByName(EStatus.PENDING.toString());
            Optional<Role> role = roleRepository.findByName(ERole.ROLE_SELLER);
            if (user.isPresent() && province.isPresent() && district.isPresent() && commune.isPresent() && status.isPresent() && role.isPresent()) {
                Address address = new Address();
                address.setProvince(province.get());
                address.setDistrict(district.get());
                address.setCommune(commune.get());
                address.setDetailAddress(request.getDetailAddress());
                addressRepository.save(address);
                Store store = new Store();
                store.setAddress(address);
                store.setUser(user.get());
                store.setStatus(status.get());
                storeRepository.save(store);
                user.get().getRoles().add(role.get());
                userRepository.save(user.get());
            } else {
                return "Create store failed";
            }
        } catch (Exception e) {
            return "Create store failed";
        }
        return null;
    }

    @Override
    public String updateStore() {
        return null;
    }

    @Override
    public String deleteStore() {
        return null;
    }

    @Override
    public String getStore() {
        return null;
    }

    @Override
    public String getAllStores() {
        return null;
    }
}
