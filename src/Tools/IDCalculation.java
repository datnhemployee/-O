/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 *
 * @author DAT
 */
public class IDCalculation {
    private IDCalculation(){}
    
    public static String getNextID(String previous){
        if(previous.isEmpty())
            return "0";
        else{
            Integer iresult = Integer.parseInt(previous) + 1;
            return iresult.toString();
                    
        }
    }
    
}
