public class Item {
    public long value;
    public int monkey;

    public Item(long value, int monkey) {
        this.value = value;
        this.monkey = monkey;
    }

    public void change(long value, int monkey) {
        this.value = value;
        this.monkey = monkey;
    }
}
