import java.util.*;

public class BTreeNode {
    List<String> keys;
    List<String> values;
    List<BTreeNode> children;

    public BTreeNode() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        this.children = new ArrayList<>();
    }
}