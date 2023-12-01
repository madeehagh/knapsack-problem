package com.mobiquity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
public class Item {
    private final int index;
    private final BigDecimal weight;
    private final BigDecimal price;
}
