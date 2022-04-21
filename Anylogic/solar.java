import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.io.*;
import java.util.*;
import java.lang.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class solar extends Agent{

protected void setup(){
try{
System.out.println("Agent Solar initializing:\n");
addBehaviour(new LooperSolar(this,50));
}catch(Exception e){System.out.println(e);System.out.println("Error :: Solar :: Initialization FAILED");}
}

class LooperSolar extends SimpleBehaviour
{
        int    n = 1,message=0,prevmessage=-1;
        long   dt;
        FileInputStream fis;
        Row row;
        XSSFWorkbook book;
        XSSFSheet sheet;
        ACLMessage msg;

        public LooperSolar( Agent a, long dt) throws IOException{
                super(a);
                this.dt = dt;
                try{
                fis = new FileInputStream(new File("/home/narein/jade/excel/Solar.xlsx"));
                }catch(Exception e){System.out.println(e);System.out.println("Error :: Solar :: Initialization FAILED");}
                book = new XSSFWorkbook(fis);
                sheet = book.getSheetAt(0);
                System.out.println("Agent :: Solar -- INITIALIZED");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Agent :: Solar :: Reading data");
                System.out.println("-----------------------------------------------------------------------");
        }

        public void action()
        {
                row = sheet.getRow(n);
                message=(int)row.getCell(2).getNumericCellValue();
                msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent(Integer.toString(message));
                msg.addReceiver(new AID("wind",AID.ISLOCALNAME));
                if(message==1){
                     msg.addReceiver(new AID("grid",AID.ISLOCALNAME));
                }
                send(msg);
                prevmessage=message;
                block( dt );
                n++;
        }

        public  boolean done() {
               try{
               row=sheet.getRow(n);row.getCell(2);
               }catch(Exception e){System.out.println("Data complete :: Solar");
                     msg = new ACLMessage(ACLMessage.INFORM);msg.setContent("done");msg.addReceiver(new AID("grid",AID.ISLOCALNAME));send(msg);                     return true;
               } return false; 
        }

}

}

