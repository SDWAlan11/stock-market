package com.market.stock.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "SYMBOL")
public class Symbol {
    @Id
    @Column(name = "ID", length = 10)
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "NAME")
    private String name;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "symbol",fetch = FetchType.LAZY)
    private List<Orders> orders;

}
