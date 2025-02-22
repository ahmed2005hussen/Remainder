package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// إدارة المستخدم وتخزين البيانات في ملف
class UserManager {
    private static final String FILE_NAME = "user_data.txt";

    public static void saveUser(String username, String password, String securityWord) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(username + "\n" + password + "\n" + securityWord);
        } catch (IOException e) {
            System.out.println("حدث خطأ أثناء الحفظ!");
        }
    }

    public static String[] loadUser() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String username = reader.readLine();
            String password = reader.readLine();
            String securityWord = reader.readLine();
            return new String[]{username, password, securityWord};
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean authenticate(String username, String password) {
        String[] userData = loadUser();
        return userData != null && userData[0].equals(username) && userData[1].equals(password);
    }

    public static boolean checkSecurityWord(String word) {
        String[] userData = loadUser();
        return userData != null && userData[2].equals(word);
    }
}

// واجهة تسجيل المستخدم لأول مرة
class FirstTimeSetup {
    FirstTimeSetup() {
        JFrame frame = new JFrame("إعداد الحساب لأول مرة");
        frame.setSize(350, 250);
        frame.setLayout(new GridLayout(4, 2));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField securityWordField = new JTextField();
        JButton saveButton = new JButton("حفظ البيانات");

        frame.add(new JLabel("أدخل اسم المستخدم:"));
        frame.add(usernameField);
        frame.add(new JLabel("أدخل كلمة المرور:"));
        frame.add(passwordField);
        frame.add(new JLabel("أدخل كلمة الأمان:"));
        frame.add(securityWordField);
        frame.add(saveButton);

        saveButton.addActionListener(e -> {
            UserManager.saveUser(usernameField.getText(), new String(passwordField.getPassword()), securityWordField.getText());
            JOptionPane.showMessageDialog(frame, "تم حفظ البيانات! يمكنك الآن تسجيل الدخول.");
            frame.dispose();
            new SecureVault();
        });

        frame.setVisible(true);
    }
}

// واجهة تسجيل الدخول
public class SecureVault {
    public static void main(String[] args) {
        if (UserManager.loadUser() == null) {
            new FirstTimeSetup();
        } else {
            new LoginGUI();
        }
    }
}

class LoginGUI {
    LoginGUI() {
        JFrame frame = new JFrame("تسجيل الدخول");
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("تسجيل الدخول");
        JButton resetButton = new JButton("إعادة تعيين");
        JButton forgetButton = new JButton("نسيت كلمة المرور");

        frame.add(new JLabel("اسم المستخدم:"));
        frame.add(usernameField);
        frame.add(new JLabel("كلمة المرور:"));
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(forgetButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (UserManager.authenticate(username, password)) {
                JOptionPane.showMessageDialog(frame, "تم تسجيل الدخول بنجاح!");
                new VaultPage(username);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "خطأ في اسم المستخدم أو كلمة المرور!");
            }
        });

        resetButton.addActionListener(e -> new ResetGUI());
        forgetButton.addActionListener(e -> new ForgetPasswordGUI());

        frame.setVisible(true);
    }
}

// صفحة الخزنة
class VaultPage {
    VaultPage(String username) {
        JFrame frame = new JFrame("الخزنة - " + username);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));

        JButton myPasswords = new JButton("كلمات المرور الخاصة بي");
        JButton myNotes = new JButton("ملاحظاتي");
        JButton myPhotos = new JButton("صوري");

        frame.add(myPasswords);
        frame.add(myNotes);
        frame.add(myPhotos);

        frame.setVisible(true);
    }
}

// إعادة تعيين كلمة المرور
class ResetGUI {
    ResetGUI() {
        JFrame frame = new JFrame("إعادة تعيين كلمة المرور");
        frame.setSize(350, 200);
        frame.setLayout(new GridLayout(3, 2));

        JTextField oldUsername = new JTextField();
        JPasswordField oldPassword = new JPasswordField();
        JTextField newPassword = new JTextField();
        JButton resetButton = new JButton("إعادة تعيين");

        frame.add(new JLabel("اسم المستخدم القديم:"));
        frame.add(oldUsername);
        frame.add(new JLabel("كلمة المرور القديمة:"));
        frame.add(oldPassword);
        frame.add(new JLabel("كلمة المرور الجديدة:"));
        frame.add(newPassword);
        frame.add(resetButton);

        resetButton.addActionListener(e -> {
            if (UserManager.authenticate(oldUsername.getText(), new String(oldPassword.getPassword()))) {
                UserManager.saveUser(oldUsername.getText(), newPassword.getText(), UserManager.loadUser()[2]);
                JOptionPane.showMessageDialog(frame, "تم تغيير كلمة المرور بنجاح!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "بيانات غير صحيحة!");
            }
        });

        frame.setVisible(true);
    }
}

// استعادة كلمة المرور
class ForgetPasswordGUI {
    ForgetPasswordGUI() {
        JFrame frame = new JFrame("نسيت كلمة المرور");
        frame.setSize(350, 200);
        frame.setLayout(new GridLayout(2, 2));

        JTextField securityWord = new JTextField();
        JTextField newPassword = new JTextField();
        JButton recoverButton = new JButton("استعادة");

        frame.add(new JLabel("كلمة الأمان:"));
        frame.add(securityWord);
        frame.add(new JLabel("كلمة المرور الجديدة:"));
        frame.add(newPassword);
        frame.add(recoverButton);

        recoverButton.addActionListener(e -> {
            if (UserManager.checkSecurityWord(securityWord.getText())) {
                String[] userData = UserManager.loadUser();
                UserManager.saveUser(userData[0], newPassword.getText(), userData[2]);
                JOptionPane.showMessageDialog(frame, "تم تغيير كلمة المرور بنجاح!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "كلمة الأمان غير صحيحة!");
            }
        });

        frame.setVisible(true);
    }
}