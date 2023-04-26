package assign02;

import java.util.ArrayList;

/**
 * This class represents a CS2420 student who is a U of U student
 *
 * @authors Andrew Tolton & Tyler Wilcox
 * @version Jan 20th, 2023
 *
 */

public class CS2420Student extends UofUStudent {

    private EmailAddress contactInfo;
    private ArrayList<Double> hwScores;
    private ArrayList<Double> testScores;
    private ArrayList<Double> labScores;
    private ArrayList<Double> quizScores;

    /**
     * Creates a CS 2420 student from the given first name, last name, uNID and contact info
     *
     * @param firstName
     * @param lastName
     * @param uNID - U of U ID
     * @param contactInfo - EmailAddress for contacting the student
     */
    public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo)
    {
        super(firstName, lastName, uNID);
        this.contactInfo = contactInfo;

        this.hwScores = new ArrayList<Double>();
        this.testScores = new ArrayList<Double>();
        this.labScores = new ArrayList<Double>();
        this.quizScores = new ArrayList<Double>();
    }

    /**
     * Gets student contact info
     * @return EmailAddress contactInfo
     */
    public EmailAddress getContactInfo()
    {
        return this.contactInfo;
    }

    /**
     * Adds a score to the specified score list using one of:
     *      -"assignment"
     *      -"exam"
     *      -"lab"
     *      -"quiz"
     *
     * @param score - double representing points scored
     * @param category - String specifying list to be appended to
     */
    public void addScore(double score, String category)
    {
        switch(category)
        {
            case "assignment":
                hwScores.add(score);
                break;
            case "exam":
                testScores.add(score);
                break;
            case "lab":
                labScores.add(score);
                break;
            case "quiz":
                quizScores.add(score);
                break;
            default:
                return;
        }
    }
    
    /**
     * Computes the final grade (as a percent, from 0-100) for this student based on their scores,
     *  using the following weights:
     * 	-Assignments: 40%
     *  -Exams: 40%
     *  -Labs: 10%
     *  -Quizzes: 10%
     *  
     *  If the student's average exam grade is below 65%, their final course score
     *  is their exam grade average.
     * @return double with the student's final score, as a percent
     */
    public double computeFinalScore() {
    	// Calculate the percent score for each score category by dividing the awarded score by the 
    	// Total possible score
    	if (testScores.size() == 0) return 0.0;
    	if (hwScores.size() == 0) return 0.0;
    	if (labScores.size() == 0) return 0.0;
    	if (quizScores.size() == 0) return 0.0;
    	
    	double testPossible = 100*testScores.size();
    	double testTotal = 0;
    	for (Double score : testScores) {
    		testTotal += score;
    	}
    	testTotal = testTotal/testPossible*100;
    	
    	if (testTotal < 65) {
    		return testTotal;
    	}
    	
    	// If exam score is not below 65%, calculate the weight of all categories
    	
    	double hwPossible = 100*hwScores.size();
    	double hwTotal = 0;
    	for (Double score : hwScores) {
    		hwTotal += score;
    	}
    	hwTotal = hwTotal/hwPossible*100;
    	
    	double quizPossible = 100*quizScores.size();
    	double quizTotal = 0;
    	for (Double score : quizScores) {
    		quizTotal += score;
    	}
    	quizTotal = quizTotal/quizPossible*100;
    	
    	double labPossible = 100*labScores.size();
    	double labTotal = 0;
    	for (Double score : labScores) {
    		labTotal += score;
    	}
    	labTotal = labTotal/labPossible*100;
    	
    	return testTotal*0.4 + hwTotal*0.4 + labTotal*0.1 + quizTotal*0.1;
    }
    
    /**
     * Gets the students final letter grade based on their final score, with the following scale:
     * (Inclusive at the bottom)
     * 
     * A 	93-100
     * A-	90-93
     * B+	87-90
     * B	83-87
     * B-	80-83
     * C+	77-80
     * C	73-77
     * C-	70-73
     * D+	67-70
     * D	63-67
     * D-	60-63
     * E	<60
     * 
     * @return String with the student's letter grade
     */
    public String computeFinalGrade() {
    	if (testScores.size() == 0) return "N/A";
    	if (hwScores.size() == 0) return "N/A";
    	if (labScores.size() == 0) return "N/A";
    	if (quizScores.size() == 0) return "N/A";
    	
    	double score = computeFinalScore();
    	
    	if (score >= 93) return "A";
    	if (score >= 90) return "A-";
    	if (score >= 87) return "B+";
    	if (score >= 83) return "B";
    	if (score >= 80) return "B-";
    	if (score >= 77) return "C+";
    	if (score >= 73) return "C";
    	if (score >= 70) return "C-";
    	if (score >= 67) return "D+";
    	if (score >= 63) return "D";
    	if (score >= 60) return "D-";
    	else 			 return "E";
    	
    }
}