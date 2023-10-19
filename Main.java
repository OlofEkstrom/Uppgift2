import java.io.IOException;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {


        Member member = new Member();
        Reception reception = new Reception();
        member.MembersList();
        member.MemberArray();
        member.CurrentMember();
        member.ActiveMember();
        reception.Menu();
    }
}