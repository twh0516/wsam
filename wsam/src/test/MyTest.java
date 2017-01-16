package test;
public class MyTest {
	public static void main(String args[]) {
		TestSync sc = new TestSync();
		sc.sayNameHello("twh");
	}
}

class TestSync {
	
	private synchronized void sayHello() {
		System.out.println("hello");
	}
	
	public synchronized void sayNameHello(String name) {
		System.out.print(name);
		sayHello();
	}
}

class People {
    String name;

    public People() {
        System.out.print(1);
    }

    public People(String name) {
        System.out.print(2);
        this.name = name;
    }
}

class Child extends People {
    People father;

    public Child(String name) {
        System.out.print(3);
        this.name = name;
        father = new People(name + ":F");
    }

    public Child() {
        System.out.print(4);
    }
    
}