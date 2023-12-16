// B-Tree
class BTree {
    BTreeNode root;
    private int t; // minimum degree

    public BTree(int t) {
        this.root = new BTreeNode();
        this.t = t;
    }

    // insert a key and value into the B-tree
    public void insert(String key, String value) {
        BTreeNode rootNode = this.root;

        if (rootNode.keys.size() == 2 * t - 1) {
            BTreeNode newRoot = new BTreeNode();
            newRoot.children.add(rootNode);
            splitChild(newRoot, 0);
            this.root = newRoot;
            insertNonFull(newRoot, key, value);
        } else {
            insertNonFull(rootNode, key, value);
        }
    }

    // insert into a non-full node
    private void insertNonFull(BTreeNode node, String key, String value) {
        int i = node.keys.size() - 1;

        if (node.children.isEmpty()) {
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }
            i++;
            node.keys.add(i, key);
            node.values.add(i, value);
        } else {
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }
            i++;

            if (node.children.get(i).keys.size() == 2 * t - 1) {
                splitChild(node, i);
                if (key.compareTo(node.keys.get(i)) > 0) {
                    i++;
                }
            }

            insertNonFull(node.children.get(i), key, value);
        }
    }

    // split child of a node
    private void splitChild(BTreeNode parentNode, int childIndex) {
        BTreeNode child = parentNode.children.get(childIndex);
        BTreeNode newChild = new BTreeNode();

        parentNode.keys.add(childIndex, child.keys.get(t - 1));
        parentNode.values.add(childIndex, child.values.get(t - 1));

        newChild.keys.addAll(child.keys.subList(t, 2 * t - 1));
        newChild.values.addAll(child.values.subList(t, 2 * t - 1));
        child.keys.subList(t - 1, 2 * t - 1).clear();
        child.values.subList(t - 1, 2 * t - 1).clear();

        if (!child.children.isEmpty()) {
            newChild.children.addAll(child.children.subList(t, 2 * t));
            child.children.subList(t, 2 * t).clear();
        }

        parentNode.children.add(childIndex + 1, newChild);
    }

    // search for a value associated with a key
    public String search(String key) {
        return searchKey(root, key);
    }

    // recursive search for a key
    private String searchKey(BTreeNode node, String key) {
        int i = 0;
        while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }

        if (i < node.keys.size() && key.equals(node.keys.get(i))) {
            return node.values.get(i);
        } else if (node.children.isEmpty()) {
            return null;
        } else {
            return searchKey(node.children.get(i), key);
        }
    }
}
