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

import org.jopendocument.dom.spreadsheet.SpreadSheet;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Plan {


    // Save the data to an ODS file and open it.
    public void planErstellen(ArrayList<ArrayList<Integer>> runden) {

        final File file = new File("turnier.ods");
        DefaultTableModel model = new DefaultTableModel();

        for(int i = 0; i < runden.size(); i++) {

            Object spaltename = "Runde " + (i+1);
            Object[] data = runden.get(i).toArray();
            Object[] data1 = new Object[runden.get(i).size()];
            Object[] data2 = new Object[runden.get(i).size()];
            Object[] table = new Object[runden.get(i).size()];

            for(int j = 0; j < runden.get(i).size(); j++){
                    ArrayList d = (ArrayList) data[j];
                    table[j] = "Tisch " + (j+1);
                    data1[j] = d.get(0);
                    data2[j] = d.get(1);
                }

            model.addColumn(spaltename, table);
            model.addColumn(spaltename, data1);
            model.addColumn(spaltename, data2);


        }

        try {
            SpreadSheet.createEmpty(model).saveAs(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
