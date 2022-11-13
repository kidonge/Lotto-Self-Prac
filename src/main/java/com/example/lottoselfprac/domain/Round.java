package com.example.lottoselfprac.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Round {
    @Id
    @Column(name = "lottery")
    private Long id;

    @Column(nullable = false)
    private Long bonus;

    // String? Date? 굳이 Date일 필요가 있나?
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Long num1;

    @Column(nullable = false)
    private Long num2;

    @Column(nullable = false)
    private Long num3;

    @Column(nullable = false)
    private Long num4;

    @Column(nullable = false)
    private Long num5;

    @Column(nullable = false)
    private Long num6;



//    @OneToMany(mappedBy = "round",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY)
//    private List<Lotto> lottos = new ArrayList<>();

}
