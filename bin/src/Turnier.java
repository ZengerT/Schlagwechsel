/*
Copyright 2020
Reboot ITS
Thomas Zenger (contact@thomas-zenger.de)

GNU GENERAL PUBLIC LICENSE


This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiter verteilen und/oder modifizieren.

    Dieses Programm wird in der Hoffnung bereitgestellt, dass es nützlich sein wird, jedoch
    OHNE JEDE GEWÄHR,; sogar ohne die implizite
    Gewähr der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Einzelheiten.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <https://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.Random;

public class Turnier {

    private int t;
    private int r;
    private int p;
    private ArrayList m = new ArrayList();
    private ArrayList<Integer> l = new ArrayList<Integer>();
    private ArrayList<ArrayList<Integer>> runden = new ArrayList<ArrayList<Integer>>();

    public Turnier(int t, int r) {
        this.t = t;
        this.r = r;

        if(t%2 != 0){
            this.t = t +1;
        }

        this.p = this.t / 2;

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

    public static void einfacheRunde(ArrayList m, ArrayList<Integer> l) {
        ArrayList<Integer> t = (ArrayList<Integer>) l.clone();

        while(t.size() != 0) {
            ArrayList<Integer> paar = erstellePaarung(t);
            m.add(paar);
            continue;
            }
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

    public void turnierErstellen(boolean trigger) {

        //Teamliste befuellen
        for (int a = 1; a <= t; a++) {
            this.l.add(a);
        }

        //pro Runde
        for (int a = 1; a <= r; a++) {
            if (trigger == true) {
                erstelleRunde(this.m, this.l);
            }
            else {
                einfacheRunde(this.m, this.l);
            }
        }
        return;
    }

    public ArrayList<ArrayList<Integer>> ausgabeRunde(){
        int i = 0;
        int k = p;

        for (int a = 1; a <= r; a++) {
            ArrayList s = new ArrayList<Integer>(m.subList(i,k));
            runden.add(s);
            i = i + p;
            k = k + p;
        }

        return runden;
    }

}
