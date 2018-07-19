package Entities;


public class Movie {
    private String title, genre, year, customer, invoice;
    private Boolean pay;
    public Movie() {
    }

    public Movie(String title, String genre, String year, Boolean pay, String customer, String invoice_id) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.customer = customer;
        this.pay = pay;
        this.invoice = invoice_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}