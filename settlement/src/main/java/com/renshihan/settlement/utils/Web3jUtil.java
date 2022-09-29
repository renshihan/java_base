package com.renshihan.settlement.utils;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.renshihan.settlement.config.HttpConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.jctools.maps.NonBlockingHashMap;
import org.web3j.abi.*;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.renshihan.settlement.handle.准备物料.MATIC_NODE_URL;

@Slf4j
public class Web3jUtil {
//    public static ForkJoinPool pool = new ForkJoinPool(Math.max(Runtime.getRuntime().availableProcessors() * 8, 256));

    public static ListMultimap<String, Web3jHelper> chainMap = ArrayListMultimap.create();

    public static final String emptAddress = "0x0000000000000000000000000000000000000000";
    public static final String WETH_ADDRESS = "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2";

    public static void addHelper(String chain, Web3jHelper helper) {
        chainMap.put(chain.toUpperCase(), helper);
    }
    static {
        HttpConfig httpConfig = new HttpConfig();
        OkHttpClient httpClient = httpConfig.createHttp(false);
//        Web3jHelper web3jHelper = new Web3jHelper("https://rpc-mainnet.matic.network", httpClient);
//        Web3jHelper web3jHelper = new Web3jHelper("https://matic-mainnet-full-rpc.bwarelabs.com", httpClient);

        Web3jHelper web3jHelper = new Web3jHelper(MATIC_NODE_URL, httpClient);
        addHelper("BSC",web3jHelper);
    }
    public static Web3jHelper getHelper(Optional<String> type) {
        List<Web3jHelper> list = chainMap.get(type.orElse("default").toUpperCase(Locale.ROOT));

        return list.get(CommonUtils.randomInt(0,list.size()));
    }

    private static NonBlockingHashMap<String, Web3jHelper> web3jClientMap = new NonBlockingHashMap();
    private static OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .callTimeout(3, TimeUnit.SECONDS)
            .connectTimeout(3, TimeUnit.SECONDS)
            .pingInterval(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(100, 1, TimeUnit.MINUTES))
            .retryOnConnectionFailure(true)
            .addInterceptor(new OkHttpLoggerInterceptor(true))
            .followRedirects(true);
    private static OkHttpClient httpClient = new OkHttpClient(builder);

    /**
     * 测试使用
     */
    @VisibleForTesting
    public static Web3jHelper getInstanceByUrl(String url) {
        if (web3jClientMap.contains(url)) {
            return web3jClientMap.get(url);
        } else {
            synchronized (url.intern()) {
                Web3jHelper helper = new Web3jHelper(url, httpClient);
                web3jClientMap.put(url, helper);
                return helper;
            }
        }
    }

    public static Object invoke(String methodDefine, final Map<String, Object> env) {
        RpcMethod method = RpcMethod.parse(methodDefine);
        if (!method.isValid()) {
            return null;
        }

        List<Type> inputArgs = Lists.newArrayList();
        for (int i = 0; i < method.getArgTypes().size(); i++) {
            String key = method.getArgName().get(i);
            Object argValue = env.get(key);
            inputArgs.add(i, TypeHelper.newTypeInstance(method.getArgTypes().get(i), argValue));
        }
        String contractAddress = (String) env.get("contractAddress");
        Web3jHelper helper = getHelper(Optional.ofNullable((String) env.get("type")));
        return helper.executeFunction(contractAddress, method, inputArgs);
    }

    public static Object invoke2(String methodDefine,String account, final Map<String, Object> env) {
        RpcMethod method = RpcMethod.parse(methodDefine);
        if (!method.isValid()) {
            return null;
        }

        List<Type> inputArgs = Lists.newArrayList();
        for (int i = 0; i < method.getArgTypes().size(); i++) {
            String key = method.getArgName().get(i);
            Object argValue = env.get(key);
            inputArgs.add(i, TypeHelper.newTypeInstance(method.getArgTypes().get(i), argValue));
        }
        String contractAddress = (String) env.get("contractAddress");
        Web3jHelper helper = getHelper(Optional.ofNullable((String) env.get("type")));
        helper.setTransactionManager(account);
        return helper.executeFunction(contractAddress, method, inputArgs);
    }

    public static Object exec(String type, String address, String methodDefine, final Map<String, Object> env) {
        env.put("type", type);
        env.put("contractAddress", address);
        return invoke(methodDefine, env);
    }

    public static Object exec(String type, String address, String methodDefine, Object... args) {
        Map<String, Object> env = newEnv(args);
        env.put("type", type);
        env.put("contractAddress", address);
        return invoke(methodDefine, env);
    }

    public static Object exec2(String type, String address,String account, String methodDefine, Object... args) {
        Map<String, Object> env = newEnv(args);
        env.put("type", type);
        env.put("contractAddress", address);
        return invoke2(methodDefine,account, env);
    }

    public static Object[] execTuple(String type, String address, String methodDefine, Object... args) {
        Map<String, Object> env = newEnv(args);
        env.put("type", type);
        env.put("contractAddress", address);
        return (Object[]) invoke(methodDefine, env);
    }

    public static Object[] execTuple(String type, String address, String methodDefine, final Map<String, Object> env) {
        env.put("type", type);
        env.put("contractAddress", address);
        return (Object[]) invoke(methodDefine, env);
    }

    public static Object exec(String type, String address, String methodDefine, boolean isAbi, final Map<String, Object> env) {
        env.put("type", type);
        env.put("contractAddress", address);
        return invoke(methodDefine, env);
    }

    public static Object storageAt(String type, String contract, int position, String rvType) {
        return getHelper(Optional.of(type)).getStorageAt(contract, BigInteger.valueOf(position), TypeHelper.detectRvClass(rvType));
    }

    public static BigInteger blockNumber(String type) {
        return getHelper(Optional.of(type)).blockNumber();
    }

    public static EthBlock block(String type, BigInteger blockNumber) {
        return getHelper(Optional.of(type)).block(blockNumber);
    }

    public static Optional<TransactionReceipt> transactionReceipt(String type, String txHash) {
        return getHelper(Optional.of(type)).transactionReceipt(txHash);
    }

    public static Optional<Transaction> transactionByHash(String type, String txHash) {
        return getHelper(Optional.of(type)).transactionByHash(txHash);
    }

    public static BigInteger transactionCount(String chain, String address) {
        return getHelper(Optional.of(chain)).transactionCount(address);
    }
    public static boolean isContract(String type, String contract) {
        return getHelper(Optional.of(type)).isContract(contract);
    }

    public static BigInteger totalSupply(final String type, final String contractAddress) {
        return getHelper(Optional.of(type)).executeFunction(contractAddress,
                "totalSupply"
                , Lists.newArrayList(),
                Lists.newArrayList(new TypeReference<Uint256>() {
                }), BigInteger.class);
    }

//    public static BigInteger decimals(final String type, final String contractAddress) {
//        Optional<Token> token = loadTokenFromDb(type, contractAddress);
//        if (token.isPresent()) {
//            return BigInteger.valueOf(token.get().getDecimals());
//        }
//        return getHelper(Optional.of(type)).executeFunction(contractAddress,
//                "decimals"
//                , Lists.newArrayList(),
//                Lists.newArrayList(new TypeReference<Uint8>() {
//                }), BigInteger.class);
//    }

//    public static String symbol(final String type, final String contractAddress) {
//        Optional<Token> token = loadTokenFromDb(type, contractAddress);
//        if (token.isPresent()) {
//            return token.get().getSymbol();
//        }
//        return getHelper(Optional.of(type)).executeFunction(contractAddress,
//                "symbol"
//                , Lists.newArrayList(),
//                Lists.newArrayList(new TypeReference<Utf8String>() {
//                }), String.class);
//    }

    public static String lpToken(final String type, final String contract, String tokenA, String tokenB) {
        return (String)exec(type, contract, "address@getPair(tokenA:address,tokenB:address)",
                "tokenA", tokenA, "tokenB", tokenB);
    }

    public static BigInteger balanceOf(final String type, final String address, final String contractAddress) {
        return getHelper(Optional.of(type)).executeFunction(contractAddress,
                "balanceOf"
                , Lists.newArrayList(new org.web3j.abi.datatypes.Address(address)),
                Lists.newArrayList(new TypeReference<Uint256>() {
                }), BigInteger.class);
    }

    /**
     * 主币数量
     *
     * @param type
     * @param address
     * @return
     */
    public static BigInteger balance(final String type, final String address) {
        Web3j web3j = getHelper(Optional.of(type)).getWeb3j();
        try {
            EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
            return ethGetBalance.getBalance();
        } catch (IOException e) {
            log.error("无法连接节点", e);
        }
        return null;
    }

    /**
     * @param chain
     * @param lpContractAddress
     * @param tokenAmount
     * @param decimal
     * @param baseAmount        用户金额、池子金额
     * @return
     */
    public static double amount(String chain, String lpContractAddress, BigInteger tokenAmount, Integer decimal, BigInteger baseAmount) {

        BigInteger lpTotalSupply = Web3jUtil.totalSupply(chain, lpContractAddress);
        double ratioAmount = baseAmount.multiply(tokenAmount).divide(lpTotalSupply).doubleValue() / Math.pow(10, decimal);

        return NumericUtil.keepPrecision(ratioAmount, 8);
    }

    private static Map<String, Object> newEnv(Object... args) {
        if (args != null && args.length % 2 != 0) {
            throw new IllegalArgumentException("Expect arguments number is even.");
        } else {
            Map<String, Object> env = Maps.newHashMapWithExpectedSize(args != null ? args.length : 10);
            if (args != null) {
                for (int i = 0; i < args.length; i += 2) {
                    String key = (String) args[i];
                    env.put(key, args[i + 1]);
                }
            }
            return env;
        }
    }

    /**
     * 生成地址
     *
     * @param address
     * @param nonce
     * @return
     */
    public static String generateAddress(String address, long nonce) {
        byte[] addressAsBytes = Numeric.hexStringToByteArray(address);
        byte[] calculatedAddressAsBytes =
                Hash.sha3(RlpEncoder.encode(
                        new RlpList(
                                RlpString.create(addressAsBytes),
                                RlpString.create((nonce)))));

        calculatedAddressAsBytes = Arrays.copyOfRange(calculatedAddressAsBytes,
                12, calculatedAddressAsBytes.length);
        String calculatedAddressAsHex = Numeric.toHexString(calculatedAddressAsBytes);
        return calculatedAddressAsHex;
    }

    /**
     * @param v
     * @param decimals
     * @return
     */
    public static double numberWithDecimals(BigInteger v, int decimals) {
        BigDecimal bd = new BigDecimal("10");
        bd = bd.pow(decimals);
        BigDecimal ret = new BigDecimal(v).divide(bd, decimals, BigDecimal.ROUND_DOWN);
        return ret.doubleValue();
    }

    public static BigDecimal balanceToBigDecimal(BigInteger v, int decimals) {
        if(v==null){
            return new BigDecimal(0);
        }
        BigDecimal bd = new BigDecimal("10");
        bd = bd.pow(decimals);
        return new BigDecimal(v).divide(bd, decimals, BigDecimal.ROUND_DOWN);
    }

    /**
     * one for decimals
     *
     * @param decimals
     * @return
     */
    public static BigInteger one(int decimals) {
        BigInteger bi = BigInteger.valueOf(10);
        return bi.pow(decimals);
    }

    /**
     * n for decimals
     *
     * @param decimals
     * @return
     */
    public static BigInteger n(int n, int decimals) {
        return BigInteger.valueOf(n).multiply(one(decimals));
    }

    /**
     * byte32
     *
     * @param t
     * @return
     */
    public static Bytes32 formatBytes32String(String t) {
        byte[] temp = t.getBytes();
        int len = temp.length > 32 ? 32 : temp.length;
        byte[] target = new byte[32];
        System.arraycopy(temp, 0, target, 0, len);
        return new Bytes32(target);
    }

    public static byte[] bytes32Bytes(String t) {
        byte[] temp = t.getBytes();
        int len = temp.length > 32 ? 32 : temp.length;
        byte[] target = new byte[32];
        System.arraycopy(temp, 0, target, 0, len);
        return target;
    }


}
