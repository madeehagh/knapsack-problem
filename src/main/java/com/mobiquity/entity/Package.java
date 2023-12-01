package com.mobiquity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@ToString
public class Package {

    @Getter
    private final String packageId = UUID.randomUUID().toString();

    @Getter
    private final BigDecimal weightLimit;
    private final List<Item> packageItems;

    public List<Item> getPackageItems() {
        if (null == packageItems || packageItems.isEmpty())
            return List.of();

        return packageItems.stream()
                .map(packageItem -> new Item(packageItem.getIndex(),
                        packageItem.getWeight(),
                        packageItem.getPrice()))
                .toList();
    }
}
