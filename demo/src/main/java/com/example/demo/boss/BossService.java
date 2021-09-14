package com.example.demo.boss;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BossService {

    private final BossRepository bossRepository;

    public List<Boss> getAllBoss() {
        return bossRepository.findAll();
    }
}
