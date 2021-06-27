public class Field {
    private int bombsNearBy;
    private boolean marked = false;
    private boolean isBomb = false;
    private boolean revealed = false;

    public int getBombsNearBy() {
        return isBomb? 0 : bombsNearBy;
    }

    public void incrementBombsNearBy() {
        this.bombsNearBy++;
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean toggleMarked() {
        return this.marked = !this.marked;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb() {
        isBomb = true;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed() {
        this.revealed = true;
    }
}
