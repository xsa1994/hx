package com.hx.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestTool {

    public static String INTRANET_IP = getIntranetIp(); // 内网IP

    public static String INTERNET_IP = getInternetIp(); // 外网IP

    private final static String UNKNOWN = "unknown";
    private final static String IPHONE = "iphone";
    private final static String ANDROID = "android";
    private final static String IOS = "ios";
    private final static String IPAD = "ipad";
    private final static String MACINTOSH = "macintosh";
    private final static String LINUX = "linux";
    private final static String WINDOWS = "windows";
    private final static String IP_MATCH_REGEX = "(.*[a-zA-Z].*)|(:)|(;)";
    private final static String IP_REPLACE_PATTERN = "([a-zA-Z+:])|(:)|(;)";


    private static Map<String, String> osMap = new HashMap<>();

    static {
        osMap.put(IPHONE, "iPhone");
        osMap.put(ANDROID, "Android");
        osMap.put(LINUX, "Linux");
        osMap.put(WINDOWS, "Windows");
        osMap.put(MACINTOSH, "Mac");
        osMap.put(IPAD, "iPad");
    }

    /**
     * 获得User-Agent
     */
    public static String getUserAgent(HttpServletRequest req) {
        if (req != null) {
            return req.getHeader("User-Agent");
        }
        return null;
    }

    /**
     * 获得完整的url（包含请求url中的参数，但是不包含post提交的参数部分）
     */
    public static String getFullRequestUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (queryString == null) {
            return url;
        } else {
            return url + "?" + queryString;
        }
    }

    /**
     * 如果非forward请求，则简单返回request.getRequestURL() (不包含参数)
     * 如果是forward请求，则返回完整的url（包含请求url中的参数，如果是post请求，还会包含post提交的参数部分）
     */
    public static String getRequestUrl(HttpServletRequest request) {
        String path = (String) request.getAttribute("javax.servlet.forward.request_uri");//如果是forward过来的，则这里非空
        if (path == null) {
            return request.getRequestURL().toString();
        }

        StringBuilder url = new StringBuilder(getServerPath(request)).append(path);

        Map<String, String[]> map = request.getParameterMap();
        if (!map.isEmpty()) {
            url.append("?");
        }
        int i = 0;
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            if (entry.getValue().length > 0) {
                String key = entry.getKey();
                if (i > 0) {
                    url.append("&");
                }
                url.append(key).append("=").append(entry.getValue()[0]);
                i++;
            }
        }
        return url.toString();
    }

    /**
     * 获取用户的ip地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.trim().length() > 0) {
            String[] ips = ip.trim().split(",");
            int size = ips.length;
            if (size > 0) {
                ip = ips[0].trim();
            }
        }
        if (isInvalidIp(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (isInvalidIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isInvalidIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isInvalidIp(ip)) {
            ip = request.getHeader("Cdn-Src-Ip");
        }
        if (isInvalidIp(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.startsWith("0:0:0:0")) {
            ip = "127.0.0.1";
        }
        if (!StringUtils.isEmpty(ip) && ip.matches(IP_MATCH_REGEX)) {
            Pattern pattern = Pattern.compile(IP_REPLACE_PATTERN);
            Matcher matcher = pattern.matcher(ip);
            // 把字母和乱码替换成空
            ip = matcher.replaceAll("");
        }
        return ip;
    }

    private static boolean isInvalidIp(String ip) {
        return ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip);
    }

    /**
     * 是否经过了HTTP代理
     */
    public static boolean isProxy(HttpServletRequest request) {
        //代理服务器ip
        String httpVia = request.getHeader("HTTP_VIA");
        if (httpVia != null && !"".equals(httpVia)) {
            return true;
        }

        String xForwardedFor = request.getHeader("x-forwarded-for");
        if (xForwardedFor != null && xForwardedFor.trim().length() > 0) {
            String[] ips = xForwardedFor.trim().split(",");
            long sizeOfForwardFor = ips.length;
            if (sizeOfForwardFor > 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取操作系统类型： Android,iPhone,iPad,Mac,Windows,Linux，Unknown
     */
    public static String getOS(HttpServletRequest request) {
        String ua = getUserAgent(request);
        if (ua == null) {
            return UNKNOWN;
        }
        ua = ua.toLowerCase();
        for (Map.Entry<String, String> entry : osMap.entrySet()) {
            if (ua.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return UNKNOWN;
    }

    /**
     * 获取简洁ua,对ios,mac,ipad合并为ios，返回ios/android/unknown
     */
    public static String getSimpleOs(HttpServletRequest request) {
        String ua = getUserAgent(request);
        if (ua != null) {
            ua = ua.toLowerCase();
            if (ua.contains(IPAD) || ua.contains(IPHONE) || ua.contains(MACINTOSH)) {
                return IOS;
            } else if (ua.contains(ANDROID)) {
                return ANDROID;
            }
        }
        return UNKNOWN;
    }

    /**
     * 获取操作系统类型：
     */
    public static String getOSNew(HttpServletRequest request) {
        String ua = getUserAgent(request);
        return getOSNew(ua);
    }

    /**
     * 获取操作系统类型
     */
    public static String getOSNew(String ua) {
        if (ua == null) {
            return "6";
        }
        ua = ua.toLowerCase();
        if (ua != null) {
            if (ua.contains("ipad")) {
                //iPad
                return "4";
            } else if (ua.contains("iphone")) {
                //iPhone
                return "1";
            } else if (ua.contains("android")) {
                //Android
                return "0";
            } else if (ua.contains("linux")) {
                //Linux
                return "5";
            } else if (ua.contains("windows")) {
                //Windows
                return "2";
            } else if (ua.contains("macintosh")) {
                //Mac
                return "3";
            } else if (ua.contains("ios")) {
                //ios
                return "6";
            }
        }
        return "6";
    }

    /**
     * 判断来源ip是不是本地ip(126.0.0.1)
     */
    public static boolean isLocalRequest(HttpServletRequest request) {
        String ip = getIpAddr(request);
        if (ip.startsWith("127.0.0.1") || ip.startsWith("0:0:0:0")) {
            return true;
        }
        return false;
    }

    /**
     * 获取指定的cookie
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                return c.getValue();
            }
        }
        return null;
    }

    /**
     * 获得url，不包含path和参数
     */
    public static String getServerPath(HttpServletRequest request) {
        String port = String.valueOf(request.getServerPort());
        if (("80".equals(port) && "http".equals(request.getScheme())) || ("443".equals(port) && "https".equals(request.getScheme()))) {
            port = "";
        } else {
            port = ":" + port;
        }
        return request.getScheme() + "://" + request.getServerName() + port;
    }

    /**
     * 判断请求来源是否是ios系统
     */
    public static boolean isIos(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        String ua = getUserAgent(request);
        if (ua == null) {
            return false;
        }
        ua = ua.toLowerCase();
        return ua.contains(IPHONE) || ua.contains(IPAD) || ua.contains(MACINTOSH);
    }

    /**
     * 是否是android系统
     */
    public static boolean isAndroid(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        String ua = getUserAgent(request);
        if (ua == null) {
            return false;
        }
        ua = ua.toLowerCase();
        return ua.contains(ANDROID);
    }

    /**
     * 获得内网IP
     *
     * @return 内网IP
     */
    private static String getIntranetIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     *
     * @return 外网IP
     */
    private static String getInternetIp() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements()) {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements()) {
                    ip = addrs.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INTRANET_IP)) {
                        return ip.getHostAddress();
                    }
                }
            }

            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

