package org.web3j.abi;

import com.renshihan.settlement.exception.RpcException;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Array;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.exceptions.ContractCallException;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Web3jHelper {

    private TransactionManager transactionManager;

    private Web3j web3j;
    protected DefaultBlockParameter defaultBlockParameter = DefaultBlockParameterName.LATEST;
    public static String emptyAddress = "0x0000000000000000000000000000000000000000";

    public Web3jHelper(String web3jUrl, OkHttpClient httpClient) {
        this(getWeb3j(web3jUrl, httpClient));
    }

    private Web3jHelper(Web3j web3j) {
        this.web3j = web3j;
        this.transactionManager = new ReadonlyTransactionManager(web3j, emptyAddress);
    }

    public void setTransactionManager(String emptyAddress){
        this.transactionManager = new ReadonlyTransactionManager(web3j, emptyAddress);
    }

    public <T> T executeFunction(String contractAddress, String name, List<Type> inputParameters, List<TypeReference<?>> outputParameters, Class<T> returnType) {
        final Function function = new Function(name, inputParameters, outputParameters);
        try {
            return executeCallSingleValueReturn(contractAddress, function, returnType);
        } catch (Exception e) {
            if (name.equalsIgnoreCase("balanceOf") && e.getMessage().equals("Empty value (0x) returned from contract")) {
                return (T) BigInteger.ZERO;
            }
            String msg = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getSimpleName());
            throw new RpcException(String.format("[%s] %s() error.message: %s", contractAddress, name, msg), e);
        }
    }

    public Object executeFunction(String contractAddress, RpcMethod method, List<Type> inputArgs) {
        final Function function = new Function(method.getMethodName(), inputArgs, method.getRvTypes());
        try {
            List<Type> types = executeCall(contractAddress, function);
            return wrapReturnValue(types, method.getRvTypeNames());
        } catch (InterruptedIOException e) {
            throw new RpcException(String.format("[%s] %s() timeout.", contractAddress, method.getMethodName()), e);
        } catch (Exception e) {
            String msg = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getSimpleName());
            throw new RpcException(String.format("[%s] %s() error.message: %s", contractAddress, method.getMethodName(), msg), e);
        }
    }

    private Object wrapReturnValue(List<Type> types, List<String> typeNames) {
        //兼容bsc节点，只返回0x结果的场景，这时候，给类型的默认值
        if (types != null && types.size() == 0) {
            return TypeHelper.defaultValue(typeNames);
        }
        if (types != null && types.size() != typeNames.size()) {
            throw new RuntimeException("wrong result size");
        }
        if (types.size() == 1) {
            return wrapOneValue(types.get(0), typeNames.get(0));
        }
        int size = Math.min(types.size(), typeNames.size());
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = wrapOneValue(types.get(i), typeNames.get(i));
        }
        return result;
    }

    private Object wrapOneValue(Type type, String name) {
        if (type instanceof Array) {
            return ((List<Type>) type.getValue())
                    .stream()
                    .map(x -> wrapOneValue(x, TypeHelper.detectRvClass(x.getTypeAsString())))
                    .collect(Collectors.toList());
        } else {
            return wrapOneValue(type, TypeHelper.detectRvClass(name));
        }
    }

    private <R> R wrapOneValue(Type result, Class<R> returnType) {
        if (result == null) {
            throw new ContractCallException("Empty value (0x) returned from contract");
        } else {
            Object value = result.getValue();
            if (returnType.isAssignableFrom(value.getClass())) {
                return (R) value;
            } else if (result.getClass().equals(Address.class) && returnType.equals(String.class)) {
                return (R) result.toString();
            } else {
                throw new ContractCallException("Unable to convert response: " + value + " to expected type: " + returnType.getSimpleName());
            }
        }
    }

    private List<Type> executeCall(String contractAddress, Function function) throws IOException {
        String encodedFunction = FunctionEncoder.encode(function);
        String value = this.transactionManager.sendCall(contractAddress, encodedFunction, defaultBlockParameter);
        return FunctionReturnDecoder.decode(value, function.getOutputParameters());
    }

    public <T> T getStorageAt(String contract, BigInteger position, Class<T> returnType) {
        try {
            EthGetStorageAt ethCall = web3j.ethGetStorageAt(contract, position, defaultBlockParameter).send();
            Type type = FunctionReturnDecoder.decodeIndexedValue(ethCall.getData(), TypeReference.makeTypeReference("address"));
            return wrapOneValue(type, returnType);
        } catch (InterruptedIOException e) {
            throw new RpcException(String.format("[%s] %s() timeout.", contract, "getStorageAt"), e);
        } catch (Exception e) {
            String msg = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getSimpleName());
            throw new RpcException(String.format("[%s] %s() error.message: %s", contract, "getStorageAt", msg), e);
        }
    }

    public BigInteger blockNumber() {
        try {
            EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
            return blockNumber.getBlockNumber();
        } catch (Exception e) {
            throw new RpcException("blockNumber timeout.", e);
        }
    }

    public EthBlock block(BigInteger blockNumber) {
        try {
            return web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), false).send();
        } catch (Exception e) {
            throw new RpcException("block timeout.", e);
        }
    }

    public Optional<TransactionReceipt> transactionReceipt(String txHash) {
        try {
            EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(txHash).send();
            return transactionReceipt.getTransactionReceipt();
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e);
        }
    }

    public Optional<Transaction> transactionByHash(String txHash) {
        try {
            EthTransaction ethTransaction = web3j.ethGetTransactionByHash(txHash).send();
            return ethTransaction.getTransaction();
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e);
        }
    }

    public BigInteger transactionCount(String address) {
        try {
            EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(address, defaultBlockParameter).send();
            return transactionCount.getTransactionCount();
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e);
        }
    }

    public boolean isContract(String address) {
        try {
            EthGetCode getCode = web3j.ethGetCode(address, defaultBlockParameter).send();
            return !getCode.getCode().equalsIgnoreCase("0x");
        } catch (InterruptedIOException e) {
            throw new RpcException(String.format("[%s] %s() timeout.", address, "ethGetCode"), e);
        } catch (Exception e) {
            String msg = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getSimpleName());
            throw new RpcException(String.format("[%s] %s() error.message: %s", address, "ethGetCode", msg), e);
        }
    }


    protected <T extends Type, R> R executeCallSingleValueReturn(String contractAddress, Function function, Class<R> returnType) throws IOException {
        List<Type> values = this.executeCall(contractAddress, function);
        Type result = !values.isEmpty() ? values.get(0) : null;
        return wrapOneValue(result, returnType);
    }

    private static Web3j getWeb3j(String url, OkHttpClient okHttpClient) {
        URL url1 = null;
        String credential = null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url1 != null) {
            url = url1.getProtocol() + "://" + url1.getHost() + (url1.getPort() == -1 ? "" : ":" + url1.getPort());
            String path = url1.getPath();
            if (!StringUtils.isEmpty(path)) {
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
                url = url + path;
            }
            String userInfo = url1.getUserInfo();
            if (userInfo != null) {
                String[] split = userInfo.split(":");
                credential = Credentials.basic(split[0], split[1]);
            }
        }
        HttpService httpService = new HttpService(url, okHttpClient);
        if (StringUtils.isNotBlank(credential)) {
            httpService.addHeader("Authorization", credential);
        }
        return Web3j.build(httpService);
    }

    public Web3j getWeb3j() {
        return web3j;
    }
}
