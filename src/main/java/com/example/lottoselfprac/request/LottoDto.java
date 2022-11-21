package com.example.lottoselfprac.request;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LottoDto {

    private Long firstNum;
    private Long secondNum;
    private Long thirdNum;
    private Long fourthNum;
    private Long fifthNum;
    private Long sixthNum;

    private String store;
    @QueryProjection
    public LottoDto(Long firstNum, Long secondNum, Long thirdNum, Long fourthNum, Long fifthNum, Long sixthNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
        this.thirdNum = thirdNum;
        this.fourthNum = fourthNum;
        this.fifthNum = fifthNum;
        this.sixthNum = sixthNum;
    }
}
