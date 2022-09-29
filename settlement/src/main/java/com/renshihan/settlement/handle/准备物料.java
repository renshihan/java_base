package com.renshihan.settlement.handle;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.renshihan.settlement.bean.NftInfo;
import com.renshihan.settlement.utils.HttpHostUtil;
import com.renshihan.settlement.utils.ParamUtils;
import com.renshihan.settlement.utils.Web3jUtil;
import io.netty.util.internal.ConcurrentSet;
import jnr.ffi.annotations.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.web3j.abi.datatypes.Int;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class 准备物料 {
    public static final String TOKEN_URI = "string@tokenURI(_tokenId:uint256)";
    public static final String HTTP = "http";
    public static final String IPFS = "ipfs://";
    public static final String IPFS_SLASH = "ipfs/";
//    public static final String MATIC_NODE_URL = "https://matic-mainnet-full-rpc.bwarelabs.com";
    public static final String MATIC_NODE_URL = "https://bsc-dataseed4.ninicoin.io";
    public static final ConcurrentSet<String> concurrentSet = new ConcurrentSet<>();
    public static final Map<String, String> tokenUriMap = new HashMap<>();

    /**
     * 1229   2400
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println("------start------");
//        String contract = "0x766c0f50b92f0683439656ec1574be5b14419b2f";
        //人物
        String contract = "0x766c0f50b92f0683439656ec1574be5b14419b2f";
        String network = "BSC";
        File nftInfosFile = getNftInfoFile(network, contract);
        List<String> haveFinishTokenIds = FileUtils.readLines(nftInfosFile).stream().map(str -> str.split("=")[1]).map(str -> JSONObject.parseObject(str, NftInfo.class)).map(nftInfo -> nftInfo.getTokenId()).collect(Collectors.toList());
        List<Integer> tokenIds = GetTokenId2.tokenIds().stream().filter(tokenId -> !haveFinishTokenIds.contains(tokenId)).map(Integer::valueOf).sorted().collect(Collectors.toList());

        File tokenUrilFile = getTokenUriFile(network, contract);

        try {
            FileUtils.readLines(tokenUrilFile).stream().distinct().forEach(str -> {
                String[] split = str.split(",");
                tokenUriMap.put(split[0], split[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---tokenId..size=" + tokenIds.size());
        List<NftInfo> nftInfos = tokenIds.stream()
//                .parallel()
                .map(tokenId -> {
            System.out.println("----->tokenId- start-->" + tokenId);
            NftInfo nftInfo = new NftInfo();
            nftInfo.setNetwork(network);
            nftInfo.setTokenId(tokenId + "");
            String tokenUri = getTokenUri(network, contract, tokenId);
            nftInfo.setTokenUri(tokenUri);
            if (ParamUtils.isEmpty(tokenUri)) {
                return nftInfo;
            }
            try {
                Map<String, String> respMap = HttpHostUtil.get(HttpHostUtil.Type.HTTP, tokenUri, Map.class);
                if (ParamUtils.isEmpty(respMap)) {
                    return nftInfo;
                }
                nftInfo.setName(respMap.get("name") + "#" + tokenId);
                nftInfo.setImgUrl(respMap.get("image"));
                nftInfo.setContract(contract);
                nftInfo.setTokenId(tokenId + "");
                return nftInfo;
            } catch (Exception e) {
                return nftInfo;
            }
        }).collect(Collectors.toList());
        int saveTokenUriCount = saveTokenUri(nftInfos, tokenUrilFile);
        int saveNftInfoCount = saveNftInfo(nftInfos);
        System.out.println("--------------------------------->tokenIdsCount="+tokenIds.size()+" saveTokenUriCount=" + saveTokenUriCount+" saveNftInfoCount=" + saveNftInfoCount);
        System.out.println("failse--->" + JSONObject.toJSONString(concurrentSet));
    }

    public static File getTokenUriFile(String network, String contract) {
        File file = new File(String.format("/Users/bj00078ml/other/java_base/settlement/src/main/resources/token_%s_%s.txt", network, contract));
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static File getNftInfoFile(String network, String contract) {
        File file = new File(String.format("/Users/bj00078ml/other/java_base/settlement/src/main/resources/nftInfos_%s_%s.txt", network, contract));
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static int saveTokenUri(List<NftInfo> nftInfos, File file) {
        List<String> collect = nftInfos.stream()
                .filter(nftInfo -> ParamUtils.isNotEmpty(nftInfo.getTokenUri()))
                .map(nftInfo -> nftInfo.getTokenId() + "," + nftInfo.getTokenUri()).collect(Collectors.toList());

        if(ParamUtils.isEmpty(collect)){
            return 0;
        }

        try {
            FileUtils.writeLines(file, collect,true);
            return collect.size();
        } catch (IOException e) {
            return 0;
        }


    }

    public static int saveNftInfo(List<NftInfo> nftInfos) {
        if (ParamUtils.isEmpty(nftInfos)) {
            return 0;
        }
        String contract = nftInfos.get(0).getContract();
        String network = nftInfos.get(0).getNetwork();
        List<String> finalNftInfos = new ArrayList<>();
        for (int i = 0; i < nftInfos.size(); i++) {
            NftInfo nftInfo = nftInfos.get(i);
            finalNftInfos.add("index" + i + "=" + JSONObject.toJSONString(nftInfo));
        }
        try {
            FileUtils.writeLines(getNftInfoFile(network, contract), finalNftInfos, true);
        } catch (Exception e) {

        }
        return finalNftInfos.size();
    }

    public static String getTokenUri(String network, String contract, Integer tokenId) {
        String tokenUri = null;
        try {
            tokenUri = tokenUriMap.getOrDefault(tokenId + "", getTokenUriByWeb3j(network, contract, tokenId + ""));
            if (ParamUtils.isEmpty(tokenUri)) {
                concurrentSet.add(tokenId + "");
                return null;
            }
            return tokenUri;
        } catch (Exception e) {
            log.error("拉取tokenUri异常",e);
            concurrentSet.add(tokenId + "");
            return null;
        }
    }

    public static String getTokenUriByWeb3j(String network, String contract, String tokenId) {
        String tokenUri = "";
        int tryCount = 0;
        while (ParamUtils.isEmpty(tokenUri) && tryCount < 5) {
            tokenUri = (String) Web3jUtil.exec(network, contract, TOKEN_URI, "_tokenId", Integer.valueOf(tokenId));
            tryCount++;
        }
        return tokenUri;
    }
}
