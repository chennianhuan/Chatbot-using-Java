/*
* (Display calendars) Rewrite the PrintCalendar class in Listing 6.12 to display 
* a calendar for a specified month using the Calendar and GregorianCalendar      
* classes. Your program receives the month and year from the command line. For   
* example:                                                                       
*                                                                                
* java Exercise13_04 5 2016                                                      
*                                                                                
*/

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class HW5_Q13_4 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Prompt the user to enter year
		System.out.print("Enter month in number between 1 and 12 and full year (e.g., 5 2016): ");
		int month = input.nextInt();
		int year = input.nextInt();

		// Print calendar for the month of the year
		printMonth(year, month);
	}

	/** Print the calendar for a month in a year */
	public static void printMonth(int year, int month) {
		// Print the headings of the calendar
		printMonthTitle(year, month);

		// Print the body of the calendar
		printMonthBody(year, month);
	}

	/** Print the month title, e.g., May, 1999 */
	public static void printMonthTitle(int year, int month) {
		System.out.println("         " + getMonthName(month) + " " + year);
		System.out.println("-----------------------------");
		System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
	}

	/** Get the English name for the month */
	public static String getMonthName(int month) {
		String monthName = "";
		switch (month) {
		case 1:
			monthName = "January";
			break;
		case 2:
			monthName = "February";
			break;
		case 3:
			monthName = "March";
			break;
		case 4:
			monthName = "April";
			break;
		case 5:
			monthName = "May";
			break;
		case 6:
			monthName = "June";
			break;
		case 7:
			monthName = "July";
			break;
		case 8:
			monthName = "August";
			break;
		case 9:
			monthName = "September";
			break;
		case 10:
			monthName = "October";
			break;
		case 11:
			monthName = "November";
			break;
		case 12:
			monthName = "December";
		}

		return monthName;
	}

	/** Print month body */
	public static void printMonthBody(int year, int month) {
		// Get start day of the week for the first date in the month
		int startDay = getStartDay(year, month);

		// Get number of days in the month
		int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);

		// Pad space before the first day of the month
		int i = 0;
		for (i = 0; i < startDay; i++)
			System.out.print("    ");

		for (i = 1; i <= numberOfDaysInMonth; i++) {
			System.out.printf("%4d", i);

			if ((i + startDay) % 7 == 0)
				System.out.println();
		}

		System.out.println();
	}

	/** Get the start day of month. 1 represents the 1st day */
	public static int getStartDay(int year, int month) {

		Calendar calendar1 = new GregorianCalendar();
		calendar1.set(year, month - 1, 1);

		return calendar1.get(Calendar.DAY_OF_WEEK) - 1;

	}

	/**
	 * Get the number of days in a month. 1 represents the 1st day, whereas 0
	 * represents the 1st month
	 */
	public static int getNumberOfDaysInMonth(int year, int month) {

		Calendar calendar1 = new GregorianCalendar(year, month - 1, 1);
		int dayOfMonth = calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);

		return dayOfMonth;
	}
}
