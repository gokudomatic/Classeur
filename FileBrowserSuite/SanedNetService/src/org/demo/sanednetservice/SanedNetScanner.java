/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.sanednetservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.demo.scannerservice.Scanner;
import uk.org.jsane.JSane_Base.JSane_Base_Device;
import uk.org.jsane.JSane_Base.JSane_Base_Option_Type_Descriptor;
import uk.org.jsane.JSane_Base.JSane_Base_Type_Group;
import uk.org.jsane.JSane_Exceptions.JSane_Exception;
import uk.org.jsane.JSane_Net.JSane_Net_Constraint_String_List;
import uk.org.jsane.JSane_Net.JSane_Net_Constraint_Word_List;
import uk.org.jsane.JSane_Net.JSane_Net_Type_String;
import uk.org.jsane.JSane_Net.JSane_Net_Type_Word;

/**
 *
 * @author edwin
 */
public class SanedNetScanner implements Scanner {

    private String name;
    private List<Double> listResolution;
    private List<String> listModes=null;

    @Override
    public String toString() {
        return name;
    }

    SanedNetScanner(JSane_Base_Device device) {
        try {
            name = device.getName();

            int options = device.getNumberOptions();
            for (int loop = 0; loop < options; ++loop) {
                JSane_Base_Option_Type_Descriptor option = device.getOption(loop);
                System.out.println(option);

                if (!(option.getValueType() instanceof JSane_Base_Type_Group)) {
                    System.out.println(device.getOption(loop).getValue());
                }
            }


            JSane_Base_Option_Type_Descriptor modeOption = device.getOption("mode");
            JSane_Net_Constraint_String_List modeListe=(JSane_Net_Constraint_String_List) modeOption.getConstraint();
            listModes=new ArrayList<String>();
            for(Iterator<JSane_Net_Type_String> it=modeListe.getList().iterator();it.hasNext();){
                JSane_Net_Type_String mode=it.next();
                listModes.add(mode.getString());
            }
            
            JSane_Base_Option_Type_Descriptor resolutionOption = device.getOption("resolution");
            JSane_Net_Constraint_Word_List resList = (JSane_Net_Constraint_Word_List) resolutionOption.getConstraint();
            Vector resolutions = resList.getList();
            this.listResolution = new ArrayList<Double>(resolutions.size());

            for (int idx = 0; idx < resolutions.size(); idx++) {
                final int r = ((JSane_Net_Type_Word) resolutions.get(idx)).getValue();
                listResolution.add((double)r);
            }

        } catch (JSane_Exception ex) {
            Logger.getLogger(SanedNetScanner.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Double> getSupportedResolutions() {
        return listResolution;
    }

    @Override
    public List<Integer> getSupportedBitDepth() {
        return new ArrayList<Integer>();
    }

    @Override
    public List<String> getSupportedModes() {
        return listModes;
    }
}
