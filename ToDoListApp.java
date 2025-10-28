package Task6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoListApp extends JFrame {
    private final DefaultListModel<String> taskListModel;
    private final JList<String> taskList;
    private final JTextField taskInput;

    public ToDoListApp() {
        // Frame title
        setTitle("To-Do List App");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Create components
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskInput = new JTextField();
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton clearButton = new JButton("Clear All");

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add scroll pane for list
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // Add components to main panel
        panel.add(new JLabel("Enter Task:"), BorderLayout.NORTH);
        panel.add(taskInput, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Main layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskInput.getText().trim();
                if (!task.isEmpty()) {
                    taskListModel.addElement(task);
                    taskInput.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a task!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a task to delete!");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!taskListModel.isEmpty()) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Clear all tasks?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        taskListModel.clear();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoListApp().setVisible(true);
        });
    }
}
