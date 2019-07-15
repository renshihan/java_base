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
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

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
        String format = "Insert into CASH_UAT.T_RCM_DICT (ID,VALUE,LABEL,TYPE,DES,DEL_FLAG,ORDER_NUM,PARENT_ID) values ('%s','%s','%s','%s','%s','1',1,'null');";
        String type = "RISK_TIPS";
        String des = "商户风险提示";
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y001", "机构未正常经营", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y002", "未在工商注册系统中", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y003", "负债比过高", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y004", "有不良记录", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y005", "大额50000及以上", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y006", "风险等级低", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y007", "GPS与商户城市不同", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y008", "在获取通讯录权限的情况下，通讯录或通话记录未包含紧急联系人2", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y009", "H5进件", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "Y999", "初审和反欺诈没有命中任何一条黄灯规则", type, des));
//
//
//        type = "BASIC_CONS_FLG";
//        des = "乐融商户风险提示";
//        System.out.println(String.format(format, IdGenerator.getUUID(), "C01", "结算人与法人始终不一致", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "C02", "结算人与法人目前不一致，30天前有过修改", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "C03", "结算人与法人始终一致", type, des));
//        System.out.println(String.format(format, IdGenerator.getUUID(), "C04", "结算人与法人目前一致，30天前有过修改", type, des));


        type = "monIncome";
        des = "月收入";
        System.out.println(String.format(format, IdGenerator.getUUID(), "01", "0-3K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "02", "3K-6K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "03", "6K-9K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "04", "9K-12K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "05", "12K-15K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "06", "15K-18K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "07", "18K-22K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "08", "22K-26K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "09", "26K-30K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "10", "30K-35K", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "11", "35K+", type, des));


        type = "loanUse";
        des = "借款用途";
        System.out.println(String.format(format, IdGenerator.getUUID(), "01", "日常消费", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "02", "还信用卡", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "03", "还房贷", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "03", "还房贷", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "04", "还车贷", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "05", "发放员工工资", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "06", "扩大经营", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "07", "进货", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "08", "装修住房", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "09", "装修店面", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "10", "资金周转", type, des));
        System.out.println(String.format(format, IdGenerator.getUUID(), "11", "其他", type, des));
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

    private static HashMap<Integer, Integer> levelMap = new HashMap<>();
    private static int id = 0;
    private static int beforeLevel = 0;

    public String buildSql(int level, String lableCode, String lableName) {
        String format = "insert into t_lable " +
                "(up_id,lable_code,lable_name,lable_level,lable_type,remark,status,creator,create_date) values (%s,'%s','%s',%s,'%s','%s',%s,'%s',now());";
        //标签类型:1-记录，2-预警，3-限制，4-拦截
        id += 1;
        levelMap.put(level, id);
        int upId = -1;
        if (level == 1) {
            upId = -1;
        } else {
            upId = levelMap.get(level - 1);
        }
        String lableType = "1";
        String remark = "";
        //0-删除，1-启用，2-禁用
        String status = "1";
        String creator = "renshihan";

        format = String.format(format, upId, lableCode, lableName, level, lableType, remark, status, creator);
        return format;
    }


    @Test
    public void test1123() {
        int a = 1;
        int b = 2;
        if (a != 1) {
            System.out.println("------111");
        } else if (b == 2) {
            System.out.println("--------222");
        } else {
            System.out.println("---ccccc33333");
        }
    }

    @Test
    public void test123213() {
        Date datea = new Date(1547368905000L);
        Date dateb = new Date(1547541705000L);
        System.out.println(DateFormatUtils.format(datea, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateFormatUtils.format(dateb, "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void test1234() {

        String format = "Insert into T_COMMON_JOB (" +
                //识别唯一
                "JOB_TYPE," +
                //描述
                "DESCRIPTION," +
                //是否可用
                "STATUS," +
                //运行状态
                "RUNNING_STATUS," +
                //mongo数据集
                "MONGO_COLLECTION_NAME," +
                //csv文件地址-----用来定义sql得
                "CONFIG_PATH)" +
                " values ('%s','%s','%s','%s','%s','1');";
        String sql = String.format(format, "appInf", "用户手机APP", "0", "0", "{\"collection\":\"appInf\"}", "/home/app/sxpservice/conf/config_appInf.csv");

        sql = String.format(format, "creditCardLoginInfo", "信用卡登陆信息", "0", "0", "{\"collection\":\"appInf\"}", "/home/app/sxpservice/conf/config_appInf.csv");
        System.out.println("------" + sql);


        String abc = "MERGE INTO T_DATA_PRCS_RESULT RS\n" +
                "\t\tUSING (select #{userId} USER_ID,#{fieldId} FIELD_ID,#{resultId} RESULT_ID,#{busType,jdbcType=VARCHAR} BUS_TYPE,#{fieldDataType} FIELD_DATA_TYPE,#{resultData} RESULT_DATA,#{fieldNameEn} FIELD_NAME_EN,#{fieldNameCn} FIELD_NAME_CN from DUAL) DL\n" +
                "\t\tON (RS.USER_ID = DL.USER_ID AND RS.FIELD_ID = DL.FIELD_ID)\n" +
                "\t\tWHEN MATCHED THEN\n" +
                "\t\t  UPDATE SET RS.RESULT_DATA=DL.RESULT_DATA,RS.UPDATE_TIME=SYSDATE\n" +
                "\t\tWHEN NOT MATCHED THEN\n" +
                "\t\t  INSERT(RS.RESULT_ID,RS.USER_ID,RS.BUS_TYPE,RS.FIELD_ID,RS.FIELD_DATA_TYPE,RS.RESULT_DATA,RS.CREATE_TIME,RS.FIELD_NAME_EN,RS.FIELD_NAME_CN)\n" +
                "\t\t  VALUES(DL.RESULT_ID,DL.USER_ID,DL.BUS_TYPE,DL.FIELD_ID,DL.FIELD_DATA_TYPE,DL.RESULT_DATA,SYSDATE,DL.FIELD_NAME_EN,DL.FIELD_NAME_CN)\n" +
                "\t";
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
    private static void buildAndPrintln(CgwDict cgwDict){
        System.out.println(cgwDict.getSql());
    }
    @Test
    public void cgwDict() {
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("HTTP").type("URL_TYPE").descn("URL").orderNum(1).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("HTTPS").type("URL_SEND_TYPE").descn("URL请求提交方式").orderNum(2).build());
//        buildAndPrintln(CgwDict.builder().dictValue("03").lable("不限").type("URL_SEND_TYPE").descn("URL请求提交方式").orderNum(3).build());
//
//        buildAndPrintln(CgwDict.builder().dictValue("A0").lable("账号/密钥").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(1).build());
//        buildAndPrintln(CgwDict.builder().dictValue("A1").lable("Token-密文").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(2).build());
//        buildAndPrintln(CgwDict.builder().dictValue("A2").lable("Token-接口").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(3).build());
//        buildAndPrintln(CgwDict.builder().dictValue("A3").lable("密钥文件").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
////
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("查询计费").type("BILL_TYPE").descn("计费类型").orderNum(1).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("查得计费").type("BILL_TYPE").descn("计费类型").orderNum(2).build());
          buildAndPrintln(CgwDict.builder().dictValue("03").lable("无计费").type("BILL_TYPE").descn("计费类型").orderNum(6).build());


//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("是").type("CONFIRM_TYPE").descn("是或否类别").orderNum(1).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("否").type("CONFIRM_TYPE").descn("是或否类别").orderNum(2).build());
////
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("string").type("DATA_TYPE").descn("数据类型").orderNum(1).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("int").type("DATA_TYPE").descn("数据类型").orderNum(2).build());
//        buildAndPrintln(CgwDict.builder().dictValue("03").lable("boolean").type("DATA_TYPE").descn("数据类型").orderNum(3).build());
//        buildAndPrintln(CgwDict.builder().dictValue("04").lable("lang").type("DATA_TYPE").descn("数据类型").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("05").lable("int").type("DATA_TYPE").descn("数据类型").orderNum(5).build());
//        buildAndPrintln(CgwDict.builder().dictValue("06").lable("int").type("DATA_TYPE").descn("数据类型").orderNum(6).build());
//        buildAndPrintln(CgwDict.builder().dictValue("07").lable("int").type("DATA_TYPE").descn("数据类型").orderNum(6).build());
//
//
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("小时").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("分钟").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
////
//
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("基础信息配置").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("参数配置").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("03").lable("调用配置").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("04").lable("缓存时间配置").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("05").lable("补偿配置").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("06").lable("上传文档").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("07").lable("回调配置").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("加工处理单元").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("逻辑处理单元").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//
//
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("调前处理").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("调后处理").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("03").lable("通用处理").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("jar").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("java").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("03").lable("R").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("04").lable("shell").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("05").lable("Python").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("请求头").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("请求体").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("String").type("DATA_TYPE").descn("数据类型").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("int").type("DATA_TYPE").descn("数据类型").orderNum(4).build());
//
//        buildAndPrintln(CgwDict.builder().dictValue("0101").lable("小时").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("0102").lable("缓存").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("post方式").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("get方式").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());



//
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("Token-http协议").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("https协议").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//
//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("启用").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("停用").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());
//        buildAndPrintln(CgwDict.builder().dictValue("03").lable("暂停").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());

//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("小时").type("AUTH_TYPE").descn("鉴权认证类别").orderNum(4).build());

//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("加工处理单元").type("HANDLE_FUN_TYPE").descn("处理单元类别").orderNum(2).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("逻辑处理单元").type("HANDLE_FUN_TYPE").descn("处理单元类别").orderNum(1).build());

//        buildAndPrintln(CgwDict.builder().dictValue("0").lable("失败").type("LOG_STATE_TYPE").descn("接口日志记录调用状态类别").orderNum(1).build());
//        buildAndPrintln(CgwDict.builder().dictValue("1").lable("成功").type("LOG_STATE_TYPE").descn("接口日志记录调用状态类别").orderNum(2).build());

//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("同步接口").type("API_TYPE").descn("接口类别").orderNum(1).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("异步接口").type("API_TYPE").descn("接口类别").orderNum(2).build());





//        buildAndPrintln(CgwDict.builder().dictValue("01").lable("请求头").type("BILL_TYPE").descn("位置类型").orderNum(1).build());
//        buildAndPrintln(CgwDict.builder().dictValue("02").lable("请求体").type("BILL_TYPE").descn("位置类型").orderNum(2).build());


    }
    @Data
    @Builder
    static class CgwDict {
        //
        private static final String format_have_parentId = "insert into cgw_dict " +
                "(id,dict_value,label,type,descn,order_num,parent_id,create_user,create_time,sts)" +
                " values " +
                "('%s','%s','%s','%s','%s',%s,'%s','ren_sh',now(),0);";
        private static final String format_no_parentId = "insert into cgw_dict " +
                "(id,dict_value,label,type,descn,order_num,create_user,create_time,sts)" +
                " values " +
                "('%s','%s','%s','%s','%s',%s,'ren_sh',now(),0);";
        //数据字典值
        private String dictValue;
        //字典name
        private String lable;
        //类型
        private String type;
        //描述
        private String descn;
        //排序字段
        private int orderNum;
        //父ID
        private String parentId;

        public String getSql() {
            if(null==getParentId()){
                return String.format(format_no_parentId,getUUID(), getDictValue(), getLable(), getType(), getDescn(), getOrderNum());
            }
            return String.format(format_have_parentId,getUUID(), getDictValue(), getLable(), getType(), getDescn(), getOrderNum(), getParentId());
        }
    }
    @Test
    public void t1123(){
        String path="/home/app/nfs_data/zxwg/class/V1.0.0/TimestampApp.class";
//        path=;
        System.out.println("----"+path);
    }

}