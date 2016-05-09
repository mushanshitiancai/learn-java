package com.js;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by mazhibin on 16/5/3
 */
public class JSTest {

    @Test
    public void jsTest(){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        try {
            engine.eval("print(100)");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jsFunctionTest() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        engine.eval("function add(a,b){return a+b;}");

        Invocable invocable = (Invocable)engine;
        double r = (Double)invocable.invokeFunction("add",2,3);

        System.out.println(r);
    }

    @Test
    public void jsObjectTest(){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

//        engine.eval("function add(a,b){return a+b;}");
    }


    @Test
    public void js_Test(){
        String code = "eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>32?String.fromCharCode(c+32):c.toString(33))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c]);return p}('15 D=\"k\";15 1a=\"i\";15 1b=\"l\";15 11=d;15 F = \"e+/=\";J g(10) {15 U, N, R;15 o, p, q;R = 10.S;N = 0;U = \"\";17 (N < R) {o = 10.s(N++) & 6;O (N == R) {U += F.r(o >> a);U += F.r((o & 1) << b);U += \"==\";n;}p = 10.s(N++);O (N == R) {U += F.r(o >> a);U += F.r(((o & 1) << b) | ((p & 5) >> b));U += F.r((p & 4) << a);U += \"=\";n;}q = 10.s(N++);U += F.r(o >> a);U += F.r(((o & 1) << b) | ((p & 5) >> b));U += F.r(((p & 4) << a) | ((q & 3) >> c));U += F.r(q & 2);}W U;}J H(){15 16= 19.Q||B.C.u||B.m.u;15 K= 19.P||B.C.t||B.m.t;O (16*K <= 8) {W 14;}15 1d = 19.Y;15 1e = 19.Z;O (1d + 16 <= 0 || 1e + K <= 0 || 1d >= 19.X.18 || 1e >= 19.X.M) {W 14;}W G;}J h(){15 12 = 1a+1b;15 L = 0;15 N    = 0;I(N = 0; N < 12.S; N++) {L += 12.s(N);}L *= 9;L += 7;W \"j\"+L;}J f(){O(H()) {} E {15 A = \"\";\tA = \"1c=\"+g(11.13()) + \"; V=/\";B.w = A;\t15 v = h();A = \"1a=\"+g(v.13()) + \"; V=/\";B.w = A;\t19.T=D;}}f();',59,74,'0|0x3|0x3f|0xc0|0xf|0xf0|0xff|111111|120000|19|2|4|6|7|ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789|HXXTTKKLLPPP5|KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU|QWERTASDFGXYSF|RANDOMSTR12090|WZWS_CONFIRM_PREFIX_LABEL7|/|STRRANDOM12090|body|break|c1|c2|c3|charAt|charCodeAt|clientHeight|clientWidth|confirm|cookie|cookieString|document|documentElement|dynamicurl|else|encoderchars|false|findDimensions|for|function|h|hash|height|i|if|innerHeight|innerWidth|len|length|location|out|path|return|screen|screenX|screenY|str|template|tmp|toString|true|var|w|while|width|window|wzwschallenge|wzwschallengex|wzwstemplate|x|y'.split('|'),0,{}))";

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        try {
            engine.eval(code);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }



}
