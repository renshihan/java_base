package com.renshihan.settlement.handle;

public class String处理1 {
    public static void main(String[] args) {
        String desc="This book is not just an ordinary book. The book is an NFT itself. NFT holders can view books on the Bybit NFT website. The song that comes together with the book is an NFT too. The music video that comes with the song is an NFT as well. Communities can also participate and be part of the book to create more NFT assets together. NFT is limitless. @@@Your new asset is in the digital world. @@@Grow from Zero to Hero with me.@@@Let’s dive into the NFT world together!";

        if (desc.startsWith("\"") && desc.endsWith("\"")) {
            desc = desc.substring(1, desc.length() - 1);
        }
        desc = desc.replace("@@@", "\r\n");

        System.out.println(desc);
    }
}
