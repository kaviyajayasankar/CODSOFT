import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionName;
    private List<String> options;
    private String correctAns;

    public Question(String questionName, List<String> options, String correctAns) {
        this.questionName = questionName;
        this.options = options;
        this.correctAns = correctAns;
    }

    public String getQuestionName() {
        return questionName;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAns() {
        return correctAns;
    }
}

public class Quiz {
    private List<Question> questions;
    private int currentQuesIndex;
    private int score;
    private Timer timer;
    private static final int TIME_LIMIT_SECONDS = 10;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.currentQuesIndex = 0;
        this.score = 0;
        this.timer = new Timer();
        initialize();
    }

    private void initialize() {
        
        Question question1 = new Question("Which is the most sensitive organ in our body?",
                Arrays.asList("Ears", "Skin", "Hand", "Toe"),
                "Skin");
        questions.add(question1);
        Question question2 = new Question("Brain of computer is?",
            Arrays.asList("CPU", "Mouse", "Keyboard","RAM"),
            "CPU");
        questions.add(question2);
        Question question3 = new Question("Which is the heaviest metal ?",
            Arrays.asList("Aluminium", "Gold", "Osmium6", "Copper"),
            "Osmium");
       questions.add(question3);
       Question question4 = new Question("Which is the national animal?",
            Arrays.asList("Elephant", "Tiger", "Lion", "Giraffe"),
            "Tiger");
       questions.add(question4);
       Question question5 = new Question("Which is the smallest state in India?",
            Arrays.asList("Mahashtra", "Tamilnadu", "Bihar", "Goa"),
            "Goa");
       questions.add(question5);


        
    }

    private void displayQues() {
        Question currentQues = questions.get(currentQuesIndex);
        System.out.println(currentQues.getQuestionName());

        List<String> options = currentQues.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                submitAnswer(null);
            }
        }, TIME_LIMIT_SECONDS * 1000);
    }

    private void submitAnswer(String userAnswer) {
        timer.cancel(); 

        Question currentQues = questions.get(currentQuesIndex);
        String correctAns = currentQues.getCorrectAns();

        if (userAnswer != null && userAnswer.trim().equalsIgnoreCase(correctAns.trim())) {
            System.out.println("Correct!\n");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is: " + correctAns+ "\n");
        }

        if (currentQuesIndex < questions.size() - 1) {
            currentQuesIndex++;
            displayQues();
            startTimer();
        } else {
            Result();
        }
    }

    private void Result() {
        System.out.println("Quiz completed!");
        System.out.println("Your final score: " + score + " out of " + questions.size());

       
    }

    public static void main(String[] args) {
        Quiz quizApp = new Quiz();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz App!");

        quizApp.displayQues();
        quizApp.startTimer();

        while (true) {
            System.out.print("Enter your answer (1-" + quizApp.questions.get(quizApp.currentQuesIndex).getOptions().size() + "): ");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equalsIgnoreCase("exit")) {
                System.out.println("Quiz aborted. Your final score: " + quizApp.score + " out of " + quizApp.questions.size());
                System.exit(0);
            }

            quizApp.submitAnswer(userAnswer);
        }
    }
}

