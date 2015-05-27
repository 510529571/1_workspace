package com.bedpotato.mybatisTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bedpotato.mapper.CardBinMapper;
import com.bedpotato.mapper.Ptable;
import com.bedpotato.mapper.SQLAdapter;
import com.bedpotato.po.CardBin;
import org.apache.ibatis.session.SqlSession;

import org.junit.Test;
import own.hhw.util.MybatisUtil;

public class App {
    public static void main(String[] args) throws IOException {
//        insertData();
    }

    private static void insertData() {
        SqlSession session = MybatisUtil.getSqlSession();
        CardBinMapper mapper = session.getMapper(CardBinMapper.class);
        CardBin cardBin = new CardBin("222", "2", "2", "2");
        //hhw:tag 如果源表里面有两个id相同的数据，这回包
//        CardBin cardBin3 = new CardBin("222","2","2","2");
        CardBin cardBin2 = new CardBin("2", "2", "2", "2");
        mapper.insert(cardBin);
        mapper.insert(cardBin2);
        String merge = " merge into cardbin t1\n" +
                "        using cardbin_temp t2\n" +
                "        on (t1.cardbin=t2.cardbin)\n" +
                "        when matched then\n" +
                "        update set t1.bankname=t2.bankname,t1.bankno=t2.bankno,t1.cardtype=t2.cardtype\n" +
                "        when not matched then\n" +
                "        insert values(t2.bankname,t2.bankno,t2.cardbin,t2.cardtype)";
        String clear = "delete from cardbin_temp";
        mapper.merge(new SQLAdapter(merge));
        mapper.merge(new SQLAdapter(clear));
        session.commit();
        session.close();
    }

    @Test
    public void insert4List() {
        SqlSession session = MybatisUtil.getSqlSession();
        CardBinMapper mapper = session.getMapper(CardBinMapper.class);

        List list = new ArrayList();

        Ptable ptable = new Ptable();
        ptable.setId("123");
        ptable.setType("c");
        list.add(ptable);

        Ptable ptable2 = new Ptable();
        ptable2.setId("111");
        ptable2.setType("pp");
        list.add(ptable2);

        mapper.insert4List(list);

        session.commit();
        session.close();
    }

    @Test
    public void update4List() {
        SqlSession session = MybatisUtil.getSqlSession();
        CardBinMapper mapper = session.getMapper(CardBinMapper.class);

        List list = new ArrayList();

        Ptable ptable = new Ptable();
        ptable.setId("123");
        ptable.setType("pp");

        list.add(ptable);

        Ptable ptable2 = new Ptable();
        ptable2.setId("111");
        ptable2.setType("pp");

        list.add(ptable2);

        mapper.update4List(list);

        session.commit();
        session.close();
    }

    @Test
    public void insert4ListMap() {
        SqlSession session = MybatisUtil.getSqlSession();
        CardBinMapper mapper = session.getMapper(CardBinMapper.class);

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "111");
        map.put("type", "b");

        List list = new ArrayList();
        list.add(map);
        mapper.insert4List(list);

        session.commit();
        session.close();
    }

    @Test
    public void insert4Map() {
        SqlSession session = MybatisUtil.getSqlSession();
        CardBinMapper mapper = session.getMapper(CardBinMapper.class);

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "111");
        map.put("type", "b");
        mapper.insert4Map(map);

        session.commit();
        session.close();
    }
}
