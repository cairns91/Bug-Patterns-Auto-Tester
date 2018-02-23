package tests.sleepbug;

public class Bank {
	
	private String surname;
	private boolean currentAccount;
	private int age;
	private boolean savingAccount;
	
	public Bank(String surname, boolean currentAccount, int age) {
		this.surname = surname;
		this.currentAccount = currentAccount;
		this.age = age;
		this.savingAccount = false;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public boolean getSavingAccount() {
		return this.savingAccount;
	}
	
	public boolean getCurrentAccount() {
	    return this.currentAccount;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void update(String n, boolean t, int a) {
		this.surname = n;
		this.currentAccount = t;
		this.age = a;
	}
	
	public boolean isOlder() {
		if(this.age > 20)
			return true;
		return false;
	}
	
	public void moveOn() {
		if(this.currentAccount && isOlder())
			this.savingAccount = true;
	}
	
	public void printDetails() {
		System.out.println("Surname: " + this.surname);
		System.out.println("Age: " + this.age);
		System.out.println("Current Account: " + this.currentAccount);
		System.out.println("Saving Account: " + this.savingAccount);
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + (currentAccount ? 1231 : 1237);
        result = prime * result + (savingAccount ? 1231 : 1237);
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bank other = (Bank) obj;
        if (age != other.age)
            return false;
        if (currentAccount != other.currentAccount)
            return false;
        if (savingAccount != other.savingAccount)
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }
}
