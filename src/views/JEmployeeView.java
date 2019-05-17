/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.EmployeeController;
import controllers.JobController;
import daos.DepartmentController;
import entities.Department;
import entities.Employee;
import entities.Job;
import icontrollers.IEmployeeController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import tools.HibernateUtil;

/**
 *
 * @author RR17
 */
public class JEmployeeView extends javax.swing.JInternalFrame {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //masuk koneksi pintu utama (masuk ke table HR)
    private Session session = null; //masuk ke koneksi dalam yg sudah ada tujuan(hanya beberapa sesi2)
    IEmployeeController iec = new EmployeeController(sessionFactory);

//    TimeZone tz = TimeZone.getDefault();
//    LocalDate localDate = LocalDate.now();
    Date date = new Date(); // this object contains the current date value 
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public JEmployeeView() {
        initComponents();
        showTableEmployee("");
        getDataComboJobID();
        getDataComboDepartmentID();
        getDataComboManagerID();
    }

    public void resetTextEmployee() {
        txtEmpId.setText("0");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtPhoneNumber.setText("");
//        txtHireDate.setText("");
//        cmbHireDate.setSelectedDate(Calendar.getInstance(tz));
        cmbHireDate.setSelectedDate(Calendar.getInstance(getLocale()));
//        System.out.println(tz);
        cmbJobId.setSelectedIndex(0);
        txtSalary.setText("");
        txtComm.setText("");
        cmbManagerId.setSelectedIndex(0);
        cmbDepartmentId.setSelectedIndex(0);
        txtEmpId.setEditable(false);
        btnInsert.setEnabled(true);
        btnUpdate.setEnabled(true);
        getDataComboJobID();
    }

    public void showTableEmployee(String s) {
        DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
        Object[] row = new Object[12];
        List<Employee> employees = new ArrayList<>();
        employees = iec.search(s);
        if (s.isEmpty()) {
            employees = iec.getAll();
        }
        for (int i = 0; i < employees.size(); i++) {
            row[0] = i + 1;
            row[1] = employees.get(i).getEmployeeId();
            row[2] = employees.get(i).getFirstName();
            row[3] = employees.get(i).getLastName();
            row[4] = employees.get(i).getEmail();
            row[5] = employees.get(i).getPhoneNumber();
            row[6] = formatter.format(employees.get(i).getHireDate());

            if (employees.get(i).getJobId() == null) {
                row[7] = "";
            } else {
                row[7] = employees.get(i).getJobId().getJobId()
                        + " - " + employees.get(i).getJobId().getJobTitle();
            }

            row[8] = employees.get(i).getSalary();

            if (employees.get(i).getCommissionPct() == null) {
                row[9] = "";
            } else {
                row[9] = employees.get(i).getCommissionPct();
            }

            if (employees.get(i).getManagerId() == null) {
                row[10] = "";
            } else {
                row[10] = employees.get(i).getManagerId().getEmployeeId()
                        + " - " + employees.get(i).getManagerId().getLastName();
            }

            if (employees.get(i).getDepartmentId() == null) {
                row[11] = "";
            } else {
                row[11] = employees.get(i).getDepartmentId().getDepartmentId()
                        + " - " + employees.get(i).getDepartmentId().getDepartmentName();
            }

            model.addRow(row);
        }
    }

    public void updateTableEmployee(String s) {
        DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
//        List<Country> countries = new ArrayList<>();
        model.setRowCount(0);
        showTableEmployee(s);
    }

    private void getDataComboJobID() {
        for (Job job : new JobController(sessionFactory).getAll()) {
            cmbJobId.addItem(job.getJobId() + " - " + job.getJobTitle());
        }
    }

    private void getDataComboDepartmentID() {
        for (Department department : new DepartmentController(sessionFactory).getAll()) {
            cmbDepartmentId.addItem(department.getDepartmentId() + " - " + department.getDepartmentName());
        }
    }

    private void getDataComboManagerID() {
        for (Employee employee : new EmployeeController(sessionFactory).getAll()) {
            cmbManagerId.addItem(employee.getEmployeeId() + " - " + employee.getLastName());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCountry = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableEmployee = new javax.swing.JTable();
        txtSearchEmp = new javax.swing.JTextField();
        txtEmpId = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        cmbJobId = new javax.swing.JComboBox<>();
        txtSalary = new javax.swing.JTextField();
        txtComm = new javax.swing.JTextField();
        cmbManagerId = new javax.swing.JComboBox<>();
        cmbDepartmentId = new javax.swing.JComboBox<>();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        cmbHireDate = new datechooser.beans.DateChooserCombo();

        jButton4.setText("Reset");

        jLabel13.setText("Search");

        tableCountry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Country_ID", "Country_Name", "Region_ID"
            }
        ));
        tableCountry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCountryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCountry);

        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Employee Info");

        jLabel2.setText("Employee ID");

        jLabel3.setText("First Name");

        jLabel4.setText("Last Name");

        jLabel5.setText("Email");

        jLabel6.setText("Phone Number");

        jLabel7.setText("Hire Date");

        jLabel8.setText("Job Id");

        jLabel9.setText("Salary");

        jLabel10.setText("Commision PCT");

        jLabel11.setText("Manager ID");

        jLabel12.setText("Department ID");

        jLabel14.setText("Search");

        tableEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "EmployeeID", "FirstName", "LastName", "Email", "PhoneNumber", "HireDate", "JobId", "Salary", "Commision ", "ManagerID", "DepartmentID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEmployeeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableEmployee);

        txtSearchEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchEmpKeyTyped(evt);
            }
        });

        txtEmpId.setText("0");

        cmbJobId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JobID" }));

        cmbManagerId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ManagerID" }));

        cmbDepartmentId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DepartmentID" }));

        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        cmbHireDate.setFormat(0);
        cmbHireDate.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(txtSearchEmp))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmpId)
                                    .addComponent(txtFirstName)
                                    .addComponent(txtLastName)
                                    .addComponent(txtEmail)
                                    .addComponent(txtPhoneNumber)
                                    .addComponent(cmbHireDate, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(66, 66, 66)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel11)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel12))))
                                        .addGap(0, 9, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnReset)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnInsert)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnUpdate)
                                        .addGap(5, 5, 5)
                                        .addComponent(btnDelete)
                                        .addGap(0, 28, Short.MAX_VALUE))
                                    .addComponent(txtComm, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSalary, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbJobId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbDepartmentId, 0, 0, Short.MAX_VALUE)
                                    .addComponent(cmbManagerId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(cmbJobId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtComm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(cmbManagerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbDepartmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnDelete)
                                    .addComponent(btnInsert)
                                    .addComponent(btnReset)
                                    .addComponent(btnUpdate)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmpId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(cmbHireDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtSearchEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:

        String jbid = cmbJobId.getSelectedItem().toString();
        String dprtmnid = cmbDepartmentId.getSelectedItem().toString();
        String mngrid = cmbManagerId.getSelectedItem().toString();

        jbid = jbid.substring(0, jbid.indexOf(" - "));
        dprtmnid = dprtmnid.substring(0, dprtmnid.indexOf(" - "));
        mngrid = mngrid.substring(0, mngrid.indexOf(" - "));

        date = cmbHireDate.getSelectedDate().getTime();
        String hiredate = formatter.format(date);

//        System.out.println(txtEmpId.getText());
//        System.out.println(txtFirstName.getText());
//        System.out.println(txtLastName.getText());
//        System.out.println(txtEmail.getText());
//        System.out.println(txtPhoneNumber.getText());
//        System.out.println(hiredate);
//        System.out.println(jbid);
//        System.out.println(txtSalary.getText());
//        System.out.println(txtComm.getText());
//        System.out.println(mngrid);
//        System.out.println(dprtmnid);
        //        System.out.println(employeeController.insert("0", "Rere", "Uio", "WWindi", "515123890", "07/08/2019", "12008", "0.23", "110", "101", "AC_ACCOUNT"));
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin untuk melakukan insert?", "Confirm Insert", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, iec.insert("0", txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtPhoneNumber.getText(), hiredate, txtSalary.getText(), txtComm.getText(), dprtmnid, mngrid, jbid));
            updateTableEmployee("");
            resetTextEmployee();
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String jbid = cmbJobId.getSelectedItem().toString();
        String dprtmnid = cmbDepartmentId.getSelectedItem().toString();
        String mngrid = cmbManagerId.getSelectedItem().toString();

        jbid = jbid.substring(0, jbid.indexOf(" - "));
        dprtmnid = dprtmnid.substring(0, dprtmnid.indexOf(" - "));
        mngrid = mngrid.substring(0, mngrid.indexOf(" - "));

        date = cmbHireDate.getSelectedDate().getTime();
        String hiredate = formatter.format(date);

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin untuk melakukan update?", "Confirm Update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, iec.update(txtEmpId.getText(), txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtPhoneNumber.getText(), hiredate, txtSalary.getText(), txtComm.getText(), dprtmnid, mngrid, jbid));
            updateTableEmployee("");
            resetTextEmployee();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tableCountryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCountryMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableCountryMouseClicked

//    private String modifyDateLayout(String inputDate) throws ParseException{
//        Date date = new SimpleDateFormat("EEEE, MMMM dd, YYYY").parse(inputDate);
//        return new SimpleDateFormat("EEEE, MMMM dd, YYYY").format(date);
//    }
    
    private void tableEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEmployeeMouseClicked
            // TODO add your handling code here:
            DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
            int SelectedRowIndex = tableEmployee.getSelectedRow();
//        SimpleDateFormat date = new SimpleDateFormat("MM/DD/YYYY");

            txtEmpId.setEditable(false);
            btnInsert.setEnabled(false);

            txtEmpId.setText(model.getValueAt(SelectedRowIndex, 1).toString());
            txtFirstName.setText(model.getValueAt(SelectedRowIndex, 2).toString());
            txtLastName.setText(model.getValueAt(SelectedRowIndex, 3).toString());
            txtEmail.setText(model.getValueAt(SelectedRowIndex, 4).toString());
            txtPhoneNumber.setText(model.getValueAt(SelectedRowIndex, 5).toString());
            
            //proses parsing date dari klik row pada tabel ke cmbHireDate
            String s = model.getValueAt(SelectedRowIndex, 6).toString();
            Date ds = new Date(s);
//            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MM dd, YYYY");
//            String ns = sdf.format(ds);
//            SimpleDateFormat sdp = new SimpleDateFormat("EEEE, MM dd, YYYY");
//            Date dns = sdp.parse(ns);
            Calendar c = Calendar.getInstance();
            c.setTime(ds);
            cmbHireDate.setSelectedDate(c);
            
//            txtHireDate.setText(ds.toString());
            cmbJobId.setSelectedItem(model.getValueAt(SelectedRowIndex, 7).toString());
            txtSalary.setText(model.getValueAt(SelectedRowIndex, 8).toString());
            txtComm.setText(model.getValueAt(SelectedRowIndex, 9).toString());
            cmbManagerId.setSelectedItem(model.getValueAt(SelectedRowIndex, 10).toString());
            cmbDepartmentId.setSelectedItem(model.getValueAt(SelectedRowIndex, 11).toString());
    }//GEN-LAST:event_tableEmployeeMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin untuk melakukan delete?", "Confirm Update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, iec.delete(txtEmpId.getText()));
            updateTableEmployee("");
            resetTextEmployee();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        resetTextEmployee();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtSearchEmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchEmpKeyTyped
        // TODO add your handling code here:
        updateTableEmployee(txtSearchEmp.getText());
    }//GEN-LAST:event_txtSearchEmpKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbDepartmentId;
    private datechooser.beans.DateChooserCombo cmbHireDate;
    private javax.swing.JComboBox<String> cmbJobId;
    private javax.swing.JComboBox<String> cmbManagerId;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableCountry;
    private javax.swing.JTable tableEmployee;
    private javax.swing.JTextField txtComm;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmpId;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchEmp;
    // End of variables declaration//GEN-END:variables

}
