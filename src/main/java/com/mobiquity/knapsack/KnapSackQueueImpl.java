package com.mobiquity.knapsack;

import com.mobiquity.entity.Item;
import com.mobiquity.entity.ItemsSelected;
import com.mobiquity.entity.Package;
import com.mobiquity.helper.PatternConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;


/**
 * abstract-> Queue
 *  ->
 *  0 1 2 3 4 5
 * 0 0
 * 1 1
 * 2 1 2
 * 3
 *
 */
public class KnapSackQueueImpl implements KnapSack {

    private static final Logger logger = LogManager.getLogger(KnapSackQueueImpl.class);
    private Map<Package, Queue<Item>> packageMaxHeap;
    private  List<ItemsSelected> itemsSelected;

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
     * @param packages (List)
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
     * This method select all items from sorted List of Items, such that their total weight capacity do not exceed given weight limit in file
     * @return list of items satisfying the constraint
     */
    @Override
    public void collectSelectedItems() {
        Set<Package> packageKeys = packageMaxHeap.keySet();
        packageKeys.forEach(key -> itemsSelected.add(new ItemsSelected(key.getID(), getItems(key))));
    }

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
     * This heap sorts items based on price (decreasing order) and then on weight (increasing order),
     * if the price of 2 items are same, then item with weight less is given priority
     * @return List of sorted Items
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
