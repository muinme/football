package com.example.football.services.Impl;

import com.example.football.models.HistoryMatch;
import com.example.football.models.PieInfo;
import com.example.football.models.PostMatchTeam;
import com.example.football.models.RequestMatch;
import com.example.football.repositories.HistoryMatchRepository;
import com.example.football.repositories.PostMatchTeamRepository;
import com.example.football.repositories.RequestMatchRepository;
import com.example.football.services.HistoryMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class HistoryMatchServiceImpl implements HistoryMatchService {
    @Autowired
    private HistoryMatchRepository historyMatchRepository;

    @Autowired
    private RequestMatchRepository requestMatchRepository;

    @Autowired
    private PostMatchTeamRepository postMatchTeamRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
    public HistoryMatch saveHistoryMatch1(Integer id) {

        HistoryMatch historyMatch = new HistoryMatch();
        historyMatch.setStatus("1");
        historyMatch.setRequest_match_id(id);
        historyMatch.setCreated(new Date());
        RequestMatch existingRequestMatch = requestMatchRepository.findById(id).get();
        existingRequestMatch.setStatus("1");
        Integer idPost = existingRequestMatch.getWait_match_team_id();
        PostMatchTeam existPostMatchTeam = postMatchTeamRepository.findById(idPost).get();
        existPostMatchTeam.setStatus("0");
        postMatchTeamRepository.save(existPostMatchTeam);
        requestMatchRepository.save(existingRequestMatch);
        return historyMatchRepository.save(historyMatch);
    }

    @Override
    public HistoryMatch saveHistoryMatch2(Integer id) {
        HistoryMatch historyMatch = new HistoryMatch();
        historyMatch.setStatus("0");
        historyMatch.setRequest_match_id(id);
        historyMatch.setCreated(new Date());
        RequestMatch existingRequestMatch = requestMatchRepository.findById(id).get();
        existingRequestMatch.setStatus("1");
        requestMatchRepository.save(existingRequestMatch);
        return historyMatchRepository.save(historyMatch);
    }

    @Override
    public List<HistoryMatch> listAllHistoryMatch() {
        return historyMatchRepository.findAll();
    }

    @Override
    public List<PieInfo> pieInfoList() {
        List<Object> resultList = entityManager.createNativeQuery("SELECT COUNT(*) value , status x FROM history_match hm \n" +
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

    @Override
    public Integer findTcMatch(Integer user_id) {
        return historyMatchRepository.findTcMatch(user_id);
    }

    @Override
    public Integer findTbMatch(Integer user_id) {
        return historyMatchRepository.findTbMatch(user_id);
    }

    @Override
    public Integer findTcOrderTeam(Integer football_id) {
        return historyMatchRepository.findTcOrderMatch(football_id);
    }

    @Override
    public Integer findTbOrderTeam(Integer football_id) {
        return historyMatchRepository.findTbOrderMatch(football_id);
    }
}
