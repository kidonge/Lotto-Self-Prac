package com.example.lottoselfprac.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="lotto",indexes = {
       @Index(name = "lotto_num_idx"
                , columnList = "firstNum, secondNum, thirdNum, fourthNum, fifthNum, sixthNum")
})

public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lotto_id;

    @Column(nullable = false, unique = true)
    private String uniqueCode;

    @Column(nullable = false)
    private Long firstNum;

    @Column(nullable = false)
    private Long secondNum;

    @Column(nullable = false)
    private Long thirdNum;

    @Column(nullable = false)
    private Long fourthNum;

    @Column(nullable = false)
    private Long fifthNum;

    @Column(nullable = false)
    private Long sixthNum;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="store_id")
    private Store store;



}
