import java.util.Scanner;
public class ParkingApp {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		Parking[] slot = new Parking[100];
		
		for(int i=0;i<100;i++) {
			slot[i] = new Parking();
		}
		boolean avail = false;
		int vno=0;
		String entry="";
		String exit="";
		do
		{
			System.out.println("1) Automatically check for the available parking.");
			System.out.println("2) Enter the vehicle number coming in for parking.");
			System.out.println("3) Enter the entry time of a vehicle.");
			System.out.println("4) Automatically allocate the empty slot for parking.");
			System.out.println("5) On exit, calculate the parking fee based on the time duration of parking. Parking fee is Rs 50\r\n" + 
					"		for fist 2 hours after which Rs 30/hr.");
			int select = intValidate();
			
			switch(select) {
			
			case 1:{
				
					avail = checkSlot(slot);
					if(!(avail)) {
						System.out.println("parking is not available");
					}
					break;
			}
			case 2:{
				if(avail == true) {
					System.out.println("Enter the vehicle number coming in for parking");
					vno=sc.nextInt();
					vno = checkIfPositive(vno);
				
				}
				break;
			}
			case 3:{
				if(avail == true) {
				System.out.println("Enter the entry timing of the vehicle");
				entry=sc.next();

				}
				break;
			}
			case 4:{
				if(avail == true) {
				allocate(slot,vno,entry);
				}
				break;
			}
			case 5:{
				System.out.println("enter the exit timing of the vehicle");
				exit = sc.next();
				System.out.println("Enter the vehicle number coming in for parking");
				int vehno=sc.nextInt();
				vehno = checkIfPositive(vno);
				calculate(slot,vehno,exit);
			}
			}
			
			
		}while(true);
		
		
	}

	private static int intValidate() {

		int data = 0;
		boolean validation = false;
		while (validation == false) {
			if (sc.hasNextInt()) {
				data = sc.nextInt();
				validation = true;
			} else if (!sc.hasNextInt()) {
				System.out.println("you didn't type an integer value ! please type an integer");
				sc.next();

			}
		}
		return data;
	}

	private static int checkIfPositive(int integer) {
		while (integer < 0) {
			System.out.println("you didn't type an integer value ! please type an integer");
			integer = sc.nextInt();
		}
		return integer;
	}
	
	/*
	private static boolean check(String data) {

		int a = data.length();

		for (int i = 0; i < data.length(); i++) {
			if (!((data.charAt(i) >= 'a') && (data.charAt(i) <= 'z'))
					&& !((data.charAt(i) >= 'A') && (data.charAt(i) <= 'Z')) && !(data.charAt(i) == ' ')) {
				return false;
			}

		}
		return true;
	}*/
	

	public static boolean checkSlot(Parking[] slot) {

		int len = slot.length;

		for (int i = 0; i < len; i++) {
			if (slot[i].isAvailable() == true) {
				return true;
			}
		}

		return false;
	}
	public static void allocate(Parking[] slot,int vno,String entry) {
		for(int i=0;i<slot.length;i++) {
			if(slot[i].isAvailable() == true) {
				slot[i].setVeh_Number(vno);
				slot[i].setEntryTime(entry);
				slot[i].setAvailable(false);
				break;
			}
		}
	}
	public static void calculate(Parking[] slot,int vno,String exit) {
		
		for(int i=0;i<slot.length;i++) {
			if(slot[i].getVeh_Number() == vno) {
				int duration = calculateTime(exit,slot[i].getEntryTime());
				if(duration<=2) {
					System.out.println("your paking fee is"+(2*30));
				}else {
					System.out.println("your parking fee is "+" "+(duration*50));
				}
			}
		}
	}
	public static int calculateTime(String exit,String entry) {
		
		String[] a1 = Split(exit,':');
		String[] a2 = Split(entry,':');
		
		int exitHours = convert(a1[0]);
		System.out.println(exitHours);
		int exitMin = convert(a1[1]);
		System.out.println(exitMin);
		int entryHours = convert(a2[0]);
		System.out.println(entryHours);
		int entryMin = convert(a2[1]);
		System.out.println(entryMin);
		int du=0;
		if(exitHours > entryHours) {
			du = exitHours - entryHours;
		}else if(exitHours <= entryHours) {
			du = 2;
		}
		
		/*if(exitMin > entryMin) {
			du++;
		}*/
		if(du>2) {
			if(exitMin > entryMin) {
				du++;
			}
		}
		System.out.println(du);
		return du;
	}
	
	private static String[] Split(String str, char delimiter) {
		int count = 0;
		for (int i = str.length() - 1; i >= 0; i--)
			if (str.charAt(i) == delimiter)
				count++;
		String arr[] = new String[count + 1];
		String var = "";
		int j = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == delimiter) {
				arr[j] = var;
				var = "";
				j++;
			} else
				var = var + str.charAt(i);
		}
		arr[j] = var;
		return arr;
	}
	public static int convert(String s1) {
		int sum = 0;
		for (int i = 0; i < s1.length(); i++) {
			sum = sum * 10 + (s1.charAt(i) - 48);
		}

		return sum;
	}
	public static boolean checkString(String data) {
		for (int i = 0; i < data.length(); i++) {
			if (!((data.charAt(i) >= 'a') && (data.charAt(i) <= 'z'))
					&& !((data.charAt(i) >= 'A') && (data.charAt(i) <= 'Z')) && !(data.charAt(i) == ' ') && !((data.charAt(i) >= '0') && (data.charAt(i) <= '9')) && !(data.charAt(i) == ':')) {
				return false;
			}
		}

		return true;
	}
	}