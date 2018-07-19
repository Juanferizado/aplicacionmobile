package Entities;

import java.util.Date;

public class EstatusClient {

    private Date datepay;

    private Integer total;

    private Boolean pay;

    public EstatusClient(Date _datepay, Integer _total, Boolean _pay){
        this.datepay = _datepay;
        this.total = _total;
        this.pay = _pay;

    }

    public Date getDatepay() {
        return datepay;
    }

    public void setDatepay(Date datepay) {
        this.datepay = datepay;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getPay(){
        return pay;
    }

    public void setPay(Boolean _pay){
        this.pay = _pay;
    }
}
