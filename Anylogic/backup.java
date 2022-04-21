import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.io.*;
import java.util.*;

public class backup extends Agent{

protected void setup(){
System.out.println("Agent Backup Initializing:\n");
try{
addBehaviour(new LooperBackup(this,1));
}catch(Exception e){System.out.println(e);System.out.println("Agent :: Backup :: Initialization FAILED");}
}


class LooperBackup extends SimpleBehaviour
{
        int n=0;
        long dt;
        boolean done=false;
        ACLMessage msg;

        public LooperBackup( Agent a, long dt) throws IOException{
                super(a);
                this.dt = dt;
                System.out.println("Agent :: Backup -- INITIALIZED");
                System.out.println("---------------------------------------------------------------");
                System.out.println("Backup :: Ready to acquire data");
        }

        public void action()
        {
                msg=blockingReceive();
                if(msg.getContent().equals("done")){done=true;}
                n++;
                block( dt );
        }

        public  boolean done() {  if(done){System.out.println("No.of backups: "+n);}return done;  }

}

}
