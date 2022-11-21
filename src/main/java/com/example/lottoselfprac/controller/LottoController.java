package com.example.lottoselfprac.controller;


import com.example.lottoselfprac.reponse.ResponseDto;
import com.example.lottoselfprac.request.LottoDto;
import com.example.lottoselfprac.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class LottoController {

    private final LottoService lottoService;
    //로또 더미데이터 여러개
    @CrossOrigin
    @PostMapping("/lottoAutos/{nums}")
    public ResponseDto<?> lottoAutos(@PathVariable Long nums){
        return lottoService.lottoCreates(nums);
    }
    //로또 한개 자동
    @CrossOrigin
    @PostMapping("/lottoAutos")
    public ResponseDto<?> lottoAuto() {
        return lottoService.lottoCreate();
    }

    @CrossOrigin
    @PostMapping("/lottoManuals")
    public ResponseDto<?> lottoManual(@RequestBody LottoDto lottoDto){
        return lottoService.lottoManual(lottoDto);
    }

    // {num} 회차일 때 더미데이터 중 1등부터 5등까지 각각 몇명인지 반환
    @CrossOrigin
    @PostMapping("/lottoWins/{num}")
    public ResponseDto<?> lottoWin(@PathVariable Long num){
        return lottoService.lottoWins(num);
    }

    @CrossOrigin
    @GetMapping("/winningNum")
    public ResponseDto<?> winningNum(@PageableDefault(page = 0, size = 1) Pageable pageable){
        return lottoService.winningNum(pageable);
    }



}
