package com.mobiquity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@ToString
public class Package {

    @Getter
    private final String ID = UUID.randomUUID().toString();

    @Getter
    private final double weightLimit;
    private final List<Item> packageItems;

    public List<Item> getPackageItems() {
        if (null == packageItems || packageItems.isEmpty())
            return List.of();

        return packageItems.stream()
                .map(packageItem -> new Item(packageItem.getIndex(),
                        packageItem.getWeight(),
                        packageItem.getPrice()))
                .collect(Collectors.toList());
    }
}
