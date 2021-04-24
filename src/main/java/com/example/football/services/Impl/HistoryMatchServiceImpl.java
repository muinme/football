package com.example.football.services.Impl;

import com.example.football.models.HistoryMatch;
import com.example.football.repositories.HistoryMatchRepository;
import com.example.football.services.HistoryMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryMatchServiceImpl implements HistoryMatchService {
    @Autowired
    private HistoryMatchRepository historyMatchRepository;
    @Override
    public HistoryMatch createHistoryMatch(HistoryMatch historyMatch) {
        historyMatch.setCreated(new Date());
        return historyMatchRepository.save(historyMatch);
    }

    @Override
    public HistoryMatch getByIdHistoryMatch(Integer id) {
        return historyMatchRepository.findById(id).get();
    }

    @Override
    public void saveHistoryMatch1(HistoryMatch historyMatch) {
        historyMatch.setStatus("done");
        historyMatchRepository.save(historyMatch);
    }

    @Override
    public void saveHistoryMatch2(HistoryMatch historyMatch) {
        historyMatch.setStatus("no done");
        historyMatchRepository.save(historyMatch);
    }

    @Override
    public List<HistoryMatch> listAllHistoryMatch() {
        return historyMatchRepository.findAll();
    }
}
