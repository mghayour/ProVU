package helper;

import java.util.Random;
import java.util.Scanner;

public class Helper {

	public static void println(String string) {
		System.out.println(string);
	}
	public static void print(String string) {
		System.out.print(string);
	}

	private static Scanner scanner = new Scanner(System.in);
	public static int inputInt(String string) {
		System.out.print("input "+string+" : ");
		return scanner.nextInt();
	}
	public static double inputDouble() {
		return scanner.nextDouble();
	}
	public static String inputString(String string) {
		System.out.print("input "+string+" : ");
		return scanner.next();
	}

	private static Random rand = new Random();
	public static int random(int from,int to) {
		return rand.nextInt(to-from)+from;
	}
	public static int random(int to) {
		return rand.nextInt(to);
	}

}