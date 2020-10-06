package kr.co.kokono.junit_study._03;

import static kr.co.kokono.junit_study._03.PointMatcher.isNear;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import java.util.*;
import org.junit.rules.*;

public class AssertTest {

    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }

        private static final long serialVersionUID = 1L;
    }

    class Account {
        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    class Customer {
        List<Account> accounts = new ArrayList<>();

        void add(Account account) {
            accounts.add(account);
        }

        Iterator<Account> getAccounts() {
            return accounts.iterator();
        }
    }

    /////////////////////////////////////////////////////////////////

    private Account account;

    @Before
    public void createAccount() {
        account = new Account("an account name");
    }

    //잔액이 양수일때 hasPositiveBalance 작동 검증
    @Test
    public void hasPositiveBalance() throws Exception {
        //given

        //when
        account.deposit(50);
        //then
        assertTrue(account.hasPositiveBalance());
    }


    //1. 예금 후에 초기잔액보다 잔액이 많아지는지 검증
    //2. 예금한 금액이 잘 저장되었는지 검증
    @Test
    public void depositIncreasesBalance() throws Exception {
        //given
        int initialBalance = account.getBalance();
        //when
        account.deposit(100);
        //then
        assertTrue(account.getBalance() > initialBalance);
        assertThat(account.getBalance(), equalTo(100));
    }

    @Test
    public void depositIncreasesBalance_hamcrestAssertTrue() {
        account.deposit(50);
        assertThat(account.getBalance() > 0, is(true));
    }

    @Test
    @ExpectToFail
    @Ignore
    public void assertFailure() {
        assertTrue(account.getName().startsWith("xyz"));
    }

    @Test
    @ExpectToFail
    @Ignore
    public void matchesFailure() {
        assertThat(account.getName(), startsWith("xyz"));
    }

    @Ignore
    @ExpectToFail
    @Test
    public void comparesArraysFailing() {
        assertThat(new String[] {"a", "b", "c"}, equalTo(new String[] {"a", "b"}));
    }

    @Ignore
    @ExpectToFail
    @Test
    public void comparesCollectionsFailing() {
        assertThat(Arrays.asList(new String[] {"a"}),
                equalTo(Arrays.asList(new String[] {"a", "ab"})));
    }

    @Test
    public void comparesCollectionsPassing() {
        assertThat(Arrays.asList(new String[] {"a"}),
                equalTo(Arrays.asList(new String[] {"a"})));
    }

    @Test
    public void variousMatcherTests() {
        Account account = new Account("my big fat acct");
        assertThat(account.getName(), is(equalTo("my big fat acct")));

        //allOf는 둘다 해당하는지
        assertThat(account.getName(), allOf(startsWith("my"), endsWith("acct")));

        //anyOf는 둘중에 하나가 해당하는지
        assertThat(account.getName(), anyOf(startsWith("my"), endsWith("loot")));

        assertThat(account.getName(), not(equalTo("plunderings")));

        assertThat(account.getName(), is(not(nullValue())));
        assertThat(account.getName(), is(notNullValue()));

        //타입 검사
        assertThat(account.getName(), isA(String.class));

        assertThat(account.getName(), is(notNullValue())); // 유용하지 않음
        assertThat(account.getName(), equalTo("my big fat acct"));
    }

    @Test
    public void sameInstance() {
        Account a = new Account("a");
        Account aPrime = new Account("a");
        // why needs to be fully qualified??
        assertThat(a, not(org.hamcrest.CoreMatchers.sameInstance(aPrime)));
    }

    @Test
    public void moreMatcherTests() {
        Account account = new Account(null);
        assertThat(account.getName(), is(nullValue()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void items() {
        List<String> names = new ArrayList<>();
        names.add("Moe");
        names.add("Larry");
        names.add("Curly");

        assertThat(names, hasItem("Curly"));

        assertThat(names, hasItems("Curly", "Moe"));

        assertThat(names, hasItem(endsWith("y")));

        assertThat(names, hasItems(endsWith("y"), startsWith("C"))); //warning!

        assertThat(names, not(everyItem(endsWith("y"))));
    }

    @Test
    @ExpectToFail @Ignore
    public void location() {
        Point point = new Point(4, 5);

        // WTF why do JUnit matches not include closeTo
//      assertThat(point.x, closeTo(3.6, 0.2));
//      assertThat(point.y, closeTo(5.1, 0.2));

        assertThat(point, isNear(3.6, 5.1));
    }

    @Test(expected=InsufficientFundsException.class)
    public void throwsWhenWithdrawingTooMuch() {
        account.withdraw(100);
    }

    @Test
    public void throwsWhenWithdrawingTooMuchTry() {
        try {
            account.withdraw(100);
            fail();
        }
        catch (InsufficientFundsException expected) {
            assertThat(expected.getMessage(), equalTo("balance only 0"));
        }
    }

}
