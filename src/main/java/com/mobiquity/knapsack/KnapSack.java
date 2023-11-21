package com.mobiquity.knapsack;

import com.mobiquity.entity.Package;

import java.util.List;

public interface KnapSack {

    public void init(List<Package> packages);

    public void collectSelectedItems();
}
