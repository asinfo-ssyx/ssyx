<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
	<div class="down_left">
		<div class="down_left_top"
			style="background: url(images/down_left_top_bg.png);">
			<span style="color: #FFFFFF">新增用户群名称：</span>
			<span><input type="text" id="groupName" class="text1"/></span>
		</div>
		<div
			style="width: 707px; height: 30px; margin-top: 10px; margin-left: 10px;">
			<ul id="tab_label" class="tab_xuanze">

				<li id="jct" onclick="liClick('jc')"
					style="background: url(images/chufatiaojian_yingyong_tab_xuanzhong_bg.png); color: #FFFFFF;">基础属性</li>
				<li onclick="liClick('jz')" id="jzt"
					style="cursor: pointer; background: url(images/chufatiaojian_yingyong_tab_weixuanzhong_bg.png);">用户价值</li>
			</ul>
		</div>

		<div id="jc" style="display: block;" class="yingyong_xuanze">
			<%=request.getAttribute("htmla") %>
		</div>
		<div id="jz" style="display: none;" class="yingyong_xuanze">
			<%=request.getAttribute("htmlb") %>
		</div>

		<!--
			<div id="newLabel" class="yingyong_xuanze">
				
				<table>
					<tr>
						<td style="margin-left: 10px;">
							<p>
								<input type="checkbox" name="checkbox" value="checkbox"
									style="position: absolute;" /> <img
									src="images/chufatiaojian_yingyong_icon_1.png" />
							</p>
							<p>智能360</p></td>

						<td>
							<p>
								<input type="checkbox" name="checkbox" value="checkbox"
									style="position: absolute;" /> <img
									src="images/chufatiaojian_yingyong_icon_2.png" />
							</p>
							<p>智能360</p></td>

						<td>
							<p>
								<input type="checkbox" name="checkbox" value="checkbox"
									style="position: absolute;" /> <img
									src="images/chufatiaojian_yingyong_icon_1.png" />
							</p>
							<p>UC浏览器</p></td>

						<td>
							<p>
								<input type="checkbox" name="checkbox" value="checkbox"
									style="position: absolute;" /> <img
									src="images/chufatiaojian_yingyong_icon_2.png" />
							</p>
							<p>智能360</p></td>
					</tr>
				</table>
				
			</div>
			-->
		<div
			style="clear: both; width: 150px; margin: 0 auto; padding-top: 10px;">
			<input id="btn_tj" type="button" class="btn_tj" onclick="submitLabel()"
				style="border: none; float: left; background: url(images/tijiaoneirong_btn.png); width: 67px; height: 31px;">
			<input id="btn_cz" type="button" class="btn_cz" value=" "
				style="border: none; float: left; background: url(images/chongzhi_btn.png); width: 67px; height: 31px; margin-left: 5px;">
		</div>
	</div>
	<div class="down_right">
		<div class="down_right_top">
			<ul style="list-style: none;">
				<li style="background: #58a2db; display: block; color: #FFFFFF;">热门关键词</li>
				<li style="background: #FFFFFF; display: block; color: #6f6f6f;">相关活动</li>
			</ul>
		</div>
		<div class="down_right_content_yingyong">
			<!--  
			<table>
				<tr>
					<td style="margin-left: 10px;">
						<p>
							<input type="checkbox" name="checkbox" value="checkbox"
								style="position: absolute;" /> <img
								src="images/chufatiaojian_yingyong_icon_1.png" />
						</p>
						<p>智能360</p></td>
					<td>
						<p>
							<input type="checkbox" name="checkbox" value="checkbox"
								style="position: absolute;" /> <img
								src="images/chufatiaojian_yingyong_icon_2.png" />
						</p>
						<p>智能360</p></td>

					<td>
						<p>
							<input type="checkbox" name="checkbox" value="checkbox"
								style="position: absolute;" /> <img
								src="images/chufatiaojian_yingyong_icon_1.png" />
						</p>
						<p>UC浏览器</p></td>

					<td>
						<p>
							<input type="checkbox" name="checkbox" value="checkbox"
								style="position: absolute;" /> <img
								src="images/chufatiaojian_yingyong_icon_2.png" />
						</p>
						<p>智能360</p></td>
				</tr>
			</table>
			-->
		</div>
	</div>
</body>
</html>