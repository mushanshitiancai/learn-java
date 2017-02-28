package com.regex;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mazhibin on 16/4/28
 */
public class RegexTest {

    /**
     * 判断整个输入字符串是否匹配正则
     */
    @Test
    public void matchesTest(){
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        String text;
        Matcher matcher;

        text = "a 2 c 12";
        matcher = p.matcher(text);
        Assert.assertEquals(text.matches(regex),false);
        Assert.assertEquals(matcher.matches(),false);

        text = "12a";
        matcher = p.matcher(text);
        Assert.assertEquals(text.matches(regex), false);
        Assert.assertEquals(matcher.matches(), false);

        text = "12";
        matcher = p.matcher(text);
        Assert.assertEquals(text.matches(regex),true);
        Assert.assertEquals(matcher.matches(),true);
    }

    @Test
    public void lookingAtTest(){
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        String text;
        Matcher matcher;

        text = "hello 12";
        matcher = p.matcher(text);
        Assert.assertEquals(matcher.lookingAt(),false);

        text = "12 hello";
        Assert.assertEquals(p.matcher(text).lookingAt(), true);
    }

    @Test
    public void findTest(){
        String text = "a 2 c 12";
        Pattern p = Pattern.compile("\\d(\\d)?");
        Matcher matcher = p.matcher(text);

        Assert.assertEquals(matcher.find(),true);
        Assert.assertEquals(matcher.find(),true);
        Assert.assertEquals(matcher.find(),false);
        Assert.assertEquals(matcher.find(),false);

        matcher.reset();
        Assert.assertEquals(matcher.find(0), true);
        Assert.assertEquals(matcher.find(0), true);
        Assert.assertEquals(matcher.find(0), true);
        Assert.assertEquals(matcher.find(0), true);


        matcher.reset();
        Assert.assertEquals(matcher.find(), true);
        Assert.assertEquals(matcher.group(), "2");
        Assert.assertEquals(matcher.start(), 2);
        Assert.assertEquals(matcher.end(), 3);
        Assert.assertEquals(matcher.group(1),null);
        Assert.assertEquals(matcher.start(1),-1);
        Assert.assertEquals(matcher.end(1),-1);
    }

    @Test
    public void find2Test(){
        Pattern pattern = Pattern.compile("\\d{6}((\\d{4})(\\d{2})(\\d{2}))[\\dX]{4}");
        Matcher matcher = pattern.matcher("350104199305015417");

        System.out.println(matcher.groupCount());
        while(matcher.find()){
            System.out.println(matcher.group());  //整个身份证号
            System.out.println(matcher.group(1)); //生日
            System.out.println(matcher.group(2)); //生日-年
            System.out.println(matcher.group(3)); //生日-月
            System.out.println(matcher.group(4)); //生日-日
        }
    }

    @Test
    public void groupTest(){
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher("a1a");

        matcher.find();
        System.out.println(matcher.group());
        System.out.println(matcher.start());
        System.out.println(matcher.end());
    }

    @Test
    public void usePatternTest(){
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher("a1b2c");

        matcher.find();
        System.out.println(matcher.group());

        matcher.usePattern(Pattern.compile("[a-z]"));
        System.out.println(matcher.group());
        System.out.println(matcher.start());

        matcher.find();
        System.out.println(matcher.group());
    }

    @Test
    public void replaceTest(){
        String text = "a_1_b_23";
        Pattern p = Pattern.compile("\\d(\\d)?");
        Matcher matcher = p.matcher(text);

//        StringBuffer sb = new StringBuffer();
//        if(matcher.find()){
//            matcher.appendReplacement(sb,"first");
//            System.out.println(sb);
//            matcher.appendTail(sb);
//            System.out.println(sb);
//        }
    }

    @Test
    public void anchoringTest(){
        String text = "a_1_b_23";
        Pattern p = Pattern.compile("\\d(\\d)?");
        Matcher matcher = p.matcher(text);

        matcher.region(7, 8);
        System.out.println(matcher.regionStart());
        System.out.println(matcher.regionEnd());

//        System.out.println(matcher.replaceFirst("R"));

        System.out.println(matcher.regionStart());
        System.out.println(matcher.regionEnd());

        System.out.println(matcher.find());
        System.out.println(matcher.group());

        matcher.usePattern(Pattern.compile("\\d$"));
        System.out.println(matcher.regionStart());
        System.out.println(matcher.regionEnd());

        matcher.reset();
        matcher.region(2, 3);
        matcher.useAnchoringBounds(false);
        System.out.println(matcher.find());
//        System.out.println(matcher.group());
        System.out.println(matcher.hitEnd());
        System.out.println(matcher.requireEnd());
    }

    @Test
    public void quoteTest(){
        String q = Pattern.quote("(\\E1)");

        Assert.assertEquals(q, "\\Q(\\E\\\\E\\Q1)\\E");
    }

    @Test
    public void splitTest(){
        Pattern p = Pattern.compile("\\d");

        Assert.assertArrayEquals(p.split("a_1_b_23"),new String[]{"a_","_b_"});

        Assert.assertArrayEquals(p.split("a_1_b_23",0),new String[]{"a_","_b_"});
        Assert.assertArrayEquals(p.split("a_1_b_23",1),new String[]{"a_1_b_23"});
        Assert.assertArrayEquals(p.split("a_1_b_23",2),new String[]{"a_","_b_23"});
        Assert.assertArrayEquals(p.split("a_1_b_23",3),new String[]{"a_","_b_","3"});
        Assert.assertArrayEquals(p.split("a_1_b_23",4),new String[]{"a_","_b_","",""});
        Assert.assertArrayEquals(p.split("a_1_b_23",5),new String[]{"a_","_b_","",""});
        Assert.assertArrayEquals(p.split("a_1_b_23",6),new String[]{"a_","_b_","",""});
        Assert.assertArrayEquals(p.split("a_1_b_23",-1),new String[]{"a_","_b_","",""});
        Assert.assertArrayEquals(p.split("a_1_b_23",-2),new String[]{"a_","_b_","",""});
    }

    @Test
    public void getMethodNameTest(){
        String input = "        ICircleEntityDataCache GetCircleEntityDataCache();\n" +
                "        ICircleRelationDataCache GetCircleRelationDataCache();\n" +
                "        IDrTemplateRelationDataCache GetDrTemplateRelationDataCache();\n" +
                "        IEmployeeEntityDataCache GetEmployeeEntityDataCache();\n" +
                "        IEmployeeLeaderDataCache GetEmployeeLeaderDataCache();\n" +
                "        IEmployeeSearchEntityDataCache GetEmployeeSearchEntityDataCache();\n" +
                "        IEnterpriseConfigDataCache GetEnterpriseConfigDataCache();\n" +
                "        IFuncPermissionDataCache GetFuncPermissionDataCache();\n" +
                "        IRecordDataCache GetRecordDataCache();";

        StringBuilder sb = new StringBuilder();
        Pattern getMethodPattern = Pattern.compile(".*\\b+(\\w+)\\(.*");
        Matcher matcher = getMethodPattern.matcher(input);
        while(matcher.find()){
            String methodName = matcher.group(1);
            sb.append(methodName + "\n");
        }

        System.out.println(sb);
    }

    @Test
    public void getMethodNameTest2(){
        String input = "using System.Collections.Generic;\n" +
                "using System.Linq;\n" +
                "using AutoMapper;\n" +
                "using FaciShare.Framework.Supervision.FLIApi;\n" +
                "using FaciShare.Service.XieTongLogic.Business;\n" +
                "using FaciShare.Service.XieTongLogic.Utilities;\n" +
                "\n" +
                "namespace FaciShare.Service.XieTongLogic.QueryAreas.EMXAXTL {\n" +
                "    public class OrganizationServlet : FLIApiServlet {\n" +
                "        public void GetALevelUpdateV2(\n" +
                "            A<GetALevelUpdateV2Arg> apiArg,\n" +
                "            R<GetALevelUpdateV2Result> apiRst\n" +
                "        ) {\n" +
                "            var data = apiArg.GetData();\n" +
                "            var localAVersionStamp = data.LocalAVersionStamp;\n" +
                "\n" +
                "            var biz = new OrganizationBiz(CurrentContext);\n" +
                "            var bsRst = biz.GetAlevelUpdate(localAVersionStamp);\n" +
                "            if (apiRst.DetectedBizError(biz)) {\n" +
                "                return;\n" +
                "            }\n" +
                "\n" +
                "            var rst = new GetALevelUpdateV2Result {\n" +
                "                ServerAVersionStamp = bsRst.ServerAVersionStamp\n" +
                "            };\n" +
                "\n" +
                "            if (!bsRst.EmployeeSolidList.IsNullOrEmpty()) {\n" +
                "                rst.EmployeeSolidList.AddRange(\n" +
                "                    bsRst.EmployeeSolidList.Select(Mapper.Map<EmployeeSolidEntityV2>)\n" +
                "                );\n" +
                "            }\n" +
                "\n" +
                "            if (!bsRst.StoppedEmployeeSolidList.IsNullOrEmpty()) {\n" +
                "                rst.StoppedEmployeeSolidList.AddRange(bsRst.StoppedEmployeeSolidList);\n" +
                "            }\n" +
                "\n" +
                "            if (!bsRst.CircleSolidList.IsNullOrEmpty()) {\n" +
                "                rst.CircleSolidList.AddRange(\n" +
                "                    bsRst.CircleSolidList.Select(Mapper.Map<CircleSolidEntityV2>)\n" +
                "                );\n" +
                "            }\n" +
                "\n" +
                "            if (!bsRst.StoppedCircleSolidList.IsNullOrEmpty()) {\n" +
                "                rst.StoppedCircleSolidList.AddRange(bsRst.StoppedCircleSolidList);\n" +
                "            }\n" +
                "\n" +
                "            if (!bsRst.EmployeeCircleBelongingList.IsNullOrEmpty()) {\n" +
                "                foreach (var b in bsRst.EmployeeCircleBelongingList) {\n" +
                "                    var tmp = new EmployeeCircleBelongingV2() {\n" +
                "                        EmployeeID = b.EmployeeID\n" +
                "                    };\n" +
                "\n" +
                "                    if (!b.CircleIDList.IsNullOrEmpty()) {\n" +
                "                        tmp.CircleIDList.AddRange(b.CircleIDList);\n" +
                "                    }\n" +
                "\n" +
                "                    rst.EmployeeCircleBelongingList.Add(tmp);\n" +
                "                }\n" +
                "            }\n" +
                "            apiRst.SetSuccess(rst);\n" +
                "        }\n" +
                "\n" +
                "        public void GetPLevelUpdateV2(\n" +
                "            A<GetPLevelUpdateV2Arg> apiArg,\n" +
                "            R<GetPLevelUpdateV2Result> apiRst\n" +
                "        ) {\n" +
                "            var argData = apiArg.GetData();\n" +
                "            var localPVersionStamp = argData.LocalPVersionStamp;\n" +
                "            var currentEmployeeId = argData.CurrentEmployeeID;\n" +
                "\n" +
                "            var biz = new OrganizationBiz(CurrentContext);\n" +
                "            var bsRst = biz.GetPlevelUpdate(currentEmployeeId, localPVersionStamp);\n" +
                "            if (apiRst.DetectedBizError(biz)) {\n" +
                "                return;\n" +
                "            }\n" +
                "\n" +
                "            var rst = new GetPLevelUpdateV2Result {\n" +
                "                ServerPVersionStamp = bsRst.ServerPVersionStamp,\n" +
                "                Asterisk = new MyAsteriskBatchV2(),\n" +
                "                AccountInfo = new MyAccountInfoV2()\n" +
                "            };\n" +
                "\n" +
                "            if (bsRst.Asterisk != null) {\n" +
                "                if (!bsRst.Asterisk.MyAsteriskEmployeeIDList.IsNullOrEmpty()) {\n" +
                "                    rst.Asterisk.MyAsteriskEmployeeIDList.AddRange(\n" +
                "                        bsRst.Asterisk.MyAsteriskEmployeeIDList\n" +
                "                    );\n" +
                "                }\n" +
                "\n" +
                "                if (!bsRst.Asterisk.MyAsteriskCircleIDList.IsNullOrEmpty()) {\n" +
                "                    rst.Asterisk.MyAsteriskCircleIDList.AddRange(\n" +
                "                        bsRst.Asterisk.MyAsteriskCircleIDList\n" +
                "                    );\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            if (bsRst.AccountInfo != null) {\n" +
                "                rst.AccountInfo.Mobile = bsRst.AccountInfo.Mobile;\n" +
                "            }\n" +
                "\n" +
                "            apiRst.SetSuccess(rst);\n" +
                "        }\n" +
                "\n" +
                "        public void GetELevelUpdateV2(\n" +
                "            A<GetELevelUpdateV2Arg> apiArg,\n" +
                "            R<GetELevelUpdateV2Result> apiRst\n" +
                "        ) {\n" +
                "            var argData = apiArg.GetData();\n" +
                "            var localEVersionStamp = argData.LocalEVersionStamp;\n" +
                "            var currentEmployeeId = argData.CurrentEmployeeID;\n" +
                "\n" +
                "            var biz = new OrganizationBiz(CurrentContext);\n" +
                "            var bsRst = biz.GetElevelUpdate(currentEmployeeId, localEVersionStamp);\n" +
                "            if (apiRst.DetectedBizError(biz)) {\n" +
                "                return;\n" +
                "            }\n" +
                "\n" +
                "            var rst = new GetELevelUpdateV2Result {\n" +
                "                ServerEVersionStamp = bsRst.ServerEVersionStamp\n" +
                "            };\n" +
                "\n" +
                "            if (!bsRst.EmployeeObservedEntityList.IsNullOrEmpty()) {\n" +
                "                rst.EmployeeObservedEntityList.AddRange(\n" +
                "                    bsRst.EmployeeObservedEntityList.Select(Mapper.Map<EmployeeObservedEntityV2>)\n" +
                "                );\n" +
                "            }\n" +
                "\n" +
                "            apiRst.SetSuccess(rst);\n" +
                "        }\n" +
                "\n" +
                "        public void GetEmployeeNameKeywordsV2(\n" +
                "            A<GetEmployeeNameKeywordsV2Arg> apiArg,\n" +
                "            R<GetEmployeeNameKeywordsV2Result> apiRst\n" +
                "        ) {\n" +
                "            var argData = apiArg.GetData();\n" +
                "            var employeeIDList = argData.EmployeeIDList;\n" +
                "\n" +
                "            var biz = new OrganizationBiz(CurrentContext);\n" +
                "            var bsRst = biz.GetEmployeeNameKeywords(employeeIDList);\n" +
                "            if (apiRst.DetectedBizError(biz)) {\n" +
                "                return;\n" +
                "            }\n" +
                "\n" +
                "            var rst = new GetEmployeeNameKeywordsV2Result();\n" +
                "            if (!bsRst.EmployeeNameKeywordList.IsNullOrEmpty()) {\n" +
                "                foreach (var m in bsRst.EmployeeNameKeywordList) {\n" +
                "                    var tmp = new NameKeywordEntityV2() {\n" +
                "                        ID = m.ID\n" +
                "                    };\n" +
                "                    tmp.Keywords.AddRange(m.Keywords);\n" +
                "                    rst.EmployeeNameKeywordList.Add(tmp);\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            apiRst.SetSuccess(rst);\n" +
                "        }\n" +
                "        public void GetCircleNameKeywordsV2(\n" +
                "            A<GetCircleNameKeywordsV2Arg> apiArg,\n" +
                "            R<GetCircleNameKeywordsV2Result> apiRst\n" +
                "        ) {\n" +
                "            var argData = apiArg.GetData();\n" +
                "            var circleIdList = argData.CircleIDList;\n" +
                "            var biz = new OrganizationBiz(CurrentContext);\n" +
                "            var bsRst = biz.GetCircleNameKeywords(circleIdList);\n" +
                "            if (apiRst.DetectedBizError(biz)) {\n" +
                "                return;\n" +
                "            }\n" +
                "\n" +
                "            var rst = new GetCircleNameKeywordsV2Result();\n" +
                "            if (!bsRst.CircleNameKeywordList.IsNullOrEmpty()) {\n" +
                "                foreach (var m in bsRst.CircleNameKeywordList) {\n" +
                "                    var tmp = new NameKeywordEntityV2() {\n" +
                "                        ID = m.ID\n" +
                "                    };\n" +
                "\n" +
                "                    tmp.Keywords.AddRange(m.Keywords);\n" +
                "                    rst.CircleNameKeywordList.Add(tmp);\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            apiRst.SetSuccess(rst);\n" +
                "        }\n" +
                "    }\n" +
                "}";

        StringBuilder sb = new StringBuilder();
        Pattern getMethodPattern = Pattern.compile("public\\s+\\w+\\s+(\\w+)\\s*\\(");
        Matcher matcher = getMethodPattern.matcher(input);
        while(matcher.find()){
            String methodName = matcher.group(1);
            sb.append(methodName + "\n");
        }

        System.out.println(sb);
    }
}
