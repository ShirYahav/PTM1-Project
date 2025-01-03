package Graph;

import java.util.Arrays;

public class Topic {
    public final String name;
    public Agent[] subs;
    public Agent[] pubs;

    Topic(String name , Agent[] subs , Agent[] pubs) {
        this.name=name;
        this.subs=subs;
        this.pubs=pubs;
    }

    public void subscribe(Agent a){
        if (subs != null){
            Agent[] newSubs = Arrays.copyOf(subs,subs.length + 1);
            newSubs[subs.length] = a;
            subs = newSubs;
        }
        else {
            subs = new Agent[1];
            subs[0] = a;
        }
    }

    public void unsubscribe(Agent a){
        int index = -1;
        for(int i = 0; i < this.subs.length; i++){
            if(this.subs[i] != null && this.subs[i].equals(a)){
                index = i;
                break;
            }
        }
        if(index != -1){
            for(int i = index; i < this.subs.length -1; i++){
                this.subs[i] = this.subs[ i + 1];
            }
            this.subs[this.subs.length - 1] = null;
        }
    }

    public void publish(Message m){
        for(int i = 0; i < this.subs.length; i++){
            if(this.subs[i] != null){
                this.subs[i].callback(this.name, m);
            }
        }
    }

    public void addPublisher(Agent a){
        if (pubs != null){
            Agent[] newPubs = Arrays.copyOf(pubs,pubs.length + 1);
            newPubs [pubs.length] = a;
            pubs = newPubs;
        }
        else {
            pubs = new Agent[1];
            pubs[0] = a;
        }
    }

    public void removePublisher(Agent a){
        int index = -1;
        for(int i = 0; i < this.pubs.length; i++){
            if(this.pubs[i] != null && this.pubs[i].equals(a)){
                index = i;
                break;
            }
        }
        if(index != -1){
            for(int i = index; i < this.pubs.length -1; i++){
                this.pubs[i] = this.pubs[ i + 1];
            }
            this.pubs[this.pubs.length - 1] = null;
        }
    }

}
