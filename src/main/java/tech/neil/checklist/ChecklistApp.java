package tech.neil.checklist;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ChecklistApp {
    private JFrame frame;
    private JTextField taskInputField;
    private JPanel taskPanel;
    private ArrayList<JCheckBox> taskList;

    public ChecklistApp() {
        taskList = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Daily Checklist");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel headerLabel = new JLabel("Daily Checklist", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        taskInputField = new JTextField();
        taskInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        taskInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addTask();
                }
            }
        });
        inputPanel.add(taskInputField, BorderLayout.CENTER);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addTaskButton.addActionListener(new AddTaskListener());
        inputPanel.add(addTaskButton, BorderLayout.EAST);

        headerPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(headerPanel, BorderLayout.NORTH);

        // Task Panel
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        JButton clearButton = new JButton("Clear Completed");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 14));
        clearButton.addActionListener(new ClearCompletedListener());
        footerPanel.add(clearButton);

        frame.add(footerPanel, BorderLayout.SOUTH);
    }

    private void addTask() {
        String taskText = taskInputField.getText().trim();
        if (!taskText.isEmpty()) {
            JCheckBox newTask = new JCheckBox(taskText);
            newTask.setFont(new Font("Arial", Font.PLAIN, 14));
            taskList.add(newTask);
            taskPanel.add(newTask);
            taskPanel.revalidate();
            taskPanel.repaint();
            taskInputField.setText("");
        }
    }

    private class AddTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addTask();
        }
    }

    private class ClearCompletedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            taskList.removeIf(JCheckBox::isSelected);
            taskPanel.removeAll();
            for (JCheckBox task : taskList) {
                taskPanel.add(task);
            }
            taskPanel.revalidate();
            taskPanel.repaint();
        }
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChecklistApp app = new ChecklistApp();
            app.display();
        });
    }
}
