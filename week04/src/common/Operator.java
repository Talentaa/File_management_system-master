package common;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Operator extends User {

    public Operator(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public String getTitle() {
        return "档案录入人员界面";
    }




}

