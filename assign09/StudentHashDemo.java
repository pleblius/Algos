package assign09;

/**
 * This class demonstrates how to use a hash table to store key-value pairs.
 * 
 * @author Erin Parker
 * @version March 24, 2022
 */
public class StudentHashDemo {

	public static void main(String[] args) {
		
		var alan = new StudentGoodHash(1019999, "Alan", "Turing");
		var ada = new StudentGoodHash(1004203, "Ada", "Lovelace");
		var edsger = new StudentGoodHash(1010661, "Edsger", "Dijkstra");
		var grace = new StudentGoodHash(1019941, "Grace", "Hopper");

		HashTable<StudentGoodHash, Double> studentGpaTable = new HashTable<StudentGoodHash, Double>();
		studentGpaTable.put(alan, 3.2);
		studentGpaTable.put(ada, 3.5);
		studentGpaTable.put(edsger, 3.8);
		studentGpaTable.put(grace, 4.0);
		
		for(MapEntry<StudentGoodHash, Double> e : studentGpaTable.entries())
			System.out.println("Student " + e.getKey() + " has GPA " + e.getValue() + ".");
	}
}
