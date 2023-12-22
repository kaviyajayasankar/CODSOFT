import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator extends JFrame {
    private JTextField[] subject;
    private JTextField totalMarks, avgPercentage, grade;

    public GradeCalculator() {
        setTitle("Grade Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        addComponentsToFrame();
    }

    private void initializeComponents() {
        int num = 5; 
        subject = new JTextField[num];

        for (int i = 0; i < num; i++) {
            subject[i] = new JTextField(5);
        }

        totalMarks = new JTextField(10);
        totalMarks.setEditable(false);

        avgPercentage = new JTextField(10);
        avgPercentage.setEditable(false);

        grade = new JTextField(5);
        grade.setEditable(false);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Results();
            }
        });
    }

    private void addComponentsToFrame() {
        setLayout(new GridLayout(9, 5, 10, 1)); 

        for (int i = 0; i < subject.length; i++) {
            add(new JLabel("Subject " + (i + 1) + ":"));
            add(subject[i]);
        }

        add(new JLabel("Total Marks:"));
        add(totalMarks);

        add(new JLabel("Average Percentage:"));
        add(avgPercentage);

        add(new JLabel("Grade:"));
        add(grade);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Results();
            }
        });

        add(calculateButton);
    }

    private void Results() {
        int total= 0;

        for (int i = 0; i < subject.length; i++) {
            try {
                int marks = Integer.parseInt(subject[i].getText());
                total += marks;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.");
                return;
            }
        }

        double averagePercentage = (double) total / subject.length;

        totalMarks.setText(String.valueOf(total));
        avgPercentage.setText(String.format("%.2f", averagePercentage));

        // You can customize the grading logic based on your requirements
        String gradename;
        if (averagePercentage >= 90 && averagePercentage<=100) {
            gradename = "A";
        } else if (averagePercentage >= 80 && averagePercentage<=90) {
           gradename = "B";
        } else if (averagePercentage >= 70 && averagePercentage<=80) {
            gradename = "C";
        } else if (averagePercentage >= 60 && averagePercentage<=70) {
            gradename = "D";
        } else if (averagePercentage >= 50 && averagePercentage<=60) {
            gradename = "E";
        } else {
            gradename = "F";
        }

        grade.setText(gradename);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculator().setVisible(true);
            }
        });
    }
}