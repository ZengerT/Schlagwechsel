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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class test {

    public static int factorial(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorial(n - 1);
    }

    public static boolean check(int t, int r){
        int moeglichkeiten = factorial(t) / (factorial(2) * factorial((t - 2)));
        int benoetigt = t / 2 * r;
        System.out.println(moeglichkeiten+" "+benoetigt);
        if(moeglichkeiten >= benoetigt&& moeglichkeiten != 0 && benoetigt != 0){
            return true;
        } else return false;

    }

    public static void main(String args[]) {




        JFrame frame = new JFrame("Schlagwechsel");
        Frontend gui = new Frontend();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame.setContentPane(gui.$$$getRootComponent$$$());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);

                    gui.getAnzahlRundenTextField().setDocument(new IntegerDocument(4));
                    gui.getAnzahlTeamTextField().setDocument(new IntegerDocument(4));
                } catch (Exception var2) {
                    var2.printStackTrace();
                }

            }
        });


        gui.getErstellenButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gui.getAnzahlTeamTextField().getText().trim().isEmpty() && !gui.getAnzahlRundenTextField().getText().trim().isEmpty()) {

                    int t = Integer.parseInt(gui.getAnzahlTeamTextField().getText());
                    int r = Integer.parseInt(gui.getAnzahlRundenTextField().getText());
                    Turnier a = new Turnier(t,r);
                    Plan p = new Plan();
                    if(!check(t, r)){
                        JOptionPane.showMessageDialog(null, "Nicht genügend Mannschaften.\nEs kommt zu doppelten Paarungen.", "Systemmitteilung", JOptionPane.PLAIN_MESSAGE);
                    }
                        a.turnierErstellen(check(t, r));
                        p.planErstellen(a.ausgabeRunde());
                        JOptionPane.showMessageDialog(null, "Liste erfolgreich erstellt!\nGut Blatt!", "Systemmitteilung", JOptionPane.PLAIN_MESSAGE);




                } else {
                    JOptionPane.showMessageDialog(null, "Bitte Angaben zum Turnier ergänzen!", "Systemmitteilung", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

    }
}


