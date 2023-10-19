import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PT{

    public void GymAttendee() throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(".\\src\\gymattendee.txt", true))){
            LocalDateTime ld = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = ld.format(format);
            bw.write(  formattedDateTime + " " + Reception.ExportString() + "\n");
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }


}
