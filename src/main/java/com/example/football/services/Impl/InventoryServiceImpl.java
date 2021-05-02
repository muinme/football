package com.example.football.services.Impl;

import com.example.football.models.Inventory;
import com.example.football.repositories.InventoryRepository;
import com.example.football.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class InventoryServiceImpl implements InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public Inventory createInventory(Integer pitch_id, Integer number_pitch) {
        Integer max = inventoryRepository.getMaxPitch(pitch_id);
        Inventory inventory = new Inventory();
        inventory.setCreated(new Date());
        inventory.setNumber_pitch(max+1);
        inventory.setPitch_id(pitch_id);
        return inventoryRepository.save(inventory);
    }
}
