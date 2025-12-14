package Task5_without_deadlock;
public class Account {
    private int cashBalance;

    public Account(int cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void addMoney(int money) {
        this.cashBalance += money;
    }

    public boolean takeOffMoney(int money) {
        if (this.cashBalance < money) return false;
        this.cashBalance -= money;
        return true;
    }

    public int getCashBalance() {
        return cashBalance;
    }
}

