package org.joverseer.support.readers.xml;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 1 ��� 2006
 * Time: 12:02:52 ��
 * To change this template use File | Settings | File Templates.
 */
public class EconomyWrapper {
    int armyMaint;
    int popMaint;
    int charMaint;
    int totalMaint;
    int taxRate;
    int revenue;
    int surplus;
    int reserve;
    int taxBase;

    ArrayList products = new ArrayList();

    public ArrayList getProducts() {
        return products;
    }

    public void setProducts(ArrayList products) {
        this.products = products;
    }

    public int getArmyMaint() {
        return armyMaint;
    }

    public void setArmyMaint(int armyMaint) {
        this.armyMaint = armyMaint;
    }

    public int getCharMaint() {
        return charMaint;
    }

    public void setCharMaint(int charMaint) {
        this.charMaint = charMaint;
    }

    public int getPopMaint() {
        return popMaint;
    }

    public void setPopMaint(int popMaint) {
        this.popMaint = popMaint;
    }

    public int getReserve() {
        return reserve;
    }

    public void setReserve(int reserve) {
        this.reserve = reserve;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public int getTaxBase() {
        return taxBase;
    }

    public void setTaxBase(int taxBase) {
        this.taxBase = taxBase;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getTotalMaint() {
        return totalMaint;
    }

    public void setTotalMaint(int totalMaint) {
        this.totalMaint = totalMaint;
    }

    public void addProduct(ProductWrapper product) {
        products.add(product);
    }
}
