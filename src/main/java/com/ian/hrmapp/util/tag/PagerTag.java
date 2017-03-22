package com.ian.hrmapp.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PagerTag extends SimpleTagSupport{

	private static final String TAG = "{0}";
	private int pageSize;
	private int pageIndex;
	private int recordCount;
	private String submitUrl;
	private String style = "sabrosus";
	private int totalPage = 0;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		StringBuilder res = new StringBuilder();
		StringBuilder str = new StringBuilder();
		
		if (recordCount > 0) {
			totalPage = (this.recordCount - 1) / this.pageSize;
			if (this.pageIndex == 1) {
				str.append("<span class='disabled'>上一页</span>");
				this.calcPage(str);
				if (this.pageIndex == totalPage) {
					str.append("<span class='disabled'>下一页</span>");
				}else{
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
					str.append("<a href='" + tempUrl + "'>下一页</a>");
				}
			}else if (this.pageIndex == totalPage) {
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='" + tempUrl + "'>上一页</a>");
				this.calcPage(str);
				str.append("<span class='disabled'>下一页</span>");
			}else{
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='" + tempUrl + "'>上一页</a>");
				this.calcPage(str);
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
				str.append("<a href='" + tempUrl + "'>下一页</a>");
			}
			res.append("<table width='100%' align='center' style='font-size:13px;' class='" + style + "'>");
			res.append("<tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none'>" + str.toString());
			res.append("&nbsp; 跳 转 到 &nbsp;&nbsp;<input style='text-align: center; BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; "
					+ "BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BUTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid;"
					+ " COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none type ='text' size='2'"
					+ " id='pager_jump_page_size'/>" );
			res.append("&nbsp; <input type='button' style='text-align: center; BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; "
					+ " BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px;"
					+ "BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none' value=' 确 定  ' id='pager_jump_btn'/>" );
			res.append("</td></tr>");
			res.append("<tr align='center'><td style='font-size:13px;'><tr><td style='COLOR: #006lde; MARGIN-RIGHT: 3px; PADDING-TOP: 2px;"
					+ "TEXT-DECORATION: none'>");
			int startNum = (this.pageIndex - 1) * this.pageSize + 1;
			int endNum = (this.pageIndex == this.totalPage) ? this.recordCount : this.pageIndex * this.pageSize;
			res.append("总共<font color='red'>" + this.recordCount + "</font>条记录， 当前显示" + startNum + "-" + endNum +"条记录。");
			res.append("</td></tr>");
			res.append("</table>");
			res.append("<script type='text/javascript'>");
			res.append("    document.getElementById('pager_jump_btn').onclick = function(){");
			res.append("    var page_size = document.getElementById('pager_jump_page_size').value;");
			res.append("    if(!/^[1-9]\\d*$/.text(page_size) || page_size < 1 || page_size >" + this.totalPage + "){");
			res.append("       alert('请输入[1-" + this.totalPage + "]之间的页码！');");
			res.append("    }else{");
			res.append("       window.location = submit_url.replace('" + TAG + "', page_size);");
			res.append("    }");
			res.append("}");
			res.append("</script>");
		}else{
			res.append("<table align='center' style='font-size:13px;'><tr><td style='COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px;"
					+ "TEXT-DECORATION: none'> 总共<font color='red'>0</font>条记录， 当前显示0-0条记录。</td></tr></table>");
		}
		this.getJspContext().getOut().print(res.toString());
	}

	private void calcPage(StringBuilder str) {
		if (this.totalPage <= 11) {
			for(int i = 1; i <= this.totalPage; i++){
				if (this.pageIndex == i) {
					str.append("<span class='current'>" + i + "</span>");
				}else{
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='"+ tempUrl + "'>" + i + "</a>");
				}
			}
			str.append("...");
			String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
			str.append("<a href='" + tempUrl + "'>" + this.totalPage + "</a>");
		}else if(this.pageIndex + 8 >= this.totalPage){
			String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
			str.append("<a href='" + tempUrl + "'>1</a>");
			str.append("...");
			for (int i = this.totalPage - 10; i < this.totalPage; i++) {
				if (this.pageIndex == i) {
					str.append("<span class='current'>" + i + "</span>");
				}else{
					tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='" + tempUrl + "'>" + i + "</a>");
				}
			}
		}else{
			String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
			str.append("<a href='" + tempUrl + "'>1</a>");
			str.append("...");
			for(int i = this.pageIndex - 4; i <= this.pageIndex + 4; i++){
				if (this.pageIndex == i) {
					str.append("<span class='current'>" + i + "</span>");
				}else{
					tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='" + tempUrl + "'>" + i + "</a>");
				}
			}
			str.append("...");
			tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
			str.append("<a href='" + tempUrl + "'>" + this.totalPage + "</a>");
					
		}
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
}
