package com.mobiquity.knapsack;

import com.mobiquity.entity.Package;

import java.util.List;

public interface KnapSack {

    void init(List<Package> packages);

    void collectSelectedItems();
}
