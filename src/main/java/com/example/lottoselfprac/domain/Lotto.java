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
@GenericGenerator(
        name = "sequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
                @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1000")
        }
)
public class Lotto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator"
    )
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
