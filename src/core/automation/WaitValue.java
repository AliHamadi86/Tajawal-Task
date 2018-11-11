package core.automation;

public class WaitValue {

	private int pageLoadTimeOut;
	private int ajaxTimeOut;
	private int elementTimeOut;
	private int elementNegativeTimeOut;
	private int sikuliTimeOut;
	private int sikuliNegativeTimeOut;

	public int getElementTimeOut() {
		return elementTimeOut;
	}

	public void setElementTimeOut(int elementTimeOut) {
		this.elementTimeOut = elementTimeOut;
	}

	public int getElementNegativeTimeOut() {
		return elementNegativeTimeOut;
	}
	
	public void setElementNegativeTimeOut(int elementNegativeTimeOut) {
		this.elementNegativeTimeOut = elementNegativeTimeOut;
	}
	
	public int getPageLoadTimeOut() {
		return pageLoadTimeOut;
	}

	public void setPageLoadTimeOut(int pageLoadTimeOut) {
		this.pageLoadTimeOut = pageLoadTimeOut;
	}

	public int getAjaxTimeOut() {
		return ajaxTimeOut;
	}

	public void setAjaxTimeOut(int ajaxTimeOut) {
		this.ajaxTimeOut = ajaxTimeOut;
	}

	public int getSikuliTimeOut() {
		return sikuliTimeOut;
	}

	public void setSikuliTimeOut(int sikuliTimeOut) {
		this.sikuliTimeOut = sikuliTimeOut;
	}

	public int getSikuliNegativeTimeOut() {
		return sikuliNegativeTimeOut;
	}

	public void setSikuliNegativeTimeOut(int sikuliNegativeTimeOut) {
		this.sikuliNegativeTimeOut = sikuliNegativeTimeOut;
	}
}
