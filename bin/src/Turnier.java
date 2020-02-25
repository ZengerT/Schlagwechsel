import java.util.ArrayList;
import java.util.Random;

public class Turnier {

    private int t;
    private int r;
    private int p;
    private ArrayList m = new ArrayList();
    private ArrayList<Integer> l = new ArrayList<Integer>();


    public Turnier(int t, int r) {
        this.t = t;
        this.r = r;

        if(t%2 != 0){
            this.t = t +1;
        }

        this.p = this.t / 2;

    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public static ArrayList<Integer> erstellePaarung( ArrayList<Integer> t) {
        Random random = new Random();
        ArrayList<Integer> z = new ArrayList<Integer>();
        int x = t.get(random.nextInt(t.size()));
        t.remove(t.indexOf(x));
        int y = t.get(random.nextInt(t.size()));
        t.remove(t.indexOf(y));

        z.add(x);
        z.add(y);

        return z;
    }

    public static void erstelleRunde(ArrayList m, ArrayList<Integer> l) {
        ArrayList<Integer> t = (ArrayList<Integer>) l.clone();
        ArrayList bereits_gespielt = (ArrayList) m.clone();

        while(t.size() != 0) {
            ArrayList<Integer> paar = erstellePaarung(t);
            ArrayList<Integer> paar_reverse = new ArrayList<Integer>(2);


            paar_reverse.add(paar.get(1));
            paar_reverse.add(paar.get(0));
            if (m.contains(paar) == false && m.contains(paar_reverse) == false) {
                bereits_gespielt.add(paar);
                continue;
            } else {
                //Operationen rueckgaengig machen
                //ausgeloste Teams der Liste wieder hinzufuegen
                t.add(paar.get(0));
                t.add(paar.get(1));

                //Falls nur noch 2 Teams vorhanden, Runde komplett neu starten
                if(t.size() == 2){
                    System.out.println("Grober Fehler");
                    bereits_gespielt = (ArrayList) m.clone();
                    t = (ArrayList<Integer>) l.clone();
                    continue;
                }
                //Auslosung neu starten
                continue;
            }
        }

        m.addAll(bereits_gespielt.subList(m.size(), bereits_gespielt.size()));
        return;

    }

    public void turnierErstellen() {
        //Teamliste befuellen
        for (int a = 1; a <= t; a++) {
            this.l.add(a);
        }

        //pro Runde
        for (int a = 1; a <= r; a++) {
            erstelleRunde(this.m,this.l);
        }
        return;
    }

    public void ausgabeRunde(){
        int i = 0;
        int k = p;
        for (int a = 1; a <= r; a++) {
            ArrayList s = new ArrayList<Integer>(m.subList(i,k));
            System.out.println("Runde" + a);
            for (Object w : s){
                System.out.println(w);
            }
            i = i + p;
            k = k + p;
        }
    }








}
