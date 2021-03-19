package com.renshihan.base.小工具;

import cn.gjing.tools.common.util.ParamUtil;
import com.renshihan.commons.util.ParamUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DataTest {

    @Test
    public void test1(){
        String a="f_id\tf_project_id\tf_product_id\tf_user_id\tf_uid\tf_mining_status\tf_currency\tf_amount\tf_pre_mining_amount\tf_mining_amount\tf_total_amount\tf_total_redemption_amount\tf_total_income_amount\n" +
                "6259\t11\t12\t3489231\t34892310\t1\tUSDT\t11990.17819\t0\t7781.884594\t7781.884594\t4250\t41.70640293\n" +
                "91989\t11\t12\t4809776\t48097762\t2\tUSDT\t4875.571667\t0\t0.26418513\t0.26418513\t4875.571667\t0.26418513\n" +
                "97856\t12\t13\t4809776\t48097762\t2\tBTC\t0.27684736\t0\t0\t0\t0.27693702\t0.00008966\n" +
                "133253\t11\t12\t4809776\t48097762\t2\tUSDT\t705\t0\t0\t0\t705\t0\n" +
                "153872\t11\t12\t4809776\t48097762\t2\tUSDT\t260.5511379\t0\t0\t0\t260.5511379\t0\n" +
                "170823\t11\t12\t4809776\t48097762\t2\tUSDT\t3719.573285\t0\t0\t0\t3719.573285\t0\n" +
                "184450\t11\t12\t4809776\t48097762\t2\tUSDT\t1629.307081\t0\t0\t0\t1629.307081\t0\n" +
                "204286\t11\t12\t4809776\t48097762\t2\tUSDT\t13.96311225\t0\t0\t0\t13.96311225\t0\n" +
                "228479\t11\t12\t4809776\t48097762\t2\tUSDT\t13887.94896\t0\t0\t0\t13887.94896\t0\n" +
                "116407\t11\t12\t5208017\t52080174\t2\tUSDT\t61\t0\t0\t0\t61\t0";

        a="f_id\tf_user_id\tf_uid\tf_currency\tf_total_amount\tf_pre_mining_amount\tf_mining_amount\tf_create_time\tf_update_time\tf_version\n" +
                "5156\t3489231\t34892310\tUSDT\t7781.884594\t0\t7781.884594\t1611854729396 \t1614961848294 \t58\n" +
                "27834\t3489231\t34892310\tDOT\t0\t0\t0\t1612451065808 \t1612755023213 \t4\n" +
                "55929\t4809776\t48097762\tMDX\t0\t0\t0\t1612934204209 \t1613694820688 \t5\n" +
                "55933\t4809776\t48097762\tUSDT\t0.26418513\t0\t0.26418513\t1612934269039 \t1614099728194 \t18\n" +
                "57592\t4809776\t48097762\tBTC\t0\t0\t0\t1612951753961 \t1614256823072 \t22\n" +
                "57593\t4809776\t48097762\tFIL\t0\t0\t0\t1612951754127 \t1614363255253 \t18\n" +
                "57594\t4809776\t48097762\tDOT\t0\t0\t0\t1612951754321 \t1614363246270 \t22\n" +
                "58840\t4809776\t48097762\tETH\t0\t0\t0\t1612965366152 \t1614363238923 \t32\n" +
                "94330\t4809776\t48097762\tZKS\t0\t0\t0\t1613694921746 \t1613823232901 \t5\n" +
                "68249\t5208017\t52080174\tUSDT\t0\t0\t0\t1613185525593 \t1613194710845 \t1\n" +
                "81310\t5208017\t52080174\tDOT\t0\t0\t0\t1613533672399 \t1613658212313 \t1";

        a="f_id\tf_user_id\tf_uid\tf_currency\tf_total_amount\tf_pre_mining_amount\tf_mining_amount\tf_create_time\tf_update_time\tf_version\n" +
                "5156\t3489231\t34892310\tUSDT\t8984.455791\t0\t8984.455791\t1611854729396 \t1615017120284 \t60\n" +
                "27834\t3489231\t34892310\tDOT\t0\t0\t0\t1612451065808 \t1612755023213 \t4\n" +
                "55929\t4809776\t48097762\tMDX\t0\t0\t0\t1612934204209 \t1613694820688 \t5\n" +
                "55933\t4809776\t48097762\tUSDT\t0.26418513\t0\t0.26418513\t1612934269039 \t1614099728194 \t18\n" +
                "57592\t4809776\t48097762\tBTC\t0.27744264\t0\t0.27744264\t1612951753961 \t1615017120408 \t24\n" +
                "57593\t4809776\t48097762\tFIL\t0\t0\t0\t1612951754127 \t1614363255253 \t18\n" +
                "57594\t4809776\t48097762\tDOT\t0\t0\t0\t1612951754321 \t1614363246270 \t22\n" +
                "58840\t4809776\t48097762\tETH\t0\t0\t0\t1612965366152 \t1614363238923 \t32\n" +
                "94330\t4809776\t48097762\tZKS\t0\t0\t0\t1613694921746 \t1613823232901 \t5\n" +
                "68249\t5208017\t52080174\tUSDT\t52.1557639\t0\t52.1557639\t1613185525593 \t1615014060184 \t3\n" +
                "81310\t5208017\t52080174\tDOT\t0\t0\t0\t1613533672399 \t1613658212313 \t1\n" +
                "178664\t15550110\t155501101\tUSDT\t130.4144518\t0\t130.4144518\t1615017117364 \t1615017120168 \t1";
        a="f_id\tf_project_id\tf_product_id\tf_user_id\tf_uid\tf_mining_status\tf_currency\tf_amount\tf_pre_mining_amount\tf_mining_amount\tf_total_amount\tf_total_redemption_amount\tf_total_income_amount\n" +
                "6259\t11\t12\t3489231\t34892310\t1\tUSDT\t13190.17819\t0\t8984.455791\t8984.455791\t4250\t44.27759962\n" +
                "91989\t11\t12\t4809776\t48097762\t2\tUSDT\t4875.571667\t0\t0.26418513\t0.26418513\t4875.571667\t0.26418513\n" +
                "97856\t12\t13\t4809776\t48097762\t2\tBTC\t0.27684736\t0\t0\t0\t0.27693702\t0.00008966\n" +
                "133253\t11\t12\t4809776\t48097762\t2\tUSDT\t705\t0\t0\t0\t705\t0\n" +
                "153872\t11\t12\t4809776\t48097762\t2\tUSDT\t260.5511379\t0\t0\t0\t260.5511379\t0\n" +
                "170823\t11\t12\t4809776\t48097762\t2\tUSDT\t3719.573285\t0\t0\t0\t3719.573285\t0\n" +
                "184450\t11\t12\t4809776\t48097762\t2\tUSDT\t1629.307081\t0\t0\t0\t1629.307081\t0\n" +
                "204286\t11\t12\t4809776\t48097762\t2\tUSDT\t13.96311225\t0\t0\t0\t13.96311225\t0\n" +
                "228479\t11\t12\t4809776\t48097762\t2\tUSDT\t13887.94896\t0\t0\t0\t13887.94896\t0\n" +
                "354643\t12\t13\t4809776\t48097762\t1\tBTC\t0.27736235\t0\t0.27744264\t0.27744264\t0\t0.00008029\n" +
                "116407\t11\t12\t5208017\t52080174\t2\tUSDT\t61\t0\t0\t0\t61\t0\n" +
                "354363\t11\t12\t5208017\t52080174\t1\tUSDT\t52\t0\t52.1557639\t52.1557639\t0\t0.1557639\n" +
                "354642\t11\t12\t15550110\t155501101\t1\tUSDT\t130\t0\t130.4144518\t130.4144518\t0\t0.41445179";
        a="f_id\tf_project_id\tf_user_id\tf_uid\tf_order_id\tf_principal_clear_id\tf_interest_clear_id\tf_currency\tf_amount\tf_type\tf_event_time\tf_status\tf_create_time\tf_update_time\tf_version\tf_remark\n" +
                "1\t11\t5208017\t52080174\t354363\t\t1418693\tUSDT\t52\t0\t2021/2/15\t2\t1.61501E+12\t1.61501E+12\t1\t执行完成\n" +
                "2\t11\t15550110\t155501101\t354642\t\t1418694\tUSDT\t130\t0\t2021/2/14\t2\t1.61501E+12\t1.61502E+12\t1\t执行完成\n" +
                "3\t11\t3489231\t34892310\t6259\t\t1418695\tUSDT\t1200\t0\t2021/2/20\t2\t1.61501E+12\t1.61502E+12\t1\t执行完成\n" +
                "4\t12\t4809776\t48097762\t354643\t\t1418696\tBTC\t0.27736235\t0\t2021/2/26\t2\t1.61501E+12\t1.61502E+12\t1\t执行完成";

        a="f_id\tf_project_id\tf_user_id\tf_uid\tf_order_id\tf_principal_clear_id\tf_interest_clear_id\tf_currency\tf_amount\tf_type\tf_event_time\tf_status\tf_create_time\tf_update_time\tf_version\tf_remark\n" +
                "1\t11\t5208017\t52080174\t354363\t\t1418693\tUSDT\t52\t0\t2021/2/15\t2\t1615009743000 \t1615014059387 \t1\t执行完成\n" +
                "2\t11\t15550110\t155501101\t354642\t\t1418694\tUSDT\t130\t0\t2021/2/14\t2\t1615009743000 \t1615017117407 \t1\t执行完成\n" +
                "3\t11\t3489231\t34892310\t6259\t\t1418695\tUSDT\t1200\t0\t2021/2/20\t2\t1615009743000 \t1615017117497 \t1\t执行完成\n" +
                "4\t12\t4809776\t48097762\t354643\t\t1418696\tBTC\t0.27736235\t0\t2021/2/26\t2\t1615009743000 \t1615017117586 \t1\t执行完成";
        for (String s : a.split("\n")) {

            String collect = Arrays.stream(s.split("\t")).filter(str -> ParamUtil.isNotEmpty(str)).collect(Collectors.joining("|"));
            System.out.println("|"+collect+"|");


        }
    }
}
