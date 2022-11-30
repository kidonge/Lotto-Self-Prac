package com.example.lottoselfprac.request;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestLottoDto {

    private String lottoNum;

    @QueryProjection
    public TestLottoDto(String lottoNum) {
        this.lottoNum = lottoNum;
    }
}
