package com.trais2.neighborvegetablegarden.service;

import payload.request.store.CreateStoreRequest;

public interface StoreService {
    public String createStore(CreateStoreRequest request);
    public String updateStore();
    public String deleteStore();
    public String getStore();
    public String getAllStores();
}
