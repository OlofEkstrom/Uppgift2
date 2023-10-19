import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member {

    private long personalnumber;
    private String firstname;
    private String lastname;
    private LocalDate memberdate;
    private boolean activemember;
    private List<String>memberslist = new ArrayList<>();
    private static String[][] membersArray = new String[500][5];
    public static ArrayList<Member> memberList = new ArrayList<Member>();

    public Member(long personalnumber, String firstname, String lastname, LocalDate memberdate, boolean activemember) {
        this.personalnumber = personalnumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.memberdate = memberdate;
        this.activemember = activemember;
    }

    public long getPersonalnumber() {return personalnumber;}
    public String getFirstname() {return firstname;}
    public String getLastname() {return lastname;}
    public LocalDate getMemberdate() {return memberdate;}
    public boolean isActivemember() {return activemember;}

    public Member() {}

    public String toString(){
        return getPersonalnumber() + " " + getFirstname() + " " + getLastname() + " " + getMemberdate() + " " + isActivemember();
    }

    public void MembersList() throws IOException{

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\customers.txt"))){


            while(true){
                String input = bufferedReader.readLine();
                input = input.replace(",","");
                int inputLength = input.length();
                if(inputLength > 10) {


                    String[] split = input.split(" ");
                    for (int i = 0; i < 3; i++) {
                        memberslist.add(split[i]);
                    }
                }
                else{
                    memberslist.add(input);
                }
                if(input == null){
                    break;
                }

            }

        }
        catch (Exception e){
        }

    }

    public void MemberArray() throws IOException{

        int listLength = memberslist.size();
        int temp = 0;
        for (int i = 0; i < listLength/4; i++) {

            for (int j = 0; j <= 3; j++) {
                membersArray[i][j] = memberslist.get(temp);
                temp++;


            }

        }

    }

    public void CurrentMember() throws IOException{

        LocalDate ld = LocalDate.now();
        ld = ld.minusYears(1);
        int currentArrayLength = 0;
        int maxArrayLength = membersArray.length;
        for (int i = 0; i < maxArrayLength; i++) {
            if (membersArray[i] != null)
                currentArrayLength++;
            if (membersArray[i][0] == null){
                currentArrayLength--;
                break;
            }
        }
        for (int i = 0; i < currentArrayLength; i++) {
            LocalDate ld2 = LocalDate.parse(membersArray[i][3]);
            if(ld2.compareTo(ld) > 0){
                membersArray[i][4] = "true";
            }
            else{
                membersArray[i][4] = "false";
            }

        }

    }

    public void ActiveMember() throws IOException{


        int currentArrayLength = 0;

        int maxArrayLength = membersArray.length;
        for (int i = 0; i < maxArrayLength; i++) {
            if (membersArray[i][0] != null)
                currentArrayLength++;
            if (membersArray[i][0] == null){
                break;
            }
        }

        for (int i = 0; i < currentArrayLength; i++) {
            long temp = 0;
            LocalDate ld;
            Boolean bool;
            try{
                temp = Long.parseLong(membersArray[i][0]);
            }
            catch (Exception e){

            }
            ld = LocalDate.parse(membersArray[i][3]);
            bool = Boolean.parseBoolean(membersArray[i][4]);
            Member member = new Member(temp, membersArray[i][1], membersArray[i][2], ld, bool);
            memberList.add(member);


        }

    }

}
