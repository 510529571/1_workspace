package own.hhw.util;

import java.util.ArrayList;
import java.util.List;

public class PageObj
{

	public int curPage=0;//标示展示的是第几页
	public int pageCount=0;//总页数
	public int dataCount=0;//数据条数
	public List data=new ArrayList();
	public int getCurPage()
    {
    	return curPage;
    }
	public void setCurPage(int curPage)
    {
    	this.curPage = curPage;
    }
	public int getPageCount()
    {
    	return pageCount;
    }
	public void setPageCount(int pageCount)
    {
    	this.pageCount = pageCount;
    }
	public int getDataCount()
    {
    	return dataCount;
    }
	public void setDataCount(int dataCount)
    {
    	this.dataCount = dataCount;
    }
	public List getData()
    {
    	return data;
    }
	public void setData(List data)
    {
    	this.data = data;
    }

}
