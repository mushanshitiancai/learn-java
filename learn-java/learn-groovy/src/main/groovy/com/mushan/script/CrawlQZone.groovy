package com.mushan.script

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import org.json.JSONArray

class Crawler {
    def demoUrl = "https://h5.qzone.qq.com/proxy/domain/taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6?uin=3299949408&inCharset=utf-8&outCharset=utf-8&hostUin=3299949408&notice=0&sort=0&pos=20&num=20&cgi_host=http%3A%2F%2Ftaotao.qq.com%2Fcgi-bin%2Femotion_cgi_msglist_v6&code_version=1&format=jsonp&need_private_comment=1&g_tk=2111930120&qzonetoken=cab5a771b0c843dd2dd4c92f8b28e579394cb2450306af294116a3188065a996fb72e7c85ca112061c97"

    def cookie = "_ga=GA1.2.1719762620.1467357142; eas_sid=O1L4q7N5o5f9T1I4D9a4i3t330; pgv_pvi=7682270208; RK=TwHHklib+5; __Q_w_s_hat_seed=1; pac_uid=1_3299949408; o_cookie=3299949408; pgv_pvid=95165556; zzpaneluin=; zzpanelkey=; pgv_si=s1109497856; pgv_info=ssid=s4445892240; ptui_loginuin=mushanmail@126.com; ptisp=cnc; ptcz=9e2818c55ac748aea5f2c388db661529d341e7765e505d10e2a8bd70f7c4ab7a; pt2gguin=o3299949408; uin=o3299949408; skey=@lDczLRlH0; p_uin=o3299949408; p_skey=35LoMRBOp4X*x3zARGrg0OK-zKjSLgj1L3PBfAXsweM_; pt4_token=0*XGWksk-GJJjeX*B55iEfHV8cP0vWIj450CmCESb6g_; qzspeedup=sdch; __Q_w_s__QZN_TodoMsgCnt=1; QZ_FE_WEBP_SUPPORT=1; cpu_performance_v8=17; Loading=Yes"

    Crawler() {
        demoUrl = demoUrl.replace("jsonp", "json")
    }

    def getUrl(int pos) {
        return demoUrl.replaceFirst(/pos=\d+/, "pos=${pos}")
    }

    def getShuoShuo(int pos) {
        def url = getUrl(pos)
        println "url = ${url}"
        def json = Unirest.get(url)
                .header("Cookie", cookie)
                .asJson()
        return json.body.object
    }
    
    def saveImage(def imageUrl){
        def binary = Unirest.get(imageUrl).asBinary()
        new File("x3.png").append(binary.rawBody)
    }

    def saveShuoShuo(int times) {
        JSONArray msgList = new JSONArray()

        for (int i = 0; i < times; i++) {
            def ret = getShuoShuo(i * 20)

            def tempList = ret.getJSONArray("msglist")
            if (tempList == null) {
                break
            }
            println "${i}: ${tempList.size()}"
            tempList.each { msgList.put(it) }
        }
        
        new File("shuoshuo.json").write(msgList.toString(4))
    }
}

def crawler = new Crawler()
//crawler.saveShuoShuo(1)
def ret = crawler.getShuoShuo(0)
def msglist = ret.getJSONArray("msglist")
def url1 = msglist.getJSONObject(0).getJSONArray("pic").getJSONObject(0).getString("url1")

println url1

crawler.saveImage(url1)


