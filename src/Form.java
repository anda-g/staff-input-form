import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.ui.*;
import model.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Form extends JFrame {
    private List<Staff> staffList = new ArrayList<>();
    private final JLabel heading = new JLabel("Staff Details");
    private InputField idInput = new InputField("Staff ID");
    private InputField nameInput = new InputField("Full name");
    private InputField genderInput = new InputField("Gender", new String[]{"Male", "Female"});
    private InputField contactInput = new InputField("Contact");
    private InputField positionInput = new InputField("Position");
    private InputField addressInput = new InputField("Address");

    private String[] columns = new String[]{"ID", "Full name", "Gender", "Contact", "Position", "Address"};
    private JTable table;

    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete");

    public Form(){
        initComponent();
    }
    public void initComponent(){
        this.setTitle("Staff List");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JMenuBar actionBar = new JMenuBar();
        JMenu themeSwitch = new JMenu("");
        actionBar.add(themeSwitch);

        this.setJMenuBar(actionBar);

//        Init Panel
        JPanel rightContent = new JPanel();
        rightContent.setLayout(new BorderLayout(20,20));
        JPanel leftContent = new JPanel();
        leftContent.setPreferredSize(new Dimension(0,100));

        JPanel headingPanel = new JPanel(null);
        headingPanel.setPreferredSize(new Dimension(headingPanel.getWidth(), 100));
        heading.setBounds(20,20,300,20);
        heading.setFont(new MyFont(Font.BOLD, 24));

        JLabel des = new JLabel("<html><div style='width:240px;'>Staff Details shows key information about each team member, including their roles and contact details.</div></html>");
        des.setBounds(20,40,300,50);
        des.setFont(new MyFont(des.getFont().getStyle(), des.getFont().getSize()));
        des.setForeground(new Color(0x878787));

        headingPanel.add(heading);
        headingPanel.add(des);

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        mainPanel.add(idInput);
        mainPanel.add(nameInput);
        mainPanel.add(genderInput);
        mainPanel.add(contactInput);
        mainPanel.add(positionInput);
        mainPanel.add(addressInput);

        JPanel actionPanel = new JPanel(new GridLayout(1,3,10,0));
        actionPanel.setPreferredSize(new Dimension(300,35));
        addButton.setFont(new MyFont(Font.BOLD, addButton.getFont().getSize()));
        deleteButton.setFont(new MyFont(Font.BOLD, addButton.getFont().getSize()));
        deleteButton.setEnabled(false);
        deleteRow();
        addButtonAction();
        actionPanel.add(addButton);
        actionPanel.add(deleteButton);

        JSeparator divider = new JSeparator();
        divider.setPreferredSize(new Dimension(300,1));
//        divider.setBorder(new FlatBorder());

        mainPanel.add(divider);
        mainPanel.add(actionPanel);

        leftContent.setLayout(new BorderLayout());
        leftContent.add(headingPanel, BorderLayout.NORTH);
        leftContent.add(mainPanel, BorderLayout.CENTER);

//        Right Content
        table = new JTable(new DefaultTableModel(columns, 0)){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(0xEEEEEE));
        table.setRowHeight(28);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);
        table.setFont(new MyFont(Font.PLAIN, table.getFont().getSize()));
        table.setSelectionBackground(new Color(0xEEEEEE));
        table.setSelectionForeground(Color.BLACK);

        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        tableItemOnClick();

        JTableHeader header = table.getTableHeader();
        header.setFont(new MyFont(Font.BOLD, 12));
        header.setPreferredSize(new Dimension(header.getWidth(), 28));

        JScrollPane scrollPane = new JScrollPane(table);
        rightContent.add(scrollPane);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftContent, rightContent);
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(0.3);
        splitPane.setEnabled(false);
        splitPane.setDividerSize(0);
        splitPane.setBorder(null);
        this.add(splitPane, BorderLayout.CENTER);

    }

    public void addButtonAction(){
        addButton.addActionListener(e -> {
            String id = idInput.getValue().getText();
            String name = nameInput.getValue().getText();
            String gender = Objects.requireNonNull(genderInput.getComboBox().getSelectedItem()).toString();
            String contact = contactInput.getValue().getText();
            String position = positionInput.getValue().getText();
            String address = addressInput.getValue().getText();
            if(
                    id.isEmpty()
                    || name.isEmpty()
                    || gender.isEmpty()
                    || contact.isEmpty()
                    || position.isEmpty()
                    || address.isEmpty()
            ){
                JOptionPane.showInternalMessageDialog(null, "Please input all fields","Warning",JOptionPane.WARNING_MESSAGE);
            }else {
                Staff staff = new Staff(id, name, gender, contact, position,address);
                appendRow(staff);
                clearValue();
            }
        });
    }

    public void appendRow(Staff staff){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        String[] data = new String[]{
                staff.getId(),
                staff.getName(),
                staff.getGender(),
                staff.getContact(),
                staff.getPosition(),
                staff.getAddress()
        };
        tableModel.addRow(data);
    }

    public void tableItemOnClick(){
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            deleteButton.setEnabled(row >= 0);
            String id = table.getValueAt(row, 0).toString();
            String name = table.getValueAt(row, 1).toString();
            String gender = table.getValueAt(row, 2).toString();
            String contact = table.getValueAt(row, 3).toString();
            String position = table.getValueAt(row, 4).toString();
            String address = table.getValueAt(row, 5).toString();
            Staff staff = new Staff(id, name, gender, contact, position, address);
            displayOnInput(staff);
        });
    }

    public void displayOnInput(Staff staff){
        idInput.getValue().setText(staff.getId());
        nameInput.getValue().setText(staff.getName());
        genderInput.getComboBox().setSelectedItem(staff.getGender());
        contactInput.getValue().setText(staff.getContact());
        positionInput.getValue().setText(staff.getPosition());
        addressInput.getValue().setText(staff.getAddress());
    }

    public void clearValue(){
        idInput.getValue().setText(null);
        nameInput.getValue().setText(null);
        genderInput.getComboBox().setSelectedItem("Male");
        contactInput.getValue().setText(null);
        positionInput.getValue().setText(null);
        addressInput.getValue().setText(null);
    }

    public void deleteRow(){
        for(ActionListener al : deleteButton.getActionListeners()){
            deleteButton.removeActionListener(al);
        }
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this,
                        "Please select a row to delete",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this staff member?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                try{
                    model.removeRow(selectedRow);

                } catch (Exception ignore) {
                }finally {
                    clearValue();
                }
            }
        });
    }


}


class InputField extends JPanel{
    private JLabel label;
    private JTextField value;
    private JComboBox<String> comboBox;

    public InputField(String message){
        this.setLayout(new BorderLayout(0,5));
        this.setPreferredSize(new Dimension(300,56));

        label = new JLabel(message);
        label.setFont(new MyFont(Font.BOLD, label.getFont().getSize()));



        value = new JTextField();
        value.setBorder(new FlatRoundBorder());
        value.setFont(new MyFont(Font.PLAIN, 14));



//        label.setHorizontalAlignment(JLabel.LEFT);
        this.add(label, BorderLayout.NORTH);
        this.add(value, BorderLayout.CENTER);
    }
    public InputField(String message, String[] item){
        this.setLayout(new BorderLayout(0,5));
        this.setPreferredSize(new Dimension(300,56));
        label = new JLabel(message);
        label.setFont(new MyFont(Font.BOLD, label.getFont().getSize()));



        comboBox = new JComboBox<>(item);
        comboBox.setBorder(new FlatRoundBorder());
        comboBox.setFont(new MyFont(Font.PLAIN, 14));


//        label.setHorizontalAlignment(JLabel.LEFT);
        this.add(label, BorderLayout.NORTH);
        this.add(comboBox, BorderLayout.CENTER);
    }

    public JTextField getValue(){
        return value;
    }
    public JComboBox<String> getComboBox(){
        return comboBox;
    }
}



class MyFont extends Font{

    public MyFont( int style, int size) {
        super("Cabin", style, size);
    }
}