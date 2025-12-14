package Task5_with_deadlock;

public class AccountMain {
    public static void main(String[] args) {
        Account firstAccount = new Account(100_000);
        Account secondAccount = new Account(100_000);

        Thread firstThread = new Thread(new AccountThread(firstAccount, secondAccount, 100));
        Thread secondThread = new Thread(new AccountThread(secondAccount, firstAccount, 100));

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("First account balance: " + firstAccount.getCashBalance());
        System.out.println("Second account balance: " + secondAccount.getCashBalance());
    }
}
