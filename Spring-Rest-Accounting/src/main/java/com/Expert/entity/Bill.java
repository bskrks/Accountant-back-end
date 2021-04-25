package com.Expert.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="bills")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"id"})
@ToString
@DynamicUpdate(value = true)
public class Bill {

    @Id
    @SequenceGenerator(name="seq_bills", allocationSize = 1)
    @GeneratedValue(generator = "seq_bills", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="amount")
    private Integer amount;

    @Column(name="confirmed")
    private Boolean confirmed;

    @Column(length = 300, name="product_name")
    private String productName;

    @Column(length = 10, name="bill_no")
    private String billNo;

    @ManyToOne
    @JoinColumn(name="user_bill_id")
    private User userId;

}
