import java.util.Scanner;

class Question {
    private String question;
    String[] options;
    private int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }

    public void display() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}

class Quiz {
    private Question[] questions;
    private int score;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println("Time for the next question!");
            question.display();

            System.out.print("Select your answer (1-" + question.options.length + "): ");
            int selectedOption = scanner.nextInt();

            if (selectedOption >= 1 && selectedOption <= question.options.length) {
                if (question.isCorrect(selectedOption)) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect.\n");
                }
            } else {
                System.out.println("Invalid option. Skipping this question.\n");
            }
        }

        System.out.println("Quiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.length);
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        Question[] questions = {
                new Question("What is the capital of France?", new String[]{"London", "Paris", "Berlin", "Rome"}, 2),
                new Question("Which planet is known as the Red Planet?", new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 1),
                new Question("What is the chemical symbol for gold?", new String[]{"Go", "Gd", "Au", "Ag"}, 3)
                // Add more questions here
        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
