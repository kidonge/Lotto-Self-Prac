package com.example.lottoselfprac.controller;


import com.example.lottoselfprac.reponse.ResponseDto;
import com.example.lottoselfprac.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class LottoController {

    private final LottoService lottoService;

    @PostMapping(value = "/lotto/{count}")  ///{round}
    public void createLotto(@PathVariable Long count) { //@PathVariable Long round)
       lottoService.createLotto(count);
    }

    // n회차에 정보를 집어넣엇을 때 lotto 테이블에서 1등이 몇 명, 2등이 몇 명인지
    @GetMapping(value = "/lotto/{roundId}")
    public ResponseDto<?> getLottoRank(@PathVariable Long roundId){
        return lottoService.getLottoRank(roundId);
    }




}
