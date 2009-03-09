/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.charts.base;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;

import net.sf.jasperreports.charts.JRBar3DPlot;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.util.JRStyleResolver;

import org.jfree.chart.renderer.category.BarRenderer3D;

/**
 * @author Flavius Sana (flavius_sana@users.sourceforge.net)
 * @version $Id$ 
 */
public class JRBaseBar3DPlot extends JRBaseChartPlot implements JRBar3DPlot {

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_SHOW_LABELS = "showLabels";
	
	public static final String PROPERTY_X_OFFSET = "xOffset";
	
	public static final String PROPERTY_Y_OFFSET = "yOffset";
	
	protected JRExpression categoryAxisLabelExpression = null;
	protected JRFont categoryAxisLabelFont = null;
	protected Color categoryAxisLabelColor = null;
	protected JRFont categoryAxisTickLabelFont = null;
	protected Color categoryAxisTickLabelColor = null;
	protected String categoryAxisTickLabelMask = null;
	protected Color categoryAxisLineColor = null;

	protected JRExpression valueAxisLabelExpression = null;
	protected JRExpression rangeAxisMinValueExpression = null;
	protected JRExpression rangeAxisMaxValueExpression = null;
	protected JRFont valueAxisLabelFont = null;
	protected Color valueAxisLabelColor = null;
	protected JRFont valueAxisTickLabelFont = null;
	protected Color valueAxisTickLabelColor = null;
	protected String valueAxisTickLabelMask = null;
	protected Color valueAxisLineColor = null;

	private Double xOffsetDouble = null;
	private Double yOffsetDouble = null;
	private Boolean showLabels = null;
	

	/**
	 * 
	 */
	public JRBaseBar3DPlot(JRChartPlot plot, JRChart chart)
	{
		super(plot, chart);
		
		JRBar3DPlot barPlot = plot instanceof JRBar3DPlot ? (JRBar3DPlot)plot : null;
		if (barPlot == null)
		{
			categoryAxisLabelFont = new JRBaseFont(chart, null);
			categoryAxisTickLabelFont = new JRBaseFont(chart, null);
			valueAxisLabelFont = new JRBaseFont(chart, null);
			valueAxisTickLabelFont = new JRBaseFont(chart, null);
		}
		else
		{
			categoryAxisLabelFont = new JRBaseFont(chart, barPlot.getCategoryAxisLabelFont());
			categoryAxisTickLabelFont = new JRBaseFont(chart, barPlot.getCategoryAxisTickLabelFont());
			valueAxisLabelFont = new JRBaseFont(chart, barPlot.getValueAxisLabelFont());
			valueAxisTickLabelFont = new JRBaseFont(chart, barPlot.getValueAxisTickLabelFont());
		}
	}


	/**
	 * 
	 */
	public JRBaseBar3DPlot(JRBar3DPlot barPlot, JRBaseObjectFactory factory )
	{
		super( barPlot, factory );
		
		xOffsetDouble = barPlot.getXOffsetDouble();
		yOffsetDouble = barPlot.getYOffsetDouble();
		showLabels = barPlot.getShowLabels();
		
		categoryAxisLabelExpression = factory.getExpression( barPlot.getCategoryAxisLabelExpression() );
		categoryAxisLabelFont = new JRBaseFont(barPlot.getChart(), barPlot.getCategoryAxisLabelFont());
		categoryAxisLabelColor = barPlot.getOwnCategoryAxisLabelColor();
		categoryAxisTickLabelFont = new JRBaseFont(barPlot.getChart(), barPlot.getCategoryAxisTickLabelFont());
		categoryAxisTickLabelColor = barPlot.getOwnCategoryAxisTickLabelColor();
		categoryAxisTickLabelMask = barPlot.getCategoryAxisTickLabelMask();
		categoryAxisLineColor = barPlot.getOwnCategoryAxisLineColor();
		
		valueAxisLabelExpression = factory.getExpression( barPlot.getValueAxisLabelExpression() );
		rangeAxisMinValueExpression = factory.getExpression( barPlot.getRangeAxisMinValueExpression() );
		rangeAxisMaxValueExpression = factory.getExpression( barPlot.getRangeAxisMaxValueExpression() );
		valueAxisLabelFont = new JRBaseFont(barPlot.getChart(), barPlot.getValueAxisLabelFont());
		valueAxisLabelColor = barPlot.getOwnValueAxisLabelColor();
		valueAxisTickLabelFont = new JRBaseFont(barPlot.getChart(), barPlot.getValueAxisTickLabelFont());
		valueAxisTickLabelColor = barPlot.getOwnValueAxisTickLabelColor();
		valueAxisTickLabelMask = barPlot.getValueAxisTickLabelMask();
		valueAxisLineColor = barPlot.getOwnValueAxisLineColor();
	}
	
	/**
	 * 
	 */
	public JRExpression getCategoryAxisLabelExpression(){
		return categoryAxisLabelExpression;
	}
	
	/**
	 * 
	 */
	public JRFont getCategoryAxisLabelFont()
	{
		return categoryAxisLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getCategoryAxisLabelColor()
	{
		return JRStyleResolver.getCategoryAxisLabelColor(this, this);
	}
	
	/**
	 * 
	 */
	public Color getOwnCategoryAxisLabelColor()
	{
		return categoryAxisLabelColor;
	}
	
	/**
	 * 
	 */
	public JRFont getCategoryAxisTickLabelFont()
	{
		return categoryAxisTickLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getCategoryAxisTickLabelColor()
	{
		return JRStyleResolver.getCategoryAxisTickLabelColor(this, this);
	}

	/**
	 * 
	 */
	public Color getOwnCategoryAxisTickLabelColor()
	{
		return categoryAxisTickLabelColor;
	}

	/**
	 * 
	 */
	public String getCategoryAxisTickLabelMask()
	{
		return categoryAxisTickLabelMask;
	}

	/**
	 * 
	 */
	public Color getCategoryAxisLineColor()
	{
		return JRStyleResolver.getCategoryAxisLineColor(this, this);
	}
		
	/**
	 * 
	 */
	public Color getOwnCategoryAxisLineColor()
	{
		return categoryAxisLineColor;
	}
		
	/**
	 * 
	 */
	public JRExpression getValueAxisLabelExpression(){
		return valueAxisLabelExpression;
	}

	/**
	 * 
	 */
	public JRExpression getRangeAxisMinValueExpression(){
		return rangeAxisMinValueExpression;
	}

	/**
	 * 
	 */
	public JRExpression getRangeAxisMaxValueExpression(){
		return rangeAxisMaxValueExpression;
	}

	/**
	 * 
	 */
	public JRFont getValueAxisLabelFont()
	{
		return valueAxisLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getValueAxisLabelColor()
	{
		return JRStyleResolver.getValueAxisLabelColor(this, this);
	}
	
	/**
	 * 
	 */
	public Color getOwnValueAxisLabelColor()
	{
		return valueAxisLabelColor;
	}
	
	/**
	 * 
	 */
	public JRFont getValueAxisTickLabelFont()
	{
		return valueAxisTickLabelFont;
	}
	
	/**
	 * 
	 */
	public Color getValueAxisTickLabelColor()
	{
		return JRStyleResolver.getValueAxisTickLabelColor(this, this);
	}

	/**
	 * 
	 */
	public Color getOwnValueAxisTickLabelColor()
	{
		return valueAxisTickLabelColor;
	}

	/**
	 * 
	 */
	public String getValueAxisTickLabelMask()
	{
		return valueAxisTickLabelMask;
	}

	/**
	 * 
	 */
	public Color getValueAxisLineColor()
	{
		return JRStyleResolver.getValueAxisLineColor(this, this);
	}
	
	/**
	 * 
	 */
	public Color getOwnValueAxisLineColor()
	{
		return valueAxisLineColor;
	}
	
	/**
	 * @deprecated Replaced by {@link #getXOffsetDouble()}
	 */
	public double getXOffset(){
		return getXOffsetDouble() == null ? BarRenderer3D.DEFAULT_X_OFFSET : getXOffsetDouble().doubleValue();
	}
	
	/**
	 * 
	 */
	public Double getXOffsetDouble(){
		return xOffsetDouble;
	}
	
	/**
	 * @deprecated Replaced by {@link #setXOffset(Double)}
	 */
	public void setXOffset( double xOffset ){
		setXOffset(new Double(xOffset));
	}
	
	/**
	 * 
	 */
	public void setXOffset( Double xOffset ){
		Double old = this.xOffsetDouble;
		this.xOffsetDouble = xOffset;
		getEventSupport().firePropertyChange(PROPERTY_X_OFFSET, old, this.xOffsetDouble);
	}
	
	/**
	 * @deprecated Replaced by {@link #getYOffsetDouble()}
	 */
	public double getYOffset(){
		return getYOffsetDouble() == null ? BarRenderer3D.DEFAULT_Y_OFFSET : getYOffsetDouble().doubleValue();
	}
	
	/**
	 * 
	 */
	public Double getYOffsetDouble(){
		return yOffsetDouble;
	}
	
	/**
	 * @deprecated Replaced by {@link #setYOffset(Double)}
	 */
	public void setYOffset( double yOffset ){
		setYOffset(new Double(yOffset));
	}
	
	/**
	 * 
	 */
	public void setYOffset( Double yOffset ){
		Double old = this.yOffsetDouble;
		this.yOffsetDouble = yOffset;
		getEventSupport().firePropertyChange(PROPERTY_Y_OFFSET, old, this.yOffsetDouble);
	}
	
	/**
	 * @deprecated Replaced by {@link #getShowLabels()} 
	 */
	public boolean isShowLabels(){
		return showLabels == null ? false : showLabels.booleanValue();
	}
	
	/**
	 * 
	 */
	public Boolean getShowLabels(){
		return showLabels;
	}
	
	/**
	 * @deprecated Replaced by {@link #setShowLabels(Boolean)} 
	 */
	public void setShowLabels( boolean isShowLabels ){
		setShowLabels(Boolean.valueOf(isShowLabels));
	}

	/**
	 * 
	 */
	public void setShowLabels( Boolean showLabels ){
		Boolean old = this.showLabels;
		this.showLabels = showLabels;
		getEventSupport().firePropertyChange(PROPERTY_SHOW_LABELS, old, this.showLabels);
	}

	/**
	 *
	 */
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	/**
	 *
	 */
	public Object clone(JRChart parentChart) 
	{
		JRBaseBar3DPlot clone = (JRBaseBar3DPlot)super.clone(parentChart);
		if (categoryAxisLabelExpression != null)
		{
			clone.categoryAxisLabelExpression = (JRExpression)categoryAxisLabelExpression.clone();
		}
		if (valueAxisLabelExpression != null)
		{
			clone.valueAxisLabelExpression = (JRExpression)valueAxisLabelExpression.clone();
		}
		if (rangeAxisMinValueExpression != null)
		{
			clone.rangeAxisMinValueExpression = (JRExpression)rangeAxisMinValueExpression.clone();
		}
		if (rangeAxisMaxValueExpression != null)
		{
			clone.rangeAxisMaxValueExpression = (JRExpression)rangeAxisMaxValueExpression.clone();
		}
		return clone;
	}


	/**
	 * This field is only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID_3_1_0;
	private double xOffset = BarRenderer3D.DEFAULT_X_OFFSET;
	private double yOffset = BarRenderer3D.DEFAULT_Y_OFFSET;
	private boolean isShowLabels = false;
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		
		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_1_0)
		{
			xOffsetDouble = new Double(xOffset);
			yOffsetDouble = new Double(yOffset);
			showLabels = Boolean.valueOf(isShowLabels);
		}
	}
}
