import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GPA_Calculator extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel unweightedPanel;
    private JTextField gradeField, classNameField, creditField,weightingField;
    private JLabel gpaLabel, classLabel, gradeLabel, creditLabel, weightingLabel;
    private JList<String> classList;
    private DefaultListModel<String> classListModel;
    private List<String> classGradeList = new ArrayList<>();


    public GPA_Calculator() {
        setTitle("GPA Calculator");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        unweightedPanel = new JPanel();
        unweightedPanel.setLayout(new FlowLayout());
        tabbedPane.addTab("unweighted", unweightedPanel);
        unweightedPanel.setBackground(Color.white);


        // class input area
        classLabel = new JLabel("Class Name: ");
        classNameField = new JTextField(10);
        unweightedPanel.add(classLabel);
        unweightedPanel.add(classNameField);

        // grade input area
        gradeLabel = new JLabel("Grade: ");
        gradeField = new JTextField(10);
        unweightedPanel.add(gradeLabel);
        unweightedPanel.add(gradeField);



        // credit input area
        creditLabel = new JLabel("Credit: ");
        creditField = new JTextField(10);
        unweightedPanel.add(creditLabel);
        unweightedPanel.add(creditField);

        // add button
        JButton addButton = new JButton("Add Class");
        addButton.addActionListener(new AddClassListener());
        unweightedPanel.add(addButton);

        // list of classes
        classListModel = new DefaultListModel<>();
        classList = new JList<>(classListModel);
        classList.setPreferredSize(new Dimension(250, 100));
        classList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int index = classList.getSelectedIndex();
                    if (index != -1) {
                        JOptionPane.showMessageDialog(GPA_Calculator.this, classGradeList.get(index));
                    }
                }
            }
        });
        unweightedPanel.add(classList);

        // calculate button
        JButton calculateButton = new JButton("Calculate GPA");
        calculateButton.addActionListener(new CalculateListener());
        unweightedPanel.add(calculateButton);

        // gpa display
        gpaLabel = new JLabel("GPA: ");
        unweightedPanel.add(gpaLabel);

    }

    private class AddClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String className = classNameField.getText();
            String grade = gradeField.getText();
            String credit = creditField.getText();
            classListModel.addElement(className + "     " + grade + "       " + credit);
            classGradeList.add(className + ": " + grade + ": " + credit);
        }
    }

    private class CalculateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String grades = gradeField.getText();
            String credits = creditField.getText();
            double gpa = calculateGPA(grades, credits);
            gpaLabel.setText("GPA: " + gpa);
        }
    }

    private double calculateGPA(String grades, String credits) {
        double totalPoints = 0;
        double totalUnits = 0;

        String[] gradeList = grades.split(",");
        String[] creditList = credits.split(",");

        for (int i = 0; i < gradeList.length; i++) {
            double units = Double.parseDouble(creditList[i]);
            double points = getPointsForGrade(gradeList[i]);
            totalPoints += (units * points);
            totalUnits += units;
        }
        return totalPoints / totalUnits;
    }


    private double getPointsForGrade(String letterGrade) {
        double points;
        switch (letterGrade) {
            case "A+": points = 4.0; break;
            case "A": points = 4.0; break;
            case "A-": points = 3.7; break;
            case "B+": points = 3.3; break;
            case "B": points = 3.0; break;
            case "B-": points = 2.7; break;
            case "C+": points = 2.3; break;
            case "C": points = 2.0; break;
            case "C-": points = 1.7; break;
            case "D+": points = 1.3; break;
            case "D": points = 1.0; break;
            case "F": points = 0.0; break;
            default: points = 0.0;
        }
        return points;
    }

    public static void main(String[] args) {
        GPA_Calculator gpaCalculator = new GPA_Calculator();
        gpaCalculator.setVisible(true);
    }

//    private class DeleteClassListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            int selectedIndex = classList.getSelectedIndex();
//
//            if (selectedIndex != -1) {
//                classListModel.remove(selectedIndex);
//            }
//        }
//    }



}