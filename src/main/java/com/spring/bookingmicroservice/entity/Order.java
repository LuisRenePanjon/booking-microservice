package com.spring.bookingmicroservice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    private Long id;

    @Column(name = "order_number")
    private String OrderNumber;

    @Column(name = "order_items")
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


}
