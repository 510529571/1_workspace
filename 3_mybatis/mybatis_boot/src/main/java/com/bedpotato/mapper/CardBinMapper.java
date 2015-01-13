package com.bedpotato.mapper;

import com.bedpotato.po.CardBin;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-1
 * Time: обнГ4:51
 * To change this template use File | Settings | File Templates.
 */
public interface CardBinMapper {
    void insert(CardBin cardBin);
    void merge(SQLAdapter sqlAdapter);
    void merge2(String s);
}
