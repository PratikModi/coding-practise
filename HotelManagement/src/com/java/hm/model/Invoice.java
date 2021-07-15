package com.java.hm.model;

import java.util.List;

public class Invoice {

    private Long id;
    private List<InvoiceItem> invoiceItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public void createBill(){

    }
}
