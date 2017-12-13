package notebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {

    private JFrame frame;
    private JButton addButton;
    private JButton updateButton;
    private JButton removeButton;
    private JTable notesTable;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private NotesTableModel ntm;

    private NotebookController controller;

    public MainFrame() {

        controller = new NotebookController();

        frame = new JFrame("Notebook");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame addFrame = new JFrame("Добавить");
                addFrame.setSize(300, 400);
                addFrame.setLocationRelativeTo(frame);
                addFrame.setLayout(new GridBagLayout());

                JLabel phone = new JLabel(ntm.getColumnName(1) + ":");
                JLabel name = new JLabel(ntm.getColumnName(2) + ":");
                JLabel email = new JLabel(ntm.getColumnName(3) + ":");
                JLabel note = new JLabel(ntm.getColumnName(4) + ":");

                final JTextField phoneTextField = new JTextField(30);
                final JTextField nameTextField = new JTextField(30);
                final JTextField emailTextField = new JTextField(30);
                final JTextArea noteTextField = new JTextArea(4, 30);

                JButton addButton = new JButton("Добавить");
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //TODO Проверка значений

                        final Contact contact = new Contact();
                        contact.setPhone(phoneTextField.getText());
                        contact.setName(nameTextField.getText());
                        contact.setEmail(emailTextField.getText());
                        contact.setNote(noteTextField.getText());

                        if (!controller.isExists(contact)) {
                            controller.addContact(contact);
                        } else {
                            final JFrame updateOrCloseFrame = new JFrame();
                            updateOrCloseFrame.setSize(300, 400);
                            updateOrCloseFrame.setLocationRelativeTo(addFrame);
                            updateOrCloseFrame.setLayout(new GridBagLayout());

                            JLabel questionLabel = new JLabel("Запись с таким номером телефона уже существует. " +
                                    "Перезаписать существующую запись?");
                            JButton updateButton = new JButton("Перезаписать");
                            updateButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    controller.updateContact(contact);
                                    ntm.updateTable();
                                    updateOrCloseFrame.setVisible(false);
                                }
                            });

                            JButton closeButton = new JButton("Отмена");
                            closeButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    updateOrCloseFrame.setVisible(false);
                                }
                            });

                            updateOrCloseFrame.add(questionLabel, new GridBagConstraints(0, 0,
                                    2, 1,
                                    1, 1,
                                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                                    new Insets(1, 5, 1, 5), 0, 0));
                            updateOrCloseFrame.add(updateButton, new GridBagConstraints(0, 1,
                                    1, 1,
                                    1, 1,
                                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                                    new Insets(1, 5, 1, 5), 0, 0));
                            updateOrCloseFrame.add(closeButton, new GridBagConstraints(1, 1,
                                    1, 1,
                                    1, 1,
                                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                                    new Insets(1, 5, 1, 5), 0, 0));

                            updateOrCloseFrame.pack();
                            updateOrCloseFrame.setVisible(true);
                        }

                        ntm.updateTable();
                        addFrame.setVisible(false);
                    }
                });

                addFrame.add(phone, new GridBagConstraints(0, 1,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 5, 1, 5), 0, 0));
                addFrame.add(name, new GridBagConstraints(0, 2,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 5, 1, 5), 0, 0));
                addFrame.add(email, new GridBagConstraints(0, 3,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 5, 1, 5), 0, 0));
                addFrame.add(note, new GridBagConstraints(0, 4,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 5, 1, 5), 0, 0));

                addFrame.add(phoneTextField, new GridBagConstraints(1, 1,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));
                addFrame.add(nameTextField, new GridBagConstraints(1, 2,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));
                addFrame.add(emailTextField, new GridBagConstraints(1, 3,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));
                addFrame.add(noteTextField, new GridBagConstraints(1, 4,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));

                addFrame.add(addButton, new GridBagConstraints(1, 5,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));

                addFrame.pack();
                addFrame.setVisible(true);
            }
        });

        updateButton = new JButton("Изменить");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame updateFrame = new JFrame("Изменить");
                updateFrame.setSize(300, 400);
                updateFrame.setLocationRelativeTo(frame);
                updateFrame.setLayout(new GridBagLayout());

                JLabel phone = new JLabel(ntm.getColumnName(1) + ":");
                JLabel name = new JLabel(ntm.getColumnName(2) + ":");
                JLabel email = new JLabel(ntm.getColumnName(3) + ":");
                JLabel note = new JLabel(ntm.getColumnName(4) + ":");

                final JTextField phoneTextField = new JTextField((String) ntm.getValueAt(notesTable.getSelectedRow(), 1),30);
                final JTextField nameTextField = new JTextField((String) ntm.getValueAt(notesTable.getSelectedRow(), 2),30);
                final JTextField emailTextField = new JTextField((String) ntm.getValueAt(notesTable.getSelectedRow(), 3),30);
                final JTextArea noteTextField = new JTextArea((String) ntm.getValueAt(notesTable.getSelectedRow(), 4),4, 30);

                JButton updateButton = new JButton("Изменить");
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //TODO Проверка значений

                        final Contact contact = new Contact();
                        contact.setPhone(phoneTextField.getText());
                        contact.setName(nameTextField.getText());
                        contact.setEmail(emailTextField.getText());
                        contact.setNote(noteTextField.getText());

                        int id = (int) ntm.getValueAt(notesTable.getSelectedRow(), 0);
                        contact.setId(id);
                        if (!controller.isExists(contact)) {
                            controller.updateContact(contact);
                        } else {
                            final JFrame errorFrame = new JFrame();
                            errorFrame.setSize(300, 400);
                            errorFrame.setLocationRelativeTo(updateFrame);
                            errorFrame.setLayout(new GridBagLayout());

                            JLabel errorLabel = new JLabel("Запись с таким номером телефона уже существует.");

                            JButton okButton = new JButton("OK");
                            okButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    errorFrame.setVisible(false);
                                }
                            });

                            errorFrame.add(errorLabel, new GridBagConstraints(0, 0,
                                    2, 1,
                                    1, 1,
                                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                                    new Insets(1, 5, 1, 5), 0, 0));
                            errorFrame.add(okButton, new GridBagConstraints(0, 1,
                                    1, 1,
                                    1, 1,
                                    GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                                    new Insets(1, 5, 1, 5), 0, 0));

                            errorFrame.pack();
                            errorFrame.setVisible(true);
                        }

                        ntm.updateTable();
                        updateFrame.setVisible(false);
                    }
                });

                updateFrame.add(phone, new GridBagConstraints(0, 1,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 5, 1, 5), 0, 0));
                updateFrame.add(name, new GridBagConstraints(0, 2,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 5, 1, 5), 0, 0));
                updateFrame.add(email, new GridBagConstraints(0, 3,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 5, 1, 5), 0, 0));
                updateFrame.add(note, new GridBagConstraints(0, 4,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 5, 1, 5), 0, 0));

                updateFrame.add(phoneTextField, new GridBagConstraints(1, 1,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));
                updateFrame.add(nameTextField, new GridBagConstraints(1, 2,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));
                updateFrame.add(emailTextField, new GridBagConstraints(1, 3,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));
                updateFrame.add(noteTextField, new GridBagConstraints(1, 4,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));

                updateFrame.add(updateButton, new GridBagConstraints(1, 5,
                        1, 1,
                        1, 1,
                        GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                        new Insets(1, 1, 1, 1), 0, 0));

                updateFrame.pack();
                updateFrame.setVisible(true);
            }
        });

        removeButton = new JButton("Удалить");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Contact contact = new Contact();
                contact.setId((int) ntm.getValueAt(notesTable.getSelectedRow(), 0));
                contact.setPhone((String) ntm.getValueAt(notesTable.getSelectedRow(), 1));
                contact.setName((String) ntm.getValueAt(notesTable.getSelectedRow(), 2));
                contact.setEmail((String) ntm.getValueAt(notesTable.getSelectedRow(), 3));
                contact.setNote((String) ntm.getValueAt(notesTable.getSelectedRow(), 4));

                controller.removeContact(contact);
                ntm.updateTable();
            }
        });

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);

        ntm = new NotesTableModel(controller);
        notesTable = new JTable(ntm);
        notesTable.getColumn("ID").setPreferredWidth(0);
        notesTable.getColumn("ID").setMinWidth(0);
        notesTable.getColumn("ID").setMaxWidth(0);

        scrollPane = new JScrollPane(notesTable);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        frame.add(scrollPane, new GridBagConstraints(0, 0,
                1, 1,
                1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        frame.add(buttonPanel, new GridBagConstraints(1, 0,
                1, 1,
                1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        frame.pack();
        frame.setVisible(true);
    }
}