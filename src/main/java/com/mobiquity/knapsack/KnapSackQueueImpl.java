package com.mobiquity.knapsack;

import com.mobiquity.entity.Item;
import com.mobiquity.entity.ItemsSelected;
import com.mobiquity.entity.Package;
import com.mobiquity.constants.PatternConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class KnapSackQueueImpl implements KnapSack {

    private static final Logger logger = LogManager.getLogger(KnapSackQueueImpl.class);
    private final Map<Package, Queue<Item>> packageMaxHeap;
    private  final List<ItemsSelected> itemsSelected;

    public KnapSackQueueImpl() {
        itemsSelected = new LinkedList<>();
        packageMaxHeap = new LinkedHashMap<>();
    }

    /**
     *  This method formats output, which is list of selected items in a string format.
     *  index of item separated by ',', if not item is selected in a package then '-'
     * @return output as String
     */
    @Override
    public String toString() {
        return selectedItemsAsString();
    }

    /**
     * This method takes all packages and sort items based on price and weight
     * @param packages packages a list of packages
     * @return
     */
    @Override
    public void init(List<Package> packages) {
        packages.forEach(pack -> {
            packageMaxHeap.put(pack, sortItemsOnPriceAndWeight());
            packageMaxHeap.get(pack).addAll(pack.getPackageItems());
        });
    }

    /**
     * This method collects the selected items from each package
     * based on the weight constraint.
     * @return list of items satisfying the constraint
     */
    @Override
    public void collectSelectedItems() {
        packageMaxHeap.forEach((pack, itemQueue) ->
                itemsSelected.add(new ItemsSelected(pack.getID(), getItems(pack))));

        /*Set<Package> packageKeys = packageMaxHeap.keySet();
        packageKeys.forEach(key -> itemsSelected.add(new ItemsSelected(key.getID(), getItems(key))));
*/    }

    private List<Item> getItems(Package key) {
        double weightLimit = key.getWeightLimit();
        List<Item> items = new ArrayList<>();
        Queue<Item> itemQueue = packageMaxHeap.get(key);

        while (!itemQueue.isEmpty() && weightLimit > 0) {
            Item item = itemQueue.poll();
            if (weightLimit >= item.getWeight()) {
                items.add(item);
                weightLimit -= item.getWeight();
            }
        }
        return items;
    }

    private String selectedItemsAsString() {
     return itemsSelected.stream()
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
        return new PriorityQueue<>((p1, p2) -> {
            int costComparison = Double.compare(p2.getPrice(), p1.getPrice());
            return (costComparison == 0) ?
                    Double.compare(p1.getWeight(), p2.getWeight()) :
                    costComparison > 0 ? 1 : -1;
        });
    }
}
