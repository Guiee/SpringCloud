package com.guier.sso.core.store;


import com.guier.sso.core.conf.Conf;
import com.guier.sso.core.user.XxlSsoUser;
import com.guier.sso.core.util.JedisUtil;

/**
 * local login store
 *
 */
public class SsoLoginStore {

    private static int redisExpireMinute = 1440;    // 1440 minute, 24 hour
    public static void setRedisExpireMinute(int redisExpireMinute) {
        if (redisExpireMinute < 30) {
            redisExpireMinute = 30;
        }
        SsoLoginStore.redisExpireMinute = redisExpireMinute;
    }
    public static int getRedisExpireMinute() {
        return redisExpireMinute;
    }

    /**
     * get
     *
     * @param storeKey
     * @return
     */
    public static XxlSsoUser get(String storeKey) {

        String redisKey = redisKey(storeKey);
        Object objectValue = JedisUtil.getObjectValue(redisKey);
        if (objectValue != null) {
            XxlSsoUser xxlUser = (XxlSsoUser) objectValue;
            return xxlUser;
        }
        return null;
    }

    /**
     * remove
     *
     * @param storeKey
     */
    public static void remove(String storeKey) {
        String redisKey = redisKey(storeKey);
        JedisUtil.del(redisKey);
    }

    /**
     * put
     *
     * @param storeKey
     * @param xxlUser
     */
    public static void put(String storeKey, XxlSsoUser xxlUser) {
        String redisKey = redisKey(storeKey);
        JedisUtil.setObjectValue(redisKey, xxlUser, redisExpireMinute * 60);  // minute to second
    }

    private static String redisKey(String sessionId){
        return Conf.SSO_SESSIONID.concat("#").concat(sessionId);
    }

}
