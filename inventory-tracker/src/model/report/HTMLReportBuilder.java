/**
 * 
 */

package model.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author Michael
 * 
 */
public class HTMLReportBuilder implements IReportBuilder
{
	// Variables
	protected String document = null;
	private int cellWidth = 0;
	protected String path = null;

	/**
	 * Constructor for HTMLReportBuilder
	 */
	public HTMLReportBuilder()
	{
		this.document = "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#addBulletedList(java.lang.String[])
	 */
	@Override
	public void addBulletedList(String[] items)
	{
		this.document += "<br><ul>";
		for(String item: items)
		{
			this.document +=
					"<li>" + StringEscapeUtils.escapeHtml4(item) + "</li>";
		}
		this.document += "</ul>";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#addSectionHeader(java.lang.String)
	 */
	@Override
	public void addSectionHeader(String title)
	{
		this.document += "<br><h2>" + title + "</h2>";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#addTableRow(java.lang.String[])
	 */
	@Override
	public void addTableRow(String[] cells)
	{
		this.document += "<tr>";
		for(String cell: cells)
		{
			this.document +=
					"<td width=\"" + this.cellWidth + "%\">"
							+ StringEscapeUtils.escapeHtml4(cell) + "</td>";
		}
		this.document += "</tr>";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#addText(java.lang.String)
	 */
	@Override
	public void addText(String text)
	{
		this.document += "<br><p>" + text + "</p>";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#buildHead(java.lang.String)
	 */
	@Override
	public void buildHead(String title)
	{
		this.document +=
				"<!DOCTYPE html><html><head><title>" + title
						+ "</title></head><body><br><h1 align=\"center\"><b>"
						+ title + "</b></h1>";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#finishAndSave(java.lang.String)
	 */
	@Override
	public void finishAndSave()
	{
		this.document += "</body></html>";
		File directory =
				new File(this.path.substring(0,
						this.path.lastIndexOf(File.separator)));
		directory.mkdirs();

		File file = new File(this.path);
		BufferedWriter bw = null;
		try
		{
			if(!file.exists())
			{
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(this.path));
			bw.write(this.document);
			bw.close();
			java.awt.Desktop.getDesktop().open(new File(this.path));
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#finishTable()
	 */
	@Override
	public void finishTable()
	{
		this.document += "</tbody><tfoot></tfoot></table>";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path)
	{
		this.path = path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.report.IReportBuilder#startTable(java.lang.String[])
	 */
	@Override
	public void startTable(String[] columnHeaders)
	{
		cellWidth = 100 / columnHeaders.length;
		this.document +=
				"<table width=\"100%\" align=\"center\" "
						+ "style=\"text-align: left; border: 1px solid;\"><thead>";
		for(String str: columnHeaders)
		{
			this.document +=
					"<th width=\"" + this.cellWidth + "%\">" + str + "</th>";
		}
		this.document += "</thead><tbody>";
	}
}
