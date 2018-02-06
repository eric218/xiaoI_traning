package eastrobot.robotdev.ticketsystem.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
	public static void main(String[] args){
		JSONArray initSilerBarJson = new JSONArray();
		//主页
		JSONObject mainJson = new JSONObject();
		mainJson.put("name", "主页");
		mainJson.put("url", "getMainPage.api");
		mainJson.put("icon", "<svg class=\"glyph stroked dashboard dial\"><use xlink:href=\"#stroked-dashboard-dial\"/></svg>");
		JSONArray mainChild = new JSONArray();
		mainJson.put("child", mainChild);
		initSilerBarJson.add(mainJson);
		
		//待审申请
		JSONObject signJson = new JSONObject();
		signJson.put("name", "待审申请");
		signJson.put("url", "signPage.api");
		signJson.put("icon", "<svg class=\"glyph stroked pen tip\"><use xlink:href=\"#stroked-pen-tip\"/></svg>");
		//待审申请的子菜单
		JSONArray signJsonChild = new JSONArray();
		
		JSONObject firstChild = new JSONObject();
		firstChild.put("name", "党员组织关系转接办理");
		firstChild.put("url", "xxxx.api");
		firstChild.put("icon", "<svg class=\"glyph stroked tag\"><use xlink:href=\"#stroked-tag\"/></svg>");
		firstChild.put("child", new JSONArray());
		signJsonChild.add(firstChild);
		
		JSONObject secondChild = new JSONObject();
		secondChild.put("name", "民政服务");
		secondChild.put("url", "mzfw.api");	
		secondChild.put("icon", "<svg class=\"glyph stroked folder\"><use xlink:href=\"#stroked-folder\"/></svg>");	
		JSONArray secondSecondChild = new JSONArray();
		JSONObject secondSecondChild1 = new JSONObject();
		secondSecondChild1.put("name", "老年证办理");
		secondSecondChild1.put("url", "lnz.api");
		secondSecondChild1.put("icon", "<svg class=\"glyph stroked tag\"><use xlink:href=\"#stroked-tag\"/></svg>");

		secondSecondChild1.put("child", new JSONArray());
		secondSecondChild.add(secondSecondChild1);
		
		JSONObject secondSecondChild2 = new JSONObject();
		secondSecondChild2.put("name", "残疾人证登记");
		secondSecondChild2.put("url", "cjrzdj.api");
		secondSecondChild2.put("icon", "<svg class=\"glyph stroked tag\"><use xlink:href=\"#stroked-tag\"/></svg>");

		secondSecondChild2.put("child", new JSONArray());
		secondSecondChild.add(secondSecondChild2);
		secondChild.put("child", secondSecondChild);
		signJsonChild.add(secondChild);
		signJson.put("child", signJsonChild);
		
		
		initSilerBarJson.add(signJson);
		System.out.println(initSilerBarJson.toJSONString());
	}
}
