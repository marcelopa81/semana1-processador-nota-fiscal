package br.com.alura.oobj;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    if (args.length <= 0) {
      throw new IllegalArgumentException("Forneça um nome de arquivo.");
    }

    String arquivo = args[0];

    List<ItemPedido> itensPedido;

    if (arquivo.endsWith(".csv")) {
      try {
        Reader reader = new FileReader(arquivo);
        CsvToBean<ItemPedido> csvToBean = new CsvToBeanBuilder<ItemPedido>(reader)
            .withSeparator(';')
            .withType(ItemPedido.class)
            .build();
        itensPedido = csvToBean.parse();
      } catch (IOException ex) {
        throw new IllegalStateException(ex);
      }

    } else if (arquivo.endsWith(".xml")) {
      try {
        Reader reader = new FileReader(arquivo);
        XmlMapper mapper = new XmlMapper();

        Pedido pedido = mapper.readValue(reader, Pedido.class);
        itensPedido = pedido.getItens();
      } catch (IOException ex) {
        throw new IllegalStateException(ex);
      }

    } else {
      throw new IllegalArgumentException("Formato de arquivo inválido: " + arquivo);
    }

    BigDecimal totalPedido = BigDecimal.ZERO;
    for (ItemPedido itemPedido : itensPedido) {
      BigDecimal subtotal = itemPedido.getValorUnitario().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
      totalPedido = totalPedido.add(subtotal);
    }

    SubTotalPorClasseFiscal subTotalPorClasseFiscal = new SubTotalPorClasseFiscal();
    for (ItemPedido itemPedido : itensPedido) {
      BigDecimal novoSubTotal = itemPedido.getValorUnitario().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
      String classeFiscal = itemPedido.getClasseFiscal();
      BigDecimal subTotal = subTotalPorClasseFiscal.get(classeFiscal);
      if (subTotal != null) {
        subTotalPorClasseFiscal.put(classeFiscal, subTotal.add(novoSubTotal));
      } else {
        subTotalPorClasseFiscal.put(classeFiscal, novoSubTotal);
      }
    }

    System.out.println("## Total do pedido: " + totalPedido);
    System.out.println("\n## Subtotal por classe fiscal");
    for (String classeFiscal : subTotalPorClasseFiscal.keySet()) {
      System.out.println("\n\tClasse fiscal: " + classeFiscal);
      BigDecimal subtotal = subTotalPorClasseFiscal.get(classeFiscal);
      System.out.println("\tSubtotal: " + subtotal);
    }


  }

}
