package net.transilk.dictionary;

public class IndexEntry {
    private final int absoluteBytePosition;
    private final int byteLength;

    public IndexEntry(int absoluteBytePosition, int byteLength) {
        this.absoluteBytePosition = absoluteBytePosition;
        this.byteLength = byteLength;
    }

    public int getAbsoluteBytePosition() {
        return absoluteBytePosition;
    }

    public int getByteLength() {
        return byteLength;
    }
}
