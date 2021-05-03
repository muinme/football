package com.example.football.services.Impl;

import com.example.football.models.*;
import com.example.football.repositories.*;
import com.example.football.services.HistoryRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryRentalServiceImpl implements HistoryRentalService {
    @Autowired
    private HistoryRentalRepository historyRentalRepository;

    @Autowired
    private RequestPitchRepository requestPitchRepository;

    @Autowired
    private DetailPitchRepository detailPitchRepository;

    @Override
    public HistoryRental saveHistoryRental1(Integer id) {
        HistoryRental historyRental = new HistoryRental();
        historyRental.setStatus("1");
        historyRental.setRequest_pitch_id(id);
        historyRental.setCreated(new Date());
        RequestPitch existingRequestPitch = requestPitchRepository.findById(id).get();
        existingRequestPitch.setStatus("1");
        Integer idPost = existingRequestPitch.getPitch_detail_id();
        DetailPitch existDetailPitch = detailPitchRepository.findById(idPost).get();
        System.out.println(existDetailPitch);
        existDetailPitch.setStatus_hire("1");
        System.out.println(existDetailPitch);
        detailPitchRepository.save(existDetailPitch);
        requestPitchRepository.save(existingRequestPitch);
        return historyRentalRepository.save(historyRental);

    }

    @Override
    public HistoryRental saveHistoryRental2(Integer id) {
        HistoryRental historyRental = new HistoryRental();
        historyRental.setStatus("0");
        historyRental.setRequest_pitch_id(id);
        historyRental.setCreated(new Date());
        RequestPitch existingRequestPitch = requestPitchRepository.findById(id).get();
        existingRequestPitch.setStatus("1");
        requestPitchRepository.save(existingRequestPitch);
        return historyRentalRepository.save(historyRental);
    }

    @Override
    public List<HistoryRental> listAllHistoryRental() {
        return null;
    }
}
