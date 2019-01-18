/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/17 11:02
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.text;

import com.renshihan.base.引入流.IdGenerator;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

import java.util.*;

/**
 * ${TODO} 写点注释吧
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/17 11:02
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/12/17 11:02
 */
public class SqlOutTest {
    @Test
    public void test22() {
        String format = "Insert into CASH.T_RCM_DICT (ID,VALUE,LABEL,TYPE,DES,DEL_FLAG,ORDER_NUM,PARENT_ID) values ('%s','%s','%s','%s','%s','1',1,'null');";
        String type = "RISK_TIPS";
        String des = "商户风险提示";
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y001", "机构未正常经营", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y002", "未在工商注册系统中", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y003", "负债比过高", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y004", "有不良记录", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y005", "大额50000及以上", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y006", "风险等级低", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y007", "GPS与商户城市不同", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y008", "在获取通讯录权限的情况下，通讯录或通话记录未包含紧急联系人2", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y009", "H5进件", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "Y999", "初审和反欺诈没有命中任何一条黄灯规则", type, des));


        type = "BASIC_CONS_FLG";
        des = "乐融商户风险提示";
        System.out.println(String.format(format, IdGenerator.getUUID(), "C01", "结算人与法人始终不一致", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "C02", "结算人与法人目前不一致，30天前有过修改", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "C03", "结算人与法人始终一致", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "C04", "结算人与法人目前一致，30天前有过修改", type, des));

    }

    @Test
    public void test23() {

        System.out.println(buildSql(1, "QZ01", "组团骗贷"));
        System.out.println(buildSql(1, "QZ02", "伪冒申请"));
        System.out.println(buildSql(1, "QZ03", "代办包装"));
        System.out.println(buildSql(1, "QZ04", "设备虚假"));
        System.out.println(buildSql(2, "QZ0401", "使用虚拟机"));
        System.out.println(buildSql(2, "QZ0402", "使用模拟器"));
        System.out.println(buildSql(2, "QZ0403", "使用代理IP"));
        System.out.println(buildSql(1, "QZ05", "信息虚假"));
        System.out.println(buildSql(2, "QZ0501", "紧急联系人虚假"));
        System.out.println(buildSql(2, "QZ0502", "通讯录虚假"));
        System.out.println(buildSql(3, "QZ050201", "通讯录手机号虚假"));
        System.out.println(buildSql(3, "QZ050202", "通讯录联系人虚假"));
        System.out.println(buildSql(3, "QZ050203", "非本人通讯录"));
        System.out.println(buildSql(2, "QZ0503", "详单虚假"));
        System.out.println(buildSql(2, "QZ0504", "公司名称虚假"));
        System.out.println(buildSql(2, "QZ0505", "公司电话虚假"));
        System.out.println(buildSql(2, "QZ0506", "居住地虚假"));
        System.out.println(buildSql(2, "QZ0507", "身份证虚假"));
        System.out.println(buildSql(2, "QZ0508", "虚假商户"));
        System.out.println(buildSql(2, "QZ0509", "虚假交易"));
        System.out.println(buildSql(1, "QZ06", "不良嗜好"));
        System.out.println(buildSql(2, "QZ0601", "吸毒"));
        System.out.println(buildSql(2, "QZ0602", "赌博"));
        System.out.println(buildSql(2, "QZ0603", "涉黄"));
        System.out.println(buildSql(1, "QZ07", "他人代偿"));
        System.out.println(buildSql(1, "QZ08", "羊毛党"));
        System.out.println(buildSql(1, "QZ09", "中介"));
    }

    private static HashMap<Integer,Integer> levelMap=new HashMap<>();
    private static int id = 0;
    private static int beforeLevel=0;
    public String buildSql(int level, String lableCode, String lableName) {
        String format = "insert into t_lable " +
                "(up_id,lable_code,lable_name,lable_level,lable_type,remark,status,creator,create_date) values (%s,'%s','%s',%s,'%s','%s',%s,'%s',now());";
        //标签类型:1-记录，2-预警，3-限制，4-拦截
        id += 1;
        levelMap.put(level,id);
        int upId=-1;
        if(level==1){
            upId=-1;
        }else {
            upId=levelMap.get(level-1);
        }
        String lableType = "1";
        String remark = "";
        //0-删除，1-启用，2-禁用
        String status = "1";
        String creator = "renshihan";

        format = String.format(format,upId , lableCode, lableName, level, lableType, remark, status, creator);
        return format;
    }


    @Test
    public void test1123(){
        int a=1;
        int b=2;
        if(a!=1){
            System.out.println("------111");
        }else if(b==2){
            System.out.println("--------222");
        }else {
            System.out.println("---ccccc33333");
        }
    }

    @Test
    public void test123213(){
        Date datea=new Date(1547368905000L);
        Date dateb=new Date(1547541705000L);
        System.out.println(DateFormatUtils.format(datea,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateFormatUtils.format(dateb,"yyyy-MM-dd HH:mm:ss"));
    }


}