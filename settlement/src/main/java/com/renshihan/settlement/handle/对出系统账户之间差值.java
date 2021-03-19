package com.renshihan.settlement.handle;

import cn.gjing.tools.common.util.ParamUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.renshihan.settlement.bean.AccountBalanceData;
import com.renshihan.settlement.bean.CurrencySourceTypeTotalAmountBean;
import com.renshihan.settlement.bean.SubaccountQueryData;
import com.renshihan.settlement.enums.OrderEnum;
import com.renshihan.settlement.enums.SysAccountEnum;
import com.renshihan.settlement.utils.HPoiUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.awt.datatransfer.SystemFlavorMap;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 SELECT f_currency, f_source_type, f_side, f_product_type, sum( f_amount ) as f_amount
   FROM
        t_saving_order_record
   GROUP BY
        f_currency,
 f_source_type,
 f_side,
  f_product_type
  ORDER BY f_currency;

 挖矿宝内部账户反推出此时的系统账户的资金情况。
 */
@Slf4j
public class 对出系统账户之间差值 {
    private static String filePath = "/Users/renshihan/Desktop/";

    private static Map<String, BigDecimal> totalLixiMap = new HashMap<>();


    @Test
    public void test() {
        String fileName = "对账111.csv";
        fileName = "export_result (8).csv";
        Map<String, String> map = new HashMap<>();
        String huoqiguiji="{\"id\":18709440,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":2002,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        String lixizhichu="{\"id\":18709597,\"list\":[{\"balance\":10.22104963,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":9374.00558676,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":30000,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":46000,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":110009.85830537,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":29782.88639976,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":430.51743527,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":14496.35683345,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":2660969.75310488,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":2799033.06213297,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":106728.79284142,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        String huoqizhichu="{\"id\":18709540,\"list\":[{\"balance\":13250.29425719,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":3089889.70255132,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":1267613,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":2225654.5688343,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":140912.81937045,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":1795429.39800129,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":666314473.13967472,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":22898138.06131905,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":6363342.68856011,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        String dingqizhichu="{\"id\":18709563,\"list\":[{\"balance\":1000,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":2998481,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":3499913,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":48815961,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":2000000,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        String dingqiguiji="{\"id\":18709491,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":369,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";


        dingqiguiji="{\"id\":18709491,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":28000,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        huoqiguiji="{\"id\":18709440,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":4002,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        dingqizhichu="{\"id\":18709563,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":2998481,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":3499913,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":48958851,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":2000000,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        huoqizhichu="{\"id\":18709540,\"list\":[{\"balance\":14089.71943612,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":1874779.50736204,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":2256604,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":2215407.9119953,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":140703.27445983,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":1735254.72084544,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":659795980.68644072,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":22727767.56800648,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":6350722.35846148,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        lixizhichu="{\"id\":18709597,\"list\":[{\"balance\":8.16876525,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":9374.00558676,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":29735.23511682,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":46000,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":110009.85830537,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":29622.57502969,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":426.80383406,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":14377.41354019,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":2559582.91777093,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":2796138.99055478,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":106287.05999243,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";

        lixizhichu="{\"id\":18709597,\"list\":[{\"balance\":8.16876525,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":9374.00558676,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":29735.23511682,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":46000,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":110009.85830537,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":29622.57502969,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":426.80383406,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":14377.41354019,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":2559582.91777093,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":2796138.99055478,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":106287.05999243,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        dingqiguiji="{\"id\":18709491,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":205,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":43787,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        dingqizhichu="{\"id\":18709563,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":3072421,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":3499913,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":49035536,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":2000000,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        huoqizhichu="{\"id\":18709540,\"list\":[{\"balance\":14086.43268657,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":2238573.51307161,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":2228688,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":2206782.99360975,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":141138.69919594,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":1737049.98489337,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":655981427.14723501,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":22724629.96921553,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":6330747.31199694,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        huoqiguiji="{\"id\":18709440,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":2002,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";

        dingqizhichu="{\"id\":18709563,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":3656486,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":3499913,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":49456984,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":2000000,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        dingqiguiji="{\"id\":18709491,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":1568,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        huoqizhichu="{\"id\":18709540,\"list\":[{\"balance\":14103.36191055,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":2441955.02267699,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":3768996,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":2167734.84877771,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":141200.38181206,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":1732225.07468499,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":651205401.84046129,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":22803482.88459709,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":3535800.05561384,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        lixizhichu="{\"id\":18709597,\"list\":[{\"balance\":8.16876525,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":9374.00558676,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":29735.23511682,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":46000,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":110009.85830537,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":29622.57502969,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":426.80383406,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":14377.41354019,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":2559582.91777093,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":2796138.99055478,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":106287.05999243,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        huoqiguiji="{\"id\":18709440,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":5691,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";

        huoqiguiji="{\"id\":18709440,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":2002,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        dingqiguiji="{\"id\":18709491,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":1568,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        huoqizhichu="{\"id\":18709540,\"list\":[{\"balance\":14117.27248274,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":2423483.54000484,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":3759908,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":2161495.70868529,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":141187.14278587,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":1732653.52071665,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":649227801.36139776,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":23018591.36736685,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":3419354.32612446,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        dingqizhichu="{\"id\":18709563,\"list\":[{\"balance\":0,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":3850576,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":3499913,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":49471100,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":3437638,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";
        lixizhichu="{\"id\":18709597,\"list\":[{\"balance\":8.16876525,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":9374.00558676,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":29735.23511682,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":46000,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":110009.85830537,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":29622.57502969,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":426.80383406,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":14377.41354019,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":2559582.91777093,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":2796138.99055478,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":106287.05999243,\"currency\":\"husd\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}";




        map.put("活期归集账户", huoqiguiji);
        map.put("定期归集账户", dingqiguiji);
        map.put("活期支出账户", huoqizhichu);
        map.put("定期支出账户", dingqizhichu);
        map.put("利息账户", lixizhichu);



//        map.put("挖矿宝收入归集账户AccountId", "{\"id\":10350896,\"list\":[{\"balance\":20791.51852828,\"currency\":\"ksm\",\"type\":\"system\"},{\"balance\":9941.84169302,\"currency\":\"lun\",\"type\":\"system\"},{\"balance\":215617.29278411,\"currency\":\"fil\",\"type\":\"system\"},{\"balance\":7679.81344596,\"currency\":\"bal\",\"type\":\"system\"},{\"balance\":18074458.64371517,\"currency\":\"kcash\",\"type\":\"system\"},{\"balance\":594940.61050434,\"currency\":\"theta\",\"type\":\"system\"},{\"balance\":8099038.53942168,\"currency\":\"npxs\",\"type\":\"system\"},{\"balance\":53660.68174975,\"currency\":\"fis\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"bar\",\"type\":\"system\"},{\"balance\":6812849.30681917,\"currency\":\"trio\",\"type\":\"system\"},{\"balance\":76971734.18871603,\"currency\":\"egt\",\"type\":\"system\"},{\"balance\":732017.58915642,\"currency\":\"stpt\",\"type\":\"system\"},{\"balance\":596399.00103563,\"currency\":\"bat\",\"type\":\"system\"},{\"balance\":6218577.15290523,\"currency\":\"mxc\",\"type\":\"system\"},{\"balance\":268056.23392939,\"currency\":\"near\",\"type\":\"system\"},{\"balance\":5842.73905726,\"currency\":\"xmr\",\"type\":\"system\"},{\"balance\":13006.6713664,\"currency\":\"you\",\"type\":\"system\"},{\"balance\":3884348.57625104,\"currency\":\"iris\",\"type\":\"system\"},{\"balance\":211615812.89204885,\"currency\":\"xmx\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"cdc\",\"type\":\"system\"},{\"balance\":1759349.777768,\"currency\":\"mxm\",\"type\":\"system\"},{\"balance\":2105212.59850983,\"currency\":\"ae\",\"type\":\"system\"},{\"balance\":14977.61,\"currency\":\"e9f9\",\"type\":\"system\"},{\"balance\":69079.8964171,\"currency\":\"a1b2\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"c5d5\",\"type\":\"system\"},{\"balance\":1843483.65106146,\"currency\":\"husd\",\"type\":\"system\"},{\"balance\":15967.2068883,\"currency\":\"ar\",\"type\":\"system\"},{\"balance\":6954.07704,\"currency\":\"epra\",\"type\":\"system\"},{\"balance\":25.32744201,\"currency\":\"dot2l\",\"type\":\"system\"},{\"balance\":5117.02150599,\"currency\":\"eth3l\",\"type\":\"system\"},{\"balance\":64844276.59898653,\"currency\":\"cova\",\"type\":\"system\"},{\"balance\":4384.01897409,\"currency\":\"wnxm\",\"type\":\"system\"},{\"balance\":50446608.4275081,\"currency\":\"eth3s\",\"type\":\"system\"},{\"balance\":196043.39788233,\"currency\":\"dgb\",\"type\":\"system\"},{\"balance\":53224.10055407,\"currency\":\"dot2s\",\"type\":\"system\"},{\"balance\":9.33062202,\"currency\":\"dgd\",\"type\":\"system\"},{\"balance\":628943.97864184,\"currency\":\"zrx\",\"type\":\"system\"},{\"balance\":24254822.88697935,\"currency\":\"aac\",\"type\":\"system\"},{\"balance\":75216.40787179,\"currency\":\"bcd\",\"type\":\"system\"},{\"balance\":18833.90715668,\"currency\":\"bcc\",\"type\":\"system\"},{\"balance\":143037.96169458,\"currency\":\"awnc\",\"type\":\"system\"},{\"balance\":44588849.51176147,\"currency\":\"jst\",\"type\":\"system\"},{\"balance\":11320456.68534635,\"currency\":\"hot\",\"type\":\"system\"},{\"balance\":55465.22867122,\"currency\":\"qash\",\"type\":\"system\"},{\"balance\":70815.5689929,\"currency\":\"ardr\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"c3d3\",\"type\":\"system\"},{\"balance\":1895836.23473549,\"currency\":\"bcv\",\"type\":\"system\"},{\"balance\":3646253.94206532,\"currency\":\"bcx\",\"type\":\"system\"},{\"balance\":2828760.13218547,\"currency\":\"matic\",\"type\":\"system\"},{\"balance\":76809.04844525,\"currency\":\"rcn\",\"type\":\"system\"},{\"balance\":15371787.56414761,\"currency\":\"uip\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"mzk\",\"type\":\"system\"},{\"balance\":3175.85813538,\"currency\":\"salt\",\"type\":\"system\"},{\"balance\":20262582.230574,\"currency\":\"lxt\",\"type\":\"system\"},{\"balance\":99474.86455549,\"currency\":\"nhbtc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"abl\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"c4d4\",\"type\":\"system\"},{\"balance\":459016.47435743,\"currency\":\"gnt\",\"type\":\"system\"},{\"balance\":13909988.0813952,\"currency\":\"hpt\",\"type\":\"system\"},{\"balance\":113405.66029676,\"currency\":\"thb\",\"type\":\"system\"},{\"balance\":8.17267111,\"currency\":\"a8s8\",\"type\":\"system\"},{\"balance\":3867669.8876917,\"currency\":\"gnx\",\"type\":\"system\"},{\"balance\":11914.09685058,\"currency\":\"dht\",\"type\":\"system\"},{\"balance\":636183.02065527,\"currency\":\"abt\",\"type\":\"system\"},{\"balance\":0.354,\"currency\":\"b1b2\",\"type\":\"system\"},{\"balance\":8493.26810911,\"currency\":\"rdn\",\"type\":\"system\"},{\"balance\":699.968182,\"currency\":\"xpt\",\"type\":\"system\"},{\"balance\":3283.30134239,\"currency\":\"eth1s\",\"type\":\"system\"},{\"balance\":139998.01221402,\"currency\":\"gof\",\"type\":\"system\"},{\"balance\":515827.99170825,\"currency\":\"lym\",\"type\":\"system\"},{\"balance\":16779923.52699745,\"currency\":\"zjlt\",\"type\":\"system\"},{\"balance\":53277.45135042,\"currency\":\"ddam\",\"type\":\"system\"},{\"balance\":251442.6109617,\"currency\":\"df\",\"type\":\"system\"},{\"balance\":35544936.2189374,\"currency\":\"ach\",\"type\":\"system\"},{\"balance\":8601263.48925357,\"currency\":\"eko\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"sexc\",\"type\":\"system\"},{\"balance\":18429777.15083118,\"currency\":\"ekt\",\"type\":\"system\"},{\"balance\":17045370.27159624,\"currency\":\"act\",\"type\":\"system\"},{\"balance\":1946314.93175607,\"currency\":\"woo\",\"type\":\"system\"},{\"balance\":1079957.80145935,\"currency\":\"itc\",\"type\":\"system\"},{\"balance\":1726.401548,\"currency\":\"meetone\",\"type\":\"system\"},{\"balance\":4561266.44057393,\"currency\":\"pai\",\"type\":\"system\"},{\"balance\":1596268.8931642,\"currency\":\"ren\",\"type\":\"system\"},{\"balance\":48255.28581849,\"currency\":\"ela\",\"type\":\"system\"},{\"balance\":1601.20072,\"currency\":\"bfc\",\"type\":\"system\"},{\"balance\":335302.36862058,\"currency\":\"elf\",\"type\":\"system\"},{\"balance\":8820192.52167364,\"currency\":\"ada\",\"type\":\"system\"},{\"balance\":135179.32568233,\"currency\":\"req\",\"type\":\"system\"},{\"balance\":6862.26576,\"currency\":\"add\",\"type\":\"system\"},{\"balance\":988.02292566,\"currency\":\"eos3l\",\"type\":\"system\"},{\"balance\":10875.176716,\"currency\":\"usd01\",\"type\":\"system\"},{\"balance\":9204.52394564,\"currency\":\"eost\",\"type\":\"system\"},{\"balance\":4236.84018223,\"currency\":\"tusd\",\"type\":\"system\"},{\"balance\":171.9715102,\"currency\":\"eoss\",\"type\":\"system\"},{\"balance\":38782.57927089,\"currency\":\"pax\",\"type\":\"system\"},{\"balance\":27076102.57277132,\"currency\":\"em\",\"type\":\"system\"},{\"balance\":22040.00063461,\"currency\":\"hb10\",\"type\":\"system\"},{\"balance\":71735.71689952,\"currency\":\"pay\",\"type\":\"system\"},{\"balance\":133041.05450503,\"currency\":\"eos3s\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"rbtc\",\"type\":\"system\"},{\"balance\":1370869.09553036,\"currency\":\"chr\",\"type\":\"system\"},{\"balance\":112812107.48172467,\"currency\":\"egcc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"adt\",\"type\":\"system\"},{\"balance\":423732.55498867,\"currency\":\"bft\",\"type\":\"system\"},{\"balance\":666348.74312076,\"currency\":\"sand\",\"type\":\"system\"},{\"balance\":76527027.37529816,\"currency\":\"she\",\"type\":\"system\"},{\"balance\":310.81217235,\"currency\":\"bch3l\",\"type\":\"system\"},{\"balance\":7853.83456468,\"currency\":\"adx\",\"type\":\"system\"},{\"balance\":1240142.57952038,\"currency\":\"bch3s\",\"type\":\"system\"},{\"balance\":2715289.54093651,\"currency\":\"chz\",\"type\":\"system\"},{\"balance\":32832337.99309687,\"currency\":\"xrp\",\"type\":\"system\"},{\"balance\":57405.61716662,\"currency\":\"wpr\",\"type\":\"system\"},{\"balance\":1604892.30669529,\"currency\":\"dka\",\"type\":\"system\"},{\"balance\":1381.8771955,\"currency\":\"xrt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"sht\",\"type\":\"system\"},{\"balance\":4453.6201176,\"currency\":\"bqcc\",\"type\":\"system\"},{\"balance\":163312.22219311,\"currency\":\"hsr\",\"type\":\"system\"},{\"balance\":4526056.77670975,\"currency\":\"for\",\"type\":\"system\"},{\"balance\":4598.32192835,\"currency\":\"uma\",\"type\":\"system\"},{\"balance\":4083.47244021,\"currency\":\"sbtc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"emt\",\"type\":\"system\"},{\"balance\":385911.49463884,\"currency\":\"luna\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"lume\",\"type\":\"system\"},{\"balance\":8174.97745479,\"currency\":\"bcha\",\"type\":\"system\"},{\"balance\":893.75366719,\"currency\":\"yfii\",\"type\":\"system\"},{\"balance\":14997.272,\"currency\":\"c1d1\",\"type\":\"system\"},{\"balance\":0.10132,\"currency\":\"pci\",\"type\":\"system\"},{\"balance\":97141.95015417,\"currency\":\"gc\",\"type\":\"system\"},{\"balance\":137255.1046007,\"currency\":\"lend\",\"type\":\"system\"},{\"balance\":88935.56908128,\"currency\":\"bhd\",\"type\":\"system\"},{\"balance\":562.19400329,\"currency\":\"eng\",\"type\":\"system\"},{\"balance\":864781.46251535,\"currency\":\"grt\",\"type\":\"system\"},{\"balance\":7939.34194698,\"currency\":\"grs\",\"type\":\"system\"},{\"balance\":81905.89364123,\"currency\":\"gt\",\"type\":\"system\"},{\"balance\":146507.00760884,\"currency\":\"bht\",\"type\":\"system\"},{\"balance\":385.26336757,\"currency\":\"bacc\",\"type\":\"system\"},{\"balance\":380403.93109101,\"currency\":\"uni\",\"type\":\"system\"},{\"balance\":1996212.59637371,\"currency\":\"gsc\",\"type\":\"system\"},{\"balance\":30607104.63902788,\"currency\":\"ckb\",\"type\":\"system\"},{\"balance\":205177.87336778,\"currency\":\"xtz\",\"type\":\"system\"},{\"balance\":319013.76462899,\"currency\":\"gsl\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"eon\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"eop\",\"type\":\"system\"},{\"balance\":1638213.789043,\"currency\":\"eos\",\"type\":\"system\"},{\"balance\":22005924.18694226,\"currency\":\"ht\",\"type\":\"system\"},{\"balance\":458807.80594103,\"currency\":\"bix\",\"type\":\"system\"},{\"balance\":26920134.91471879,\"currency\":\"skm\",\"type\":\"system\"},{\"balance\":302783.29751859,\"currency\":\"skl\",\"type\":\"system\"},{\"balance\":548681.88632041,\"currency\":\"gtc\",\"type\":\"system\"},{\"balance\":319207.32379331,\"currency\":\"1inch\",\"type\":\"system\"},{\"balance\":79638208.19415989,\"currency\":\"ocn\",\"type\":\"system\"},{\"balance\":49880.32734254,\"currency\":\"flow\",\"type\":\"system\"},{\"balance\":267468.35771807,\"currency\":\"nas\",\"type\":\"system\"},{\"balance\":104709.45634526,\"currency\":\"io\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"hvt\",\"type\":\"system\"},{\"balance\":102953.26149027,\"currency\":\"wtc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"iq\",\"type\":\"system\"},{\"balance\":2135812.74529034,\"currency\":\"xvg\",\"type\":\"system\"},{\"balance\":16351584.22873464,\"currency\":\"tnb\",\"type\":\"system\"},{\"balance\":66450137.59308876,\"currency\":\"btc3s\",\"type\":\"system\"},{\"balance\":26086.52671368,\"currency\":\"powr\",\"type\":\"system\"},{\"balance\":4120.51773558,\"currency\":\"btc3l\",\"type\":\"system\"},{\"balance\":17.83586,\"currency\":\"b3l3\",\"type\":\"system\"},{\"balance\":384390.48284979,\"currency\":\"tnt\",\"type\":\"system\"},{\"balance\":2695336.75478217,\"currency\":\"nbs\",\"type\":\"system\"},{\"balance\":380988.76120518,\"currency\":\"fsn\",\"type\":\"system\"},{\"balance\":6650.39716965,\"currency\":\"bags\",\"type\":\"system\"},{\"balance\":963657.0948134,\"currency\":\"dot\",\"type\":\"system\"},{\"balance\":5699129.75957999,\"currency\":\"cmt\",\"type\":\"system\"},{\"balance\":1003907.02644974,\"currency\":\"ncc\",\"type\":\"system\"},{\"balance\":14520.984,\"currency\":\"h9h9\",\"type\":\"system\"},{\"balance\":54760873.81785413,\"currency\":\"top\",\"type\":\"system\"},{\"balance\":2726808.29497018,\"currency\":\"cvnt\",\"type\":\"system\"},{\"balance\":32746904.36346903,\"currency\":\"gve\",\"type\":\"system\"},{\"balance\":8537255.65412269,\"currency\":\"tos\",\"type\":\"system\"},{\"balance\":489210334.45490777,\"currency\":\"fti\",\"type\":\"system\"},{\"balance\":244057.79094491,\"currency\":\"man\",\"type\":\"system\"},{\"balance\":14801937.42298129,\"currency\":\"smt\",\"type\":\"system\"},{\"balance\":331389136.22233882,\"currency\":\"cnn\",\"type\":\"system\"},{\"balance\":4036.88806708,\"currency\":\"ftt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"mpct\",\"type\":\"system\"},{\"balance\":548603.5426779,\"currency\":\"pha\",\"type\":\"system\"},{\"balance\":569817.44944245,\"currency\":\"snc\",\"type\":\"system\"},{\"balance\":711308.99258576,\"currency\":\"blz\",\"type\":\"system\"},{\"balance\":14610.17250861,\"currency\":\"api3\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"sno\",\"type\":\"system\"},{\"balance\":13263.96922969,\"currency\":\"yamv2\",\"type\":\"system\"},{\"balance\":1756520.42500264,\"currency\":\"snt\",\"type\":\"system\"},{\"balance\":203111.05828353,\"currency\":\"atom\",\"type\":\"system\"},{\"balance\":29554.58133413,\"currency\":\"snx\",\"type\":\"system\"},{\"balance\":11217448.95377743,\"currency\":\"soc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"usd\",\"type\":\"system\"},{\"balance\":74531.90929981,\"currency\":\"x8z9\",\"type\":\"system\"},{\"balance\":15017.46,\"currency\":\"h8h8\",\"type\":\"system\"},{\"balance\":12918.52259825,\"currency\":\"sol\",\"type\":\"system\"},{\"balance\":22867497.46443316,\"currency\":\"ltc3s\",\"type\":\"system\"},{\"balance\":230330.9352913,\"currency\":\"etc\",\"type\":\"system\"},{\"balance\":7689500.35375651,\"currency\":\"ogo\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"pip\",\"type\":\"system\"},{\"balance\":710204.43097908,\"currency\":\"ogn\",\"type\":\"system\"},{\"balance\":6744887.07520212,\"currency\":\"aidoc\",\"type\":\"system\"},{\"balance\":43229.29481999,\"currency\":\"eth\",\"type\":\"system\"},{\"balance\":6301.9190823,\"currency\":\"mco\",\"type\":\"system\"},{\"balance\":2789560.15989186,\"currency\":\"topc\",\"type\":\"system\"},{\"balance\":32356.6242045,\"currency\":\"neo\",\"type\":\"system\"},{\"balance\":642494.87414156,\"currency\":\"sushi\",\"type\":\"system\"},{\"balance\":134.4982038,\"currency\":\"ltc3l\",\"type\":\"system\"},{\"balance\":1147083.69761356,\"currency\":\"etn\",\"type\":\"system\"},{\"balance\":12975.66475916,\"currency\":\"xzc\",\"type\":\"system\"},{\"balance\":187970148.06807656,\"currency\":\"new\",\"type\":\"system\"},{\"balance\":198538.63527518,\"currency\":\"gxs\",\"type\":\"system\"},{\"balance\":9897.17142494,\"currency\":\"trb\",\"type\":\"system\"},{\"balance\":57135.03921111,\"currency\":\"fil3s\",\"type\":\"system\"},{\"balance\":10067623.5942881,\"currency\":\"mt\",\"type\":\"system\"},{\"balance\":21163.95566067,\"currency\":\"bnt\",\"type\":\"system\"},{\"balance\":9873607.45860874,\"currency\":\"lba\",\"type\":\"system\"},{\"balance\":912695.88788179,\"currency\":\"mx\",\"type\":\"system\"},{\"balance\":465.20893176,\"currency\":\"fil3l\",\"type\":\"system\"},{\"balance\":391852.97130742,\"currency\":\"utk\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"spn\",\"type\":\"system\"},{\"balance\":1992614.37794816,\"currency\":\"wxt\",\"type\":\"system\"},{\"balance\":112226242.4837595,\"currency\":\"trx\",\"type\":\"system\"},{\"balance\":17607071.60285181,\"currency\":\"mds\",\"type\":\"system\"},{\"balance\":186433.1255768,\"currency\":\"nuls\",\"type\":\"system\"},{\"balance\":628440.55184337,\"currency\":\"mdx\",\"type\":\"system\"},{\"balance\":237.13171958,\"currency\":\"bor\",\"type\":\"system\"},{\"balance\":72.78716187,\"currency\":\"bot\",\"type\":\"system\"},{\"balance\":20967115.08886398,\"currency\":\"box\",\"type\":\"system\"},{\"balance\":2084494.10288142,\"currency\":\"mee\",\"type\":\"system\"},{\"balance\":76.33469481,\"currency\":\"uni2l\",\"type\":\"system\"},{\"balance\":3634329.42291095,\"currency\":\"arpa\",\"type\":\"system\"},{\"balance\":186672828.03848947,\"currency\":\"dta\",\"type\":\"system\"},{\"balance\":11577.73878,\"currency\":\"mchain\",\"type\":\"system\"},{\"balance\":164631269.59636901,\"currency\":\"uuu\",\"type\":\"system\"},{\"balance\":8035613.6629995,\"currency\":\"uni2s\",\"type\":\"system\"},{\"balance\":17870358.41501422,\"currency\":\"cre\",\"type\":\"system\"},{\"balance\":1322.37921983,\"currency\":\"zec3l\",\"type\":\"system\"},{\"balance\":69.3075564,\"currency\":\"beth\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"e1f1\",\"type\":\"system\"},{\"balance\":14094976.61770661,\"currency\":\"kan\",\"type\":\"system\"},{\"balance\":72.71525003,\"currency\":\"a9a9\",\"type\":\"system\"},{\"balance\":186074.61483031,\"currency\":\"zec3s\",\"type\":\"system\"},{\"balance\":3156894.60401728,\"currency\":\"cro\",\"type\":\"system\"},{\"balance\":7173951.74365878,\"currency\":\"mex\",\"type\":\"system\"},{\"balance\":106940.90719834,\"currency\":\"ant\",\"type\":\"system\"},{\"balance\":1558517.22785224,\"currency\":\"crv\",\"type\":\"system\"},{\"balance\":15497.80480655,\"currency\":\"evx\",\"type\":\"system\"},{\"balance\":5189.7313276,\"currency\":\"cru\",\"type\":\"system\"},{\"balance\":5174.55168422,\"currency\":\"btc1s\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"plg\",\"type\":\"system\"},{\"balance\":15031.23,\"currency\":\"h7h7\",\"type\":\"system\"},{\"balance\":99010.39230973,\"currency\":\"srn\",\"type\":\"system\"},{\"balance\":18461.39480048,\"currency\":\"badger\",\"type\":\"system\"},{\"balance\":14413.54555309,\"currency\":\"plo\",\"type\":\"system\"},{\"balance\":47637617.1384454,\"currency\":\"pc\",\"type\":\"system\"},{\"balance\":10571991.58666819,\"currency\":\"lamb\",\"type\":\"system\"},{\"balance\":182097.00670614,\"currency\":\"rpx\",\"type\":\"system\"},{\"balance\":2435.14597451,\"currency\":\"bsv3l\",\"type\":\"system\"},{\"balance\":511668.41106745,\"currency\":\"ring\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"echo\",\"type\":\"system\"},{\"balance\":81213.78722968,\"currency\":\"x6z7\",\"type\":\"system\"},{\"balance\":34782566.04584747,\"currency\":\"ssp\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"mgo\",\"type\":\"system\"},{\"balance\":14257575.63664689,\"currency\":\"let\",\"type\":\"system\"},{\"balance\":38699.72917067,\"currency\":\"band\",\"type\":\"system\"},{\"balance\":11543474.72639409,\"currency\":\"iotx\",\"type\":\"system\"},{\"balance\":2409064.98137745,\"currency\":\"dock\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"bt1\",\"type\":\"system\"},{\"balance\":1096.53049542,\"currency\":\"pearl\",\"type\":\"system\"},{\"balance\":49832.53841729,\"currency\":\"waves\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"bt2\",\"type\":\"system\"},{\"balance\":10506211.10163462,\"currency\":\"reef\",\"type\":\"system\"},{\"balance\":599072.71515207,\"currency\":\"iota\",\"type\":\"system\"},{\"balance\":3067534.55247154,\"currency\":\"stk\",\"type\":\"system\"},{\"balance\":15029.28528,\"currency\":\"h6h6\",\"type\":\"system\"},{\"balance\":243857592.29425951,\"currency\":\"node\",\"type\":\"system\"},{\"balance\":24763866.60465185,\"currency\":\"musk\",\"type\":\"system\"},{\"balance\":73038984.41380214,\"currency\":\"iost\",\"type\":\"system\"},{\"balance\":84857600.06073945,\"currency\":\"pnt\",\"type\":\"system\"},{\"balance\":70348.86711492,\"currency\":\"bsv3s\",\"type\":\"system\"},{\"balance\":28309.18349934,\"currency\":\"bsv\",\"type\":\"system\"},{\"balance\":331012.33278199,\"currency\":\"omg\",\"type\":\"system\"},{\"balance\":28.66043,\"currency\":\"lgc\",\"type\":\"system\"},{\"balance\":76999.76313978,\"currency\":\"sun\",\"type\":\"system\"},{\"balance\":2.61664534,\"currency\":\"wbtc\",\"type\":\"system\"},{\"balance\":76116.11829276,\"currency\":\"appc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"x5z5\",\"type\":\"system\"},{\"balance\":3122.81860395,\"currency\":\"btc\",\"type\":\"system\"},{\"balance\":1834693.44592253,\"currency\":\"nkn\",\"type\":\"system\"},{\"balance\":44327182.60791782,\"currency\":\"rsr\",\"type\":\"system\"},{\"balance\":3543410.93280761,\"currency\":\"sc\",\"type\":\"system\"},{\"balance\":994138.76534271,\"currency\":\"cvc\",\"type\":\"system\"},{\"balance\":637.00600907,\"currency\":\"btg\",\"type\":\"system\"},{\"balance\":1425.03144007,\"currency\":\"link3l\",\"type\":\"system\"},{\"balance\":7992426.70340951,\"currency\":\"btm\",\"type\":\"system\"},{\"balance\":154393.18067426,\"currency\":\"cvn\",\"type\":\"system\"},{\"balance\":13775.75292549,\"currency\":\"cvp\",\"type\":\"system\"},{\"balance\":90926806.99986504,\"currency\":\"link3s\",\"type\":\"system\"},{\"balance\":6069383.13038412,\"currency\":\"bts\",\"type\":\"system\"},{\"balance\":6783892.17354803,\"currency\":\"rte\",\"type\":\"system\"},{\"balance\":13277.16349635,\"currency\":\"ars\",\"type\":\"system\"},{\"balance\":2336348190.88507835,\"currency\":\"btt\",\"type\":\"system\"},{\"balance\":1087077.29034206,\"currency\":\"rccc\",\"type\":\"system\"},{\"balance\":34501.91835846,\"currency\":\"yam\",\"type\":\"system\"},{\"balance\":11632751.75473304,\"currency\":\"one\",\"type\":\"system\"},{\"balance\":0.879296,\"currency\":\"ong\",\"type\":\"system\"},{\"balance\":3770.7952912,\"currency\":\"efor\",\"type\":\"system\"},{\"balance\":13947929.63747511,\"currency\":\"nest\",\"type\":\"system\"},{\"balance\":906600.17114912,\"currency\":\"storj\",\"type\":\"system\"},{\"balance\":773476.15583362,\"currency\":\"ugas\",\"type\":\"system\"},{\"balance\":1944997.04814812,\"currency\":\"mana\",\"type\":\"system\"},{\"balance\":1275117.9004186,\"currency\":\"ont\",\"type\":\"system\"},{\"balance\":3928952.0710074,\"currency\":\"bkbt\",\"type\":\"system\"},{\"balance\":9994913.44509449,\"currency\":\"ruff\",\"type\":\"system\"},{\"balance\":6278561.7796019,\"currency\":\"ankr\",\"type\":\"system\"},{\"balance\":1349381.14439252,\"currency\":\"algo\",\"type\":\"system\"},{\"balance\":4449867.44101878,\"currency\":\"but\",\"type\":\"system\"},{\"balance\":34293962.59375724,\"currency\":\"tt\",\"type\":\"system\"},{\"balance\":742824.64317668,\"currency\":\"ast\",\"type\":\"system\"},{\"balance\":986425.0719052,\"currency\":\"wicc\",\"type\":\"system\"},{\"balance\":175233136.83044925,\"currency\":\"doge\",\"type\":\"system\"},{\"balance\":35484.13637071,\"currency\":\"nano\",\"type\":\"system\"},{\"balance\":30416980.57323089,\"currency\":\"uc\",\"type\":\"system\"},{\"balance\":96260.48902313,\"currency\":\"qsp\",\"type\":\"system\"},{\"balance\":1263450.05909837,\"currency\":\"loom\",\"type\":\"system\"},{\"balance\":212.64245315,\"currency\":\"mkr\",\"type\":\"system\"},{\"balance\":491571.7996648,\"currency\":\"ycc\",\"type\":\"system\"},{\"balance\":35887.63312699,\"currency\":\"zec\",\"type\":\"system\"},{\"balance\":333125.55676948,\"currency\":\"hbar\",\"type\":\"system\"},{\"balance\":31979940.60320363,\"currency\":\"atp\",\"type\":\"system\"},{\"balance\":6993714.43995599,\"currency\":\"ncash\",\"type\":\"system\"},{\"balance\":73289.01069287,\"currency\":\"icx\",\"type\":\"system\"},{\"balance\":2978740.27863551,\"currency\":\"vsys\",\"type\":\"system\"},{\"balance\":0.95139948,\"currency\":\"zel\",\"type\":\"system\"},{\"balance\":8411.96130285,\"currency\":\"zen\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"a7b7\",\"type\":\"system\"},{\"balance\":1744969.3886426,\"currency\":\"rvn\",\"type\":\"system\"},{\"balance\":110513.17951145,\"currency\":\"pro\",\"type\":\"system\"},{\"balance\":816.3147474,\"currency\":\"mln\",\"type\":\"system\"},{\"balance\":72821.33484362,\"currency\":\"kava\",\"type\":\"system\"},{\"balance\":47322410.78516211,\"currency\":\"idr\",\"type\":\"system\"},{\"balance\":3226869.40454868,\"currency\":\"idt\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"tfuel\",\"type\":\"system\"},{\"balance\":64249760.48342499,\"currency\":\"vidy\",\"type\":\"system\"},{\"balance\":542154.8860621,\"currency\":\"mass\",\"type\":\"system\"},{\"balance\":1505823.60695459,\"currency\":\"qun\",\"type\":\"system\"},{\"balance\":7346.41925856,\"currency\":\"auction\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"pizza\",\"type\":\"system\"},{\"balance\":5636.9975036,\"currency\":\"gas\",\"type\":\"system\"},{\"balance\":84767428.3216705,\"currency\":\"yee\",\"type\":\"system\"},{\"balance\":279375.56537218,\"currency\":\"nexo\",\"type\":\"system\"},{\"balance\":8327323.71712394,\"currency\":\"ptc\",\"type\":\"system\"},{\"balance\":57667.86935776,\"currency\":\"mask\",\"type\":\"system\"},{\"balance\":5185.83146208,\"currency\":\"aave\",\"type\":\"system\"},{\"balance\":7.15730696,\"currency\":\"pti\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"a6b6\",\"type\":\"system\"},{\"balance\":25788.43958743,\"currency\":\"wan\",\"type\":\"system\"},{\"balance\":14987.13,\"currency\":\"h3h3\",\"type\":\"system\"},{\"balance\":642001.67659823,\"currency\":\"wax\",\"type\":\"system\"},{\"balance\":0.03027,\"currency\":\"eman\",\"type\":\"system\"},{\"balance\":61510049.33566092,\"currency\":\"swftc\",\"type\":\"system\"},{\"balance\":70.22368838,\"currency\":\"yfi\",\"type\":\"system\"},{\"balance\":15687.33097868,\"currency\":\"avax\",\"type\":\"system\"},{\"balance\":2390961776.46151191,\"currency\":\"xrp3s\",\"type\":\"system\"},{\"balance\":106525.28322289,\"currency\":\"yfv\",\"type\":\"system\"},{\"balance\":178.682172,\"currency\":\"fac\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"a4a4\",\"type\":\"system\"},{\"balance\":1291594.22312997,\"currency\":\"fair\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"mon\",\"type\":\"system\"},{\"balance\":129362.85808869,\"currency\":\"ost\",\"type\":\"system\"},{\"balance\":7727130.64955869,\"currency\":\"zil\",\"type\":\"system\"},{\"balance\":4936.34359526,\"currency\":\"xrp3l\",\"type\":\"system\"},{\"balance\":947.41085764,\"currency\":\"comp\",\"type\":\"system\"},{\"balance\":1225709.21001094,\"currency\":\"xem\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"a8s83l\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"a5b5\",\"type\":\"system\"},{\"balance\":7.72730494,\"currency\":\"a8s83s\",\"type\":\"system\"},{\"balance\":1199015.93765534,\"currency\":\"18t\",\"type\":\"system\"},{\"balance\":166271059.35258602,\"currency\":\"pvt\",\"type\":\"system\"},{\"balance\":1489398.05135599,\"currency\":\"ctxc\",\"type\":\"system\"},{\"balance\":261400.10367408,\"currency\":\"wdc\",\"type\":\"system\"},{\"balance\":775981.01868532,\"currency\":\"hive\",\"type\":\"system\"},{\"balance\":222320156.62583103,\"currency\":\"iic\",\"type\":\"system\"},{\"balance\":12387.95985631,\"currency\":\"kmd\",\"type\":\"system\"},{\"balance\":506836.3433965,\"currency\":\"steem\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"a3a3\",\"type\":\"system\"},{\"balance\":315842027.35402452,\"currency\":\"lol\",\"type\":\"system\"},{\"balance\":2582456.02516458,\"currency\":\"get\",\"type\":\"system\"},{\"balance\":13300689.49890599,\"currency\":\"cnns\",\"type\":\"system\"},{\"balance\":89278.26767962,\"currency\":\"knc\",\"type\":\"system\"},{\"balance\":405266.27598204,\"currency\":\"zks\",\"type\":\"system\"},{\"balance\":2012252.1866974,\"currency\":\"bifi\",\"type\":\"system\"},{\"balance\":408.463988,\"currency\":\"wet\",\"type\":\"system\"},{\"balance\":466580.77787471,\"currency\":\"zla\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"fanco\",\"type\":\"system\"},{\"balance\":4616932.61318286,\"currency\":\"chat\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"eosdac\",\"type\":\"system\"},{\"balance\":13918.94941958,\"currency\":\"gfx\",\"type\":\"system\"},{\"balance\":9849.1326308,\"currency\":\"pola\",\"type\":\"system\"},{\"balance\":31246749.60012512,\"currency\":\"dac\",\"type\":\"system\"},{\"balance\":85878389.7276467,\"currency\":\"seele\",\"type\":\"system\"},{\"balance\":101432.96558978,\"currency\":\"dai\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"a2a2\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"gusd\",\"type\":\"system\"},{\"balance\":285986966.10241558,\"currency\":\"hit\",\"type\":\"system\"},{\"balance\":7435914.78427346,\"currency\":\"dat\",\"type\":\"system\"},{\"balance\":82787.90998006,\"currency\":\"titan\",\"type\":\"system\"},{\"balance\":282863702.29365507,\"currency\":\"usdt\",\"type\":\"system\"},{\"balance\":14284.8932677,\"currency\":\"mta\",\"type\":\"system\"},{\"balance\":191620.23959387,\"currency\":\"lrc\",\"type\":\"system\"},{\"balance\":0.10532512,\"currency\":\"renbtc\",\"type\":\"system\"},{\"balance\":267164.34430222,\"currency\":\"swrv\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"ven\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"wgp\",\"type\":\"system\"},{\"balance\":12759274.45995067,\"currency\":\"dbc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"j4j4\",\"type\":\"system\"},{\"balance\":2755.18212351,\"currency\":\"mtl\",\"type\":\"system\"},{\"balance\":26999743.51271998,\"currency\":\"vet\",\"type\":\"system\"},{\"balance\":700218.44666051,\"currency\":\"mtn\",\"type\":\"system\"},{\"balance\":32235.35339739,\"currency\":\"a3b4\",\"type\":\"system\"},{\"balance\":204026.89263348,\"currency\":\"oxt\",\"type\":\"system\"},{\"balance\":122131.45576189,\"currency\":\"usdc\",\"type\":\"system\"},{\"balance\":229490.96070303,\"currency\":\"mtx\",\"type\":\"system\"},{\"balance\":21310055.12306685,\"currency\":\"edu\",\"type\":\"system\"},{\"balance\":10976.4660566,\"currency\":\"dash\",\"type\":\"system\"},{\"balance\":3126659.37786681,\"currency\":\"pond\",\"type\":\"system\"},{\"balance\":1768674.77861695,\"currency\":\"lina\",\"type\":\"system\"},{\"balance\":6601.00490965,\"currency\":\"lsk\",\"type\":\"system\"},{\"balance\":50538.88974358,\"currency\":\"nsure\",\"type\":\"system\"},{\"balance\":117954.75127611,\"currency\":\"d8f8\",\"type\":\"system\"},{\"balance\":387605.99127726,\"currency\":\"link\",\"type\":\"system\"},{\"balance\":38229577.24538166,\"currency\":\"akro\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"a1a1\",\"type\":\"system\"},{\"balance\":206810.07221744,\"currency\":\"qtum\",\"type\":\"system\"},{\"balance\":1785.60756644,\"currency\":\"dcr\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"retc\",\"type\":\"system\"},{\"balance\":76563.01086404,\"currency\":\"ltc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"inc\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"mvl\",\"type\":\"system\"},{\"balance\":6913.76116282,\"currency\":\"inj\",\"type\":\"system\"},{\"balance\":0,\"currency\":\"c6d6\",\"type\":\"system\"},{\"balance\":35129220.36436076,\"currency\":\"datx\",\"type\":\"system\"},{\"balance\":2599.7913,\"currency\":\"krw\",\"type\":\"system\"},{\"balance\":285648.534008,\"currency\":\"eft\",\"type\":\"system\"},{\"balance\":193465.32237889,\"currency\":\"pols\",\"type\":\"system\"},{\"balance\":46989090.30628926,\"currency\":\"hbpoint\",\"type\":\"system\"},{\"balance\":60423.96663998,\"currency\":\"poly\",\"type\":\"system\"},{\"balance\":5203533.86602484,\"currency\":\"xlm\",\"type\":\"system\"},{\"balance\":50111.58970524,\"currency\":\"front\",\"type\":\"system\"}],\"state\":\"working\",\"subType\":\"1\",\"type\":\"system\"}");





        print(fileName, map);

        // usdt 2002 + 51802.54506502 =
    }

    private static void print(String fileName, Map<String, String> map) {
        Map<String, List<SubaccountQueryData>> accountBalanceData = getSysAccountBalanceData(map);
        Map<String, Map<String, BigDecimal>> wakuangbaoAmountMap = getWakuangbaoAmountMap(fileName, false);
        Map<String,Map<String,BigDecimal>> chazhiMap=new HashMap<>();
        wakuangbaoAmountMap.keySet().stream().forEach(code -> {

            List<SubaccountQueryData> sysAccountList = accountBalanceData.get(code);
            Map<String, BigDecimal> wkbMap = wakuangbaoAmountMap.get(code);

            for (SubaccountQueryData queryData : sysAccountList) {
                String currency = queryData.getCurrency().toUpperCase();
                if(!chazhiMap.containsKey(currency)){
                    chazhiMap.put(currency,new HashMap<>());
                }


                BigDecimal sysBalance = queryData.getBalance();
                BigDecimal wkbBalance = null == wkbMap.get(currency) ? BigDecimal.ZERO : wkbMap.get(currency);


                BigDecimal subtract = sysBalance.subtract(wkbBalance);
//存放差值
                chazhiMap.get(currency).put(code,subtract);

                if (sysBalance.compareTo(wkbBalance) == 0) {
                    continue;
                }

                System.out.println(currency + code + " 实际：" + sysBalance.toPlainString() + " 按流水评估:" + wkbBalance.toPlainString() + " 差值:" + subtract.toPlainString());

            }


            System.out.println();
            System.out.println("    ");
        });

        System.out.println("---差值分析---");
        chazhiMap.keySet().stream().forEach(currency->{

//            if(!currency.equals("USDT")){
//                return;
//            }
            Map<String, BigDecimal> amountMap = chazhiMap.get(currency);
            BigDecimal huoqiguiji=amountMap.containsKey(SysAccountEnum.DEMAND_IN_AMOUNT_ACCOUNT.getMessage())?amountMap.get(SysAccountEnum.DEMAND_IN_AMOUNT_ACCOUNT.getMessage()):BigDecimal.ZERO;
            BigDecimal dingqiguiji=amountMap.containsKey(SysAccountEnum.REGULAR_IN_AMOUNT_ACCOUNT.getMessage())?amountMap.get(SysAccountEnum.REGULAR_IN_AMOUNT_ACCOUNT.getMessage()):BigDecimal.ZERO;
            BigDecimal huoqizhichu=amountMap.containsKey(SysAccountEnum.DEMAND_OUT_AMOUNT_ACCOUNT.getMessage())?amountMap.get(SysAccountEnum.DEMAND_OUT_AMOUNT_ACCOUNT.getMessage()):BigDecimal.ZERO;
            BigDecimal dingqizhichu=amountMap.containsKey(SysAccountEnum.REGULAR_OUT_AMOUNT_ACCOUNT.getMessage())?amountMap.get(SysAccountEnum.REGULAR_OUT_AMOUNT_ACCOUNT.getMessage()):BigDecimal.ZERO;
            BigDecimal lixizhanghu=amountMap.containsKey(SysAccountEnum.INCOME_IN_OUT_AMOUNT_ACCOUNT.getMessage())?amountMap.get(SysAccountEnum.INCOME_IN_OUT_AMOUNT_ACCOUNT.getMessage()):BigDecimal.ZERO;

            BigDecimal huoqi = huoqizhichu.add(huoqiguiji);

            BigDecimal dingqi=dingqizhichu.add(dingqiguiji);
            System.out.println(currency+" 活期："+huoqi.toPlainString() +" 定期："+dingqi.toPlainString());

            if(lixizhanghu.compareTo(BigDecimal.ZERO)>0){       //表示实际比预期多利息，说明利息没有从利息账户付到支出
                System.out.println(currency + " " + huoqi.add(lixizhanghu).toPlainString()+" （考虑利息因素）");
            }else {
                //实际比预期少，说明利息可能多划（某次重复结息）
                if(currency.equals("USDT")){
                    //重复出款
                    System.out.println(currency + " 活期：" + huoqi.subtract(new BigDecimal("21699.8358375")).toPlainString()+" （考虑利息因素）");
                }else {

                }

            }
            System.out.println("   ");

        });


    }



    private static Map<String, Map<String, BigDecimal>> getWakuangbaoAmountMap(String fileName, boolean isPrint) {
        Map<String, Map<String, BigDecimal>> resultMap = new LinkedHashMap<>();
        resultMap.put(SysAccountEnum.DEMAND_IN_AMOUNT_ACCOUNT.getMessage(), new HashMap<>());
        resultMap.put(SysAccountEnum.REGULAR_IN_AMOUNT_ACCOUNT.getMessage(), new HashMap<>());
        resultMap.put(SysAccountEnum.DEMAND_OUT_AMOUNT_ACCOUNT.getMessage(), new HashMap<>());
        resultMap.put(SysAccountEnum.REGULAR_OUT_AMOUNT_ACCOUNT.getMessage(), new HashMap<>());
        resultMap.put(SysAccountEnum.INCOME_IN_OUT_AMOUNT_ACCOUNT.getMessage(), new HashMap<>());


        List<CurrencySourceTypeTotalAmountBean> list = HPoiUtils.exeCsv(new File(filePath + fileName), ImmutableList.of("currency", "sourceType", "side", "productType", "totalAmount"), CurrencySourceTypeTotalAmountBean.class);
        Map<String, List<CurrencySourceTypeTotalAmountBean>> currencyMap =
                list.stream().collect(Collectors.groupingBy(CurrencySourceTypeTotalAmountBean::getCurrency));

        currencyMap.keySet().forEach(currency -> {
            List<CurrencySourceTypeTotalAmountBean> currencyList = currencyMap.get(currency);
            //活期支出账户
            BigDecimal huoqizhichu = BigDecimal.ZERO;
            //定期支出账户
            BigDecimal dingqizhichu = BigDecimal.ZERO;
            //活期归集账户
            BigDecimal huoqiguiji = BigDecimal.ZERO;
            //定期归集账户
            BigDecimal dingqiguiji = BigDecimal.ZERO;
            //利息账户
            BigDecimal lixi = totalLixiMap.get(currency);
            for (CurrencySourceTypeTotalAmountBean bean : currencyList) {

                //遍历订单流水
                Integer sourceType = bean.getSourceType();
                Integer productType = bean.getProductType();
                //主动下单
                if (OrderEnum.OrderSourceTypeEnum.ORDER_SOURCE_TYPE_USER.getValue().equals(sourceType)) {
                    if (OrderEnum.ProductTypeEnum.FLEXIBLE.getValue().equals(productType)) {
                        huoqiguiji = huoqiguiji.add(bean.getTotalAmount());
                    } else {
                        dingqiguiji = dingqiguiji.add(bean.getTotalAmount());
                    }
                }
                //自动定转活
                if (OrderEnum.OrderSourceTypeEnum.ORDER_SOURCE_TYPE_FIXED_TO_CURRENT.getValue().equals(sourceType)) {
                    if (OrderEnum.ProductTypeEnum.FLEXIBLE.getValue().equals(productType)) {
                        huoqiguiji = huoqiguiji.add(bean.getTotalAmount());
                    } else {
                        dingqiguiji = dingqiguiji.add(bean.getTotalAmount());
                    }
                }
                //余额自动挖矿
                if (OrderEnum.OrderSourceTypeEnum.ORDER_SOURCE_TYPE_BALANCE.getValue().equals(sourceType)) {
                    if (OrderEnum.ProductTypeEnum.FLEXIBLE.getValue().equals(productType)) {
                        huoqiguiji = huoqiguiji.add(bean.getTotalAmount());
                    } else {
                        dingqiguiji = dingqiguiji.add(bean.getTotalAmount());
                    }
                }
                //到期赎回 支出到币币
                if (OrderEnum.OrderSourceTypeEnum.MATURITY_REDEEM.getValue().equals(sourceType)) {
                    if (OrderEnum.ProductTypeEnum.FLEXIBLE.getValue().equals(productType)) {
                        huoqizhichu = huoqizhichu.subtract(bean.getTotalAmount());
                    } else {
                        dingqizhichu = dingqizhichu.subtract(bean.getTotalAmount());
                    }
                }
                //随时赎回 支出到币币
                if (OrderEnum.OrderSourceTypeEnum.ANY_TIME_REDEEM.getValue().equals(sourceType)) {
                    if (OrderEnum.ProductTypeEnum.FLEXIBLE.getValue().equals(productType)) {
                        huoqizhichu = huoqizhichu.subtract(bean.getTotalAmount());
                    } else {
                        dingqizhichu = dingqizhichu.subtract(bean.getTotalAmount());
                    }
                }
                //收益发放 利息到币币
                if (OrderEnum.OrderSourceTypeEnum.INCOME_SEND.getValue().equals(sourceType)) {
                    if (OrderEnum.ProductTypeEnum.FLEXIBLE.getValue().equals(productType)) {
                        lixi = lixi.subtract(bean.getTotalAmount());
                    } else {
                        lixi = lixi.subtract(bean.getTotalAmount());

                    }
                }
                //收益复投 利息到币币
                if (OrderEnum.OrderSourceTypeEnum.INCOME_REINVEST.getValue().equals(sourceType)) {
                    if (OrderEnum.ProductTypeEnum.FLEXIBLE.getValue().equals(productType)) {
                        lixi = lixi.subtract(bean.getTotalAmount());
                        huoqizhichu = huoqizhichu.add(bean.getTotalAmount());
                    } else {
                        lixi = lixi.subtract(bean.getTotalAmount());
                        dingqizhichu = dingqizhichu.add(bean.getTotalAmount());
                    }
                }
                //存币宝升级转入 财务直接打到支出
                if (OrderEnum.OrderSourceTypeEnum.COIN_DEPOSIT_TREASURE.getValue().equals(sourceType)) {
                    if (OrderEnum.ProductTypeEnum.FLEXIBLE.getValue().equals(productType)) {
                        huoqizhichu = huoqizhichu.add(bean.getTotalAmount());
                    } else {
                        dingqizhichu = dingqizhichu.add(bean.getTotalAmount());
                    }
                }
                //测试插入（方便删除）
                if (OrderEnum.OrderSourceTypeEnum.TEST.getValue().equals(sourceType)) {
                    System.out.println("测试插入。。。");
                }
                //定期利息支出
//                if (OrderEnum.OrderSourceTypeEnum.FIXED_INCOME_ROLL_IN.getValue().equals(sourceType)) {
//                    if (OrderEnum.ProductTypeEnum.FLEXIBLE.getValue().equals(productType)) {
//                        lixi = lixi.add(bean.getTotalAmount());
//                    } else {
//                        lixi = lixi.add(bean.getTotalAmount());
//                    }
//                }

            }

            //最终归集到支出
            huoqizhichu = huoqizhichu.add(huoqiguiji);
            dingqizhichu = dingqizhichu.add(dingqiguiji);

            huoqiguiji = BigDecimal.ZERO;
            dingqiguiji = BigDecimal.ZERO;

            resultMap.get(SysAccountEnum.DEMAND_IN_AMOUNT_ACCOUNT.getMessage()).put(currency, huoqiguiji);
            resultMap.get(SysAccountEnum.REGULAR_IN_AMOUNT_ACCOUNT.getMessage()).put(currency, dingqiguiji);
            resultMap.get(SysAccountEnum.DEMAND_OUT_AMOUNT_ACCOUNT.getMessage()).put(currency, huoqizhichu);
            resultMap.get(SysAccountEnum.REGULAR_OUT_AMOUNT_ACCOUNT.getMessage()).put(currency, dingqizhichu);
            resultMap.get(SysAccountEnum.INCOME_IN_OUT_AMOUNT_ACCOUNT.getMessage()).put(currency, lixi);


            if (isPrint) {
                System.out.println("--------------" + currency + "---------------");
                if (huoqiguiji.compareTo(BigDecimal.ZERO) != 0) {
                    System.out.println(currency + "---活期归集账户:" + huoqiguiji.toPlainString());
                }
                if (dingqiguiji.compareTo(BigDecimal.ZERO) != 0) {
                    System.out.println(currency + "---定期归集账户:" + dingqiguiji.toPlainString());
                }
                if (huoqizhichu.compareTo(BigDecimal.ZERO) != 0) {
                    System.out.println(currency + "---活期支出账户:" + huoqizhichu.toPlainString());
                }
                if (dingqizhichu.compareTo(BigDecimal.ZERO) != 0) {
                    System.out.println(currency + "---定期支出账户:" + dingqizhichu.toPlainString());
                }
                if (lixi.compareTo(BigDecimal.ZERO) != 0) {
                    System.out.println(currency + "---定期支出账户:" + lixi.toPlainString());
                }
                System.out.println("   ");
            }

        });


        return resultMap;
    }

    private static Map<String, List<SubaccountQueryData>> getSysAccountBalanceData(Map<String, String> map) {

        Map<String, List<SubaccountQueryData>> collect = map.keySet().stream().collect(Collectors.toMap(key -> key, key -> {
            String s = map.get(key);
            AccountBalanceData balanceData = JSONObject.parseObject(s, AccountBalanceData.class);

            List<SubaccountQueryData> queryData = balanceData.getList();

            return queryData;
        }));


        return collect;
    }

    static {
        totalLixiMap.put("BTC", new BigDecimal("24.42377098")   //1～2月份转入
                .add(new BigDecimal("8.94777178")       //3月份转入
                )
        );
        totalLixiMap.put("USDT", new BigDecimal("4874634.20529975")   //1～2月份转入
                .add(new BigDecimal("760475.9225")       //3月份转入
                )
        );
        totalLixiMap.put("HUSD", new BigDecimal("109589.04109589")   //1～2月份转入
                .add(new BigDecimal("7671.232877")       //3月份转入
                )
        );
        totalLixiMap.put("ETH", new BigDecimal("526.02739726")   //1～2月份转入
                .add(new BigDecimal("23.28767123")       //3月份转入
                )
        );
        totalLixiMap.put("FIL", new BigDecimal("16657.53424657")   //1～2月份转入
                .add(new BigDecimal("0")       //3月份转入
                )
        );
        totalLixiMap.put("MDX", new BigDecimal("35260.27400662")   //1～2月份转入
                .add(new BigDecimal("17260.27397")       //3月份转入
                )
        );
        totalLixiMap.put("ZKS", new BigDecimal("150000.00000000")   //1～2月份转入
                .add(new BigDecimal("0")       //3月份转入
                )
        );
        totalLixiMap.put("DOT", new BigDecimal("34830.32583657")   //1～2月份转入
                .add(new BigDecimal("19657.53425")       //3月份转入

                )
        );
        totalLixiMap.put("XEM", new BigDecimal("0")   //1～2月份转入
                .add(new BigDecimal("30000")       //3月份转入
                )
        );
        totalLixiMap.put("STPT", new BigDecimal("0")   //1～2月份转入
                .add(new BigDecimal("46000")       //3月份转入
                )
        );
        totalLixiMap.put("DOT", new BigDecimal("34830.32583657")   //1～2月份转入
                .add(new BigDecimal("19657.53425")       //3月份转入
                )
        );
        totalLixiMap.put("DOCK", new BigDecimal("0")   //1～2月份转入
                .add(new BigDecimal("2800000")       //3月份转入
                )
        );
    }
}
