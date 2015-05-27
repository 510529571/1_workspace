package com.bedpotato.mapper;

import com.bedpotato.po.CardBin;

import java.util.List;
import java.util.Map;

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

    void insert4List(List<Ptable> list);
    void insert4ListMap(List<Map<String,String>> list);
    void insert4Map(Map<String,String> map);

    void update4List(List<Ptable> list);
}
