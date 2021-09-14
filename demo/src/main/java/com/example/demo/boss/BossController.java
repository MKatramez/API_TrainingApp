package com.example.demo.boss;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/boss")
@AllArgsConstructor
public class BossController {

    final private BossService bossService;

    //No Constructor because of lombok notation @AllArgsConstructor
 //    @Autowired
//    public BossController(BossService bossService) {
//        this.bossService = bossService;
//    }

    @GetMapping
    public List<Boss> getBoss(){
        return bossService.getAllBoss();
    }

}
