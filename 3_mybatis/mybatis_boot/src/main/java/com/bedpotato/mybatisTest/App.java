package com.bedpotato.mybatisTest;

import java.io.IOException;

import com.bedpotato.mapper.CardBinMapper;
import com.bedpotato.mapper.SQLAdapter;
import com.bedpotato.po.CardBin;
import org.apache.ibatis.session.SqlSession;

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



}
