package br.com.alura.oobj;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeMap;

public class SubTotalPorClasseFiscal {
    private TreeMap<String, BigDecimal> SubTotalPorClasseFiscal = new TreeMap<String, BigDecimal>();

    public BigDecimal adicionaSubTotal(String classeFiscal, BigDecimal subTotal){
        return this.SubTotalPorClasseFiscal.put(classeFiscal, subTotal);
    }

    public BigDecimal obterSubTotal(String classeFiscal){
        return this.SubTotalPorClasseFiscal.get(classeFiscal);
    }

    public Set<String> obterClasseFiscal(){
        return this.SubTotalPorClasseFiscal.keySet();
    }

}
