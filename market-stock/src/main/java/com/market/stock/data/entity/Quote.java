package com.market.stock.data.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@JsonIgnoreProperties(value = { "symbol" })
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Quote extends Audit{

    @Id
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="symbolId",referencedColumnName = "id")
    private Symbol symbol;

    @Column(name = "LAST_PRICE")
    private double regularMarketPrice;

    @Column(name = "ASK")
    private double ask;

    @Column(name = "BID")
    private double bid;

    @Column(name = "CURRENCY")
    private Currency currency;

    public enum Currency {
        USD,
        MXN
    }

}
