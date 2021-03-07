//package assignment2;

import java.util.Arrays;

public class GreedyTester {
	
	public static boolean typicalInput() {//CodePost test 1-2
		//This is the typical kind of input you will be tested with. The format will always be the same
		//Each index represents a single homework. For example, homework zero has weight 23 and deadline t=3.
		int[] weights = new int[] {23, 60, 14, 25, 7}; 
		int[] deadlines = new int[] {3, 1, 2, 1, 3};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {1,2,0};
		//System.out.println(Arrays.toString(student));
		
		//System.out.println(Arrays.toString(student));
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean noConflicts() { //CodePost test #3
		int[] weights = new int[] {3, 2, 1}; 
		int[] deadlines = new int[] {3, 2, 1};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {2,1,0};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean weightAdvantage() { //CodePost test #4
		int[] weights = new int[] {3, 2, 9}; 
		int[] deadlines = new int[] {2, 1, 1};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {2,0};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean notUrgent() { //CodePost test #5
		int[] weights = new int[] {1, 1, 100}; 
		int[] deadlines = new int[] {1, 2, 3};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {0,1,2};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean codepostTests() {
		System.out.println("-------TESTING CODEPOST'S TESTS-----\n");
		int trigger = 0;
		if (typicalInput()==false) { System.out.println("Error found in typicalInput()."); trigger = 1; }
		if (noConflicts()==false) { System.out.println("Error found in typicalInput()."); trigger = 1; }
		if (weightAdvantage()==false) { System.out.println("Error found in typicalInput()."); trigger = 1; }
		if (notUrgent()==false) { System.out.println("Error found in typicalInput()."); trigger = 1; }
		
		if (trigger == 1) { return false; }
		else { return true; }
	}
	
	public static boolean freeTimeSlot() { //empty time slot  @HW_Sched[3]
		int[] weights = new int[] {3, 2, 1, 4}; 
		int[] deadlines = new int[] {4, 2, 1, 5};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {2,1,-1, 0, 3};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean oneAssignment() { //checks for errors with a single assignment
		int[] weights = new int[] {1}; 
		int[] deadlines = new int[] {1};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {0};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean dueInAWhile() { //checks for errors with a single assignment due at a "big" deadline
		int[] weights = new int[] {1}; 
		int[] deadlines = new int[] {10};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1, 0};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean noAssignment() { //checks for errors (null pointers) when no assignments are inputed
		int[] weights = new int[] {}; 
		int[] deadlines = new int[] {};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean zeroDeadLine() { //checks if the code handles an assignment due now (deadline=0)
		int[] weights = new int[] {3, 2, 1, 4}; 
		int[] deadlines = new int[] {4, 2, 1, 0};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {2, 1,-1, 0};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean allZeroDeadLine() { //checks if the code handles all assignments due now (deadline=0)
		int[] weights = new int[] {420, 69, 666, 58008}; 
		int[] deadlines = new int[] {0, 0, 0, 0};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean sameWeight() { //testing what happens for situations where more than 1 assignment have the same weight
		int[] weights = new int[] {5, 5, 3, 5, 6}; 
		int[] deadlines = new int[] {4, 3, 4, 1, 2};
		int m = weights.length;
		
		//This is the declaration of a schedule of the appropriate size
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		
		//This call organizes the assignments and outputs homeworkPlan
		int[] student = schedule.SelectAssignments();
		int[] expected = new int[] {3,4,1,0};
		//System.out.println(Arrays.toString(student));
		
		for (int i=0; i<expected.length; i++) {
			if (student[i]!=expected[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean additionalTests() {
		System.out.println("-------TESTING ADDITIONAL TESTS-----\n");
		int trigger = 0;
		if (freeTimeSlot()==false) { System.out.println("Error found in freeTimeSlot()."); trigger = 1; }
		if (oneAssignment()==false) { System.out.println("Error found in oneAssignment()."); trigger = 1; }
		if (dueInAWhile()==false) { System.out.println("Error found in dueInAWhile()."); trigger = 1; }
		if (noAssignment()==false) { System.out.println("Error found in noAssignment()."); trigger = 1; }
		if (zeroDeadLine()==false) { System.out.println("Error found in zeroDeadLine()."); trigger = 1; }
		if (allZeroDeadLine()==false) { System.out.println("Error found in allZeroDeadLine()."); trigger = 1; }
		if (sameWeight()==false) { System.out.println("Error found in sameWeight()."); trigger = 1; }

		if (trigger == 1) { return false; }
		else { return true; }
	}
	
	public static void main(String[] args) {
		int trigger = 0;
		System.out.println("-------STARTING GREEDY TESTER-------\n");
		System.out.println("------------------------------------\n");
		
		if (codepostTests()==false) { trigger = 1; }
		if (additionalTests()==false) { trigger = 1; }
		
		System.out.println("------------------------------------\n");
		if (trigger == 0) { System.out.println("DIDN'T FIND ANY PROBLEMS W/ SOLUTION."); }
		else { System.out.println("FOUND SOME PROBLEMS W/ SOLUTION."); }
	}
		
}