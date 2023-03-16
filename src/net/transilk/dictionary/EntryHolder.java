package net.transilk.dictionary;

public class EntryHolder {

    private String left;
    private String right;
    private String synonym;
    private String see;
    /*
    private String leftDescription;
    private String[] note;
    private String rightDescription;
    private String phoneticSpelling;
    */

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getSynonym() {
        if (synonym == null) synonym = "";
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getSee() {
        if (see == null) see = "";
        return see;
    }

    public void setSee(String see) {
        this.see = see;
    }

    /*
    public String getPhoneticSpelling() {
        return phoneticSpelling;
    }

    public void setPhoneticSpelling(String phoneticSpelling) {
        this.phoneticSpelling = phoneticSpelling;
    }

    public String getLeftDescription() {
        return leftDescription;
    }

    public void setLeftDescription(String leftDescription) {
        this.leftDescription = leftDescription;
    }

    public String getRightDescription() {
        return rightDescription;
    }

    public void setRightDescription(String rightDescription) {
        this.rightDescription = rightDescription;
    }

    public String[] getNote() {
        return note;
    }

    public void setNote(String[] note) {
        this.note = note;
    }
    */
}
