package com.example.lottoselfprac.request;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoundDto {
    private Long num1;
    private Long num2;
    private Long num3;
    private Long num4;
    private Long num5;
    private Long num6;
    private Long bonus;

    @QueryProjection
    public RoundDto(Long num1, Long num2, Long num3, Long num4, Long num5, Long num6, Long bonus) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.bonus = bonus;
    }


}
