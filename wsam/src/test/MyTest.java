package test;

import java.nio.ByteBuffer;

public class MyTest {
	public static void main(String args[]) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		byte[] b = new byte[5];
		for(int i = 0; i < 5; i++) {
			b[i] = (byte)i;
		}
		
		buf.put(0, (byte)9);
		buf.put(1, (byte)8);
		buf.put(2,(byte)7);
		for(int i = 3; i < b.length + 3; i++) {
			buf.put(i, b[i-3]);
		}
		byte[] ba = buf.array();
		for(byte bb : ba) {
			System.out.println(bb);
		}
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