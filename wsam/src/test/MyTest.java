package test;

import org.json.JSONException;
import org.json.JSONObject;

public class MyTest {
	public static void main(String args[]) {
		String jsonTest = "{\"person1\":{\"name\":\"twh\",\"sex\":1,}}";
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonTest);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(jsonObject.getJSONObject("person1").get("name"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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