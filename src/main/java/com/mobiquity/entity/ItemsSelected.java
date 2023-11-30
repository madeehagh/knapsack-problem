package com.mobiquity.entity;

import com.mobiquity.constants.PatternConstants;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * This class is the output entity
 */
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class ItemsSelected {

    @Getter
    private final String id;
    private final List<Item> selectedItems;

    @Override
    public String toString() {
        if (null == selectedItems || selectedItems.isEmpty())
            return PatternConstants.NO_ITEM_SELECTED;
        StringBuilder sb = new StringBuilder();
        selectedItems.forEach(item -> sb.append(item.getIndex()).append(PatternConstants.OUTPUT_DELIMITER));
        // This line deletes extra , added at line 32 at EOL
        return sb.deleteCharAt(sb.length() -1).toString();
    }
}
