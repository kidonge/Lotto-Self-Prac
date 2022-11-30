package com.example.lottoselfprac.controller;

import com.example.lottoselfprac.reponse.ResponseDto;
import com.example.lottoselfprac.service.TestLottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class TestLottoController {

    private final TestLottoService testService;

    @CrossOrigin
    @PostMapping("/test/{num}")
    public ResponseDto<?>  tset(@PathVariable Long num){
        return testService.test(num);
    }

    @CrossOrigin
    @GetMapping("/test/{num}")
    public ResponseDto<?> rankCount(@PathVariable Long num){
        return testService.rankCount(num);
    }



}
