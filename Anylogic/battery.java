import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.io.*;
import java.util.*;

public class battery extends Agent{

protected void setup(){
System.out.println("Agent Battery Initializing:\n");
try{
addBehaviour(new LooperBattery(this,1));
}catch(Exception e){System.out.println(e);System.out.println("Agent :: Battery :: Initialization FAILED");}
}


class LooperBattery extends SimpleBehaviour
{
        long dt;
        boolean done=false;
        ACLMessage msg;

        public LooperBattery( Agent a, long dt) throws IOException{
                super(a);
                this.dt = dt;
                System.out.println("Agent :: Battery -- INITIALIZED");
                System.out.println("---------------------------------------------------------------");
                System.out.println("Battery :: Charging");
        }

        public void action()
        {
                msg=blockingReceive();
                if(msg.getContent().equals("0")){
                System.out.println("---BATTERY :: Utilized---");}
                else if(msg.getContent().equals("done")){done=true;System.out.println("Charging :: COMPLETE");}
                block( dt );
        }

        public  boolean done() {  return done;  }

}

}
