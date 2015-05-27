package own.hhw;

import own.hhw.dao.PayInfoMapper;
import own.hhw.domain.PayInfo;
import own.hhw.domain.PayInfoExample;
import own.hhw.util.MybatisUtil;

import java.util.List;

/**
 * User: hanwei
 * Date: 15-4-1
 * Time: обнГ3:11
 */
public class Main {
    public static void main(String[] args) {
        PayInfoMapper payInfoMapper= MybatisUtil.getSqlSession().getMapper(PayInfoMapper.class);
        PayInfoExample payInfoExample=new PayInfoExample();
        payInfoExample.createCriteria().andIdentityNoEqualTo("123");
        List<PayInfo> list= payInfoMapper.selectByExample(payInfoExample);
        System.out.println(list.size());

    }
}
