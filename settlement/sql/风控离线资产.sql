

-- 查询 最后一个  + 总数
SELECT  max(mn.id),count(1) FROM
                                tb_market_nft mn,tb_market_commodity mc
WHERE mn.commodity_id = mc.id
  and mc.future_address IN (
                            "0x957bD1556C4FB00c62d6d392c6d8d4dF82FB6e4e",
                            "0x1038bFf48370574501044292993C0A93CfC91Cd3",
                            "0x2e4066743d917Ead1EE6A2bC495B330048CE363f",
                            "0x137352b26bfc6d17230df8885d675dd64c801b37",
                            "0xa338cA2a79d2d373Dc402bAFF94E0c45Bd41B915",
                            "0x73A2fef690CEB60dF69428b45154659Aa48dE71C",
                            "0xa80617371a5f511bf4c1ddf822e6040acaa63e71",
                            "0x11716db9B822F0ac5FdC978AB7cCE0328dB83614"
    );


SELECT  mn.id, mn.token_id, mc.future_address, mc.network
FROM
    tb_market_nft mn,tb_market_commodity mc
WHERE mn.commodity_id = mc.id
  and mc.future_address IN (
                            "0x957bD1556C4FB00c62d6d392c6d8d4dF82FB6e4e",
                            "0x1038bFf48370574501044292993C0A93CfC91Cd3",
                            "0x2e4066743d917Ead1EE6A2bC495B330048CE363f",
                            "0x137352b26bfc6d17230df8885d675dd64c801b37",
                            "0xa338cA2a79d2d373Dc402bAFF94E0c45Bd41B915",
                            "0x73A2fef690CEB60dF69428b45154659Aa48dE71C",
                            "0xa80617371a5f511bf4c1ddf822e6040acaa63e71",
                            "0x11716db9B822F0ac5FdC978AB7cCE0328dB83614"
    )
  and mn.id > 13151

order by mn.id desc;

ETH:
0x73a2fef690ceb60df69428b45154659aa48de71c
0x957bd1556c4fb00c62d6d392c6d8d4df82fb6e4e
0x2e4066743d917ead1ee6a2bc495b330048ce363f
0x137352b26bfc6d17230df8885d675dd64c801b37
0xa338ca2a79d2d373dc402baff94e0c45bd41b915
0x1038bff48370574501044292993c0a93cfc91cd3
0xa80617371a5f511bf4c1ddf822e6040acaa63e71
0xfda07191be8d7019789cd1e1cf86d97604ce7351
0x11716db9b822f0ac5fdc978ab7cce0328db83614

BSC:
0x91d7742f1e44ba6cda5de2a1590858d20876226c

SELECT  mn.id, mn.token_id, mc.future_address, mc.network
FROM
    tb_market_nft mn,tb_market_commodity mc
WHERE mn.commodity_id = mc.id
  and mc.future_address IN (
    "0x13d29de126a18b65fe9f234c0f651ac3644258c1",
    "0x73a2fef690ceb60df69428b45154659aa48de71c",
    "0x957bd1556c4fb00c62d6d392c6d8d4df82fb6e4e",
    "0x2e4066743d917ead1ee6a2bc495b330048ce363f",
    "0x137352b26bfc6d17230df8885d675dd64c801b37",
    "0xa338ca2a79d2d373dc402baff94e0c45bd41b915",
    "0x1038bff48370574501044292993c0a93cfc91cd3",
    "0xa80617371a5f511bf4c1ddf822e6040acaa63e71",
    "0xfda07191be8d7019789cd1e1cf86d97604ce7351",
    "0x11716db9b822f0ac5fdc978ab7cce0328db83614"
    )
  and mn.commodity_type in ('2','3')
  and mn.id > 15323
order by mn.id ;


