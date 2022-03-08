package br.com.alura.oobj;

import java.util.List;

public class Pedido {

  private List<ItemPedido> itens;

  public List<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(List<ItemPedido> itens) {
    this.itens = itens;
  }

  @Override
  public String toString() {
    return "Pedido{" +
        "itens=" + itens +
        '}';
  }
}
