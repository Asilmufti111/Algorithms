
//assignment title: Binary search tree
//date: 10 NOV 2022
package UberSystemProgram;

public class CaptainNode {
//DATA FILED //

    private int captainID;
    private String CaptainName;
    private int star;
    private boolean available;
    private CaptainNode right;
    private CaptainNode left;

    //CONSTRACTERS//
    public CaptainNode() {
        captainID = 0;
        CaptainName = "";
        star = 0;
        available = true;
        left = right = null;

    }

    public CaptainNode(int captainID, String CaptainName) {
        this.captainID = captainID;
        this.CaptainName = CaptainName;
        this.star = 0;
        this.available = true;
        left = right = null;
    }

    public CaptainNode(int captainID, String CaptainName, int star, boolean available, CaptainNode right, CaptainNode left) {
        this.captainID = captainID;
        this.CaptainName = CaptainName;
        this.star = star;
        this.available = available;
        this.right = right;
        this.left = left;
    }
// ACCESSORS// 

    public int getCaptainID() {
        return captainID;
    }

    public String getCaptainName() {
        return CaptainName;
    }

    public int getStar() {
        return star;
    }

    public boolean isAvailable() {
        return available;
    }

    public CaptainNode getRight() {
        return right;
    }

    public CaptainNode getLeft() {
        return left;
    }

    // MUTATORS //
    public void setCaptainID(int captainID) {
        this.captainID = captainID;
    }

    public void setCaptainName(String CaptainName) {
        this.CaptainName = CaptainName;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setRight(CaptainNode right) {
        this.right = right;
    }

    public void setLeft(CaptainNode left) {
        this.left = left;
    }
}
