package com.mobiquity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Item {
    private final int index;
    private final double weight;
    private final double price;
}
