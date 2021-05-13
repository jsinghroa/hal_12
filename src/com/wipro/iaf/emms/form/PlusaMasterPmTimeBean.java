package com.wipro.iaf.emms.form;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PlusaMasterPmTimeBean {
		
	private String plusamasterpmtime;
	private String frequency;
	private String frequnit;
	private String iterationseq;
	private String iterationfreq;
	
	public String getPlusamasterpmtime() {
		return plusamasterpmtime;
	}
	public void setPlusamasterpmtime(String plusamasterpmtime) {
		this.plusamasterpmtime = plusamasterpmtime;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getFrequnit() {
		return frequnit;
	}
	public void setFrequnit(String frequnit) {
		this.frequnit = frequnit;
	}
	public String getIterationseq() {
		return iterationseq;
	}
	public void setIterationseq(String iterationseq) {
		this.iterationseq = iterationseq;
	}
	
	public String getIterationfreq() {
		return iterationfreq;
	}
	public void setIterationfreq(String iterationfreq) {
		this.iterationfreq = iterationfreq;
	}
	@Override
	public String toString() {
		return "PlusaMasterPmTimeBean [plusamasterpmtime=" + plusamasterpmtime
				+ ", frequency=" + frequency + ", frequnit=" + frequnit
				+ ", iterationseq=" + iterationseq + ", iterationfreq="
				+ iterationfreq + "]";
	}
	
	
	
	
}
