package org.openmrs.propertyeditor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DateOrDatetimeEditorTest {
	
	DateOrDatetimeEditor ed;
	
	DateFormat ymdhm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Before
	public void before() {
		ed = new DateOrDatetimeEditor();
	}
	
	/**
	 * @see DateOrDatetimeEditor#getAsText()
	 * @verifies print date without time
	 */
	@Test
	public void getAsText_shouldPrintDateWithoutTime() throws Exception {
		ed.setValue(ymdhm.parse("2011-10-27 00:00"));
		Assert.assertEquals("27/10/2011", ed.getAsText());
	}
	
	/**
	 * @see DateOrDatetimeEditor#getAsText()
	 * @verifies print date and time with time
	 */
	@Test
	public void getAsText_shouldPrintDateAndTimeWithTime() throws Exception {
		ed.setValue(ymdhm.parse("2011-10-27 17:59"));
		Assert.assertEquals("27/10/2011 17:59", ed.getAsText());
	}
	
	/**
	 * @see DateOrDatetimeEditor#setAsText(String)
	 * @verifies handle date
	 */
	@Test
	public void setAsText_shouldHandleDate() throws Exception {
		ed.setAsText("27/10/2011");
		Assert.assertEquals(ymdhm.parse("2011-10-27 00:00"), ed.getValue());
	}
	
	/**
	 * @see DateOrDatetimeEditor#setAsText(String)
	 * @verifies handle date and time
	 */
	@Test
	public void setAsText_shouldHandleDateAndTime() throws Exception {
		ed.setAsText("27/10/2011 17:59");
		Assert.assertEquals(ymdhm.parse("2011-10-27 17:59"), ed.getValue());
	}
	
	/**
	 * @see DateOrDatetimeEditor#setAsText(String)
	 * @verifies fail on partial date
	 */
	@Test(expected = IllegalArgumentException.class)
	public void setAsText_shouldFailOnPartialDate() throws Exception {
		ed.setAsText("27/10");
	}
	
	/**
	 * @see DateOrDatetimeEditor#setAsText(String)
	 * @verifies fail on partial date and time
	 */
	@Test(expected = IllegalArgumentException.class)
	public void setAsText_shouldFailOnPartialDateAndTime() throws Exception {
		ed.setAsText("27/10/2011 17");
	}
}
