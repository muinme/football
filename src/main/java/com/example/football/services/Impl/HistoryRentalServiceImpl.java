package com.example.football.services.Impl;

import com.example.football.models.*;
import com.example.football.repositories.*;
import com.example.football.services.HistoryRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class HistoryRentalServiceImpl implements HistoryRentalService {
    @Autowired
    private HistoryRentalRepository historyRentalRepository;

    @Autowired
    private RequestPitchRepository requestPitchRepository;

    @Autowired
    private DetailPitchRepository detailPitchRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Override
    public List<PieInfo> pieInfoList() {
        List<Object> resultList = entityManager.createNativeQuery("SELECT COUNT(*) value , status x FROM history_rental hr \n" +
                "GROUP BY status").getResultList();
        List<PieInfo> data = new ArrayList<PieInfo>();

        Iterator it = resultList.iterator();
        while (it.hasNext()) {
            Object[] row = (Object[]) it.next();
            PieInfo summary = new PieInfo();
            summary.setX("1".equals(String.valueOf(row[1])) ? "Thành Công" : "Thất bại");
            summary.setValue(Long.valueOf(String.valueOf(row[0])));
            data.add(summary);
        }
        return data;
    }
}
