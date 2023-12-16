package com.mobiquity.knapsack;

import com.mobiquity.entity.Item;
import com.mobiquity.entity.ItemsSelected;
import com.mobiquity.entity.Package;
import com.mobiquity.constants.PatternConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class KnapSackQueueImpl implements KnapSack {

    private static final Logger logger = LogManager.getLogger(KnapSackQueueImpl.class);
    private final Map<Package, Queue<Item>> packageMaxHeap;
    private final List<ItemsSelected> itemsSelected;
    private final Lock lock;

    public KnapSackQueueImpl() {
        itemsSelected = new LinkedList<>();
        packageMaxHeap = new LinkedHashMap<>();
        lock = new ReentrantLock();
    }

    /**
     * This method formats output, which is a list of selected items in a string format.
     * The index of each item is separated by ',', and if no item is selected in a package, it is represented by '-'
     *
     * @return output as String
     */
    @Override
    public String toString() {
        return selectedItemsAsString();
    }

    /**
     * This method takes all packages and sorts the items based on price and weight.
     *
     * @param packages a list of packages
     */
    @Override
    public void init(List<Package> packages) {
        packages.forEach(pack -> {
            this.packageMaxHeap.put(pack, sortItemsOnPriceAndWeight());
            this.packageMaxHeap.get(pack).addAll(pack.getPackageItems());
        });
    }

    /**
     * This method collects the selected items from each package based on the weight constraint.
     */
    @Override
    public void collectSelectedItems() {
        packageMaxHeap.forEach((pack, itemQueue) -> {
            lock.lock();
            try {
                this.itemsSelected.add(new ItemsSelected(pack.getPackageId(), getItems(pack)));
            } finally {
                lock.unlock();
            }
        });
    }

    private List<Item> getItems(Package key) {
        BigDecimal weightLimit = key.getWeightLimit();
        List<Item> items = new ArrayList<>();
        Queue<Item> itemQueue = this.packageMaxHeap.get(key);

        while (!itemQueue.isEmpty() && weightLimit.compareTo(BigDecimal.ZERO) > 0) {
            Item item = itemQueue.poll();
            if (weightLimit.compareTo(item.getWeight()) >= 0) {
                items.add(item);
                weightLimit = weightLimit.subtract(item.getWeight());
            }
        }
        return items;
    }

    private String selectedItemsAsString() {
        return this.itemsSelected.stream()
                .map(ItemsSelected::toString)
                .collect(Collectors.joining(PatternConstants.END_OF_LINE));
    }

    /**
     * This method sorts the items based on price (in decreasing order) and then on weight (in increasing order).
     * If the price of two items is the same, the item with a lower weight is given priority.
     *
     * @return a priority queue of sorted items
     */
    private Queue<Item> sortItemsOnPriceAndWeight() {
        return new PriorityQueue<>(Comparator.comparing(Item::getPrice)
                .reversed()
                .thenComparing(Item::getWeight));
    }
}