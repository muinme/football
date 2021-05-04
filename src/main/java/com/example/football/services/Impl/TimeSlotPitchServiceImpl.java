package com.example.football.services.Impl;

import com.example.football.models.PieInfo;
import com.example.football.models.TimeSlotPitch;
import com.example.football.repositories.TimeSlotPitchRepository;
import com.example.football.services.TimeSlotPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TimeSlotPitchServiceImpl implements TimeSlotPitchService {
    @Autowired
    private TimeSlotPitchRepository timeSlotPitchRepository;

    @Override
    public TimeSlotPitch getTimeById(Integer id) {
        return timeSlotPitchRepository.findById(id).get();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PieInfo> pieInfoList() {
        List<Object> resultList = entityManager.createNativeQuery("SELECT COUNT(*) value , tp.id x FROM  timeslot_pitch tp\n" +
                "INNER JOIN detail_pitchs dp ON dp.timeslot_id = tp.id \n" +
                "WHERE dp.status_hire = \"1\"\n" +
                "GROUP BY tp.id ").getResultList();
        List<PieInfo> data = new ArrayList<PieInfo>();

        Iterator it = resultList.iterator();
        while (it.hasNext()) {
            Object[] row = (Object[]) it.next();
            PieInfo summary = new PieInfo();
            switch(String.valueOf(row[1])) {
                case "1":
                    summary.setX("8h30-10h" );
                    break;
                case "2":
                    summary.setX("10h-11h30");
                    break;
                case "3":
                    summary.setX("11h30-13h");
                    break;
                case "4":
                    summary.setX("13h-14h30");
                    break;
                case "5":
                    summary.setX("14h30-16h");
                    break;
                case "6":
                    summary.setX("16h-17h30");
                    break;
                case "7":
                    summary.setX("17h30-19h");
                    break;
                case "8":
                    summary.setX("19h-20h30");
                    break;
                case "9":
                    summary.setX("20h30-22h");
                    break;
                default:
                    summary.setX("22h-23h30");
                    break;
            }
            summary.setValue(Long.valueOf(String.valueOf(row[0])));
            data.add(summary);
        }
        return data;
    }
}
