package input;

import com.mobiquity.entity.Item;
import com.mobiquity.entity.Package;

import java.util.Arrays;
import java.util.List;

public final class TestInput {

    private TestInput() {
    }

    public static String expectedInputFileResult() {
        return "4\n-\n2,7\n8,9";
    }

    public static List<String> fileReaderInput() {
        return Arrays.asList(
                "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)",
                "8 : (1,15.3,€34)",
                "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)",
                "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)");
    }

    public static List<String> givenPackages() {
        return Arrays.asList("81 : (1,53.38,€45)");
    }

    public static List<Package> packages() {
        System.out.println(Arrays.asList(new Package(81,
                Arrays.asList(new Item(1, 53.38, 45)))).toString());
        return Arrays.asList(new Package(81,
                Arrays.asList(new Item(1, 53.38, 45))));
    }

    public static List<Package> packagesForWightCal() {
        return Arrays.asList(new Package(81,
                Arrays.asList(
                        new Item(1, 53.38, 45),
                        new Item(2, 88.62, 98),
                        new Item(3, 78.48, 3),
                        new Item(4, 72.30, 76),
                        new Item(5, 30.18, 9),
                        new Item(6, 46.34, 48))));
    }

    public static List<Package> pkgWithWtItemsMoreThanCapacity() {
        return Arrays.asList(new Package(8,
                Arrays.asList(
                        new Item(1, 15.3, 34))));
    }

    public static List<String> givenPackageWithInvalidWeightDelimiter() {
        return Arrays.asList("81  (1,53.38,€45)");
    }

    public static List<String> givenPackageWithInvalidIndex() {
        return Arrays.asList("81 : (a,53.38,€45)");
    }

    public static List<String> givenPackageWithInvalidWeight() {
        return Arrays.asList("81 : (1,ww,€45)");
    }

    public static List<String> givenPackageWithInvalidPrice() {
        return Arrays.asList("81 : (1,53.34,abc)");
    }

    public static List<String> givenPackageWithInvalidItemAttr() {
        return Arrays.asList("81 : (1,53.34,€45,121)");
    }
}
