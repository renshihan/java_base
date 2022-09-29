package com.renshihan.settlement.bean;

import lombok.Data;

@Data
public class NftInfo {
    //
    private String network;
    private String name;
    private String contract;
    private String tokenUri;
    //图片名字：
    private String imgUrl;
    private String tokenId;

}
