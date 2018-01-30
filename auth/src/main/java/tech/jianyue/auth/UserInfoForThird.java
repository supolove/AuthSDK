package tech.jianyue.auth;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.json.JSONObject;

/**
 * 描述: 第三方登录、注册返回信息
 * 作者: WJ
 * 时间: 2017/8/3
 * 版本: 1.0
 */
public class UserInfoForThird {
    public String fromId = "";                          // 第三方平台ID  1 新浪微博 2 腾讯QQ 3 微信账号
    public String aToken = "";                          // 第三方平台 access token
    public String rToken = "";                          // 第三方平台 refresh token
    public String userId = "";                          // 第三方平台 用户 ID
    public String openid = "";                          // 第三方平台 OPENID
    public String nickname = "";                        // 第三方平台 用户昵称
    public String expireIn = "0";                       // 第三方平台 token 有效期
    public String expireTime = "0";                     // 第三方平台 TOKEN 过期时间
    public String userLink = "";                        // 第三方平台 用户链接
    public String portrait = "";                        // 第三方平台 用户头像
    public String userInfo = "";                        // 第三方平台 用户信息 Json


    public UserInfoForThird initForWX(String json, String aToken, String rToken, String openid, long expires_in) throws Exception {
        JSONObject object = new JSONObject(json);
        if (object.optInt("errcode", -1) == -1 && !object.optString("unionid", "").equals("")) {
            this.fromId = "3";                                                 // 来自微信
            this.nickname = object.optString("nickname");               // 用户昵称
            this.userId = object.optString("unionid");                  // 用户 id
            this.openid = openid;
            this.aToken = aToken;
            this.rToken = rToken;
            this.expireIn = String.valueOf(expires_in);
            this.portrait = object.optString("headimgurl");
            this.userInfo = json;
            return this;
        }
        return null;
    }

    public UserInfoForThird initForWB(String json, Oauth2AccessToken oauth) throws Exception {
        JSONObject object = new JSONObject(json);
        if (object.optInt("error_code", -1) == -1) {
            this.fromId = "1";                                                         // 来自微博
            this.nickname = object.optString("screen_name");                    // 用户昵称
            this.userId = object.optString("idstr");                            // 用户 id
            this.aToken = oauth.getToken();
            this.rToken = oauth.getRefreshToken();
            this.expireTime = String.valueOf(oauth.getExpiresTime());
            this.portrait = object.optString("avatar_large");
            this.userLink = object.optString("url");
            this.userInfo = json;
            return this;
        }
        return null;
    }

    public UserInfoForThird initForQQ(JSONObject object, String openid, String aToken, long expires_time, int expires_in) throws Exception {
        if (object.optInt("ret", -1) == 0) {
            this.fromId = "2";                                                          // 来自QQ
            this.nickname = object.optString("nickname");                        // 用户昵称
//            this.userId = openid;                                                       // 用户 id
            this.openid = openid;
            this.aToken = aToken;
            this.expireIn = String.valueOf(expires_in);
            this.expireTime = String.valueOf(expires_time);
            this.portrait = object.optString("figureurl_2");
            this.userInfo = object.toString();
            return this;
        }
        return null;
    }

//    微信
//    private String nickname;
//    private String openid;
//    private int sex;
//    private String language;
//    private String city;
//    private String province;
//    private String country;
//    private String headimgurl;
//    private String unionid;
//    private List<?> privilege;


//    QQ
//    /**
//     * ret : 0
//     * openid : 264BE4E665D4BA118B6CFBA35D65025E
//     * access_token : 32A2A07969C32DBB2E4A7A8BE60DD925
//     * pay_token : 65F89CE33365920D2F708406A22D4769
//     * expires_in : 7776000
//     * pf : desktop_m_qq-10000144-android-2002-
//     * pfkey : 3756291e080646fe14c80f016c1b5843
//     * msg :
//     * login_cost : 72
//     * query_authority_cost : 225
//     * authority_cost : 2569
//     * expires_time : 1524726401581
//     */
//
//    private int ret;
//    private String openid;
//    private String access_token;
//    private String pay_token;
//    private int expires_in;
//    private String pf;
//    private String pfkey;
//    private String msg;
//    private int login_cost;
//    private int query_authority_cost;
//    private int authority_cost;
//    private long expires_time;

//    QQ 2次
//    /**
//     * ret : 0
//     * msg :
//     * is_lost : 0
//     * nickname : Jagger
//     * gender : 男
//     * province :
//     * city :
//     * figureurl : http://qzapp.qlogo.cn/qzapp/100761191/264BE4E665D4BA118B6CFBA35D65025E/30
//     * figureurl_1 : http://qzapp.qlogo.cn/qzapp/100761191/264BE4E665D4BA118B6CFBA35D65025E/50
//     * figureurl_2 : http://qzapp.qlogo.cn/qzapp/100761191/264BE4E665D4BA118B6CFBA35D65025E/100
//     * figureurl_qq_1 : http://q.qlogo.cn/qqapp/100761191/264BE4E665D4BA118B6CFBA35D65025E/40
//     * figureurl_qq_2 : http://q.qlogo.cn/qqapp/100761191/264BE4E665D4BA118B6CFBA35D65025E/100
//     * is_yellow_vip : 0
//     * vip : 0
//     * yellow_vip_level : 0
//     * level : 0
//     * is_yellow_year_vip : 0
//     */
//
//    private int ret;
//    private String msg;
//    private int is_lost;
//    private String nickname;
//    private String gender;
//    private String province;
//    private String city;
//    private String figureurl;
//    private String figureurl_1;
//    private String figureurl_2;
//    private String figureurl_qq_1;
//    private String figureurl_qq_2;
//    private String is_yellow_vip;
//    private String vip;
//    private String yellow_vip_level;
//    private String level;
//    private String is_yellow_year_vip;



//    微博
//    /**
//     * id : 1404376560
//     * screen_name : zaku
//     * name : zaku
//     * province : 11
//     * city : 5
//     * location : 北京 朝阳区
//     * description : 人生五十年，乃如梦如幻；有生斯有死，壮士复何憾。
//     * url : http://blog.sina.com.cn/zaku
//     * profile_image_url : http://tp1.sinaimg.cn/1404376560/50/0/1
//     * domain : zaku
//     * gender : m
//     * followers_count : 1204
//     * friends_count : 447
//     * statuses_count : 2908
//     * favourites_count : 0
//     * created_at : Fri Aug 28 00:00:00 +0800 2009
//     * following : false
//     * allow_all_act_msg : false
//     * geo_enabled : true
//     * verified : false
//     * status : {"created_at":"Tue May 24 18:04:53 +0800 2011","id":11142488790,"text":"我的相机到了。","source":"","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","geo":null,"mid":"5610221544300749636","annotations":[],"reposts_count":5,"comments_count":8}
//     * allow_all_comment : true
//     * avatar_large : http://tp1.sinaimg.cn/1404376560/180/0/1
//     * verified_reason :
//     * follow_me : false
//     * online_status : 0
//     * bi_followers_count : 215
//     */
//
//    private int id;
//    private String screen_name;
//    private String name;
//    private String province;
//    private String city;
//    private String location;
//    private String description;
//    private String url;
//    private String profile_image_url;
//    private String domain;
//    private String gender;
//    private int followers_count;
//    private int friends_count;
//    private int statuses_count;
//    private int favourites_count;
//    private String created_at;
//    private boolean following;
//    private boolean allow_all_act_msg;
//    private boolean geo_enabled;
//    private boolean verified;
//    private StatusBean status;
//    private boolean allow_all_comment;
//    private String avatar_large;
//    private String verified_reason;
//    private boolean follow_me;
//    private int online_status;
//    private int bi_followers_count;
//
//    public static class StatusBean {
//        /**
//         * created_at : Tue May 24 18:04:53 +0800 2011
//         * id : 11142488790
//         * text : 我的相机到了。
//         * source :
//         * favorited : false
//         * truncated : false
//         * in_reply_to_status_id :
//         * in_reply_to_user_id :
//         * in_reply_to_screen_name :
//         * geo : null
//         * mid : 5610221544300749636
//         * annotations : []
//         * reposts_count : 5
//         * comments_count : 8
//         */
//        private String created_at;
//        private long id;
//        private String text;
//        private String source;
//        private boolean favorited;
//        private boolean truncated;
//        private String in_reply_to_status_id;
//        private String in_reply_to_user_id;
//        private String in_reply_to_screen_name;
//        private Object geo;
//        private String mid;
//        private int reposts_count;
//        private int comments_count;
//        private List<?> annotations;
//    }

}