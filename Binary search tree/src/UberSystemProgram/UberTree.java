
//assignment title: Binary search tree
//date: 10 NOV 2022
package UberSystemProgram;

import java.io.PrintWriter;

public class UberTree {
//DATA FILED 

    private CaptainNode root;
    // CONSTRUCTORS

    public UberTree() {
        root = null;
    }

    //
    // boolean | isEmpty()
    //
    public boolean isEmpty() {
        return root == null;
    }

    public CaptainNode getRoot() {
        return root;
    }

    public void add_captains(CaptainNode captain) {
        root = add_captains(root, captain);
    }
    //
    // CaptainNode | add_captains(CaptainNode, CaptainNode)
    //

    private CaptainNode add_captains(CaptainNode p, CaptainNode newNode) {
        // IF there is no tree, newNode will be the root, so just return it
        if (p == null) {
            return newNode;
        } // ELSE, we have a tree. Insert to the right or the left
        else {
            // Insert to the RIGHT of root
            if (newNode.getCaptainID() > p.getCaptainID()) {
                // Recursively insert into the right subtree
                // The result of insertion is an updated root of the right subtree
                CaptainNode temp = add_captains(p.getRight(), newNode);
                // Save this newly updated root of right subtree into p.right
                p.setRight(temp);
            } // Insert to the LEFT of root
            else {
                // Recursively insert into the left subtree
                // The result of insertion is an updated root of the left subtree
                CaptainNode temp = add_captains(p.getLeft(), newNode);
                // Save this newly updated root of left subtree into p.left
                p.setLeft(temp);
            }
        }
        // Return root of updated tree
        return p;
    }

    public CaptainNode search(int data) {
        return search(root, data);
    }
    //
    // CaptainNode | search(CaptainNode, int)
    //

    private CaptainNode search(CaptainNode p, int id) {
        if (p == null) {
            return null;
        } else {
            // if the data we are searching for is found at p (at the current root)
            if (id == p.getCaptainID()) {
                return p;
            } else if (id < p.getCaptainID()) {
                return search(p.getLeft(), id);
            } else {
                return search(p.getRight(), id);
            }
        }
    }

    // method the display all information for the specified captain based on his/her ID
    public void display_captain_by_ID(CaptainNode captain, PrintWriter output) {

        output.println("		        ID: " + captain.getCaptainID() + "\n"
                + "			Name: " + captain.getCaptainName() + "\n"
                + "			Available: " + captain.isAvailable() + " \n"
                + "			Rating star: " + captain.getStar() + "\n"
                + "						\n"
                + "----------------------------------------------------------------");
    }

    public void display_all_captains(PrintWriter output) {
        display_all_captains(root, output);
    }
    //
    // void | display_all_captains(CaptainNode)
    //

//a function that prints all captains’ names with their specified IDs and rating stars and availablty.
    private void display_all_captains(CaptainNode p, PrintWriter output) {
        if (p != null) {
            display_all_captains(p.getLeft(), output);
            output.println("			ID: " + p.getCaptainID() + "\n"
                    + "			Name: " + p.getCaptainName() + "\n"
                    + "			Available: " + p.isAvailable() + " \n"
                    + "			Rating star: " + p.getStar() + "\n"
                    + "			\n"
                    + "----------------------------------------------------------------");

            display_all_captains(p.getRight(), output);
        }
    }

    public void delete(int id) {
        root = delete(root, id);
    }

    //
    // CaptainNode | delete(CaptainNode, int)
    //
//6.	a function that removes a particular captain from the tree.
    private CaptainNode delete(CaptainNode p, int id) {
        CaptainNode node2delete, newnode2delete, parent;
        int saveValue;

        // Step 1: Find the node we want to delete
        node2delete = search(p, id);
        // If node is not found (does not exist in tree), we clearly cannot delete it.
        if (node2delete == null) {
            return root;
        }

        // Step 2: Find the parent of the node we want to delete
        parent = parent(p, node2delete);

        // Step 3: Perform Deletion based on three possibilities
        // **1** :  node2delete is a leaf node
        if (isLeaf(node2delete)) {
            // if parent is null, this means that node2delete is the ONLY node in the tree
            if (parent == null) {
                return null; // we return null as the updated root of the tree
            }
            // Delete node if it is a left child
            if (id < parent.getCaptainID()) {
                parent.setLeft(null);
            } // Delete node if it is a right child
            else {
                parent.setRight(null);
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **2** : node2delete has only a left child
        if (hasOnlyLeftChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getLeft();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some node
            // IF it is the left child of some node
            if (id < parent.getCaptainID()) {
                parent.setLeft(parent.getLeft().getLeft());
            } // ELSE it is the right child of some node
            else {
                parent.setRight(parent.getRight().getLeft());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **3** :  node2delete has only a right child
        if (hasOnlyRightChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getRight();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some node
            // IF it is the left child of some node
            if (id < parent.getCaptainID()) {
                parent.setLeft(parent.getLeft().getRight());
            } // ELSE it is the right child of some node
            else {
                parent.setRight(parent.getRight().getRight());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **4** :  node2delete has TWO children.
        // Remember, we have two choices: the minVal from the right subtree (of the deleted node)
        // or the maxVal from the left subtree (of the deleted node)
        // We choose to find the minVal from the right subtree.
        newnode2delete = minNode(node2delete.getRight());
        // Now we need to temporarily save the data value(s) from this node
        saveValue = newnode2delete.getCaptainID();

        // Now, we recursively call our delete method to actually delete this node that we just copied the data from
        p = delete(p, saveValue);

        // Now, put the saved data (in saveValue) into the correct place (the original node we wanted to delete)
        node2delete.setCaptainID(saveValue);

        // Finally, return the root of the tree (in case the root got updated)
        return p;
    }

    //method to find the parent of a spicific node
    private CaptainNode parent(CaptainNode root, CaptainNode p) {
        // Take care of NULL cases
        if (root == null || root == p) {
            return null; // because there is on parent
        }
        // If root is the actual parent of node p
        if (root.getLeft() == p || root.getRight() == p) {
            return root; // because root is the parent of p
        }
        // Look for p's parent in the left side of root
        if (p.getCaptainID() < root.getCaptainID()) {
            return parent(root.getLeft(), p);
        } // Look for p's parent in the right side of root
        else if (p.getCaptainID() > root.getCaptainID()) {
            return parent(root.getRight(), p);
        } // Take care of any other cases
        else {
            return null;
        }
    }

    //
    // boolean | isLeaf(CaptainNode)
    //
    public boolean isLeaf(CaptainNode p) {
        return (p.getLeft() == null && p.getRight() == null);
    }

    //
    // boolean | hasOnlyLeftChild(CaptainNode)
    //
    public boolean hasOnlyLeftChild(CaptainNode p) {
        return (p.getLeft() != null && p.getRight() == null);
    }

    //
    // boolean | hasOnlyRightChild(CaptainNode)
    //
    public boolean hasOnlyRightChild(CaptainNode p) {
        return (p.getLeft() == null && p.getRight() != null);
    }

    //
    // CaptainNode | minNode(CaptainNode)
    //
// method to find the minimum node in the tree
    public CaptainNode minNode(CaptainNode root) {
        if (root == null) {
            return null;
        } else {
            if (root.getLeft() == null) {
                return root;
            } else {
                return minNode(root.getLeft());
            }
        }
    }

    //
    // CaptainNode | maxNode(CaptainNode)
    //
    // method to find the maximum node in the tree
    public CaptainNode maxNode(CaptainNode root) {
        if (root == null) {
            return null;
        } else {
            if (root.getRight() == null) {
                return root;
            } else {
                return maxNode(root.getRight());
            }
        }
    }

// a function that books a ride.
    public void book(CaptainNode captain, PrintWriter output, int id) {

        if (captain != null) {// check if the captain is exist in the tree
            if (captain.isAvailable() == false) {//if the captain is not available, print a massege
                output.println("Command BOOK_RIDE: The captain " + captain.getCaptainName() + " is not available. He is on another ride!\n\n");
            } else {// if the captain is available
                captain.setAvailable(false);//make the captain in a new ride
                output.println("Command BOOK_RIDE: Book a new Ride with captin " + captain.getCaptainID() + "\n");

            }
        } else {// if the captain is not exist in the tree
            output.println("Command BOOK_RIDE: Couldn’t  find any captain with ID number " + id + "\n");

        }
        output.println("----------------------------------------------------------------");
    }
//	a function that finishes a ride. The method calculates the captain’s stars based on the rider satisfaction. 

    public void finish(CaptainNode captain, int star, PrintWriter output, int id) {

        if (captain != null) {// the captain is exist in the tree
            if (captain.isAvailable() == false) {//if the captain was in ride
                captain.setAvailable(true);//the ride is finish so make the captain available again
                if (star == 1 && captain.getStar() != 5) {//if the rider satisfied and the stars not =5
                    captain.setStar(captain.getStar() + 1);// increment the stars by 1
                } else if (star == 0 && captain.getStar() != 0) {//if the rider is not satisfied and the stars not =0
                    captain.setStar(captain.getStar() - 1);// decrement the stars by 1
                }

                output.println("Command FINISH_RIDE: Finish ride with captin " + captain.getCaptainID() + "\n");
                display_captain_by_ID(captain, output);

            } else {//if the captain is not in the ride, print a massage 
                output.println("Command FINISH_RIDE: The  captain " + captain.getCaptainName() + " is not in a ride!");
                output.println("----------------------------------------------------------------");
            }

        } else {// the captain does not exist in the tree
            output.println("Command FINISH_RIDE: Couldn’t  find any captain with ID number " + id);
            output.println("----------------------------------------------------------------");

        }
    }

}

