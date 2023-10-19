import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reception {

    Member member = new Member();
    PT pt = new PT();
    long numberSearch;
    String fullname;
    String firstname;
    String lastname;
    static String export;
    public static String ExportString(){ return export;}

    public void Menu() throws IOException{

        while(true) {
            int menuselection1 = 0;
            int menuselection2 = 0;
            String input = JOptionPane.showInputDialog("Gör ett val:\n" +
                    "1: Sök medlem\n" +
                    "2: Avsluta");
            if ((input != null) && (input.length() > 0)) {


                try {
                    menuselection1 = Integer.parseInt(input);
                    if (menuselection1 == 1) {
                        String input2 = JOptionPane.showInputDialog("Vill du söka efter\n" +
                                "1: Personnummer\n" +
                                "2: Namn");
                        try {
                            menuselection2 = Integer.parseInt(input2);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Du måste göra ett giltigt val.");
                        }
                        if (menuselection2 < 1 || menuselection2 > 2) {
                            JOptionPane.showMessageDialog(null, "Du måste göra ett giltigt val.");
                        }
                        if (menuselection2 == 1) {
                            String input3 = JOptionPane.showInputDialog("Ange personnummer:");
                            try {
                                numberSearch = Long.parseLong(input3);
                                ComparePersonalNumber();
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Du måste ange ett giltigt tal.");
                            }

                        }
                        if (menuselection2 == 2) {
                            fullname = JOptionPane.showInputDialog("Ange ett namn att söka efter:");
                            if (fullname.length() < 1) {
                                JOptionPane.showMessageDialog(null, "Du måste ange ett namn.");
                            } else {
                                CompareName();
                            }
                        }
                    }
                    if (menuselection1 == 2) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Du måste ange ett giltigt tal.");
                }
                if (menuselection1 < 1 || menuselection1 > 2) {
                    JOptionPane.showMessageDialog(null, "Du måste ange ett giltigt tal.");
                }

            }
            else{
                break;
            }
        }

    }

    public void ComparePersonalNumber() throws IOException{
        boolean exist = false;
        int listSize = member.memberList.size();
        for (int i = 0; i < listSize; i++) {
            long comparison = member.memberList.get(i).getPersonalnumber();
            if(comparison == numberSearch){
                exist = true;
                if(member.memberList.get(i).isActivemember() == true){
                    JOptionPane.showMessageDialog(null, member.memberList.get(i).getPersonalnumber() +
                            " " + member.memberList.get(i).getFirstname() +
                            " " + member.memberList.get(i).getLastname() +
                            " är en betalande medlem.");
                    String workout = JOptionPane.showInputDialog("Vill " +
                            member.memberList.get(i).getFirstname() +
                            " " + member.memberList.get(i).getLastname() + " träna?\n" +
                            "1: Ja\n" +
                            "2: Nej");
                    try {
                        int workoutSelection = Integer.parseInt(workout);
                        if (workoutSelection == 1) {
                            export = "Personnummer: " + member.memberList.get(i).getPersonalnumber() + " Namn: " +
                                    member.memberList.get(i).getFirstname() + " " + member.memberList.get(i).getLastname();
                            pt.GymAttendee();
                            JOptionPane.showMessageDialog(null, member.memberList.get(i).getFirstname() +
                                    " " + member.memberList.get(i).getLastname() + " är nu registrerad som besökare på gymmet.");
                        }
                        if (workoutSelection == 2) {
                            break;
                        }
                        if (workoutSelection < 1 || workoutSelection > 2){
                            JOptionPane.showMessageDialog(null, "Du måste göra ett giltigt val.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Du måste ange ett giltigt val.");
                    }
                }
                if(member.memberList.get(i).isActivemember() == false){
                    JOptionPane.showMessageDialog(null, member.memberList.get(i).getPersonalnumber() +
                            " " + member.memberList.get(i).getFirstname() +
                            " " + member.memberList.get(i).getLastname() +
                            "'s medlemskap har löpt ut.");
                }
            }
        }
        if (exist == false){
            JOptionPane.showMessageDialog(null, "Användare finns ej.");
        }
    }

    public void CompareName() throws IOException{
        boolean exist = false;
        int listSize = member.memberList.size();
        if(fullname.contains(" ")){
            int temp = fullname.indexOf(" ");
            int temp2 = fullname.length();
            char c;
            String tempString = "";
            String tempString2 = "";
            for (int i = 0; i < temp; i++) {
                c = fullname.charAt(i);
                tempString = tempString + c;
            }
            firstname = tempString;
            for (int i = temp+1; i < temp2; i++) {
                c = fullname.charAt(i);
                tempString2 = tempString2 + c;
            }
            lastname = tempString2;
            for (int i = 0; i < listSize; i++) {
                String compareFirstname = member.memberList.get(i).getFirstname();
                if(compareFirstname.toUpperCase().equals(firstname.toUpperCase())){
                    String compareLastname = member.memberList.get(i).getLastname();
                    if (compareLastname.toUpperCase().equals(lastname.toUpperCase())) {
                        exist = true;
                        if (member.memberList.get(i).isActivemember() == true) {
                            JOptionPane.showMessageDialog(null, member.memberList.get(i).getPersonalnumber() +
                                    " " + member.memberList.get(i).getFirstname() +
                                    " " + member.memberList.get(i).getLastname() +
                                    " är en betalande medlem.");
                            String workout = JOptionPane.showInputDialog("Vill " +
                                    member.memberList.get(i).getFirstname() +
                                    " " + member.memberList.get(i).getLastname() + " träna?\n" +
                                    "1: Ja\n" +
                                    "2: Nej");
                            try {
                                int workoutSelection = Integer.parseInt(workout);
                                if (workoutSelection == 1) {
                                    export = "Personnummer: " + member.memberList.get(i).getPersonalnumber() + " Namn: " +
                                            member.memberList.get(i).getFirstname() + " " + member.memberList.get(i).getLastname();
                                    pt.GymAttendee();
                                    JOptionPane.showMessageDialog(null, member.memberList.get(i).getFirstname() +
                                            " " + member.memberList.get(i).getLastname() + " är nu registrerad som besökare på gymmet.");
                                }
                                if (workoutSelection == 2) {
                                    break;
                                }
                                if (workoutSelection < 1 || workoutSelection > 2){
                                    JOptionPane.showMessageDialog(null, "Du måste göra ett giltigt val.");
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Du måste göra ett giltigt val.");
                            }
                        }
                            if (member.memberList.get(i).isActivemember() == false){
                                JOptionPane.showMessageDialog(null, member.memberList.get(i).getPersonalnumber() +
                                        " " + member.memberList.get(i).getFirstname() +
                                        " " + member.memberList.get(i).getLastname() +
                                        "'s medlemskap har löpt ut.");
                        }
                    }
                }
            }
        }



        for (int i = 0; i < listSize; i++) {

            String comparison = member.memberList.get(i).getFirstname();
            if(comparison.toUpperCase().equals(fullname.toUpperCase())){
                exist = true;
                if(member.memberList.get(i).isActivemember() == true) {
                    JOptionPane.showMessageDialog(null, member.memberList.get(i).getPersonalnumber() +
                            " " + member.memberList.get(i).getFirstname() +
                            " " + member.memberList.get(i).getLastname() +
                            " är en betalande medlem.");
                    String workout = JOptionPane.showInputDialog("Vill " +
                            member.memberList.get(i).getFirstname() +
                            " " + member.memberList.get(i).getLastname() + " träna?\n" +
                            "1: Ja\n" +
                            "2: Nej");
                    try {
                        int workoutSelection = Integer.parseInt(workout);
                        if (workoutSelection == 1) {
                            export = "Personnummer: " + member.memberList.get(i).getPersonalnumber() + " Namn: " +
                                    member.memberList.get(i).getFirstname() + " " + member.memberList.get(i).getLastname();
                            pt.GymAttendee();
                            JOptionPane.showMessageDialog(null, member.memberList.get(i).getFirstname() +
                                    " " + member.memberList.get(i).getLastname() + " är nu registrerad som besökare på gymmet.");
                        }
                        if (workoutSelection == 2) {
                            break;
                        }
                        if (workoutSelection < 1 || workoutSelection > 2){
                            JOptionPane.showMessageDialog(null, "Du måste göra ett giltigt val.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Du måste göra ett giltigt val.");
                    }
                }
                if(member.memberList.get(i).isActivemember() == false)
                    JOptionPane.showMessageDialog(null, member.memberList.get(i).getPersonalnumber() +
                            " " + member.memberList.get(i).getFirstname() +
                            " " + member.memberList.get(i).getLastname() +
                            "'s medlemskap har löpt ut.");

            }
            String comparison2 = member.memberList.get(i).getLastname();
            if(comparison2.toUpperCase().equals(fullname.toUpperCase())){
                exist = true;
                if(member.memberList.get(i).isActivemember() == true) {
                    JOptionPane.showMessageDialog(null, member.memberList.get(i).getPersonalnumber() +
                            " " + member.memberList.get(i).getFirstname() +
                            " " + member.memberList.get(i).getLastname() +
                            " är en betalande medlem.");
                    String workout = JOptionPane.showInputDialog("Vill " +
                            member.memberList.get(i).getFirstname() +
                            " " + member.memberList.get(i).getLastname() + " träna?\n" +
                            "1: Ja\n" +
                            "2: Nej");
                    try {
                        int workoutSelection = Integer.parseInt(workout);
                        if (workoutSelection == 1) {
                            export = "Personnummer: " + member.memberList.get(i).getPersonalnumber() + " Namn: " +
                                    member.memberList.get(i).getFirstname() + " " + member.memberList.get(i).getLastname();
                            pt.GymAttendee();
                            JOptionPane.showMessageDialog(null, member.memberList.get(i).getFirstname() +
                                    " " + member.memberList.get(i).getLastname() + " är nu registrerad som besökare på gymmet.");
                        }
                        if (workoutSelection == 2) {
                            break;
                        }
                        if (workoutSelection < 1 || workoutSelection > 2){
                            JOptionPane.showMessageDialog(null, "Du måste göra ett giltigt val.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Du måste göra ett giltigt val.");
                    }
                }
                if(member.memberList.get(i).isActivemember() == false) {
                    JOptionPane.showMessageDialog(null, member.memberList.get(i).getPersonalnumber() +
                            " " + member.memberList.get(i).getFirstname() +
                            " " + member.memberList.get(i).getLastname() +
                            "'s medlemskap har löpt ut.");
                }
            }
        }
        if (exist == false){
            JOptionPane.showMessageDialog(null, "Användare finns ej.");
        }

    }

}
