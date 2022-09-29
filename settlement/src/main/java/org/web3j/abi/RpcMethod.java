package org.web3j.abi;

import com.google.common.collect.Lists;
import com.renshihan.settlement.utils.ParamUtils;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jctools.maps.NonBlockingHashMap;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@ToString
@Data
@Slf4j
public class RpcMethod {

    private static NonBlockingHashMap<String, RpcMethod> expressions = new NonBlockingHashMap<>();
    private static Pattern pattern = Pattern.compile("<?(.*?)>?@(.*)\\((.*)\\)");

    private boolean valid;
    private String method;
    private String methodName;
    private List<TypeReference<?>> rvTypes = Lists.newArrayList();
    private List<String> rvTypeNames = Lists.newArrayList();
    private List<String> argTypes = Lists.newArrayList();
    private List<String> argName = Lists.newArrayList();

    public static RpcMethod parse(String line) {
        line = StringUtils.trimAllWhitespace(line);
        if (expressions.containsKey(line)) {
            return expressions.get(line);
        } else {
            return new RpcMethod(line);
        }
    }

    @SneakyThrows
    private RpcMethod(String line) {
        this.method = line;
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            this.methodName = matcher.group(2);
            String rvTypeName = matcher.group(1);

            if (ParamUtils.isNotEmpty(rvTypeName)) {
                for (String type : rvTypeName.split(",")) {
                    rvTypeNames.add(type);
                    rvTypes.add(TypeReference.makeTypeReference(type));
                }
            }

            Arrays.stream(matcher.group(3).split(","))
                    .map(arg -> arg.split(":"))
                    .filter(one -> one.length > 1)
                    .forEach(arr -> {
                        argTypes.add(arr[1]);
                        argName.add(arr[0]);
                    });
        }
        this.valid = true;
        expressions.putIfAbsent(line, this);
    }


    public static void main(String[] args) {
//        System.out.println(RpcMethod.parse("address@instrument()").toString());
//        System.out.println(RpcMethod.parse("address@instrument()").toString());
//        System.out.println(RpcMethod.parse("uint256@balanceOf(address:address)"));
//        System.out.println(RpcMethod.parse("uint256@balanceOf(address:address,address2:address)"));
//        System.out.println(RpcMethod.parse("<address>@instrument()"));
//        System.out.println(RpcMethod.parse("<address,address>@instrument(address:address)"));
//        System.out.println(RpcMethod.parse("<address[3],address>@instrument(address:address)"));
//        System.out.println(RpcMethod.parse("<address[],address>@instrument(address:address)"));
        System.out.println(RpcMethod.parse("uint256[]@getAmountsIn(amountOut:uint256,router:address[])"));
        System.out.println(RpcMethod.parse("@deposit(_pid:uint256,_amount:uint256)"));

    }



}
