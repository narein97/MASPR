import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.io.*;
import java.util.*;

public class grid extends Agent{

protected void setup(){
System.out.println("Agent Grid Initializing:\n");
try{
addBehaviour(new LooperGrid(this,1));
}catch(Exception e){System.out.println(e);System.out.println("Agent :: Grid :: Initialization FAILED");}
}


class LooperGrid extends SimpleBehaviour
{
        int n=1;
        long dt;
        boolean done=false;
        ACLMessage msg,msg_out;

        public LooperGrid( Agent a, long dt) throws IOException{
                super(a);
                this.dt = dt;
                System.out.println("Agent :: Grid -- INITIALIZED");
                System.out.println("---------------------------------------------------------------");
                System.out.println("Grid :: Ready to acquire data");
        }

        public void action()
        {
                msg=blockingReceive();
                msg_out = new ACLMessage(ACLMessage.INFORM);
                msg_out.setContent(msg.getContent());
                msg_out.addReceiver(new AID("battery",AID.ISLOCALNAME));
                send(msg_out);
                if(msg.getContent().equals("done")){
                     done=true;
                }
                if(!msg.getContent().equals("0")){
                System.out.println(n+":  "+msg.getContent());}n++;
                block( dt );
        }

        public  boolean done() {  return done;  }

}

}
