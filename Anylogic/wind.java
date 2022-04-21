import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import java.io.*;
import java.util.*;
import java.lang.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class wind extends Agent{

protected void setup(){
System.out.println("Agent Wind Initializing:\n");
try{
addBehaviour(new LooperWind(this,50));
}catch(Exception e){System.out.println(e);System.out.println("Error :: Wind :: Initialization FAILED");}
}


class LooperWind extends SimpleBehaviour
{
        int    n = 1,message=0;
        String fromsolar;
        long   dt;
        FileInputStream fis;
        Row row;
        XSSFWorkbook book;
        XSSFSheet sheet;
        ACLMessage msg_in,msg_out;

        public LooperWind( Agent a, long dt) throws IOException{
                super(a);
                this.dt = dt;
                try{
                fis = new FileInputStream(new File("/home/narein/jade/excel/Wind.xlsx"));
                }catch(Exception e){System.out.println(e);System.out.println("Error :: Wind :: Initialization FAILED");}
                book = new XSSFWorkbook(fis);
                sheet = book.getSheetAt(0);
                System.out.println("Agent :: Wind -- INITIALIZED");
                System.out.println("---------------------------------------------------------------");
                System.out.println("Agent :: Wind :: Reading data");
                System.out.println("---------------------------------------------------------------");
        }

        public void action()
        {
                msg_in=blockingReceive();
                row=sheet.getRow(n);
                message=(int)row.getCell(2).getNumericCellValue();
                msg_out=new ACLMessage(ACLMessage.INFORM);
                msg_out.setContent(Integer.toString(message));
                if(msg_in.getContent().equals("0")){
                     msg_out.addReceiver(new AID("grid",AID.ISLOCALNAME));
                }
                else{
                     msg_out.addReceiver(new AID("backup",AID.ISLOCALNAME));
                }
                send(msg_out);
                block( dt );
                n++;
        }

        public  boolean done() {
                try{
                row=sheet.getRow(n);row.getCell(2);
                }catch(Exception e){
                    System.out.println("Data complete :: Wind");
                    msg_out = new ACLMessage(ACLMessage.INFORM);msg_out.setContent("done");msg_out.addReceiver(new AID("backup",AID.ISLOCALNAME));send(msg_out);
                    return true;}
                return false; 
        }

}

}
