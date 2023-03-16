package assign07;

public class CreateRandomGraphs {

	public static void main(String[] args) {
		
		RandomGraphDot.generateRandomAcyclicDotFile("RADG.txt", 7);
		RandomGraphDot.generateRandomConnectedDotFile("RCDG.txt", 20);

	}

}
