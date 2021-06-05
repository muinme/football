package com.example.football.services.Impl;

import com.example.football.infrastructure.security.JwtUtil;
import com.example.football.models.DetailPitch;
import com.example.football.models.Pitch;
import com.example.football.models.Price;
import com.example.football.repositories.DetailPitchRepository;
import com.example.football.repositories.InventoryRepository;
import com.example.football.repositories.PitchRepository;
import com.example.football.repositories.PriceRepository;
import com.example.football.services.DetailPitchService;
import com.example.football.services.InventoryService;
import com.example.football.services.PitchService;
import com.example.football.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DetailPitchServiceImpl implements DetailPitchService{

    @Autowired
    private DetailPitchRepository detailPitchRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceService priceService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Override
    public List<DetailPitch> createDetailPitch(Integer pitch_id) {
        List<DetailPitch> detailPitchList = new ArrayList<>();
        Integer sl = inventoryRepository.getSLPitch(pitch_id);
        inventoryService.createInventory(pitch_id, sl+1);
        for (int i=1; i<=10; i++){
            for(int j=1; j<=7; j++){
                DetailPitch detailPitch = new DetailPitch();
                detailPitch.setPitch_id(pitch_id);
                detailPitch.setStatus_hire("0");
                detailPitch.setLast_update(new Date());
                detailPitch.setTimeslot_id(i);
                detailPitch.setDay_id(j);
                detailPitch.setNumber_pitch_id(sl+1);
                detailPitch.setPrice_id(1);
                detailPitchRepository.save(detailPitch);
                detailPitchList.add(detailPitch);
            }
        }
        return detailPitchList;
    }


    @Override
    public DetailPitch getByIdDetailPitch(Integer id) {
        return detailPitchRepository.findById(id).get();
    }


    @Override
    public List<DetailPitch> getListDetailPitch(Integer pitch_id, Integer timeslot_id, Integer day_id) {
        return detailPitchRepository.getListDetailPitch(timeslot_id, pitch_id, day_id,"0");
    }

    @Override
    public List<String> getListStatusHire(Integer pitch_id, Integer number_pitch_id) {
        return detailPitchRepository.getListStatusHire(pitch_id, number_pitch_id);
    }

    @Override
    public List<String> getListMoneyPitch(Integer pitch_id, Integer number_pitch_id) {
        return detailPitchRepository.getListMoneyPitch(pitch_id, number_pitch_id);
    }

    @Override
    public void updateDetailPitchOfTime(Integer pitch_id, Integer timeslot_id, Integer day_id, Integer number_pitch_id,String status_hire) {
        DetailPitch exsitDetailPitch = detailPitchRepository.getDetailPitch(timeslot_id, pitch_id, day_id, number_pitch_id);
        exsitDetailPitch.setStatus_hire(status_hire);
        System.out.println(exsitDetailPitch);
        detailPitchRepository.save(exsitDetailPitch);

    }

    @Override
    public void saveDetailPitch(DetailPitch detailPitch) {
        detailPitchRepository.save(detailPitch);
    }

    @Override
    public void deleteDetailPitch(Integer id) {
        detailPitchRepository.deleteById(id);
    }

    @Override
    public List<DetailPitch> listAllDetailPitch() {
        return detailPitchRepository.findAll();
    }

    @Override
    public DetailPitch getInfo(Integer request_pitch_id) {
        return detailPitchRepository.getInFo(request_pitch_id);
    }

    @Override
    public Integer getSLPitch(Integer pitch_id) {
        return null;
    }

    @Override
    public DetailPitch updateDetailPitchMoney(Integer pitch_id, Integer timeslot_id, Integer day_id, Integer number_pitch_id, Integer money) {
        DetailPitch exsitDetailPitch = detailPitchRepository.getDetailPitch(timeslot_id, pitch_id, day_id, number_pitch_id);
        Integer id = priceRepository.findByIdMoney(money);
        if(id == null)
        {
            Price price = new Price();
            price.setPrice(money);
            priceService.createPrice(price);
        }
        id = priceRepository.findByIdMoney(money);
        exsitDetailPitch.setPrice_id(id);
        System.out.println(exsitDetailPitch);
        return detailPitchRepository.save(exsitDetailPitch);

    }

}
