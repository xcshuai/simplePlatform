package com.revert.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.revert.platform.common.base.model.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("open/v1/map")
public class MapController {

    public static ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(method = RequestMethod.POST)
    public WebResult receiveAddress(@RequestParam("address") String address, HttpServletRequest request){
        String remoteAddr = getIpAddr(request);
        if(map.get(remoteAddr) == null){
            map.put(request.getRequestURI(),address);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("revert_xiecong@163.com");
            message.setTo("1376477341@qq.com");
            message.setSubject("主题：地址:"+remoteAddr);
            message.setText(address);
            mailSender.send(message);
        }

        return new WebResult();
    }

    @Scheduled(cron = "0 0/20 * * * ?")
    public void clear(){
        if(map.size() > 0){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("revert_xiecong@163.com");
            message.setTo("1376477341@qq.com");
            message.setSubject("主题：Map清除地址");
            message.setText(JSONObject.toJSONString(map));
            mailSender.send(message);
            map.clear();
        }
    }

    /**
     * 获取当前网络ip
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
