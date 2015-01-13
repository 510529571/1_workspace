package com.bedpotato.po;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-1
 * Time: ÏÂÎç4:49
 * To change this template use File | Settings | File Templates.
 */
public class CardBin {
    private String bankName;
    private String bankNo;
    private String cardBin;
    private String cardType;

    public CardBin() {
    }

    public CardBin(String bankName, String bankNo, String cardBin, String cardType) {
        this.bankName = bankName;
        this.bankNo = bankNo;
        this.cardBin = cardBin;
        this.cardType = cardType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getCardBin() {
        return cardBin;
    }

    public void setCardBin(String cardBin) {
        this.cardBin = cardBin;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
