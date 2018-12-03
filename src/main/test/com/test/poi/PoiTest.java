package com.test.poi;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIDocument;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoiTest {

    public static void main(String[] args)  {
//        String filePath = "C:\\Users\\xc\\Desktop\\大众\\9款车详情\\2速派.docx";
//        String content = readWord(filePath);
//        for(String c:content.split("\n")) {
//            if (StringUtils.isEmpty(c)) {
//                System.out.println("Null");
//            } else {
//                System.out.println(c);
//            }
//        }

        /** 科米克*/
//        Kemike();

        /** 科迪亚克*/
//        Kediyake();

        /**明锐旅行车*/
//        Mingruilvxingche();

        /** 速派*/
//        Supai();
        /**明锐*/
//        Mingrui();

//        Keluoke();

        /**昕动pa*/
//        XindongPA();

        /**昕锐*/
//        Xinrui();

        KediyakeGT();
    }


    /**
     * 返回CarDetail对象
     * @param params  * key: filePath(excel路径)、name(商品名称)、price(价格)、item(itemID)、polourl（1层图片地址)、initImgUrl(初始化图片地址)
     * @return
     */
    public static CarDetail getCarDetail(Map<String, String> params){
        String filePath = params.get("filePath");

        CarDetail carDetail = new CarDetail();
        carDetail.setMainname(params.get("name"));
        carDetail.setPrice(params.get("price"));
        carDetail.setItem(Integer.parseInt(params.get("item")));
        carDetail.setPolourl(params.get("polourl"));
        carDetail.setAlt(carDetail.getMainname());
        carDetail.setParseFlag(params.get("parseFlag") == null ? "1" : params.get("parseFlag"));
        String content = readWord(filePath);

        String splits[] = content.split("\n");
        String initImgUrl = params.get("initImgUrl");
        int imgFlag = Integer.parseInt(params.get("imgFlag"));
        parseStringArray(carDetail,splits,initImgUrl,imgFlag);
        return carDetail;

    }

    /**
     *  解析 excel内容
     * @param carDetail
     * @param splits
     * @param initImgUrl
     * @param imgFlag
     */
    public static void parseStringArray(CarDetail carDetail, String splits[],String initImgUrl,int imgFlag){
        int indexTitle = -1;
        List<CarTitle> listCarTitle = new ArrayList<>();
        int initI,arrayLen;
        CarTitle carTitle = null;/** 2层*/
        CarTitleMsg carTitleMsg = null;/** 3层*/
        for(initI = 0, arrayLen = splits.length; initI < arrayLen; initI++){
            String s = splits[initI];
            if("1".equals(carDetail.getParseFlag())) {
                if(s.startsWith("2a、")){
                    List<CarTitleMsg> listTitleMsg = new ArrayList<>();
                    carTitle = new CarTitle();
                    listCarTitle.add(carTitle);
                    carTitle.setMsg(listTitleMsg);
                    indexTitle ++;
                }
                if(s.startsWith("3a、")){
                    carTitleMsg = new CarTitleMsg();
                    listCarTitle.get(indexTitle).getMsg().add(carTitleMsg);
                }
                if(s.startsWith("2a、")){
                    s = s.substring(3);
                    carTitle.setHotname(s);
                    carTitle.setHottitle(s);
                }else if(s.startsWith("2b、")){
                    s = s.substring(3);
                    carTitle.setHotdetail(s);
                }else if(s.startsWith("3a、")){
                    s = s.substring(3);
                    carTitleMsg.setMsgTitle(s);
                    carTitleMsg.setAlt(s);
                }else if(s.startsWith("3b、")){
                    s = s.substring(3);
                    carTitleMsg.setMsgbody(s);
                }else{
                    carTitleMsg.setPolourl(initImgUrl.replace("#",imgFlag+""));
                    imgFlag++;
                }
            /**-------------------------------------------------------------------------------------------------------------**/
            }else if("2".equals(carDetail.getParseFlag())){
                if(carTitle == null){
                    carTitle = new CarTitle();
                    listCarTitle.add(carTitle);
                    List<CarTitleMsg> listTitleMsg = new ArrayList<>();
                    carTitle.setMsg(listTitleMsg);
                    indexTitle++;
                }
                if(carTitleMsg == null){
                    carTitleMsg = new CarTitleMsg();
                    carTitle.getMsg().add(carTitleMsg);
                }
                if(StringUtils.isEmpty(s)){
                    carTitleMsg.setPolourl(initImgUrl.replace("#",imgFlag+""));
                    imgFlag ++;
                    carTitleMsg = new CarTitleMsg();
                    carTitle.getMsg().add(carTitleMsg);
                    continue;
                }
                if(carTitleMsg.getAlt() == null){
                    carTitleMsg.setMsgTitle(s);
                    carTitleMsg.setAlt(s);
                }else{
                    carTitleMsg.setMsgbody(s);
                }
            }
        }
        carDetail.setTitle(listCarTitle);
    }
    /**
     * 读取内容
     * @param filePath 读取excel文件
     * @return
     */
    public static String readWord(String filePath) {
        String buffer = "";
        try {
            if (filePath.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(filePath));
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
            } else if (filePath.endsWith("docx")) {
                OPCPackage oPCPackage = POIXMLDocument.openPackage(filePath);
                XWPFDocument xwpf = new XWPFDocument(oPCPackage);
                POIXMLTextExtractor ex = new XWPFWordExtractor(xwpf);
                buffer = ex.getText();
            } else {
                System.out.println("此文件不是word文件！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer;
    }
    /**
     * 科米克
     */
    public static void Kemike(){
        String filePath = "C:\\Users\\xc\\Desktop\\大众\\9款车详情\\6柯米克.docx";
        Map<String, String> params = new HashMap<>();
        params.put("name","科米克");
        params.put("parseFlag","1");
        params.put("item","4348");
        params.put("price","75900元起");
        params.put("polourl","https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/Kemike/1.jpg");

        params.put("filePath",filePath);
        params.put("imgFlag","6");
        params.put("initImgUrl","https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/Kemike/#.jpg");
        CarDetail carDetail = getCarDetail(params);
        Map<String,CarDetail> m = new HashMap<>();
        m.put("5951",carDetail);
        System.out.println(JSONObject.toJSONString(m));
    }
    /**
     * 科迪亚克
     */
    public static void Kediyake(){
        String filePath = "C:\\Users\\xc\\Desktop\\大众\\9款车详情\\4柯迪亚克.docx";
        String urlTemplate = "https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/Kediyake/#.jpg";
        Map<String, String> params = new HashMap<>();
        params.put("name","柯迪亚克");
        params.put("parseFlag","1");
        params.put("item","4348");
        params.put("price","75900元起");
        params.put("polourl",urlTemplate.replace("#","1"));

        params.put("filePath",filePath);
        params.put("imgFlag","6");
        params.put("initImgUrl",urlTemplate);
        CarDetail carDetail = getCarDetail(params);
        Map<String,CarDetail> m = new HashMap<>();
        m.put("5952",carDetail);
        System.out.println(JSONObject.toJSONString(m));
    }

    /**
     * 明锐旅行车
     */
    public static void Mingruilvxingche(){
        String filePath = "C:\\Users\\xc\\Desktop\\大众\\9款车详情\\1明锐旅行车.docx";
        Map<String, String> params = new HashMap<>();
        params.put("name","明锐旅行车");
        params.put("price","75900元起");
        params.put("parseFlag","2");
        params.put("item","4348");
        params.put("polourl","https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/Mingruilvxingche/0.jpg");

        params.put("filePath",filePath);
        params.put("imgFlag","1");
        params.put("initImgUrl","https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/Mingruilvxingche/#.jpg");
        CarDetail carDetail = getCarDetail(params);
        Map<String,CarDetail> m = new HashMap<>();
        m.put("5949",carDetail);
        System.out.println(JSONObject.toJSONString(m));
    }

    /**
     * 速派
     */
    public static void Supai(){
        String filePath = "C:\\Users\\xc\\Desktop\\大众\\9款车详情\\2速派.docx";
        String urlTemplate = "https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/Supai/#.jpg";
        Map<String, String> params = new HashMap<>();
        params.put("name","速派");
        params.put("price","75900元起");
        params.put("parseFlag","2");
        params.put("item","4348");
//        params.put("polourl",urlTemplate.replace("#","1"));

        params.put("filePath",filePath);
        params.put("imgFlag","1");
        params.put("initImgUrl",urlTemplate);
        CarDetail carDetail = getCarDetail(params);
        Map<String,CarDetail> m = new HashMap<>();
        m.put("5950",carDetail);
        System.out.println(JSONObject.toJSONString(m));
    }

    /**
     * 明锐
     */
    public static void Mingrui(){
        String filePath = "C:\\Users\\xc\\Desktop\\大众\\9款车详情\\3明锐19.docx";
        String urlTemplate = "https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/Mingrui/#.jpg";
        Map<String, String> params = new HashMap<>();
        params.put("name","明锐");
        params.put("price","75900元起");
        params.put("parseFlag","2");
        params.put("item","4348");
//        params.put("polourl",urlTemplate.replace("#","1"));

        params.put("filePath",filePath);
        params.put("imgFlag","1");
        params.put("initImgUrl",urlTemplate);
        CarDetail carDetail = getCarDetail(params);
        Map<String,CarDetail> m = new HashMap<>();
        m.put("5951",carDetail);
        System.out.println(JSONObject.toJSONString(m));
    }
    /**
     * 柯珞克车
     */
    public static void Keluoke(){
        String filePath = "C:\\Users\\xc\\Desktop\\大众\\9款车详情\\5柯珞克车详页.docx";
        String urlTemplate = "https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/Keluoke/#.png";
        Map<String, String> params = new HashMap<>();
        params.put("name","柯珞克");
        params.put("price","75900元起");
        params.put("parseFlag","2");
        params.put("item","4348");
//        params.put("polourl",urlTemplate.replace("#","1"));

        params.put("filePath",filePath);
        params.put("imgFlag","5");
        params.put("initImgUrl",urlTemplate);
        CarDetail carDetail = getCarDetail(params);
        Map<String,CarDetail> m = new HashMap<>();
        m.put("5957",carDetail);
        System.out.println(JSONObject.toJSONString(m));
    }


    /**
     * 7昕动PA
     */
    public static void XindongPA(){
        String filePath = "C:\\Users\\xc\\Desktop\\大众\\9款车详情\\7昕动PA.docx";
        String urlTemplate = "https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/XindongPA/#.jpg";
        Map<String, String> params = new HashMap<>();
        params.put("name","昕动");
        params.put("price","75900元起");
        params.put("parseFlag","2");
        params.put("item","4348");
//        params.put("polourl",urlTemplate.replace("#","1"));

        params.put("filePath",filePath);
        params.put("imgFlag","1");
        params.put("initImgUrl",urlTemplate);
        CarDetail carDetail = getCarDetail(params);
        Map<String,CarDetail> m = new HashMap<>();
        m.put("5954",carDetail);
        System.out.println(JSONObject.toJSONString(m));
    }

    /**
     * 昕锐
     */
    public static void Xinrui(){
        String filePath = "C:\\Users\\xc\\Desktop\\大众\\9款车详情\\8昕锐19.docx";
        String urlTemplate = "https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/Xinrui/#.jpg";
        Map<String, String> params = new HashMap<>();
        params.put("name","昕锐");
        params.put("price","75900元起");
        params.put("parseFlag","2");
        params.put("item","4348");
//        params.put("polourl",urlTemplate.replace("#","1"));

        params.put("filePath",filePath);
        params.put("imgFlag","1");
        params.put("initImgUrl",urlTemplate);
        CarDetail carDetail = getCarDetail(params);
        Map<String,CarDetail> m = new HashMap<>();
        m.put("5955",carDetail);
        System.out.println(JSONObject.toJSONString(m));
    }

    /**
     * 昕锐
     */
    public static void KediyakeGT(){
        String filePath = "C:\\Users\\xc\\Desktop\\科迪亚克GT.docx";
        String urlTemplate = "https://sx-skoda-dev.oss-cn-shanghai.aliyuncs.com/CarDetails/KediyakeGTnew/#.jpg";
        Map<String, String> params = new HashMap<>();
        params.put("name","柯迪亚克");
        params.put("price","75900元起");
        params.put("parseFlag","1");
        params.put("item","4348");
        params.put("polourl",urlTemplate.replace("#","1"));

        params.put("filePath",filePath);
        params.put("imgFlag","6");
        params.put("initImgUrl",urlTemplate);
        CarDetail carDetail = getCarDetail(params);
        Map<String,CarDetail> m = new HashMap<>();
        m.put("5956",carDetail);
        System.out.println(JSONObject.toJSONString(m));
    }

}
