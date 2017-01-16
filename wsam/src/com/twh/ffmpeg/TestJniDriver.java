package com.twh.ffmpeg;

public class TestJniDriver {
	public static void main(String args[]){
		System.loadLibrary("MyFirstJni");
		System.out.println(JavaInvodeCPlus.returnHelloWorldToUpcase("ttt"));
		JavaInvodeCPlus.sayHelloWolrd();
	}
	
}
