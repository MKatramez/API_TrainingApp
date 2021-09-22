package com.example.demo.boss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BossService {

    private final BossRepository bossRepository;

    @Autowired
    public BossService(BossRepository bossRepository) {
        this.bossRepository = bossRepository;
    }

    public List<Boss> getAllBoss() {
        return bossRepository.findAll();
    }

}
